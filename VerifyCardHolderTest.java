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
import com.ppi.api.payloads.mone.VerifyCardHolderRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class VerifyCardHolderTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(VerifyCardHolderTest.class.getName());
	public SoftAssert softAssert;
	VerifyCardHolderRequest vchr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		vchr = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "VerifyCardHolder", typeoftest);
	}		

	@Test(dataProvider = "getExcelData")
	public void testVerifyCardHolder(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String urn, String customerMobile, String emailId, 
			String last4digits, String cardExpiry, String dob, String EncryptResponseStatusCode,
			String VerifyCardHolderResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		vchr = new VerifyCardHolderRequest();
		vchr.setMessageCode(messageCode);
		vchr.setClientTxnId(clientTxnId);
		vchr.setRequestDateTime(requestDateTime);
		vchr.setUrn(urn);
		vchr.setCustomerMobile(customerMobile);
		vchr.setEmailId(emailId);		
		vchr.setLast4Digits(last4digits);
		vchr.setCardExpiry(cardExpiry);
		vchr.setDob(dob);
		
		String encryptRequestBody = vchr.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = vchr.verifyCardHolderRequestBody(encryptedToken);
		Response verifyCardHolderResponse = MobilityOneApiMethods.verifyCardholder(serviceRequestBody);
		verifyCardHolderResponse.then().log().all();
		softAssert.assertEquals(verifyCardHolderResponse.getStatusCode(),
				Integer.parseInt(VerifyCardHolderResponseStatusCode));
		JsonPath jsonPathEvaluator = verifyCardHolderResponse.jsonPath();
		String verifyCardHolderToken = jsonPathEvaluator.getString("token");
		System.out.println("verifyCardHolderToken::" + verifyCardHolderToken);
		generic.explicitWait(1);

		String decryptRequestBody = vchr.decryptRequestBody(verifyCardHolderToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		//String decryptVerifyCardHolderRequest = decryptDataResponse.getBody().asString();
		//System.out.println("decryptVerifyCardHolderRequest::" + decryptVerifyCardHolderRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}

}
