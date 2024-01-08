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
import com.ppi.api.payloads.mone.VerifyOtpMobileRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class VerifyOtpMobileTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(VerifyOtpMobileTest.class.getName());
	public SoftAssert softAssert;
	VerifyOtpMobileRequest vomr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		vomr = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "VerifyOtpMobile", typeoftest);
	}		

	
	@Test(dataProvider = "getExcelData")
	public void testVerifyOtpMobile(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String mobileNumber, String otp,
			String smsVerifySkipFlag, String EncryptResponseStatusCode,
			String VerifyOtpMobileResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		vomr = new VerifyOtpMobileRequest();
		vomr.setMessageCode(messageCode);
		vomr.setClientTxnId(clientTxnId);
		vomr.setRequestDateTime(requestDateTime);
		vomr.setMobileNumber(mobileNumber);
		vomr.setOtp(otp);		
		vomr.setSmsVerifySkipFlag(smsVerifySkipFlag);
		
		String encryptRequestBody = vomr.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = vomr.verifyOtpMobileRequestBody(encryptedToken);
		Response verifyOtpMobileResponse = MobilityOneApiMethods.verifyOtpMoblile(serviceRequestBody);
		verifyOtpMobileResponse.then().log().all();
		softAssert.assertEquals(verifyOtpMobileResponse.getStatusCode(),
				Integer.parseInt(VerifyOtpMobileResponseStatusCode));
		JsonPath jsonPathEvaluator = verifyOtpMobileResponse.jsonPath();
		String verifyOtpMobileToken = jsonPathEvaluator.getString("token");
		//System.out.println("verifyOtpMobileToken::" + verifyOtpMobileToken);
		generic.explicitWait(1);

		String decryptRequestBody = vomr.decryptRequestBody(verifyOtpMobileToken);
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
