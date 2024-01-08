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
import com.ppi.api.payloads.mone.TransactionProfileRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TransactionProfileTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(TransactionProfileTest.class.getName());
	public SoftAssert softAssert;
	public TransactionProfileRequest tpr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@DataProvider
	public Object[][] transactionProfileData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "TransactionProfile", typeoftest);
	}

	@Test(dataProvider = "transactionProfileData")
	public void testTransactionProfile(String TCID, String TestScenario, String TestType, String messageCode, String clientTxnId,
			String requestDateTime, String urn, String subWalletId, String enablementType, String transactionProfileId,
			String transactionType, String status, String EncryptResponseStatusCode,
			String TransactionProfileResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::" + TCID + " :: " + TestScenario);
		softAssert = new SoftAssert();
		tpr = new TransactionProfileRequest();

		tpr.setMessageCode(messageCode);
		tpr.setClientTxnId(clientTxnId);
		tpr.setRequestDateTime(requestDateTime);
		tpr.setUrn(urn);
		tpr.setSubWalletId(subWalletId);
		tpr.setEnablementType(enablementType);
		tpr.setTransactionProfileId(transactionProfileId);
		tpr.setTransactionType(transactionType);
		tpr.setStatus(status);
		tpr.setTransactionProfileList();

		Response encryptDataResponse = MobilityOneApiMethods.encryptData(tpr.encryptRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String transactionProfileRequestBody = tpr.transactionProfileRequestBody(encryptedToken);
		Response transactionProfileResponse = MobilityOneApiMethods.transactionProfile(transactionProfileRequestBody);
		transactionProfileResponse.then().log().all();
		softAssert.assertEquals(transactionProfileResponse.getStatusCode(), Integer.parseInt(TransactionProfileResponseStatusCode));
		JsonPath jsonPathEvaluator = transactionProfileResponse.jsonPath();
		String transactionProfileToken = jsonPathEvaluator.getString("token");
		// System.out.println("transactionProfileToken::" + transactionProfileToken);
		generic.explicitWait(1);

		String decryptRequestBody = tpr.decryptRequestBody(transactionProfileToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		// String decryptUnloadRequest = decryptDataResponse.getBody().asString();
		// System.out.println("decryptUnloadRequest::" + decryptUnloadRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);

		softAssert.assertAll();

	}

	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		tpr = null;
	}

}
