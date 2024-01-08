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
import com.ppi.api.payloads.mone.CheckStatusRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CheckStatusTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(CheckStatusTest.class.getName());
	public SoftAssert softAssert;
	public CheckStatusRequest csr;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@DataProvider
	public Object[][] checkStatusData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "CheckStatus", typeoftest);
	}

	@Test(dataProvider = "checkStatusData")
	public void testCheckStatus(String TCID, String TestScenario, String TestType, String messageCode,
			String clientTxnId, String requestDateTime, String verifyclientTxnId, String fromDate, String toDate,
			String EncryptResponseStatusCode, String CheckStatusResponseStatusCode, String DecryptResponseStatusCode,
			String ResponseCode, String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::" + TCID + " :: " + TestScenario);
		softAssert = new SoftAssert();
		csr = new CheckStatusRequest();

		csr.setMessageCode(messageCode);
		csr.setClientTxnId(clientTxnId);
		csr.setRequestDateTime(requestDateTime);
		csr.setVerifyclientTxnId(verifyclientTxnId);
		csr.setFromDate(fromDate);
		csr.setToDate(toDate);

		Response encryptDataResponse = MobilityOneApiMethods.encryptData(csr.encryptRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String checkStatusRequestBody = csr.checkStatusRequestBody(encryptedToken);
		Response checkStatusResponse = MobilityOneApiMethods.checkStatus(checkStatusRequestBody);
		checkStatusResponse.then().log().all();
		softAssert.assertEquals(checkStatusResponse.getStatusCode(), Integer.parseInt(CheckStatusResponseStatusCode));
		JsonPath jsonPathEvaluator = checkStatusResponse.jsonPath();
		String checkStatusToken = jsonPathEvaluator.getString("token");
		// System.out.println("checkStatusToken::" + checkStatusToken);
		generic.explicitWait(1);

		String decryptRequestBody = csr.decryptRequestBody(checkStatusToken);
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
		csr = null;
	}

}
