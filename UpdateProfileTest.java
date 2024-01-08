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
import com.ppi.api.payloads.mone.UpdateProfileRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateProfileTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(UpdateProfileTest.class.getName());
	public SoftAssert softAssert;
	public UpdateProfileRequest upr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");		
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		upr = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "UpdateProfile", typeoftest);
	}

	@Test(dataProvider = "getExcelData")
	public void testUpdateProfile(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String customerId, String urn, String productId,
			String customerMobile, String cardProfileId, String sorCustomerId, String newCardProfileId,
			String profileIdChangeDate, String docType, String docCountry, String docExpiry, String docNumber,
			String cKycDocNo, String docConsent, String kycDateStamp, String updateVectors, String updateDate,
			String addressline1, String addressline2, String addressline3, String cardholderAddress,
			String cardholderCity, String cardholderState, String cardholderCountry, String cardholderZipCode,
			String firstName, String lastName, String email, String dateOfBirth, String gender, String alternateEmailId,
			String sorDisable, String EncryptResponseStatusCode, String UpdateProfileResponseStatusCode,
			String DecryptResponseStatusCode, String ResponseCode, String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);
		softAssert = new SoftAssert();
		upr = new UpdateProfileRequest();

		upr.setMessageCode(messageCode);
		upr.setClientTxnId(clientTxnId);
		upr.setRequestDateTime(requestDateTime);
		upr.setCustomerId(customerId);
		upr.setUrn(urn);
		upr.setProductId(productId);
		upr.setCustomerMobile(customerMobile);
		upr.setCardProfileId(cardProfileId);
		upr.setSorCustomerId(sorCustomerId);
		upr.setNewCardProfileId(newCardProfileId);
		upr.setProfileIdChangeDate(profileIdChangeDate);
		upr.setDocType(docType);
		upr.setDocCountry(docCountry);
		upr.setDocExpiry(docExpiry);
		upr.setDocNumber(docNumber);
		upr.setDocList();
		upr.setcKycDocNo(cKycDocNo);
		upr.setDocConsent(docConsent);
		upr.setKycDateStamp(kycDateStamp);
		upr.setUpdateVectors(updateVectors);
		upr.setUpdateDate(updateDate);
		upr.setAddressline1(addressline1);
		upr.setAddressline2(addressline2);
		upr.setAddressline3(addressline3);
		upr.setCardholderAddress(cardholderAddress);
		upr.setCardholderCity(cardholderCity);
		upr.setCardholderState(cardholderState);
		upr.setCardholderCountry(cardholderCountry);
		upr.setCardholderZipCode(cardholderZipCode);
		upr.setFirstName(firstName);
		upr.setLastName(lastName);
		upr.setEmail(email);
		upr.setDateOfBirth(dateOfBirth);
		upr.setGender(gender);
		upr.setAlternateEmailId(alternateEmailId);
		upr.setSorDisable(sorDisable);

		String encryptRequestBody = upr.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = upr.updateProfileRequestBody(encryptedToken);
		Response upateProfileResponse = MobilityOneApiMethods.updateProfile(serviceRequestBody);
		upateProfileResponse.then().log().all();
		softAssert.assertEquals(upateProfileResponse.getStatusCode(),
				Integer.parseInt(UpdateProfileResponseStatusCode));
		JsonPath jsonPathEvaluator = upateProfileResponse.jsonPath();
		String updateProfileToken = jsonPathEvaluator.getString("token");
		System.out.println("updateProfileToken::" + updateProfileToken);
		generic.explicitWait(1);

		String decryptRequestBody = upr.decryptRequestBody(updateProfileToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		String decryptUpdateProfileRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptUpdateProfileRequest::" + decryptUpdateProfileRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}

}
