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
import com.ppi.api.payloads.mone.TransactionInquiryRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TransactionInquiryTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(TransactionInquiryTest.class.getName());
	public SoftAssert softAssert;
	public TransactionInquiryRequest tir;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@DataProvider
	public Object[][] transactionInquiryData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "TransactionInquiry", typeoftest);
	}

	@Test(dataProvider = "transactionInquiryData")
	public void testTransactionInquiry(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String fromDate, String toDate, String last4Digits, String urn,
			String customerId, String pageNumber, String count, String fromRowId, String EncryptResponseStatusCode,
			String TransactionInquiryResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::" + TCID + " :: " + TestScenario);
		softAssert = new SoftAssert();
		tir = new TransactionInquiryRequest();

		tir.setMessageCode(messageCode);
		tir.setClientTxnId(clientTxnId);
		tir.setRequestDateTime(requestDateTime);
		tir.setFromDate(fromDate);
		tir.setToDate(toDate);
		tir.setLast4Digits(last4Digits);
		tir.setUrn(urn);
		tir.setPageNumber(pageNumber);
		tir.setCount(count);
		tir.setFromRowId(fromRowId);
		
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(tir.encryptRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String transactionInquiryRequestBody = tir.transactionInquiryRequestBody(encryptedToken);
		Response transactionInquiryResponse = MobilityOneApiMethods.transactionInquiry(transactionInquiryRequestBody);
		transactionInquiryResponse.then().log().all();
		softAssert.assertEquals(transactionInquiryResponse.getStatusCode(), Integer.parseInt(TransactionInquiryResponseStatusCode));
		JsonPath jsonPathEvaluator = transactionInquiryResponse.jsonPath();
		String transactionInquiryToken = jsonPathEvaluator.getString("token");
		// System.out.println("transactionInquiryToken::" + transactionInquiryToken);
		generic.explicitWait(1);

		String decryptRequestBody = tir.decryptRequestBody(transactionInquiryToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		// String decryptRequest = decryptDataResponse.getBody().asString();
		// System.out.println("decryptRequest::" + decryptRequest);
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
		tir = null;
	}

}
