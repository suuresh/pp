package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class RetrieveCustRecordRequest {
	public String messageCode;
	public String clientTxnId;
	public String requestDateTime;
	public String customerMobile;
	public String customerId;
	public String emailId;
	public String urn;
	public String cardNumber;
	
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
	public String getCustomerMobile() {
		return customerMobile;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getUrn() {
		return urn;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "3422";
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
	
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
		if(customerMobile.contentEquals("Auto")) {
			this.customerMobile = "60" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!customerMobile.toLowerCase().equals("false")) {
			jsonObject.put("customerMobile", customerMobile.equals("null") ? JSONObject.NULL : this.customerMobile);	
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
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
		if(emailId.contentEquals("Auto")) {
			this.emailId = faker.internet().emailAddress();
		}		
		if(!emailId.toLowerCase().equals("false")) {
			jsonObject.put("emailId", emailId.equals("null") ? JSONObject.NULL : this.emailId);	
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
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		if(cardNumber.contentEquals("Auto")) {
			this.cardNumber = faker.number().randomNumber(16, true)+"";
		}		
		if(!cardNumber.toLowerCase().equals("false")) {
			jsonObject.put("cardNumber", cardNumber.equals("null") ? JSONObject.NULL : this.cardNumber);	
		}
	}
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String retrieveCustRecordRequestBody(String token) {
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
