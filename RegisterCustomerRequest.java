package com.ppi.api.payloads.spicemoney;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.api.endpoints.Constants;

import com.ppi.utilities.GenericMethods;

public class RegisterCustomerRequest {
	private String customerId;
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
    private JSONObject customerDetails;
    private String sorCustomerId;
    private JSONObject addressDetails;
    private JSONObject docList[];
    private String kycProfile;
    private String riskCategory;
    private String riskScore;
    private boolean form60;
    private String productId;
    private boolean formFactorRequired;
    private String initialLoad;
    private String initialLoadAmtCurrency;
    private String reserveField1;
    private String reserveField2;    
    private String ovdType;
    private String ovdExpDate;
    private String ovdNo;
    
    public JSONObject jsonObject = new JSONObject();
	public Faker faker = new Faker();
    
    public Document docs[];
    public CustomerDetails cd = new CustomerDetails();
    public AddressDetails ad = new AddressDetails();


	public JSONObject[] getDocList() {
		return docList;
	}
	public void setDocList(JSONObject[] docList) {
		this.docList = docList;
	}

	public String getCustomerId() {
		return customerId;
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

	public JSONObject getCustomerDetails() {
		return customerDetails;
	}

	public String getSorCustomerId() {
		return sorCustomerId;
	}

	public JSONObject getAddressDetails() {
		return addressDetails;
	}

	public String getKycProfile() {
		return kycProfile;
	}

	public String getRiskCategory() {
		return riskCategory;
	}

	public String getRiskScore() {
		return riskScore;
	}

	public boolean isForm60() {
		return form60;
	}

	public String getProductId() {
		return productId;
	}

	public boolean isFormFactorRequired() {
		return formFactorRequired;
	}

	public String getInitialLoad() {
		return initialLoad;
	}

	public String getInitialLoadAmtCurrency() {
		return initialLoadAmtCurrency;
	}

	public String getReserveField1() {
		return reserveField1;
	}

	public String getReserveField2() {
		return reserveField2;
	}

	public String getOvdType() {
		return ovdType;
	}

	public String getOvdExpDate() {
		return ovdExpDate;
	}

	public String getOvdNo() {
		return ovdNo;
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
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "3510";
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
	
	public void setCustomerDetails(JSONObject customerDetails, String operation) {
		this.customerDetails = customerDetails;
		if(operation.contentEquals("Auto")) {
			this.customerDetails = cd.getJsonObject();			
		}
		if(!operation.equals("Remove")) {
			jsonObject.put("customerDetails", this.customerDetails);	
		}
	}
	
	public void setSorCustomerId(String sorCustomerId) {
		this.sorCustomerId = sorCustomerId;
		if(sorCustomerId.contentEquals("Auto")) {
			this.sorCustomerId = faker.letterify("?????????");
		}		
		if(!sorCustomerId.toLowerCase().equals("false")) {
			jsonObject.put("sorCustomerId", sorCustomerId.equals("null") ? JSONObject.NULL : this.sorCustomerId);	
		}
	}
	
	public void setAddressDetails(JSONObject addressDetails, String operation) {
		this.addressDetails = addressDetails;
		if(operation.contentEquals("Auto")) {
			this.addressDetails = ad.getJsonObject();			
		}
		if(!operation.equals("Remove")) {
			jsonObject.put("addressDetails", this.addressDetails);	
		}
	}
	
	public void setKycProfile(String kycProfile) {
		this.kycProfile = kycProfile;
		if(kycProfile.contentEquals("Auto")) {
			this.kycProfile = Constants.kycProfileIds[faker.number().numberBetween(0, Constants.kycProfileIds.length)];
		}		
		if(!kycProfile.toLowerCase().equals("false")) {
			jsonObject.put("kycProfile", kycProfile.equals("null") ? JSONObject.NULL : this.kycProfile);	
		}
	}
	
	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
		if(kycProfile.contentEquals("Auto")) {
			this.riskCategory = Constants.riskCategories[faker.number().numberBetween(0, Constants.riskCategories.length)];
		}		
		if(!riskCategory.toLowerCase().equals("false")) {
			jsonObject.put("riskCategory", riskCategory.equals("null") ? JSONObject.NULL : this.riskCategory);	
		}
	}
	
	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
		if(riskScore.contentEquals("Auto")) {
			this.riskScore = "50";
		}		
		if(!riskScore.toLowerCase().equals("false")) {
			jsonObject.put("riskScore", riskScore.equals("null") ? JSONObject.NULL : this.riskScore);	
		}
	}
	
