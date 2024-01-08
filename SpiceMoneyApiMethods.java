package com.ppi.api.apimethods;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.ppi.api.endpoints.Constants;
import com.ppi.api.endpoints.Routes;

import io.restassured.response.Response;

public class SpiceMoneyApiMethods {
	
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
		Response encryptDataResponse = given()				
				.headers(SpiceMoneyApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.spice_money_encrypt_url);

		return encryptDataResponse;
	}
	
public static Response decryptData(String jsonbody) {
		
		Response decryptDataResponse = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(SpiceMoneyApiMethods.getApikeyHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.spice_money_decrypt_url);
		return decryptDataResponse;
	}
	
	public static Response registerCustomer(String jsonbody) {		
		Response registerCustomerResponse = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(SpiceMoneyApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.registerCustomer_url);
		return registerCustomerResponse;
	}
	
	public static Response load(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.spice_money_load_url);
		return response;
	}
	
	public static Response unload(String jsonbody) {
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(MobilityOneApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.patch(Routes.spice_money_unload_url);
		return response;
	}
	
	public static Response fundTransfer(String jsonbody) {		
		Response response = given()
				//.accept(ContentType.JSON).with()
				//.contentType(ContentType.JSON).and()
				.headers(SpiceMoneyApiMethods.getAllHeaders())
				.body(jsonbody)
			.when()
				.post(Routes.spice_money_fund_transfer_url);
		return response;
	}
	
	
}
