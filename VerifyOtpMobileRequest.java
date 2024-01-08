package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class VerifyOtpMobileRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String mobileNumber;
	private String otp;
	private boolean smsVerifySkipFlag;
	
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public boolean getSmsVerifySkipFlag() {
		return smsVerifySkipFlag;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1960";
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
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		if(mobileNumber.contentEquals("Auto")) {
			this.mobileNumber = "60" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!mobileNumber.toLowerCase().equals("false")) {
			jsonObject.put("cardHolderMobileNumber", mobileNumber.equals("null") ? JSONObject.NULL : this.mobileNumber);	
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
	
	public void setSmsVerifySkipFlag(String smsVerifySkipFlag) {
		this.smsVerifySkipFlag = smsVerifySkipFlag.toLowerCase().equals("true")? true : false;
		if(smsVerifySkipFlag.contentEquals("Auto")) {
			this.smsVerifySkipFlag = false;
		}		
		if(!smsVerifySkipFlag.toLowerCase().equals("false")) {
			jsonObject.put("smsVerifySkipFlag", smsVerifySkipFlag.equals("null") ? JSONObject.NULL : this.smsVerifySkipFlag);	
		}
	}
	
	public String encryptRequestBody() {
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String verifyOtpMobileRequestBody(String token) {
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