	public void setForm60(String form60) {
		this.form60 = form60.toLowerCase().equals("true")? true : false;
		if(form60.contentEquals("Auto")) {
			this.form60 = false;
		}		
		if(!form60.toLowerCase().equals("false")) {
			jsonObject.put("form60", form60.equals("null") ? JSONObject.NULL : this.form60);	
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
	
	public void setFormFactorRequired(String formFactorRequired) {
		this.formFactorRequired = formFactorRequired.toLowerCase().equals("true")? true : false;
		if(formFactorRequired.contentEquals("Auto")) {
			this.formFactorRequired = false;
		}		
		if(!formFactorRequired.toLowerCase().equals("false")) {
			jsonObject.put("formFactorRequired", formFactorRequired.equals("null") ? JSONObject.NULL : this.formFactorRequired);	
		}
	}
	
	public void setInitialLoad(String initialLoad) {
		this.initialLoad = initialLoad;
		if(initialLoad.contentEquals("Auto")) {
			this.initialLoad = "10000";
		}		
		if(!initialLoad.toLowerCase().equals("false")) {
			jsonObject.put("initialLoad", initialLoad.equals("null") ? JSONObject.NULL : this.initialLoad);	
		}
	}
	
	public void setInitialLoadAmtCurrency(String initialLoadAmtCurrency) {
		this.initialLoadAmtCurrency = initialLoadAmtCurrency;
		if(initialLoadAmtCurrency.contentEquals("Auto")) {
			this.initialLoadAmtCurrency = "INR";
		}		
		if(!initialLoadAmtCurrency.toLowerCase().equals("false")) {
			jsonObject.put("initialLoadAmtCurrency", initialLoadAmtCurrency.equals("null") ? JSONObject.NULL : this.initialLoadAmtCurrency);	
		}
	}
	
	public void setReserveField1(String reserveField1) {
		this.reserveField1 = reserveField1;
		if(reserveField1.contentEquals("Auto")) {
			this.reserveField1 = "Reserve Field";
		}		
		if(!reserveField1.toLowerCase().equals("false")) {
			jsonObject.put("reserveField1", reserveField1.equals("null") ? JSONObject.NULL : this.reserveField1);	
		}
	}
	
	public void setReserveField2(String reserveField2) {
		this.reserveField2 = reserveField2;
		if(reserveField2.contentEquals("Auto")) {
			this.reserveField2 = "Reserve Field";
		}		
		if(!reserveField2.toLowerCase().equals("false")) {
			jsonObject.put("reserveField2", reserveField2.equals("null") ? JSONObject.NULL : this.reserveField2);	
		}
	}
	
	public void setOvdType(String ovdType) {
		this.ovdType = ovdType;
		String[] dt =  ovdType.split(",");
		docList = new JSONObject[dt.length];
		docs = new Document[dt.length];
		for(int i=0;i<dt.length;i++) {
			Document d = new Document();
			d.setOvdType(dt[i]);
			docs[i] = d;
		}
	}
	
	public void setOvdExpDate(String ovdExpDate) {
		this.ovdExpDate = ovdExpDate;		
		String[] dc =  ovdExpDate.split(",");		
		for(int i=0;i<dc.length;i++) {			
			docs[i].setOvdExpDate(dc[i]);
		}
	}
	
	public void setOvdNo(String ovdNo) {
		this.ovdNo = ovdNo;		
		String[] dc =  ovdNo.split(",");		
		for(int i=0;i<dc.length;i++) {			
			docs[i].setOvdNo(dc[i]);
		}
	}
	
	public void setDocList() {
		if ((ovdType.contentEquals("false") && ovdExpDate.contentEquals("false") && ovdNo.contentEquals("false"))
				|| (ovdType.contentEquals("") && ovdExpDate.contentEquals("") && ovdNo.contentEquals(""))) {
			System.out.println("Remove doc list");
		} else if (this.docs.length > 0) {
			JSONObject obj[] = new JSONObject[docs.length];
			for (int i = 0; i < docs.length; i++) {
				obj[i] = docs[i].getJsonobject();
			}
			jsonObject.put("docList", obj);
		}
	}
	
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		//System.out.println(jsonString);
		return jsonString;
	}
	
	public String registerCustomerRequestBody(String token) {
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
