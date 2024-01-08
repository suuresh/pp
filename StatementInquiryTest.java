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
import com.ppi.api.payloads.mone.StatementInquiryRequest;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StatementInquiryTest extends InitTest {
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(StatementInquiryTest.class.getName());
	public SoftAssert softAssert;
	public StatementInquiryRequest sir;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}

	@DataProvider
	public Object[][] statementInquiryData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "StatementInquiry", typeoftest);
	}

	@Test(dataProvider = "statementInquiryData")
	public void testUnload(String TCID, String TestScenario, String TestType, String messageCode, String clientTxnId,
			String requestDateTime, String fromDate, String toDate, String last4Digits, String urn, String customerId,
			String pageNumber, String count, String fromRowId, String EncryptResponseStatusCode,
			String StatementInquiryResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage) throws Exception {
		System.out.println("");
		System.out.println("*****Test Started for ::" + TCID + " :: " + TestScenario);
		softAssert = new SoftAssert();
		sir = new StatementInquiryRequest();

		sir.setMessageCode(messageCode);
		sir.setClientTxnId(clientTxnId);
		sir.setRequestDateTime(requestDateTime);
		sir.setFromDate(fromDate);
		sir.setToDate(toDate);
		sir.setLast4Digits(last4Digits);
		sir.setUrn(urn);
		sir.setPageNumber(pageNumber);
		sir.setCount(count);
		sir.setFromRowId(fromRowId);
		
		
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(sir.encryptRequestBody());
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");
		generic.explicitWait(1);

		String statementInquiryRequestBody = sir.statementInquiryRequestBody(encryptedToken);
		Response statementInquiryResponse = MobilityOneApiMethods.statementInquiry(statementInquiryRequestBody);
		statementInquiryResponse.then().log().all();
		softAssert.assertEquals(statementInquiryResponse.getStatusCode(), Integer.parseInt(StatementInquiryResponseStatusCode));
		JsonPath jsonPathEvaluator = statementInquiryResponse.jsonPath();
		String statementInquiryToken = jsonPathEvaluator.getString("token");
		// System.out.println("statementInquiryToken::" + statementInquiryToken);
		generic.explicitWait(1);

		String decryptRequestBody = sir.decryptRequestBody(statementInquiryToken);
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		// String decryptRequest = decryptDataResponse.getBody().asString();
		// System.out.println("decryptRequest::" + decryptRequest);
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
		sir = null;
	}

}
