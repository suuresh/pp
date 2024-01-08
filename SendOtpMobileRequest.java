package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class SendOtpMobileRequest {
	private String messageCode; 
	private String clientTxnId;
	private String requestDateTime;	
	private String cardHolderMobileNumber;
	private String productId;	
	private String otp_generation_type; 
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
	public String getCardHolderMobileNumber() {
		return cardHolderMobileNumber;
	}
	public String getProductId() {
		return productId;
	}
	public String getOtp_generation_type() {
		return otp_generation_type;
	}
	public boolean getSmsVerifySkipFlag() {
		return smsVerifySkipFlag;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1940";
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
	
	public void setCardholderMobileNumber(String cardHolderMobileNumber) {
		this.cardHolderMobileNumber = cardHolderMobileNumber;
		if(cardHolderMobileNumber.contentEquals("Auto")) {
			this.cardHolderMobileNumber = "60" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!cardHolderMobileNumber.toLowerCase().equals("false")) {
			jsonObject.put("cardHolderMobileNumber", cardHolderMobileNumber.equals("null") ? JSONObject.NULL : this.cardHolderMobileNumber);	
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
	
	public void setOtpGenerationType(String otp_generation_type) {
		this.otp_generation_type = otp_generation_type;
		if(otp_generation_type.contentEquals("Auto")) {
			this.otp_generation_type = "1";
		}		
		if(!otp_generation_type.toLowerCase().equals("false")) {
			jsonObject.put("otp_generation_type", otp_generation_type.equals("null") ? JSONObject.NULL : this.otp_generation_type);	
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
	
	public String sendOtpMobileRequestBody(String token) {
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
