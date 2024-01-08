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
import com.ppi.api.payloads.mone.VerifyOtpEmailRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class VerifyOtpEmailTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(VerifyOtpEmailTest.class.getName());
	public SoftAssert softAssert;
	VerifyOtpEmailRequest voer;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		voer = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "VerifyOtpEmail", typeoftest);
	}		
	
	@Test(dataProvider = "getExcelData")
	public void testVerifyOtpEmail(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String emailId, String otp,
			String emailVerifySkipFlag, String EncryptResponseStatusCode,
			String VerifyOtpEmailResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		voer = new VerifyOtpEmailRequest();
		voer.setMessageCode(messageCode);
		voer.setClientTxnId(clientTxnId);
		voer.setRequestDateTime(requestDateTime);
		voer.setEmailId(emailId);
		voer.setOtp(otp);		
		voer.setEmailVerifySkipFlag(emailVerifySkipFlag);
		
		String encryptRequestBody = voer.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = voer.verifyOtpEmailRequestBody(encryptedToken);
		Response verifyOtpEmailResponse = MobilityOneApiMethods.verifyOtpEmail(serviceRequestBody);
		verifyOtpEmailResponse.then().log().all();
		softAssert.assertEquals(verifyOtpEmailResponse.getStatusCode(),
				Integer.parseInt(VerifyOtpEmailResponseStatusCode));
		JsonPath jsonPathEvaluator = verifyOtpEmailResponse.jsonPath();
		String verifyOtpEmailToken = jsonPathEvaluator.getString("token");
		//System.out.println("verifyOtpEmailToken::" + verifyOtpEmailToken);
		generic.explicitWait(1);

		String decryptRequestBody = voer.decryptRequestBody(verifyOtpEmailToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}

}
