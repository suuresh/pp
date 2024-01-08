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
import com.ppi.api.payloads.mone.SendOtpMobileRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SendOtpMobileTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(SendOtpMobileTest.class.getName());
	public SoftAssert softAssert;
	SendOtpMobileRequest somr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		somr = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "SendOtpMobile", typeoftest);
	}		

	

	@Test(dataProvider = "getExcelData")
	public void testSendOtpMobile(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String cardHolderMobileNumber, String productId,
			String otp_generation_type, String smsVerifySkipFlag, String EncryptResponseStatusCode,
			String SendOtpMobileResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		somr = new SendOtpMobileRequest();
		somr.setMessageCode(messageCode);
		somr.setClientTxnId(clientTxnId);
		somr.setRequestDateTime(requestDateTime);
		somr.setCardholderMobileNumber(cardHolderMobileNumber);
		somr.setProductId(productId);
		somr.setOtpGenerationType(otp_generation_type);
		somr.setSmsVerifySkipFlag(smsVerifySkipFlag);
		
		String encryptRequestBody = somr.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = somr.sendOtpMobileRequestBody(encryptedToken);
		Response sendOtpMobileResponse = MobilityOneApiMethods.sendOtpMoblie(serviceRequestBody);
		sendOtpMobileResponse.then().log().all();
		softAssert.assertEquals(sendOtpMobileResponse.getStatusCode(),
				Integer.parseInt(SendOtpMobileResponseStatusCode));
		JsonPath jsonPathEvaluator = sendOtpMobileResponse.jsonPath();
		String sendOtpMobileToken = jsonPathEvaluator.getString("token");
		//System.out.println("sendOtpMobileToken::" + sendOtpMobileToken);
		generic.explicitWait(1);

		String decryptRequestBody = somr.decryptRequestBody(sendOtpMobileToken);
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
