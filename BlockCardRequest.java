package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.api.endpoints.Constants;
import com.ppi.utilities.GenericMethods;

public class BlockCardRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String customerId;
	private String last4Digits;
	private String urn;
	private String blockType;
	private String reserved1;
	private String reserved2;
	
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
	public String getCustomerId() {
		return customerId;
	}
	public String getLast4Digits() {
		return last4Digits;
	}
	public String getUrn() {
		return urn;
	}
	public String getBlockType() {
		return blockType;
	}
	public String getReserved1() {
		return reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1240";
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
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
		if(customerId.contentEquals("Auto")) {
			this.customerId = faker.number().randomNumber(15,true)+"";
		}		
		if(!customerId.toLowerCase().equals("false")) {
			jsonObject.put("customerId", customerId.equals("null") ? JSONObject.NULL : this.customerId);	
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
	
	public void setBlockType(String blockType) {
		this.blockType = blockType;
		if(blockType.contentEquals("Auto")) {
			this.blockType =  Constants.blockTypes[faker.number().numberBetween(0, Constants.blockTypes.length)];
		}
		if(!blockType.toLowerCase().equals("false")) {
			jsonObject.put("blockType", blockType.equals("null") ? JSONObject.NULL : this.blockType);
		}
	}
	
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
		if(reserved1.contentEquals("Auto")) {
			this.reserved1 =  "Block";
		}
		if(!reserved1.toLowerCase().equals("false")) {
			jsonObject.put("reserved1", reserved1.equals("null") ? JSONObject.NULL : this.reserved1);
		}
	}
	
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
		if(reserved2.contentEquals("Auto")) {
			this.reserved2 =  "Mobile lost, Blocking the card";
		}
		if(!reserved2.toLowerCase().equals("false")) {
			jsonObject.put("reserved2", reserved2.equals("null") ? JSONObject.NULL : this.reserved2);
		}
	}
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String blockCardRequestBody(String token) {
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
