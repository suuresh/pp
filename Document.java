package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class Document {
	private String docType;
	private String docCountry;
	private String docExpiry;
	private String docNumber;
	public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
	public String getDocType() {
		return docType;
	}
	public String getDocCountry() {
		return docCountry;
	}
	public String getDocExpiry() {
		return docExpiry;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocType(String docType) {
		this.docType = docType;
		if(docType.contentEquals("Auto")) {
			this.docType = "NA";
		}		
		if(!docType.toLowerCase().equals("false")) {
			jsonObject.put("docType", docType.equals("null") ? JSONObject.NULL : this.docType);	
		}
	}
	
	public void setDocCountry(String docCountry) {
		this.docCountry = docCountry;
		if(docCountry.contentEquals("Auto")) {
			this.docCountry = "India";
		}		
		if(!docCountry.toLowerCase().equals("false")) {
			jsonObject.put("docCountry", docCountry.equals("null") ? JSONObject.NULL : this.docCountry);	
		}
	}
	
	public void setDocExpiry(String docExpiry) {
		this.docExpiry = docExpiry;
		if(docExpiry.contentEquals("Auto")) {
			this.docExpiry = GenericMethods.getCurrentDateTime("yyyy")+"1231";
		}		
		if(!docExpiry.toLowerCase().equals("false")) {
			jsonObject.put("docExpiry", docExpiry.equals("null") ? JSONObject.NULL : this.docExpiry);	
		}
	}
	
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
		if(docNumber.contentEquals("Auto")) {
			this.docNumber = faker.number().randomNumber(12, true)+"";
		}		
		if(!docNumber.toLowerCase().equals("false")) {
			jsonObject.put("docNumber", docNumber.equals("null") ? JSONObject.NULL : this.docNumber);	
		}
	}
	
	public JSONObject getJsonobject() {
		return jsonObject;
	}
	
	
}
