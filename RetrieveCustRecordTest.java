package com.ppi.api.testcases.mone;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;
import com.ppi.api.apimethods.MobilityOneApiMethods;
import com.ppi.api.payloads.mone.RetrieveCustRecordRequest;
import com.ppi.api.payloads.mone.RetrieveCustRecordResponse;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RetrieveCustRecordTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(RetrieveCustRecordTest.class.getName());
	public SoftAssert softAssert;
	RetrieveCustRecordRequest rcrr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		rcrr = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "RetrieveCustRecord", typeoftest);
	}

	@Test(dataProvider = "getExcelData")
	public void testRetrieveCustRecord(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String customerMobile, String customerId, String emailId,
			String urn, String cardNumber, String EncryptResponseStatusCode,
			String RetrieveCustRecordResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		rcrr = new RetrieveCustRecordRequest();
		rcrr.setMessageCode(messageCode);
		rcrr.setClientTxnId(clientTxnId);
		rcrr.setRequestDateTime(requestDateTime);
		rcrr.setCustomerId(customerId);
		rcrr.setEmailId(emailId);
		rcrr.setUrn(urn);
		rcrr.setCustomerMobile(customerMobile);
		rcrr.setCardNumber(cardNumber);

		String encryptRequestBody = rcrr.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = rcrr.retrieveCustRecordRequestBody(encryptedToken);
		Response retrieveCustRecordResponse = MobilityOneApiMethods.retriveCustRecord(serviceRequestBody);
		retrieveCustRecordResponse.then().log().all();
		softAssert.assertEquals(retrieveCustRecordResponse.getStatusCode(),
				Integer.parseInt(RetrieveCustRecordResponseStatusCode));
		JsonPath jsonPathEvaluator = retrieveCustRecordResponse.jsonPath();
		String retrieveCustRecordToken = jsonPathEvaluator.getString("token");
		System.out.println("retrieveCustRecordToken::" + retrieveCustRecordToken);
		generic.explicitWait(1);

		String decryptRequestBody = rcrr.decryptRequestBody(retrieveCustRecordToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		String decryptRetrieveCustRecordRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptRetrieveCustRecordRequest::" + decryptRetrieveCustRecordRequest);
		
		RetrieveCustRecordResponse rcrr = new Gson().fromJson(decryptRetrieveCustRecordRequest, RetrieveCustRecordResponse.class);
		
		System.out.println(rcrr.getResponseCode());
		System.out.println(rcrr.getResponseMessage());
		
		
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}

}
