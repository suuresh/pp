package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class SendOtpEmailRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String email;
	private String otp_generation_type;
	private String productId;
	private boolean emailVerifySkipFlag;
	
	public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
	
	public String getMessageCode() {
		return messageCode;
	}
	public String getClientTxnId() {
		return clientTxnId;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public String getEmail() {
		return email;
	}
	public String getOtp_generation_type() {
		return otp_generation_type;
	}
	public String getProductId() {
		return productId;
	}
	public boolean isEmailVerifySkipFlag() {
		return emailVerifySkipFlag;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "2080";
		}
		if(!messageCode.toLowerCase().equals("false")) {
			jsonObject.put("messageCode", messageCode.equals("null") ? JSONObject.NULL : this.messageCode);
		}
	}
	public void setClientTxnId(String clientTxnId) {
		this.clientTxnId = clientTxnId;
		if(clientTxnId.contentEquals("Auto")) {
			this.clientTxnId = faker.number().randomNumber(12, true)+"";
		}
		if(!clientTxnId.toLowerCase().equals("false")) {
			jsonObject.put("clientTxnId", clientTxnId.equals("null") ? JSONObject.NULL : this.clientTxnId);	
		}
	}
	
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
		if(requestDateTime.contentEquals("Auto")) {
			this.requestDateTime = GenericMethods.getCurrentDateTime("yyyyMMddHHmmss");
		}		
		if(!requestDateTime.toLowerCase().equals("false")) {
			jsonObject.put("requestDateTime", requestDateTime.equals("null") ? JSONObject.NULL : this.requestDateTime);	
		}
	}
	
	public void setEmail(String email) {
		this.email = email;
		if(email.contentEquals("Auto")) {
			this.email = faker.internet().emailAddress();
		}		
		if(!email.toLowerCase().equals("false")) {
			jsonObject.put("email", email.equals("null") ? JSONObject.NULL : this.email);	
		}
	}
	
	public void setOtpGenerationType(String otp_generation_type) {
		this.otp_generation_type = otp_generation_type;
		if(otp_generation_type.contentEquals("Auto")) {
			this.otp_generation_type = "0";
		}		
		if(!otp_generation_type.toLowerCase().equals("false")) {
			jsonObject.put("otp_generation_type", otp_generation_type.equals("null") ? JSONObject.NULL : this.otp_generation_type);	
		}
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
		if(productId.contentEquals("Auto")) {
			this.productId = "1";
		}		
		if(!productId.toLowerCase().equals("false")) {
			jsonObject.put("productId", productId.equals("null") ? JSONObject.NULL : this.productId);	
		}
	}
	
	public void setEmailVerifySkipFlag(String emailVerifySkipFlag) {		
		this.emailVerifySkipFlag = emailVerifySkipFlag.toLowerCase().equals("true")? true : false;
		if(emailVerifySkipFlag.contentEquals("Auto")) {
			this.emailVerifySkipFlag = false;
		}		
		if(!emailVerifySkipFlag.toLowerCase().equals("false")) {
			jsonObject.put("emailVerifySkipFlag", emailVerifySkipFlag.equals("null") ? JSONObject.NULL : this.emailVerifySkipFlag);	
		}
	}
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String sendOtpEmailRequestBody(String token) {
		String jsonbody = "{"
				+ "    \"token\": \""+token+"\""
				+ "}";
		return jsonbody;
	}
	
	public String decryptRequestBody(String token) {
		String jsonbody = "{"
				+ "    \"token\": \""+token+"\""
				+ "}";
		return jsonbody;
	}
}
