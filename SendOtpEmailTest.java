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
import com.ppi.api.payloads.mone.SendOtpEmailRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SendOtpEmailTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(SendOtpEmailTest.class.getName());
	public SoftAssert softAssert;
	SendOtpEmailRequest soer;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		soer = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "SendOtpEmail", typeoftest);
	}		

	

	@Test(dataProvider = "getExcelData")
	public void testSendOtpEmail(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String email, String otp_generation_type, String productId,
			String emailVerifySkipFlag, String EncryptResponseStatusCode,
			String SendOtpEmailResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		soer = new SendOtpEmailRequest();
		soer.setMessageCode(messageCode);
		soer.setClientTxnId(clientTxnId);
		soer.setRequestDateTime(requestDateTime);
		soer.setEmail(email);
		soer.setOtpGenerationType(otp_generation_type);
		soer.setProductId(productId);		
		soer.setEmailVerifySkipFlag(emailVerifySkipFlag);
		
		String encryptRequestBody = soer.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = soer.sendOtpEmailRequestBody(encryptedToken);
		Response sendOtpEmailResponse = MobilityOneApiMethods.sendOtpEmail(serviceRequestBody);
		sendOtpEmailResponse.then().log().all();
		softAssert.assertEquals(sendOtpEmailResponse.getStatusCode(),
				Integer.parseInt(SendOtpEmailResponseStatusCode));
		JsonPath jsonPathEvaluator = sendOtpEmailResponse.jsonPath();
		String sendOtpEmailToken = jsonPathEvaluator.getString("token");
		//System.out.println("sendOtpEmailToken::" + sendOtpEmailToken);
		generic.explicitWait(1);

		String decryptRequestBody = soer.decryptRequestBody(sendOtpEmailToken);
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
