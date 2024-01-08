package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.api.endpoints.Constants;
import com.ppi.utilities.GenericMethods;

public class ActivateCardRequest {
	
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	
	private boolean isStatusCheckRequest;
    private String urn;
    private String last4Digits;
    private String loginId;
    private String loadAmount;
    private int taxAmount;
    private int feeAmount;
    private int sourceAccountType;
    private String customerId;
    private String cardProfileId;
    private JSONObject cardHolder;
    private JSONObject nominee;
    
    private String productId;
    
    public CardHolder ch = new CardHolder();
    public Nominee nomi = new Nominee();
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
	public boolean isStatusCheckRequest() {
		return isStatusCheckRequest;
	}
	public String getUrn() {
		return urn;
	}
	public String getLast4Digits() {
		return last4Digits;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getLoadAmount() {
		return loadAmount;
	}
	public int getTaxAmount() {
		return taxAmount;
	}
	public int getFeeAmount() {
		return feeAmount;
	}
	public int getSourceAccountType() {
		return sourceAccountType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getCardProfileId() {
		return cardProfileId;
	}
	public JSONObject getCardHolder() {
		return cardHolder;
	}
	public JSONObject getNominee() {
		return nominee;
	}	
	public String getProductId() {
		return productId;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "3424";
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
	public void setStatusCheckRequest(String isStatusCheckRequest) {		
		this.isStatusCheckRequest = isStatusCheckRequest.toLowerCase().equals("true")? true : false;
		if(isStatusCheckRequest.contentEquals("Auto")) {
			this.isStatusCheckRequest = false;
		}		
		if(!isStatusCheckRequest.toLowerCase().equals("false")) {
			jsonObject.put("isStatusCheckRequest", isStatusCheckRequest.equals("null") ? JSONObject.NULL : this.isStatusCheckRequest);	
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
	
	public void setLast4Digits(String last4Digits) {
		this.last4Digits = last4Digits;
		if(last4Digits.contentEquals("Auto")) {
			this.last4Digits = faker.number().randomNumber(4, true)+"";
		}		
		if(!last4Digits.toLowerCase().equals("false")) {
			jsonObject.put("last4Digits", last4Digits.equals("null") ? JSONObject.NULL : this.last4Digits);	
		}		
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
		if(loginId.contentEquals("Auto")) {
			this.loginId = "1";
		}		
		if(!loginId.toLowerCase().equals("false")) {
			jsonObject.put("loginId", loginId.equals("null") ? JSONObject.NULL : this.loginId);	
		}
	}
	
	public void setLoadAmount(String loadAmount) {
		this.loadAmount = loadAmount;
		if(loadAmount.contentEquals("Auto")) {
			this.loadAmount = faker.number().randomNumber(5, true)+"";
		}		
		if(!loadAmount.toLowerCase().equals("false")) {
			jsonObject.put("loadAmount", loadAmount.equals("null") ? JSONObject.NULL : this.loadAmount);	
		}
	}
	
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = Integer.parseInt(taxAmount);
		if(taxAmount.contentEquals("Auto")) {
			this.taxAmount = Integer.parseInt(faker.number().randomNumber(2, true)+"");
		}		
		if(!taxAmount.toLowerCase().equals("false")) {
			jsonObject.put("taxAmount", taxAmount.equals("null") ? JSONObject.NULL : this.taxAmount);	
		}
	}
	
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = Integer.parseInt(feeAmount);
		if(feeAmount.contentEquals("Auto")) {
			this.feeAmount = Integer.parseInt(faker.number().randomNumber(3, true)+"");
		}		
		if(!feeAmount.toLowerCase().equals("false")) {
			jsonObject.put("feeAmount", feeAmount.equals("null") ? JSONObject.NULL : this.feeAmount);	
		}
	}
	
	public void setSourceAccountType(String sourceAccountType) {
		this.sourceAccountType = Integer.parseInt(sourceAccountType);
		if(sourceAccountType.contentEquals("Auto")) {
			this.sourceAccountType = Integer.parseInt(faker.number().randomNumber(1, true)+"");
		}		
		if(!sourceAccountType.toLowerCase().equals("false")) {
			jsonObject.put("sourceAccountType", sourceAccountType.equals("null") ? JSONObject.NULL : this.sourceAccountType);	
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
	
	public void setCardProfileId(String cardProfileId) {
		this.cardProfileId = cardProfileId;
		if(cardProfileId.contentEquals("Auto")) {
			this.cardProfileId = Constants.cardProfileIds[faker.number().numberBetween(0, Constants.cardProfileIds.length)];
		}		
		if(!cardProfileId.toLowerCase().equals("false")) {
			jsonObject.put("cardProfileId", cardProfileId.equals("null") ? JSONObject.NULL : this.cardProfileId);	
		}
	}
	
	public void setCardHolder(JSONObject cardHolder, String operation) {
		this.cardHolder = cardHolder;
		if(operation.contentEquals("Auto")) {
			this.cardHolder = ch.getJsonObject();			
		}
		if(!operation.equals("Remove")) {
			jsonObject.put("cardHolder", this.cardHolder);	
		}
	}
	public void setNominee(JSONObject nominee, String operation) {
		this.nominee = nominee;
		if(operation.contentEquals("Auto")) {
			this.nominee = nomi.getJsonObject();			
		}
		if(!operation.equals("Remove")) {
			jsonObject.put("nominee", this.nominee);
		}		
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
		if(productId.contentEquals("Auto")) {
			this.productId = "1";
		}		
		if(!productId.toLowerCase().equals("false")) {
			jsonObject.put("productId", productId.equals("null") ? JSONObject.NULL : this.productId);	
		}
	}
	
	public String encryptActivateCardRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String activateCardRequestBody(String token) {
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
