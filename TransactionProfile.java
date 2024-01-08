package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;

public class TransactionProfile {
	private String transactionProfileId;
	private String transactionType;
	private String status;
	
	public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
    
	public String getTransactionProfileId() {
		return transactionProfileId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public String getStatus() {
		return status;
	}
	public void setTransactionProfileId(String transactionProfileId) {
		this.transactionProfileId = transactionProfileId;
		if(transactionProfileId.contentEquals("Auto")) {
			this.transactionProfileId = "1";
		}		
		if(!transactionProfileId.toLowerCase().equals("false")) {
			jsonObject.put("transactionProfileId", transactionProfileId.equals("null") ? JSONObject.NULL : this.transactionProfileId);	
		}
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		if(transactionType.contentEquals("Auto")) {
			this.transactionType = "pos";
		}		
		if(!transactionType.toLowerCase().equals("false")) {
			jsonObject.put("transactionType", transactionType.equals("null") ? JSONObject.NULL : this.transactionType);	
		}
	}
	
	public void setStatus(String status) {
		this.status = status;
		if(status.contentEquals("Auto")) {
			this.status = "false";
		}		
		if(!status.toLowerCase().equals("false")) {
			jsonObject.put("status", status.equals("null") ? JSONObject.NULL : this.status);	
		}
	}	
	
	public JSONObject getJsonobject() {
		return jsonObject;
	}
}
