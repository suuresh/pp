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
import com.ppi.api.payloads.mone.ChangeCardStatusRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ChangeCardStatusTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(ChangeCardStatusTest.class.getName());
	public SoftAssert softAssert;
	public ChangeCardStatusRequest ccsr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		ccsr = null;
	}

	@DataProvider
	public Object[][] changeCardStatusData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "ChangeCardStatus", typeoftest);
	}

	@Test(dataProvider = "changeCardStatusData")
	public void testChangeCardStatus(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String urn, String cardStatus,
			String reason, String EncryptResponseStatusCode, String ChangeCardStatusResponseStatusCode,
			String DecryptResponseStatusCode, String ResponseCode, String ResponseMessage) throws Exception {
	System.out.println("*****Test Start for :: "+ TCID);
		softAssert = new SoftAssert();
		ccsr = new ChangeCardStatusRequest();

		ccsr.setMessageCode(messageCode);
		ccsr.setClientTxnId(clientTxnId);
		ccsr.setRequestDateTime(requestDateTime);		
		ccsr.setUrn(urn);
		ccsr.setCardStatus(cardStatus);
		ccsr.setReason(reason);
		
		String encryptRequestBody = ccsr.encryptChangeCardStatusRequestBody();
		System.out.println(encryptRequestBody);
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = ccsr.serviceRequestBody(encryptedToken);
		Response changeCardStatusResponse = MobilityOneApiMethods.changeCardStatus(serviceRequestBody);
		changeCardStatusResponse.then().log().all();
		softAssert.assertEquals(changeCardStatusResponse.getStatusCode(), Integer.parseInt(ChangeCardStatusResponseStatusCode));
		JsonPath changeCardStatusJsonPathEvaluator = changeCardStatusResponse.jsonPath();
		String changeCardStatusToken = changeCardStatusJsonPathEvaluator.getString("token");
		System.out.println("changeCardStatusToken::" + changeCardStatusToken);
		generic.explicitWait(1);

		String decryptRequestBody = ccsr.decryptRequestBody(changeCardStatusToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		String decryptActivateCardRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptActivateCardRequest::" + decryptActivateCardRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}	
}
