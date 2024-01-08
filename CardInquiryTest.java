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
import com.ppi.api.payloads.mone.CardInquiryRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CardInquiryTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(CardInquiryTest.class.getName());
	public SoftAssert softAssert;
	public CardInquiryRequest cir;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		cir = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "CardInquiry", typeoftest);
	}
	
	@Test(dataProvider = "getExcelData")
	public void testCardInquiry(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String customerId, String urn, String last4Digits,
			String EncryptResponseStatusCode, String CardInquiryResponseStatusCode, String DecryptResponseStatusCode,
			String ResponseCode, String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);
		softAssert = new SoftAssert();
		cir = new CardInquiryRequest();

		cir.setMessageCode(messageCode);
		cir.setClientTxnId(clientTxnId);
		cir.setRequestDateTime(requestDateTime);
		cir.setUrn(urn);
		cir.setLast4Digits(last4Digits);
		cir.setCustomerId(customerId);

		String encryptRequestBody = cir.encryptRequestBody();
		// System.out.println(encryptRequestBody);
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = cir.cardInquiryRequestBody(encryptedToken);
		Response cardInquiryResponse = MobilityOneApiMethods.cardInquiry(serviceRequestBody);
		cardInquiryResponse.then().log().all();
		softAssert.assertEquals(cardInquiryResponse.getStatusCode(), Integer.parseInt(CardInquiryResponseStatusCode));
		JsonPath jsonPathEvaluator = cardInquiryResponse.jsonPath();
		String cardInquiryToken = jsonPathEvaluator.getString("token");
		System.out.println("cardInquiryToken::" + cardInquiryToken);
		generic.explicitWait(1);

		String decryptRequestBody = cir.decryptRequestBody(cardInquiryToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		String decryptCardInquiryRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptCardInquiryRequest::" + decryptCardInquiryRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();
		
	}
}
