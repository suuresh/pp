package com.ppi.api.testcases.spicemoney;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppi.api.apimethods.SpiceMoneyApiMethods;
import com.ppi.api.payloads.mone.LoadRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LoadTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(LoadTest.class.getName());
	public SoftAssert softAssert;
	public LoadRequest lr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@DataProvider
	public Object[][] loadData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "Load", typeoftest);
	}

	@Test(dataProvider = "loadData")
	public void testLoad(String TCID, String TestScenario, String TestType, String messageCode, String clientTxnId,
			String requestDateTime, String urn, String transactionAmount, String sender, String reciver,
			String loadCurrency, String sourceType, String account_number, String subWalletId, String sourceAccount,
			String originalClientTxnId, String implId, String implType, String refundFileId, String fundFlowType,
			String fee, String customerMobile, String customerId, String pgNo, String orderId, String mcc,
			String acceptorNameLocation, String acceptorMerchantId, String acceptorTerminalId, String remarks,
			String refundOriginalFee, String refundOriginalFeeAmount, String EncryptResponseStatusCode,
			String LoadResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::"+TCID +" :: "+ TestScenario);
		softAssert = new SoftAssert();
		lr = new LoadRequest();

		lr.setMessageCode(messageCode);
		lr.setClientTxnId(clientTxnId);
		lr.setRequestDateTime(requestDateTime);
		lr.setUrn(urn);
		lr.setTransactionAmount(transactionAmount);
		lr.setSender(sender);
		lr.setReciver(reciver);
		lr.setLoadCurrency(loadCurrency);
		lr.setSourceType(sourceType);
		lr.setAccountNumber(account_number);
		lr.setSubWalletId(subWalletId);
		lr.setSourceAccount(sourceAccount);
		lr.setOriginalClientTxnId(originalClientTxnId);
		lr.setImplId(implId);
		lr.setImplType(implType);
		lr.setRefundFileId(refundFileId);
		lr.setFundFlowType(fundFlowType);
		lr.setFee(fee);
		lr.setCustomerMobile(customerMobile);
		lr.setCustomerId(customerId);
		lr.setPgNo(pgNo);
		lr.setOrderId(orderId);
		lr.setMcc(mcc);
		lr.setAcceptorNameLocation(acceptorNameLocation);
		lr.setAcceptorMerchantId(acceptorMerchantId);
		lr.setAcceptorTerminalId(acceptorTerminalId);
		lr.setRemarks(remarks);
		lr.setRefundOriginalFee(refundOriginalFee);
		lr.setRefundOriginalFeeAmount(refundOriginalFeeAmount);
		
		Response encryptDataResponse = SpiceMoneyApiMethods.encryptData(lr.encryptRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String loadRequestBody = lr.loadRequestBody(encryptedToken);
		Response loadResponse = SpiceMoneyApiMethods.load(loadRequestBody);
		loadResponse.then().log().all();
		softAssert.assertEquals(loadResponse.getStatusCode(), Integer.parseInt(LoadResponseStatusCode));
		JsonPath jsonPathEvaluator = loadResponse.jsonPath();
		String loadToken = jsonPathEvaluator.getString("token");
		//System.out.println("loadToken::" + loadToken);
		generic.explicitWait(1);

		String decryptRequestBody = lr.decryptRequestBody(loadToken);
		Response decryptDataResponse = SpiceMoneyApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		//String decryptLoadRequest = decryptDataResponse.getBody().asString();
		//System.out.println("decryptLoadRequest::" + decryptLoadRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}
	
	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		lr = null;
	}

}
