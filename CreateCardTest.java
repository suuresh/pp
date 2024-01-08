package com.ppi.api.testcases.mone;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppi.api.apimethods.MobilityOneApiMethods;
import com.ppi.api.payloads.mone.CreateCard;
import com.ppi.api.testcases.InitTest;
import com.ppi.utilities.GenericMethods;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateCardTest extends InitTest{
	
	GenericMethods generic = new GenericMethods();
	public static Logger log = LogManager.getLogger(CreateCardTest.class.getName());
	public SoftAssert softAssert;		
	public CreateCard cc;

	@BeforeTest
	public void preCondition() {
		System.out.println("BeforeTestExecuted");
	}
	
	@AfterTest
	public void postCondition() {
		generic = null;
		softAssert = null;
		cc = null;
	}
	
	@DataProvider
	public Object[][] createCardData() throws IOException {
		log.info("Reading data from excell file");
		return generic.getData(xlFileName, "CreateCard", typeoftest);
	}
		
	@Test(dataProvider = "createCardData", priority=1)
	public void testCreateCard(String TCID, String TestScenario, String TestType, String MessageCode, String ProductId,
			String ClientTxnId, String RequestDateTime, String FirstName, String LastName, String Mobile,
			String CustomerId, String Email, String DateOfBirth, String CardProfileId, String SourceAccountType,
			String NewCardStatus, String LoadAmount, String Eventid, String SorCustomerId, String KitNo, String Gender,
			String DocType, String DocCountry, String DocExpiry, String DocNumber, String CKycDocNo, String DocConsent,
			String KycDateStamp, String SourceAccount, String Addressline1, String Addressline2, String Addressline3,
			String DeliveryAddress, String CardholderCity, String CardholderState, String CardholderCountry,
			String CardholderZipCode, String CreateQR, String EncryptResponseStatusCode,
			String CreateCardResponseStatusCode, String DecryptResponseStatusCode, String ResponseCode,
			String ResponseMessage)
			throws Exception {
		
		
		
		softAssert = new SoftAssert();
		cc = new CreateCard();
		cc.setMessageCode(MessageCode);
		cc.setProductId(ProductId);
		cc.setClientTxnId(ClientTxnId);
		cc.setRequestDateTime(RequestDateTime);		  
		cc.setFirstName(FirstName);
		cc.setLastName(LastName);
		cc.setMobile(Mobile);
		cc.setCustomerId(CustomerId);
		cc.setEmail(Email);
		cc.setDateOfBirth(DateOfBirth);
		cc.setCardProfileId(CardProfileId);
		cc.setSourceAccountType(SourceAccountType);
		cc.setNewCardStatus(NewCardStatus);
		cc.setLoadAmount(LoadAmount);
		cc.setEventid(Eventid);
		cc.setSorCustomerId(SorCustomerId);
		cc.setKitNo(KitNo);
		cc.setGender(Gender);
		cc.setDocType(DocType);
		cc.setDocCountry(DocCountry);
		cc.setDocExpiry(DocExpiry);
		cc.setDocNumber(DocNumber);
		cc.setcKycDocNo(CKycDocNo);
		cc.setDocConsent(DocConsent);
		cc.setKycDateStamp(KycDateStamp);
		cc.setSourceAccount(SourceAccount);
		cc.setAddressline1(Addressline1);
		cc.setAddressline2(Addressline2);
		cc.setAddressline3(Addressline3);
		cc.setDeliveryAddress(DeliveryAddress);
		cc.setCardholderCity(CardholderCity);
		cc.setCardholderState(CardholderState);
		cc.setCardholderCountry(CardholderCountry);
		cc.setCardholderZipCode(CardholderZipCode);
		cc.setCreateQR(CreateQR);
		 
		
		System.out.println("*****Test Start for " + TCID);
		
		String encryptCreateCardRequestBody = cc.encryptCreateCardRequestBody();
		System.out.println(encryptCreateCardRequestBody);
		Response encryptDataResponse = MobilityOneApiMethods.encryptData(encryptCreateCardRequestBody);
		encryptDataResponse.then().log().all();
		softAssert.assertEquals(encryptDataResponse.getStatusCode(), Integer.parseInt(EncryptResponseStatusCode));		
		//String encryptRequest = encryptDataResponse.getBody().asString();
		//System.out.println("encryptRequest::" + encryptRequest);
		JsonPath encryptJsonPathEvaluator = encryptDataResponse.jsonPath();
		String encryptedToken = encryptJsonPathEvaluator.getString("token");	
		generic.explicitWait(1);
		
		String createCardRequestBody = CreateCard.createCardRequestBody(encryptedToken);		
		Response createCardResponse = MobilityOneApiMethods.createCard(createCardRequestBody);
		createCardResponse.then().log().all();
		softAssert.assertEquals(createCardResponse.getStatusCode(), Integer.parseInt(CreateCardResponseStatusCode));
		//String createCardRequest = createCardResponse.getBody().asString();
		//System.out.println("createCardRequest::" + createCardRequest);
		JsonPath crateCardJsonPathEvaluator = createCardResponse.jsonPath();
		String createCardToken = crateCardJsonPathEvaluator.getString("token");
		System.out.println("createCardToken::"+createCardToken);
		generic.explicitWait(1);
		
		String decryptRequestBody = CreateCard.decryptRequestBody(createCardToken);		
		Response decryptDataResponse = MobilityOneApiMethods.decryptData(decryptRequestBody);
		decryptDataResponse.then().log().all();
		softAssert.assertEquals(decryptDataResponse.getStatusCode(), Integer.parseInt(DecryptResponseStatusCode));
		String decryptCreateCardRequest = decryptDataResponse.getBody().asString();
		System.out.println("decryptCreateCardRequest::" + decryptCreateCardRequest);
		JsonPath decryptJsonPathEvaluator = decryptDataResponse.jsonPath();		
		String responseMessage = decryptJsonPathEvaluator.getString("responseMessage");
		softAssert.assertEquals(responseMessage, ResponseMessage);
		softAssert.assertEquals(decryptJsonPathEvaluator.getString("responseCode"), ResponseCode);
		generic.explicitWait(1);
		
		// Write the data to excel
		if(responseMessage.contentEquals("CARD_CREATED_SUCCESSFULLY")) {
			JSONObject json = new JSONObject(decryptCreateCardRequest);  
			//System.out.println(json.toString());
			int rowCount = GenericMethods.getRowCount(xlFileName, "CardDetails");
			rowCount++;
			JSONArray keys = json.names();
			for (int i = 0; i < keys.length(); i++) {

				String key = keys.getString(i); // Here's your key
				Object value = json.get(key); // Here's your value
				//System.out.println(key+","+value);
				
				if(!key.equals("description") || !key.equals("responseCode") || !key.equals("messageCode")
						|| !key.equals("responseDateTime") || !key.equals("responseMessage") || !key.equals("subwalletListDetails"))
				GenericMethods.writingToExcel(xlFileName, "CardDetails", key, rowCount, value.toString());				
			}
			
			//JSONObject json1 = new JSONObject(json.getString("subwalletListDetails"));
			JSONArray jarray =  json.getJSONArray("subwalletListDetails");
			JSONObject json1 = jarray.getJSONObject(0);
			keys = json1.names();
			for (int i = 0; i < keys.length(); i++) {

				String key = keys.getString(i); // Here's your key
				Object value = json1.get(key); // Here's your value
				//System.out.println(key+","+value);
				GenericMethods.writingToExcel(xlFileName, "CardDetails", key, rowCount, value.toString());
			}			
		}
		
		softAssert.assertAll();
		
	}
	
	
}
