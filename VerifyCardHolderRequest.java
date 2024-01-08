package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class VerifyCardHolderRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String urn;	
	private String customerMobile;
	private String emailId;	
	private String last4Digits;
	private String cardExpiry ;
	private String dob;
	
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
	
	public String getLast4Digits() {
		return last4Digits;
	}
	public String getUrn() {
		return urn;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getCardExpiry() {
		return cardExpiry;
	}
	public String getDob() {
		return dob;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "3020";
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
	
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
		if(customerMobile.contentEquals("Auto")) {
			this.customerMobile = "60" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!customerMobile.toLowerCase().equals("false")) {
			jsonObject.put("customerMobile", customerMobile.equals("null") ? JSONObject.NULL : this.customerMobile);	
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
	
	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
		if(cardExpiry.contentEquals("Auto")) {
			this.cardExpiry = GenericMethods.getCurrentDateTime("MMYY");
		}		
		if(!cardExpiry.toLowerCase().equals("false")) {
			jsonObject.put("cardExpiry", cardExpiry.equals("null") ? JSONObject.NULL : this.cardExpiry);	
		}
	}
	
	public void setDob(String dob) {
		this.dob = dob;
		if(dob.contentEquals("Auto")) {
			this.dob = GenericMethods.getCurrentDateTime("dd-MM-yyyy");
		}		
		if(!dob.toLowerCase().equals("false")) {
			jsonObject.put("dob", dob.equals("null") ? JSONObject.NULL : this.dob);	
		}
	}
	
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String verifyCardHolderRequestBody(String token) {
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
