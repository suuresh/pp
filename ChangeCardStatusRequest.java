package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class ChangeCardStatusRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String urn; 
	private String cardStatus;
	private String reason;
	
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

	public String getUrn() {
		return urn;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public String getReason() {
		return reason;
	} 
    
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "3010";
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

	public void setUrn(String urn) {	
		this.urn = urn;
		if(urn.contentEquals("Auto")) {
			this.urn = faker.number().randomNumber(12, true)+"";
		}		
		if(!urn.toLowerCase().equals("false")) {
			jsonObject.put("urn", urn.equals("null") ? JSONObject.NULL : this.urn);	
		}
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
		if(cardStatus.contentEquals("Auto")) {
			this.cardStatus = "Active";
		}		
		if(!cardStatus.toLowerCase().equals("false")) {
			jsonObject.put("cardStatus", cardStatus.equals("null") ? JSONObject.NULL : this.cardStatus);	
		}
	}

	public void setReason(String reason) {
		this.reason = reason;
		if(reason.contentEquals("Auto")) {
			this.reason = "Active";
		}		
		if(!reason.toLowerCase().equals("false")) {
			jsonObject.put("reason", reason.equals("null") ? JSONObject.NULL : this.reason);	
		}
	}	

	public String encryptChangeCardStatusRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		//System.out.println(jsonString);
		return jsonString;
	}
	
	public String serviceRequestBody(String token) {
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
