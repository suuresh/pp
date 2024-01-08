package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.api.endpoints.Constants;
import com.ppi.utilities.GenericMethods;

public class LoadRequest {
	public String messageCode;
	public String clientTxnId;
	public String requestDateTime;
	public String urn;
	public String transactionAmount;
	public String sender;
	public String reciver;
	public String loadCurrency;
	public String sourceType;
	public String account_number;
	public String subWalletId;
	public String sourceAccount;
	public String originalClientTxnId;
	public String implId;
	public String implType;
	public String refundFileId;
	public String fundFlowType;
	public String fee;
	public String customerMobile;
	public String customerId;
	public String pgNo;
	public String orderId;
	public String mcc;
	public String acceptorNameLocation;
	public String acceptorMerchantId;
	public String acceptorTerminalId;
	public String remarks;
	public boolean refundOriginalFee;
	public String refundOriginalFeeAmount;
	
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
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public String getSender() {
		return sender;
	}
	public String getReciver() {
		return reciver;
	}
	public String getLoadCurrency() {
		return loadCurrency;
	}
	public String getSourceType() {
		return sourceType;
	}
	public String getAccount_number() {
		return account_number;
	}
	public String getSubWalletId() {
		return subWalletId;
	}
	public String getSourceAccount() {
		return sourceAccount;
	}
	public String getOriginalClientTxnId() {
		return originalClientTxnId;
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
	public String getFee() {
		return fee;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getPgNo() {
		return pgNo;
	}
	public String getOrderId() {
		return orderId;
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
	public boolean isRefundOriginalFee() {
		return refundOriginalFee;
	}
	public String getRefundOriginalFeeAmount() {
		return refundOriginalFeeAmount;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1080";
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
	
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
		if(transactionAmount.contentEquals("Auto")) {
			this.transactionAmount = faker.number().numberBetween(0, 99999)+"";
		}		
		if(!transactionAmount.toLowerCase().equals("false")) {
			jsonObject.put("transactionAmount", transactionAmount.equals("null") ? JSONObject.NULL : this.transactionAmount);	
		}
	}
	
	public void setSender(String sender) {
		this.sender = sender;
		if(transactionAmount.contentEquals("Auto")) {
			this.sender = "Amazon";
		}		
		if(!sender.toLowerCase().equals("false")) {
			jsonObject.put("sender", sender.equals("null") ? JSONObject.NULL : this.sender);	
		}
	}
	
	public void setReciver(String reciver) {
		this.reciver = reciver;
		if(reciver.contentEquals("Auto")) {
			this.reciver = "Suresh";
		}		
		if(!reciver.toLowerCase().equals("false")) {
			jsonObject.put("reciver", reciver.equals("null") ? JSONObject.NULL : this.reciver);	
		}
	}
	
	public void setLoadCurrency(String loadCurrency) {
		this.loadCurrency = loadCurrency;
		if(loadCurrency.contentEquals("Auto")) {
			this.loadCurrency = "INR";
		}		
		if(!loadCurrency.toLowerCase().equals("false")) {
			jsonObject.put("loadCurrency", loadCurrency.equals("null") ? JSONObject.NULL : this.loadCurrency);	
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
	
	public void setAccountNumber(String account_number) {
		this.account_number = account_number;
		if (account_number.contentEquals("Auto")) {
			this.account_number = faker.number().randomNumber(15, true) + "";
		}
		if (!account_number.toLowerCase().equals("false")) {
			jsonObject.put("account_number", account_number.equals("null") ? JSONObject.NULL : this.account_number);
		}
	}
	
	public void setSubWalletId(String subWalletId) {
		this.subWalletId = subWalletId;
		if (subWalletId.contentEquals("Auto")) {
			this.subWalletId = faker.number().randomNumber(15, true) + "";
		}
		if (!subWalletId.toLowerCase().equals("false")) {
			jsonObject.put("subWalletId", subWalletId.equals("null") ? JSONObject.NULL : this.subWalletId);
		}
	}
	
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
		if (sourceAccount.contentEquals("Auto")) {
			this.sourceAccount = faker.number().randomNumber(15, true) + "";
		}
		if (!sourceAccount.toLowerCase().equals("false")) {
			jsonObject.put("sourceAccount", sourceAccount.equals("null") ? JSONObject.NULL : this.sourceAccount);
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
	
	public void setPgNo(String pgNo) {
		this.pgNo = pgNo;
		if(customerId.contentEquals("Auto")) {
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
			this.remarks = "Loading the card";
		}		
		if(!remarks.toLowerCase().equals("false")) {
			jsonObject.put("remarks", remarks.equals("null") ? JSONObject.NULL : this.remarks);	
		}	
	}
	
	public void setRefundOriginalFee(String refundOriginalFee) {
		this.refundOriginalFee = refundOriginalFee.toLowerCase().equals("true")? true : false;
		if(refundOriginalFee.contentEquals("Auto")) {
			this.refundOriginalFee = false;
		}		
		if(!refundOriginalFee.toLowerCase().equals("false")) {
			jsonObject.put("refundOriginalFee", refundOriginalFee.equals("null") ? JSONObject.NULL : this.refundOriginalFee);	
		}
	}
	
	public void setRefundOriginalFeeAmount(String refundOriginalFeeAmount) {
		this.refundOriginalFeeAmount = refundOriginalFeeAmount;
		if(refundOriginalFeeAmount.contentEquals("Auto")) {
			this.refundOriginalFeeAmount = "1000";
		}		
		if(!refundOriginalFeeAmount.toLowerCase().equals("false")) {
			jsonObject.put("refundOriginalFeeAmount", refundOriginalFeeAmount.equals("null") ? JSONObject.NULL : this.refundOriginalFeeAmount);	
		}	
	}
	
	public String encryptRequestBody() {
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;		
	}
	
	public String loadRequestBody(String token) {
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
