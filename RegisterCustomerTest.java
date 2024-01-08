package com.ppi.api.testcases.spicemoney;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppi.api.apimethods.SpiceMoneyApiMethods;
import com.ppi.api.payloads.spicemoney.RegisterCustomerRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RegisterCustomerTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(RegisterCustomerTest.class.getName());
	public SoftAssert softAssert;
	public RegisterCustomerRequest rcr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
		log.info("BeforeTest");
	}

	@AfterTest
	public void postCondition() {
		log.info("AfterTest");
		generic = null;
		softAssert = null;
		rcr = null;
	}

	@DataProvider
	public Object[][] registerCustomerData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "RegisterCustomer", typeoftest);
	}
	
	@Test(dataProvider = "registerCustomerData", priority = 1)
	public void testCreateCard(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String customerId, String sorCustomerId, String firstName,
			String middleName, String lastName, String gender, String dateOfBirth, String mobileNumber,
			String emailAddress, String address1, String address2, String city, String state, String country,
			String zipCode, String ovdType, String ovdExpDate, String ovdNo, String kycProfile, String riskCategory,
			String riskScore, String form60, String productId, String formFactorRequired,
			String initialLoad, String initialLoadAmtCurrency, String reserveField1, String reserveField2,
			String EncryptResponseStatusCode, String RegisterCustomerResponseStatusCode,
			String DecryptResponseStatusCode, String ResponseCode, String ResponseMessage) throws Exception {

		softAssert = new SoftAssert();
		rcr = new RegisterCustomerRequest();
		rcr.setMessageCode(messageCode);
		rcr.setClientTxnId(clientTxnId);
		rcr.setRequestDateTime(requestDateTime);
		rcr.setCustomerId(customerId);
		rcr.setSorCustomerId(sorCustomerId);
		
		rcr.cd.setFirstName(firstName);
		rcr.cd.setLastName(lastName);
		rcr.cd.setMiddleName(middleName);
		rcr.cd.setGender(gender);
		rcr.cd.setDateOfBirth(dateOfBirth);
		rcr.cd.setMobileNumber(mobileNumber);
		rcr.cd.setEmailAddress(emailAddress);
		rcr.setCustomerDetails(rcr.cd.getJsonObject(), "Default");
		rcr.ad.setAddress1(address1);
		rcr.ad.setAddress2(address2);
		rcr.ad.setCity(city);
		rcr.ad.setState(state);
		rcr.ad.setCountry(country);
		rcr.ad.setZipCode(zipCode);
		rcr.setAddressDetails(rcr.ad.getJsonObject(), "Default");
		rcr.setOvdType(ovdType);
		rcr.setOvdExpDate(ovdExpDate);
		rcr.setOvdNo(ovdNo);
		rcr.setDocList();
		
		rcr.setKycProfile(kycProfile);
		rcr.setRiskCategory(riskCategory);
		rcr.setRiskScore(riskScore);
		rcr.setForm60(form60);
		rcr.setProductId(productId);
		rcr.setFormFactorRequired(formFactorRequired);
		rcr.setInitialLoad(initialLoad);
		rcr.setInitialLoadAmtCurrency(initialLoadAmtCurrency);
		rcr.setReserveField1(reserveField1);
		rcr.setReserveField2(reserveField2);
		
		System.out.println("*****Test Start for " + TCID + ", " + TestScenario);

		String encryptRequestBody = rcr.encryptRequestBody();
		System.out.println(encryptRequestBody);
		Response encryptDataResponse = SpiceMoneyApiMethods.encryptData(encryptRequestBody);
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		// String encryptRequest = encryptDataResponse.getBody().asString();
		// System.out.println("encryptRequest::" + encryptRequest);
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String registerCustomerRequestBody = rcr.registerCustomerRequestBody(encryptedToken);
		Response registerCustomerResponse = SpiceMoneyApiMethods.registerCustomer(registerCustomerRequestBody);
		registerCustomerResponse.then().log().all();
		softAssert.assertEquals(registerCustomerResponse.getStatusCode(), Integer.parseInt(RegisterCustomerResponseStatusCode));
		// String createCardRequest = createCardResponse.getBody().asString();
		// System.out.println("createCardRequest::" + createCardRequest);
		JsonPath jsonPathEvaluator = registerCustomerResponse.jsonPath();
		String registerCustomerToken = jsonPathEvaluator.getString("token");
		System.out.println("registerCustomerToken::" + registerCustomerToken);
		generic.explicitWait(1);

		String decryptRequestBody = rcr.decryptRequestBody(registerCustomerToken);
		Response decryptDataResponse = SpiceMoneyApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		//String decryptResponse = decryptDataResponse.getBody().asString();
		//System.out.println("decryptResponse::" + decryptResponse);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		// Write the data to excel
		/*
		 * if (responseMessage.contentEquals("CARD_CREATED_SUCCESSFULLY")) { JSONObject
		 * json = new JSONObject(decryptDataResponse); //
		 * System.out.println(json.toString()); int rowCount =
		 * GenericMethods.getRowCount(xlFileName, "CardDetails"); rowCount++; JSONArray
		 * keys = json.names(); for (int i = 0; i < keys.length(); i++) {
		 * 
		 * String key = keys.getString(i); // Here's your key Object value =
		 * json.get(key); // Here's your value // System.out.println(key+","+value);
		 * 
		 * if (!key.equals("description") || !key.equals("responseCode") ||
		 * !key.equals("messageCode") || !key.equals("responseDateTime") ||
		 * !key.equals("responseMessage") || !key.equals("subwalletListDetails"))
		 * GenericMethods.writingToExcel(xlFileName, "CardDetails", key, rowCount,
		 * value.toString()); }
		 * 
		 * // JSONObject json1 = new JSONObject(json.getString("subwalletListDetails"));
		 * JSONArray jarray = json.getJSONArray("subwalletListDetails"); JSONObject
		 * json1 = jarray.getJSONObject(0); keys = json1.names(); for (int i = 0; i <
		 * keys.length(); i++) {
		 * 
		 * String key = keys.getString(i); // Here's your key Object value =
		 * json1.get(key); // Here's your value // System.out.println(key+","+value);
		 * GenericMethods.writingToExcel(xlFileName, "CardDetails", key, rowCount,
		 * value.toString()); } }
		 */

		softAssert.assertAll();

	}
}
