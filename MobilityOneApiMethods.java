package com.ppi.api.apimethods;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.ppi.api.endpoints.Constants;
import com.ppi.api.endpoints.Routes;

import io.restassured.response.Response;

public class MobilityOneApiMethods {
	
	public static Map<String,String> getAllHeaders() {
		Map<String,String> m = new HashMap<String, String>();
		m.put("clientId", Constants.clientId);
		m.put("bankId", Constants.bankId);
		m.put("entityId", Constants.entityId);
		m.put("secureCode", Constants.secureCode);
		m.put("X-Consumer-Custom-ID", Constants.X_Consumer_Custom_ID);
		m.put("x-api-key", Constants.x_api_key);
		m.put("Content-Type", "application/json");
		return m;
	}
	
	public static Map<String,String> getApikeyHeaders() {
		Map<String,String> m = new HashMap<String, String>();		
		m.put("x-api-key", Constants.x_api_key);
		m.put("Content-Type", "application/json");
		return m;
	} 
	
	public static Response encryptData(String jsonbody) {
		//System.out.println(MobilityOneApiMethods.getAllHeaders());
		Response encryptDataResponse = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.encrypt_url);

		return encryptDataResponse;
	}
	
	public static Response decryptData(String jsonbody) {
		
		Response decryptDataResponse = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getApikeyHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.decrypt_url);
		return decryptDataResponse;
	}
	
	public static Response createCard(String jsonbody) {		
		Response createCardResponse = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.createCard_url);
		return createCardResponse;
	}
	
	public static Response activateCard(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.activateCard_url);
		return response;
	}
	
	public static Response changeCardStatus(String jsonbody) {
		Response changeCardStatusResponse = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.changeCardStatus_url);
		return changeCardStatusResponse;
	}
	
	public static Response cardInquiry(String jsonbody) {		
		Response cardInquiryResponse = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.cardInquiry_url);
		return cardInquiryResponse;
	}
	
	public static Response updateProfile(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.updateProfile_url);
		return response;
	}
	
	public static Response retriveCustRecord(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.retirveCustRecord_url);
		return response;
	}
	
	public static Response blockCard(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.blockCard_url);
		return response;
	}
	
	public static Response unblockCard(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.unblockCard_url);
		return response;
	}
	
	public static Response verifyCardholder(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.verifyCardholder_url);
		return response;
	}
	
	public static Response sendOtpMoblie(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.sendOtpMobile_url);
		return response;
	}
	
	public static Response verifyOtpMoblile(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.verifyOtpMobile_url);
		return response;
	}
	
	public static Response sendOtpEmail(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.sendOtpEmail_url);
		return response;
	}
	
	public static Response verifyOtpEmail(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.verifyOtpEmail_url);
		return response;
	}
	
	public static Response load(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.load_url);
		return response;
	}
	
	public static Response unload(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.unload_url);
		return response;
	}
	
	public static Response transactionProfile(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.transactionProfile_url);
		return response;
	}
	
	public static Response checkStatus(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.checkStatus_url);
		return response;
	}
	
	public static Response resetPin(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.resetPin_url);
		return response;
	}
	
	public static Response statementInquiry(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.statementInquiry_url);
		return response;
	}
	
	public static Response transactionInquiry(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.transactionInquiry_url);
		return response;
	}
	
}
