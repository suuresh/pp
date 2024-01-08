package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class ResetPinRequest {
	public String messageCode;
    public String requestDateTime;
    public String clientTxnId;
    public String newPIN;
    public String last4Digits;
    public String urn;
    public String customerId;
    
    public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
    
	public String getMessageCode() {
		return messageCode;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public String getClientTxnId() {
		return clientTxnId;
	}
	public String getNewPIN() {
		return newPIN;
	}
	public String getLast4Digits() {
		return last4Digits;
	}
	public String getUrn() {
		return urn;
	}
	public String getCustomerId() {
		return customerId;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1120";
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
	
	public void setNewPIN(String newPIN) {
		this.newPIN = newPIN;
		if(newPIN.contentEquals("Auto")) {
			this.newPIN = faker.number().randomNumber(6, true)+"";
		}		
		if(!newPIN.toLowerCase().equals("false")) {
			jsonObject.put("newPIN", newPIN.equals("null") ? JSONObject.NULL : this.newPIN);	
		}		
	}
	
	public void setLast4Digits(String last4Digits) {
		this.last4Digits = last4Digits;
		if(last4Digits.contentEquals("Auto")) {
			this.last4Digits = faker.number().randomNumber(4, true)+"";
		}		
		if(!last4Digits.toLowerCase().equals("false")) {
			jsonObject.put("last4Digits", last4Digits.equals("null") ? JSONObject.NULL : this.last4Digits);	
		}		
	}
	
	public void setUrn(String urn) {
		this.urn = urn;
		if(urn.contentEquals("Auto")) {
			this.urn = faker.number().randomNumber(12, true)+"";
		}		
		if(!urn.toLowerCase().equals("false")) {
			jsonObject.put("urn", urn.equals("null") ? JSONObject.NULL : this.urn);	
		}
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
		if(customerId.contentEquals("Auto")) {
			this.customerId = faker.number().randomNumber(15,true)+"";
		}		
		if(!customerId.toLowerCase().equals("false")) {
			jsonObject.put("customerId", customerId.equals("null") ? JSONObject.NULL : this.customerId);	
		}	
	}
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String resetpinRequestBody(String token) {
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
