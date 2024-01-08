package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class VerifyOtpEmailRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String emailId;
	private String otp;	
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
	public String getEmailId() {
		return emailId;
	}
	public String getOtp() {
		return otp;
	}
	public boolean isEmailVerifySkipFlag() {
		return emailVerifySkipFlag;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "2090";
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
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
		if(emailId.contentEquals("Auto")) {
			this.emailId = faker.internet().emailAddress();
		}		
		if(!emailId.toLowerCase().equals("false")) {
			jsonObject.put("emailId", emailId.equals("null") ? JSONObject.NULL : this.emailId);	
		}
	}
	
	public void setOtp(String otp) {
		this.otp = otp;
		if(otp.contentEquals("Auto")) {
			this.otp = faker.number().randomNumber(6, true)+"";
		}		
		if(!otp.toLowerCase().equals("false")) {
			jsonObject.put("otp", otp.equals("null") ? JSONObject.NULL : this.otp);	
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
	
	public String verifyOtpEmailRequestBody(String token) {
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
