package com.ppi.api.testcases.mone;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppi.api.apimethods.MobilityOneApiMethods;
import com.ppi.api.payloads.mone.UnloadRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UnloadTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(UnloadTest.class.getName());
	public SoftAssert softAssert;
	public UnloadRequest ulr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@DataProvider
	public Object[][] unloadData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "Unload", typeoftest);
	}

	@Test(dataProvider = "unloadData")
	public void testUnload(String TCID, String TestScenario, String TestType, String messageCode, String clientTxnId,
			String requestDateTime, String urn, String subWalletId, String transactionAmount, String receiver,
			String receiverAccountType, String receiverAccount, String unloadCurrency, String originalClientTxnId,
			String sourceType, String implId, String implType, String refundFileId, String fundFlowType, String pgNo,
			String orderId, String beneficiaryId, String fee, String customerMobile, String customerId,
			String accountNumber, String mcc, String acceptorNameLocation, String acceptorMerchantId,
			String acceptorTerminalId, String remarks, String EncryptResponseStatusCode,
			String UnloadResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::" + TCID + " :: " + TestScenario);
		softAssert = new SoftAssert();
		ulr = new UnloadRequest();

		ulr.setMessageCode(messageCode);
		ulr.setClientTxnId(clientTxnId);
		ulr.setRequestDateTime(requestDateTime);
		ulr.setUrn(urn);
		ulr.setSubWalletId(subWalletId);
		ulr.setTransactionAmount(transactionAmount);
		ulr.setReciver(receiver);		
		ulr.setReceiverAccountType(receiverAccountType);
		ulr.setReceiverAccount(receiverAccount);
		ulr.setUnloadCurrency(unloadCurrency);
		ulr.setOriginalClientTxnId(originalClientTxnId);		
		ulr.setSourceType(sourceType);
		ulr.setImplId(implId);
		ulr.setImplType(implType);
		ulr.setRefundFileId(refundFileId);
		ulr.setFundFlowType(fundFlowType);
		ulr.setPgNo(pgNo);		
		ulr.setOrderId(orderId);
		ulr.setBeneficiaryId(beneficiaryId);
		ulr.setFee(fee);
		ulr.setCustomerMobile(customerMobile);
		ulr.setCustomerId(customerId);
		ulr.setAccountNumber(accountNumber);
		ulr.setMcc(mcc);
		ulr.setAcceptorNameLocation(acceptorNameLocation);
		ulr.setAcceptorMerchantId(acceptorMerchantId);
		ulr.setAcceptorTerminalId(acceptorTerminalId);
		ulr.setRemarks(remarks);
		

		Response encryptDataResponse = MobilityOneApiMethods.encryptData(ulr.encryptRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String unloadRequestBody = ulr.unloadRequestBody(encryptedToken);
		Response unloadResponse = MobilityOneApiMethods.unload(unloadRequestBody);
		unloadResponse.then().log().all();
		softAssert.assertEquals(unloadResponse.getStatusCode(), Integer.parseInt(UnloadResponseStatusCode));
		JsonPath jsonPathEvaluator = unloadResponse.jsonPath();
		String unloadToken = jsonPathEvaluator.getString("token");
		// System.out.println("unloadToken::" + unloadToken);
		generic.explicitWait(1);

		String decryptRequestBody = ulr.decryptRequestBody(unloadToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		// String decryptUnloadRequest = decryptDataResponse.getBody().asString();
		// System.out.println("decryptUnloadRequest::" + decryptUnloadRequest);
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
		ulr = null;
	}

}
