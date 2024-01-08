package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.api.endpoints.Constants;
import com.ppi.utilities.GenericMethods;

public class UnloadRequest {
	private String messageCode; 
	private String clientTxnId; 
	private String requestDateTime;
	private String urn;
	private String subWalletId;
	private String transactionAmount;
	private String receiver;
	private String receiverAccountType;
	private String receiverAccount;
	private String unloadCurrency;
	private String originalClientTxnId;
	private String sourceType;
	private String implId;
	private String implType;
	private String refundFileId;
	private String fundFlowType;
	private String pgNo;
	private String orderId;
	private String beneficiaryId;
	private String fee;
	private String customerMobile;
	private String customerId; 
	private String accountNumber;
	private String mcc;
	private String acceptorNameLocation;
	private String acceptorMerchantId;
	private String acceptorTerminalId;
	private String remarks;
	
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
	public String getSubWalletId() {
		return subWalletId;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public String getReceiver() {
		return receiver;
	}
	public String getReceiverAccountType() {
		return receiverAccountType;
	}
	public String getReceiverAccount() {
		return receiverAccount;
	}
	public String getUnloadCurrency() {
		return unloadCurrency;
	}
	public String getOriginalClientTxnId() {
		return originalClientTxnId;
	}
	public String getSourceType() {
		return sourceType;
	}
	public String getImplId() {
		return implId;
	}
	public String getImplType() {
		return implType;
	}
	public String getRefundFileId() {
		return refundFileId;
	}
	public String getFundFlowType() {
		return fundFlowType;
	}
	public String getPgNo() {
		return pgNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public String getFee() {
		return fee;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getMcc() {
		return mcc;
	}
	public String getAcceptorNameLocation() {
		return acceptorNameLocation;
	}
	public String getAcceptorMerchantId() {
		return acceptorMerchantId;
	}
	public String getAcceptorTerminalId() {
		return acceptorTerminalId;
	}
	public String getRemarks() {
		return remarks;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1480";
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
	public void setSubWalletId(String subWalletId) {
		this.subWalletId = subWalletId;
		if(subWalletId.contentEquals("Auto")) {
			this.subWalletId = faker.number().randomNumber(12, true)+"";
		}
		if(!subWalletId.toLowerCase().equals("false")) {
			jsonObject.put("subWalletId", subWalletId.equals("null") ? JSONObject.NULL : this.subWalletId);	
		}
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
		if(transactionAmount.contentEquals("Auto")) {
			this.transactionAmount = faker.number().numberBetween(0, 99999)+"";
		}		
		if(!transactionAmount.toLowerCase().equals("false")) {
			jsonObject.put("transactionAmount", transactionAmount.equals("null") ? JSONObject.NULL : this.transactionAmount);	
		}
	}
	public void setReciver(String receiver) {
		this.receiver = receiver;
		if(receiver.contentEquals("Auto")) {
			this.receiver = "Suresh";
		}		
		if(!receiver.toLowerCase().equals("false")) {
			jsonObject.put("receiver", receiver.equals("null") ? JSONObject.NULL : this.receiver);	
		}
	}
	public void setReceiverAccountType(String receiverAccountType) {
		this.receiverAccountType = receiverAccountType;
		if(receiverAccountType.contentEquals("Auto")) {
			this.receiverAccountType = "Wallet";
		}		
		if(!receiverAccountType.toLowerCase().equals("false")) {
			jsonObject.put("receiverAccountType", receiverAccountType.equals("null") ? JSONObject.NULL : this.receiverAccountType);	
		}
	}	
	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
		if(receiverAccount.contentEquals("Auto")) {
			this.receiverAccount = faker.number().randomNumber(12, true)+"";
		}		
		if(!receiverAccount.toLowerCase().equals("false")) {
			jsonObject.put("receiverAccount", receiverAccount.equals("null") ? JSONObject.NULL : this.receiverAccount);	
		}
	}
	public void setUnloadCurrency(String unloadCurrency) {
		this.unloadCurrency = unloadCurrency;
		if(unloadCurrency.contentEquals("Auto")) {
			this.unloadCurrency = "INR";
		}		
		if(!unloadCurrency.toLowerCase().equals("false")) {
			jsonObject.put("unloadCurrency", unloadCurrency.equals("null") ? JSONObject.NULL : this.unloadCurrency);	
		}
	}
	public void setOriginalClientTxnId(String originalClientTxnId) {
		this.originalClientTxnId = originalClientTxnId;
		if (originalClientTxnId.contentEquals("Auto")) {
			this.originalClientTxnId = faker.number().randomNumber(20, true) + "";
		}
		if (!originalClientTxnId.toLowerCase().equals("false")) {
			jsonObject.put("originalClientTxnId", originalClientTxnId.equals("null") ? JSONObject.NULL : this.originalClientTxnId);
		}
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
		if(sourceType.contentEquals("Auto")) {
			this.sourceType = "0";
		}		
		if(!sourceType.toLowerCase().equals("false")) {
			jsonObject.put("sourceType", sourceType.equals("null") ? JSONObject.NULL : this.sourceType);	
		}
	}
	public void setImplId(String implId) {
		this.implId = implId;
		if (implId.contentEquals("Auto")) {
			this.implId = "IR|70|700000000";
		}
		if (!implId.toLowerCase().equals("false")) {
			jsonObject.put("implId", implId.equals("null") ? JSONObject.NULL : this.implId);
		}
	}
	public void setImplType(String implType) {
		this.implType = implType;
		if (implType.contentEquals("Auto")) {
			this.implType = "P2M_W2A_O";
		}
		if (!implType.toLowerCase().equals("false")) {
			jsonObject.put("implType", implType.equals("null") ? JSONObject.NULL : this.implType);
		}
	}
	public void setRefundFileId(String refundFileId) {
		this.refundFileId = refundFileId;
		if (refundFileId.contentEquals("Auto")) {
			this.refundFileId = faker.number().randomNumber(20, true) + ".csv";
		}
		if (!refundFileId.toLowerCase().equals("false")) {
			jsonObject.put("refundFileId", refundFileId.equals("null") ? JSONObject.NULL : this.refundFileId);
		}
	}
	public void setFundFlowType(String fundFlowType) {
		this.fundFlowType = fundFlowType;
		if (fundFlowType.contentEquals("Auto")) {
			this.fundFlowType = Constants.fundFlowType[faker.number().numberBetween(0, Constants.fundFlowType.length)];
		}
		if (!fundFlowType.toLowerCase().equals("false")) {
			jsonObject.put("fundFlowType", fundFlowType.equals("null") ? JSONObject.NULL : this.fundFlowType);
		}
	}
	public void setPgNo(String pgNo) {
		this.pgNo = pgNo;
		if(pgNo.contentEquals("Auto")) {
			this.pgNo = "Pg-1";
		}		
		if(!pgNo.toLowerCase().equals("false")) {
			jsonObject.put("pgNo", pgNo.equals("null") ? JSONObject.NULL : this.pgNo);	
		}	
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
		if(orderId.contentEquals("Auto")) {
			this.orderId = faker.number().randomNumber(10,true)+"";
		}		
		if(!orderId.toLowerCase().equals("false")) {
			jsonObject.put("orderId", orderId.equals("null") ? JSONObject.NULL : this.orderId);	
		}	
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
		if(beneficiaryId.contentEquals("Auto")) {
			this.beneficiaryId = faker.number().randomNumber(10,true)+"";
		}		
		if(!beneficiaryId.toLowerCase().equals("false")) {
			jsonObject.put("beneficiaryId", beneficiaryId.equals("null") ? JSONObject.NULL : this.beneficiaryId);	
		}	
	}
	public void setFee(String fee) {
		this.fee = fee;
		if (fee.contentEquals("Auto")) {
			this.fee = faker.number().randomNumber(3, true) + "";
		}
		if (!fee.toLowerCase().equals("false")) {
			jsonObject.put("fee", fee.equals("null") ? JSONObject.NULL : this.fee);
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
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		if(accountNumber.contentEquals("Auto")) {
			this.accountNumber = faker.number().randomNumber(16,true)+"";
		}		
		if(!accountNumber.toLowerCase().equals("false")) {
			jsonObject.put("accountNumber", accountNumber.equals("null") ? JSONObject.NULL : this.accountNumber);	
		}	
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
		if(mcc.contentEquals("Auto")) {
			this.mcc = "6011";
		}		
		if(!mcc.toLowerCase().equals("false")) {
			jsonObject.put("mcc", mcc.equals("null") ? JSONObject.NULL : this.mcc);	
		}	
	}
	public void setAcceptorNameLocation(String acceptorNameLocation) {
		this.acceptorNameLocation = acceptorNameLocation;
		if(acceptorNameLocation.contentEquals("Auto")) {
			this.acceptorNameLocation = "Mumbai";
		}		
		if(!acceptorNameLocation.toLowerCase().equals("false")) {
			jsonObject.put("acceptorNameLocation", acceptorNameLocation.equals("null") ? JSONObject.NULL : this.acceptorNameLocation);	
		}	
	}
	public void setAcceptorMerchantId(String acceptorMerchantId) {
		this.acceptorMerchantId = acceptorMerchantId;
		if(acceptorMerchantId.contentEquals("Auto")) {
			this.acceptorMerchantId = "CDE5678";
		}		
		if(!acceptorMerchantId.toLowerCase().equals("false")) {
			jsonObject.put("acceptorMerchantId", acceptorMerchantId.equals("null") ? JSONObject.NULL : this.acceptorMerchantId);	
		}	
	}
	public void setAcceptorTerminalId(String acceptorTerminalId) {
		this.acceptorTerminalId = acceptorTerminalId;
		if(acceptorTerminalId.contentEquals("Auto")) {
			this.acceptorTerminalId = "CDE5678";
		}		
		if(!acceptorTerminalId.toLowerCase().equals("false")) {
			jsonObject.put("acceptorTerminalId", acceptorTerminalId.equals("null") ? JSONObject.NULL : this.acceptorTerminalId);	
		}	
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
		if(remarks.contentEquals("Auto")) {
			this.remarks = "Unloading the card";
		}		
		if(!remarks.toLowerCase().equals("false")) {
			jsonObject.put("remarks", remarks.equals("null") ? JSONObject.NULL : this.remarks);	
		}	
	}
	
	public String encryptRequestBody() {
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;		
	}
	
	public String unloadRequestBody(String token) {
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
