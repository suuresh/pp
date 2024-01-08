package com.ppi.api.testcases.mone;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppi.api.apimethods.MobilityOneApiMethods;
import com.ppi.api.payloads.mone.ActivateCardRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ActivateCardTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(ActivateCardTest.class.getName());
	public SoftAssert softAssert;
	public ActivateCardRequest acr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		acr = null;
	}

	@DataProvider
	public Object[][] activateCardData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "CardDetails", typeoftest);
	}

	@Test(dataProvider = "activateCardData")
	public void activateCard(String urn, String customerId, String clientTxnId, String clientId, String bankId,
			String accosaRefNo, String cardNumber, String cardExpiry, String cardCVV2, String cardProfileId,
			String loadAmount, String availableBalance, String availableCashLimit, String loginProfId,
			String subwallet_id, String default_wallet, String available_balance, String account_number,
			String account_status, String subwallet_name, String ledger_balance) throws Exception {
		System.out.println("*****Test Start******");
		softAssert = new SoftAssert();
		acr = new ActivateCardRequest();

		acr.setMessageCode("3424");
		acr.setClientTxnId("Auto");
		acr.setRequestDateTime("Auto");
		acr.setStatusCheckRequest("true");
		acr.setUrn(urn);
		acr.setLast4Digits(cardNumber.substring(12, 16));
		acr.setLoginId("1");
		acr.setLoadAmount("10000");
		acr.setTaxAmount("100");
		acr.setFeeAmount("1000");
		acr.setSourceAccountType("0");
		acr.setCustomerId(customerId);
		acr.setCardProfileId(cardProfileId);
		acr.setProductId("1");

		acr.ch.setCardholderFirstName("Test");
		acr.ch.setCardholderLastName("Name");
		acr.ch.setCardholderMobile("609012355685");
		acr.ch.setCardholderEmail("ram.nandwani130@wibmo.com");
		acr.ch.setCardholderAddress("");
		acr.ch.setCardholderCity("");
		acr.ch.setCardholderState("");
		acr.ch.setCardholderCountry("");
		acr.ch.setCardholderZipCode("");
		acr.ch.setCardholderDateOfBirth("06-01-2000");
		acr.ch.setIdentityProof("");
		acr.ch.setIdentityProofNumber("");
		acr.ch.setEmployeeId("");
		acr.ch.setOvdName("");
		acr.ch.setIsPanValid("");
		acr.ch.setOvdType("");
		acr.ch.setOvdNumber("");

		acr.nomi.setFirstName("Rohan");
		acr.nomi.setLastName("Goenka");
		acr.nomi.setDateOfBirth("");
		acr.nomi.setEmail("");
		acr.nomi.setMobile("");
		acr.nomi.setAddress("");
		acr.nomi.setCity("");
		acr.nomi.setState("");
		acr.nomi.setCountry("");
		acr.nomi.setZipCode("");

		acr.setCardHolder(acr.ch.getJsonObject(), "Default");
		acr.setNominee(acr.nomi.getJsonObject(), "Default");

		JSONObject jsonObj = new JSONObject(acr);
		System.out.println(jsonObj);

		Response encryptDataResponse = MobilityOneApiMethods.encryptData(jsonObj.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), 200);
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String activateCardRequestBody = acr.activateCardRequestBody(encryptedToken);
		Response activateCardResponse = MobilityOneApiMethods.activateCard(activateCardRequestBody);
		activateCardResponse.then().log().all();
		softAssert.assertEquals(activateCardResponse.getStatusCode(), 200);
		JsonPath activateCardJsonPathEvaluator = activateCardResponse.jsonPath();
		String activateCardToken = activateCardJsonPathEvaluator.getString("token");
		System.out.println("activateCardToken::" + activateCardToken);
		generic.explicitWait(1);

		String decryptRequestBody = acr.decryptRequestBody(activateCardToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), 200);
		String decryptActivateCardRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptActivateCardRequest::" + decryptActivateCardRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, "SUCCESS");
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), "00");
		generic.explicitWait(1);

		softAssert.assertAll();

	}

	@DataProvider
	public Object[][] testActivateCardData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "ActivateCard", typeoftest);
	}

	@Test(dataProvider = "testActivateCardData")

	public void testActivateCard(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String isStatusCheckRequest, String urn, String last4Digits,
			String loginId, String loadAmount, String taxAmount, String feeAmount, String sourceAccountType,
			String customerId, String cardProfileId, String productId, String cardholderFirstName,
			String cardholderMiddleName, String cardholderLastName, String cardholderMobile, String cardholderEmail,
			String cardholderAddress, String cardholderCity, String cardholderState, String cardholderCountry,
			String cardholderZipCode, String cardholderDateOfBirth, String identityProof, String identityProofNumber,
			String employeeId, String ovdName, String isPanValid, String ovdType, String ovdNumber, String firstName,
			String lastName, String dateOfBirth, String email, String mobile, String address, String city, String state,
			String country, String zipCode, String EncryptResponseStatusCode, String ActivateCardResponseStatusCode,
			String DecryptResponseStatusCode, String ResponseCode, String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::"+TCID +" :: "+ TestScenario);
		softAssert = new SoftAssert();
		acr = new ActivateCardRequest();

		acr.setMessageCode(messageCode);
		acr.setClientTxnId(clientTxnId);
		acr.setRequestDateTime(requestDateTime);
		acr.setStatusCheckRequest(isStatusCheckRequest);
		acr.setUrn(urn);
		acr.setLast4Digits(last4Digits);
		acr.setLoginId(loginId);
		acr.setLoadAmount(loadAmount);
		acr.setTaxAmount(taxAmount);
		acr.setFeeAmount(feeAmount);
		acr.setSourceAccountType(sourceAccountType);
		acr.setCustomerId(customerId);
		acr.setCardProfileId(cardProfileId);
		acr.setProductId(productId);

		acr.ch.setCardholderFirstName(firstName);
		acr.ch.setCardholderLastName(lastName);
		acr.ch.setCardholderMobile(cardholderMobile);
		acr.ch.setCardholderEmail(cardholderEmail);
		acr.ch.setCardholderAddress(cardholderAddress);
		acr.ch.setCardholderCity(cardholderCity);
		acr.ch.setCardholderState(cardholderState);
		acr.ch.setCardholderCountry(cardholderCountry);
		acr.ch.setCardholderZipCode(cardholderZipCode);
		acr.ch.setCardholderDateOfBirth(cardholderDateOfBirth);
		acr.ch.setIdentityProof(identityProof);
		acr.ch.setIdentityProofNumber(identityProofNumber);
		acr.ch.setEmployeeId(employeeId);
		acr.ch.setOvdName(ovdName);
		acr.ch.setIsPanValid(isPanValid);
		acr.ch.setOvdType(ovdType);
		acr.ch.setOvdNumber(ovdNumber);

		acr.nomi.setFirstName(firstName);
		acr.nomi.setLastName(lastName);
		acr.nomi.setDateOfBirth(dateOfBirth);
		acr.nomi.setEmail(email);
		acr.nomi.setMobile(mobile);
		acr.nomi.setAddress(address);
		acr.nomi.setCity(city);
		acr.nomi.setState(state);
		acr.nomi.setCountry(country);
		acr.nomi.setZipCode(zipCode);

		acr.setCardHolder(acr.ch.getJsonObject(), "Default");
		acr.setNominee(acr.nomi.getJsonObject(), "Default");

		/*
		 * JSONObject jsonObj = new JSONObject(acr); System.out.println(jsonObj);
		 */

		Response encryptDataResponse = MobilityOneApiMethods.encryptData(acr.encryptActivateCardRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String activateCardRequestBody = acr.activateCardRequestBody(encryptedToken);
		Response activateCardResponse = MobilityOneApiMethods.activateCard(activateCardRequestBody);
		activateCardResponse.then().log().all();
		softAssert.assertEquals(activateCardResponse.getStatusCode(), Integer.parseInt(ActivateCardResponseStatusCode));
		JsonPath activateCardJsonPathEvaluator = activateCardResponse.jsonPath();
		String activateCardToken = activateCardJsonPathEvaluator.getString("token");
		System.out.println("activateCardToken::" + activateCardToken);
		generic.explicitWait(1);

		String decryptRequestBody = acr.decryptRequestBody(activateCardToken);
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
