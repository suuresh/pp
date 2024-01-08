package com.ppi.api.payloads.mone;

public class RetrieveCustRecordResponse {
	public String messageCode;
	public String clientTxnId;
	public String requestDateTime;
	public String customerMobile;
	public String customerId;
	public String emailId;
	public String urn;
	public String cardNumber;
	public String responseCode;
	public String responseMessage;
	
	
    
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public void setClientTxnId(String clientTxnId) {
		this.clientTxnId = clientTxnId;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setUrn(String urn) {
		this.urn = urn;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
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
	
	

}
