package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class TransactionInquiryRequest {
	public String messageCode;
    public String requestDateTime;
    public String clientTxnId;
    public String fromDate;
    public String toDate;
    public String last4Digits;
    public String urn;
    public String customerId;
    public String pageNumber;
    public String count;
    public String fromRowId;
    
    public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
    
    public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1070";
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
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
		if(fromDate.contentEquals("Auto")) {
			this.fromDate = GenericMethods.getCurrentDateTime("dd/MM/yyyy");
		}		
		if(!fromDate.toLowerCase().equals("false")) {
			jsonObject.put("fromDate", fromDate.equals("null") ? JSONObject.NULL : this.fromDate);	
		}
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
		if(toDate.contentEquals("Auto")) {
			this.toDate = GenericMethods.getCurrentDateTime("dd/MM/yyyy");
		}		
		if(!toDate.toLowerCase().equals("false")) {
			jsonObject.put("toDate", toDate.equals("null") ? JSONObject.NULL : this.toDate);	
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
	
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
		if(pageNumber.contentEquals("Auto")) {
			this.pageNumber = "pgNo-1";
		}		
		if(!pageNumber.toLowerCase().equals("false")) {
			jsonObject.put("pageNumber", pageNumber.equals("null") ? JSONObject.NULL : this.pageNumber);	
		}	
	}
	
	public void setCount(String count) {
		this.count = count;
		if(count.contentEquals("Auto")) {
			this.count = faker.number().randomNumber(2,true)+"";
		}		
		if(!count.toLowerCase().equals("false")) {
			jsonObject.put("count", count.equals("null") ? JSONObject.NULL : this.count);	
		}	
	}
	
	public void setFromRowId(String fromRowId) {
		this.fromRowId = fromRowId;
		if(fromRowId.contentEquals("Auto")) {
			this.fromRowId = faker.number().randomNumber(2,true)+"";
		}		
		if(!fromRowId.toLowerCase().equals("false")) {
			jsonObject.put("fromRowId", fromRowId.equals("null") ? JSONObject.NULL : this.fromRowId);	
		}	
	}
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String transactionInquiryRequestBody(String token) {
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
