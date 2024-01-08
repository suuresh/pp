package com.ppi.api.payloads.spicemoney;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class Document {
	private String ovdType;
	private String ovdExpDate;
	private String ovdNo;
	
	public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
    
	public String getOvdType() {
		return ovdType;
	}
	public String getOvdExpDate() {
		return ovdExpDate;
	}
	public String getOvdNo() {
		return ovdNo;
	}
	public void setOvdType(String ovdType) {
		this.ovdType = ovdType;
		if(ovdType.contentEquals("Auto")) {
			this.ovdType = "NA";
		}		
		if(!ovdType.toLowerCase().equals("false")) {
			jsonObject.put("ovdType", ovdType.equals("null") ? JSONObject.NULL : this.ovdType);	
		}
	}
	
	public void setOvdExpDate(String ovdExpDate) {
		this.ovdExpDate = ovdExpDate;
		if(ovdExpDate.contentEquals("Auto")) {
			this.ovdExpDate = GenericMethods.getCurrentDateTime("yyyyMMdd");
		}		
		if(!ovdExpDate.toLowerCase().equals("false")) {
			jsonObject.put("ovdExpDate", ovdExpDate.equals("null") ? JSONObject.NULL : this.ovdExpDate);	
		}
	}
	public void setOvdNo(String ovdNo) {
		this.ovdNo = ovdNo;
		if(ovdNo.contentEquals("Auto")) {
			this.ovdNo = faker.number().randomNumber(12, true)+"";
		}		
		if(!ovdNo.toLowerCase().equals("false")) {
			jsonObject.put("ovdNo", ovdNo.equals("null") ? JSONObject.NULL : this.ovdNo);	
		}
	}
	
	public JSONObject getJsonobject() {
		return jsonObject;
	}
}
