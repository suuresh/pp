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
import com.ppi.api.payloads.mone.UnblockCardRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UnblockCardTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(UnblockCardTest.class.getName());
	public SoftAssert softAssert;
	UnblockCardRequest ubcr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		ubcr = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "UnblockCard", typeoftest);
	}
					

	@Test(dataProvider = "getExcelData")
	public void testUnblockCard(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String customerId, String last4Digits, 
			String urn, String reserved1, String reserved2, String EncryptResponseStatusCode,
			String UnblockCardResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		ubcr = new UnblockCardRequest();
		ubcr.setMessageCode(messageCode);
		ubcr.setClientTxnId(clientTxnId);
		ubcr.setRequestDateTime(requestDateTime);
		ubcr.setCustomerId(customerId);
		ubcr.setLast4Digits(last4Digits);
		ubcr.setUrn(urn);
		ubcr.setReserved1(reserved1);
		ubcr.setReserved2(reserved2);
		
		String encryptRequestBody = ubcr.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = ubcr.unblockCardRequestBody(encryptedToken);
		Response unblockCardResponse = MobilityOneApiMethods.unblockCard(serviceRequestBody);
		unblockCardResponse.then().log().all();
		softAssert.assertEquals(unblockCardResponse.getStatusCode(),
				Integer.parseInt(UnblockCardResponseStatusCode));
		JsonPath jsonPathEvaluator = unblockCardResponse.jsonPath();
		String unblockCardToken = jsonPathEvaluator.getString("token");
		System.out.println("unblockCardToken::" + unblockCardToken);
		generic.explicitWait(1);

		String decryptRequestBody = ubcr.decryptRequestBody(unblockCardToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		String decryptUnblockCardRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptUnblockCardRequest::" + decryptUnblockCardRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}

}
