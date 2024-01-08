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
import com.ppi.api.payloads.mone.BlockCardRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BlockCardTest extends InitTest {

	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(BlockCardTest.class.getName());
	public SoftAssert softAssert;
	BlockCardRequest bcr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		bcr = null;
	}

	@DataProvider
	public Object[][] getExcelData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "BlockCard", typeoftest);
	}
								

	@Test(dataProvider = "getExcelData")
	public void testBlockCard(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String customerId, String last4Digits, 
			String urn, String blockType, String reserved1, String reserved2, String EncryptResponseStatusCode,
			String BlockCardResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("*****Test Start for :: " + TCID + " " + TestScenario);

		softAssert = new SoftAssert();

		bcr = new BlockCardRequest();
		bcr.setMessageCode(messageCode);
		bcr.setClientTxnId(clientTxnId);
		bcr.setRequestDateTime(requestDateTime);
		bcr.setCustomerId(customerId);
		bcr.setLast4Digits(last4Digits);
		bcr.setUrn(urn);
		bcr.setBlockType(blockType);
		bcr.setReserved1(reserved1);
		bcr.setReserved2(reserved2);
		
		String encryptRequestBody = bcr.encryptRequestBody();
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptRequestBody.toString());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String serviceRequestBody = bcr.blockCardRequestBody(encryptedToken);
		Response blockCardResponse = MobilityOneApiMethods.blockCard(serviceRequestBody);
		blockCardResponse.then().log().all();
		softAssert.assertEquals(blockCardResponse.getStatusCode(),
				Integer.parseInt(BlockCardResponseStatusCode));
		JsonPath jsonPathEvaluator = blockCardResponse.jsonPath();
		String blockCardToken = jsonPathEvaluator.getString("token");
		System.out.println("blockCardToken::" + blockCardToken);
		generic.explicitWait(1);

		String decryptRequestBody = bcr.decryptRequestBody(blockCardToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		String decryptBlockCardRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptRetrieveCustRecordRequest::" + decryptBlockCardRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}

}
