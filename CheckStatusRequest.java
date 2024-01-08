package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class CheckStatusRequest {
	public String messageCode;
    public String clientTxnId;
    public String requestDateTime;
    public String verifyclientTxnId;
    public String fromDate;
    public String toDate;
    
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
	public String getVerifyclientTxnId() {
		return verifyclientTxnId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "2050";
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
	
	public void setVerifyclientTxnId(String verifyclientTxnId) {
		this.verifyclientTxnId = verifyclientTxnId;
		if(verifyclientTxnId.contentEquals("Auto")) {
			this.verifyclientTxnId = faker.number().randomNumber(12, true)+"";
		}
		if(!verifyclientTxnId.toLowerCase().equals("false")) {
			jsonObject.put("verifyclientTxnId", verifyclientTxnId.equals("null") ? JSONObject.NULL : this.verifyclientTxnId);	
		}
	}
	
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
		if(fromDate.contentEquals("Auto")) {
			this.fromDate = GenericMethods.getCurrentDateTime("yyyy-MM-dd");
		}		
		if(!fromDate.toLowerCase().equals("false")) {
			jsonObject.put("fromDate", fromDate.equals("null") ? JSONObject.NULL : this.fromDate);	
		}
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
		if(toDate.contentEquals("Auto")) {
			this.toDate = GenericMethods.getCurrentDateTime("yyyy-MM-dd");
		}		
		if(!toDate.toLowerCase().equals("false")) {
			jsonObject.put("toDate", toDate.equals("null") ? JSONObject.NULL : this.toDate);	
		}
	}
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String checkStatusRequestBody(String token) {
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
