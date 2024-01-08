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
import com.ppi.api.payloads.mone.ResetPinRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResetPinTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(ResetPinTest.class.getName());
	public SoftAssert softAssert;
	public ResetPinRequest rpr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@DataProvider
	public Object[][] resetPinData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "ResetPin", typeoftest);
	}

	@Test(dataProvider = "resetPinData")
	public void testResetPin(String TCID, String TestScenario, String TestType, String messageCode, String clientTxnId,
			String requestDateTime, String newPIN, String last4Digits, String urn, String customerId,
			String EncryptResponseStatusCode, String ResetPinResponseStatusCode, String DecryptResponseStatusCode,
			String ResponseCode, String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::" + TCID + " :: " + TestScenario);
		softAssert = new SoftAssert();
		rpr = new ResetPinRequest();

		rpr.setMessageCode(messageCode);
		rpr.setClientTxnId(clientTxnId);
		rpr.setRequestDateTime(requestDateTime);
		rpr.setNewPIN(newPIN);
		rpr.setLast4Digits(last4Digits);
		rpr.setUrn(urn);
		rpr.setCustomerId(customerId);
		
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(rpr.encryptRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String resetPinRequestBody = rpr.resetpinRequestBody(encryptedToken);
		Response resetPinResponse = MobilityOneApiMethods.resetPin(resetPinRequestBody);
		resetPinResponse.then().log().all();
		softAssert.assertEquals(resetPinResponse.getStatusCode(), Integer.parseInt(ResetPinResponseStatusCode));
		JsonPath jsonPathEvaluator = resetPinResponse.jsonPath();
		String resetPinToken = jsonPathEvaluator.getString("token");
		// System.out.println("resetPinToken::" + resetPinToken);
		generic.explicitWait(1);

		String decryptRequestBody = rpr.decryptRequestBody(resetPinToken);
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
		rpr = null;
	}

}
