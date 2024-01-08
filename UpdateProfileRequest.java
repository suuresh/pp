package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.api.endpoints.Constants;
import com.ppi.utilities.GenericMethods;

public class UpdateProfileRequest {
	private String messageCode;
	private String clientTxnId;
	private String requestDateTime;
	private String customerId;
	private String urn;
	private String productId;
	private String customerMobile;
	private String cardProfileId;
	private String sorCustomerId;
	private String newCardProfileId;
	private String profileIdChangeDate;
	private String docType;
	private String docCountry;
	private String docExpiry;
	private String docNumber;
	private String cKycDocNo;
	private String docConsent;
	private String kycDateStamp;
	private String updateVectors;
	private String updateDate;
	private String addressline1;
	private String addressline2;
	private String addressline3;
	private String cardholderAddress;
	private String cardholderCity;
	private String cardholderState;
	private String cardholderCountry;
	private String cardholderZipCode;
	private String firstName;
	private String lastName;
	private String email;
	private String dateOfBirth;
	private String gender;
	private String alternateEmailId;
	private boolean sorDisable;
	
	@SuppressWarnings("unused")
	private JSONObject docList[];
	private Document docs[];
	
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
	public String getCustomerId() {
		return customerId;
	}
	public String getUrn() {
		return urn;
	}
	public String getProductId() {
		return productId;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public String getCardProfileId() {
		return cardProfileId;
	}
	public String getSorCustomerId() {
		return sorCustomerId;
	}
	public String getNewCardProfileId() {
		return newCardProfileId;
	}
	public String getProfileIdChangeDate() {
		return profileIdChangeDate;
	}
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
	public String getcKycDocNo() {
		return cKycDocNo;
	}
	public String getDocConsent() {
		return docConsent;
	}
	public String getKycDateStamp() {
		return kycDateStamp;
	}
	public String getUpdateVectors() {
		return updateVectors;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public String getAddressline3() {
		return addressline3;
	}
	public String getCardholderAddress() {
		return cardholderAddress;
	}
	public String getCardholderCity() {
		return cardholderCity;
	}
	public String getCardholderState() {
		return cardholderState;
	}
	public String getCardholderCountry() {
		return cardholderCountry;
	}
	public String getCardholderZipCode() {
		return cardholderZipCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public boolean getSorDisable() {
		return sorDisable;
	}
	
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1280";
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
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
		if(customerId.contentEquals("Auto")) {
			this.customerId = faker.number().randomNumber(15,true)+"";
		}		
		if(!customerId.toLowerCase().equals("false")) {
			jsonObject.put("customerId", customerId.equals("null") ? JSONObject.NULL : this.customerId);	
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
	
	public void setProductId(String productId) {
		this.productId = productId;
		if(productId.contentEquals("Auto")) {
			this.productId = "1";
		}		
		if(!productId.toLowerCase().equals("false")) {
			jsonObject.put("productId", productId.equals("null") ? JSONObject.NULL : this.productId);	
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
	
	public void setCardProfileId(String cardProfileId) {
		this.cardProfileId = cardProfileId;
		if(cardProfileId.contentEquals("Auto")) {
			this.cardProfileId = Constants.cardProfileIds[faker.number().numberBetween(0, Constants.cardProfileIds.length)];
		}		
		if(!cardProfileId.toLowerCase().equals("false")) {
			jsonObject.put("cardProfileId", cardProfileId.equals("null") ? JSONObject.NULL : this.cardProfileId);	
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
	
	public void setNewCardProfileId(String newCardProfileId) {
		this.newCardProfileId = newCardProfileId;
		if(newCardProfileId.contentEquals("Auto")) {
			this.newCardProfileId = Constants.cardProfileIds[faker.number().numberBetween(0, Constants.cardProfileIds.length)];
		}		
		if(!newCardProfileId.toLowerCase().equals("false")) {
			jsonObject.put("newCardProfileId", newCardProfileId.equals("null") ? JSONObject.NULL : this.newCardProfileId);	
		}
	}
	
	public void setProfileIdChangeDate(String profileIdChangeDate) {
		this.profileIdChangeDate = profileIdChangeDate;
		if(profileIdChangeDate.contentEquals("Auto")) {
			this.profileIdChangeDate = GenericMethods.getCurrentDateTime("yyyyMMddHHmmss");
		}		
		if(!profileIdChangeDate.toLowerCase().equals("false")) {
			jsonObject.put("profileIdChangeDate", profileIdChangeDate.equals("null") ? JSONObject.NULL : this.profileIdChangeDate);	
		}
	}
	
	public void setDocType(String docType) {
		this.docType = docType;
		String[] dt =  docType.split(",");
		docList = new JSONObject[dt.length];
		docs = new Document[dt.length];
		for(int i=0;i<dt.length;i++) {
			Document d = new Document();
			d.setDocType(dt[i]);
			docs[i] = d;
		}
	}
	
	public void setDocCountry(String docCountry) {
		this.docCountry = docCountry;
		/*
		 * this.docCountry = docCountry; if(docCountry.contentEquals("Auto")) {
		 * this.docCountry = "India"; } if(!docCountry.toLowerCase().equals("false")) {
		 * jsonObject.put("docCountry", docCountry.equals("null") ? JSONObject.NULL :
		 * this.docCountry); }
		 */
		String[] dc =  docCountry.split(",");		
		for(int i=0;i<dc.length;i++) {			
			docs[i].setDocCountry(dc[i]);
		}
	}
	
	public void setDocExpiry(String docExpiry) {
		this.docExpiry = docExpiry;
		/*
		 * this.docExpiry = docExpiry; if(docExpiry.contentEquals("Auto")) {
		 * this.docExpiry = GenericMethods.getCurrentDateTime("yyyy")+"1231"; }
		 * if(!docExpiry.toLowerCase().equals("false")) { jsonObject.put("docExpiry",
		 * docExpiry.equals("null") ? JSONObject.NULL : this.docExpiry); }
		 */
		String[] de =  docExpiry.split(",");		
		for(int i=0;i<de.length;i++) {			
			docs[i].setDocExpiry(de[i]);
		}
	}
	
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
		/*
		 * this.docNumber = docNumber; if(docNumber.contentEquals("Auto")) {
		 * this.docNumber = faker.number().randomNumber(12, true)+""; }
		 * if(!docNumber.toLowerCase().equals("false")) { jsonObject.put("docNumber",
		 * docNumber.equals("null") ? JSONObject.NULL : this.docNumber); }
		 */
		String[] dn =  docNumber.split(",");		
		for(int i=0;i<dn.length;i++) {			
			docs[i].setDocNumber(dn[i]);
		}
	}
	
	public void setDocList() {
		if ((docType.contentEquals("false") && docCountry.contentEquals("false") && docExpiry.contentEquals("false")
				&& docNumber.contentEquals("false"))
				|| (docType.contentEquals("") && docCountry.contentEquals("") && docExpiry.contentEquals("")
						&& docNumber.contentEquals(""))) {
			System.out.println("Remove doc list");
		} else if (this.docs.length > 0) {
			JSONObject obj[] = new JSONObject[docs.length];
			for (int i = 0; i < docs.length; i++) {
				obj[i] = docs[i].getJsonobject();
			}
			jsonObject.put("docList", obj);
		}
	}
	
	public void setcKycDocNo(String cKycDocNo) {
		this.cKycDocNo = cKycDocNo;
		if(cKycDocNo.contentEquals("Auto")) {
			this.cKycDocNo = faker.number().digits(5);
		}		
		if(!cKycDocNo.toLowerCase().equals("false")) {
			jsonObject.put("cKycDocNo", cKycDocNo.equals("null") ? JSONObject.NULL : this.cKycDocNo);	
		}
	}
	
	public void setDocConsent(String docConsent) {
		this.docConsent = docConsent;
		if(docConsent.contentEquals("Auto")) {
			this.docConsent = "TRUE";
		}		
		if(!docConsent.toLowerCase().equals("false")) {
			jsonObject.put("docConsent", docConsent.equals("null") ? JSONObject.NULL : this.docConsent);	
		}
	}
	
	public void setKycDateStamp(String kycDateStamp) {
		this.kycDateStamp = kycDateStamp;
		if(kycDateStamp.contentEquals("Auto")) {
			this.kycDateStamp = GenericMethods.getCurrentDateTime("yyyyMMddHHmmss");
		}		
		if(!kycDateStamp.toLowerCase().equals("false")) {
			jsonObject.put("kycDateStamp", kycDateStamp.equals("null") ? JSONObject.NULL : this.kycDateStamp);	
		}
	}
	
	public void setUpdateVectors(String updateVectors) {
		this.updateVectors = updateVectors;
		if(updateVectors.contentEquals("Auto")) {
			this.updateVectors = "address";
		}		
		if(!updateVectors.toLowerCase().equals("false")) {
			jsonObject.put("updateVectors", updateVectors.equals("null") ? JSONObject.NULL : this.updateVectors);	
		}
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
		if(updateDate.contentEquals("Auto")) {
			this.updateDate = GenericMethods.getCurrentDateTime("yyyyMMddHHmmss");
		}		
		if(!updateDate.toLowerCase().equals("false")) {
			jsonObject.put("updateDate", updateDate.equals("null") ? JSONObject.NULL : this.updateDate);	
		}
	}
	
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
		if(addressline1.contentEquals("Auto")) {
			this.addressline1 = faker.address().buildingNumber();
		}		
		if(!addressline1.toLowerCase().equals("false")) {
			jsonObject.put("addressline1", addressline1.equals("null") ? JSONObject.NULL : this.addressline1);	
		}
	}
	
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
		if(addressline2.contentEquals("Auto")) {
			this.addressline2 = faker.address().streetAddress();
		}		
		if(!addressline2.toLowerCase().equals("false")) {
			jsonObject.put("addressline2", addressline2.equals("null") ? JSONObject.NULL : this.addressline2);	
		}
	}
	
	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
		if(addressline3.contentEquals("Auto")) {
			this.addressline3 = faker.address().streetAddress();
		}		
		if(!addressline3.toLowerCase().equals("false")) {
			jsonObject.put("addressline3", addressline3.equals("null") ? JSONObject.NULL : this.addressline3);	
		}
	}
	
	public void setCardholderAddress(String cardholderAddress) {
		this.cardholderAddress = cardholderAddress;
		if(cardholderAddress.contentEquals("Auto")) {
			this.cardholderAddress = faker.address().fullAddress();
		}		
		if(!cardholderAddress.toLowerCase().equals("false")) {
			jsonObject.put("cardholderAddress", cardholderAddress.equals("null") ? JSONObject.NULL : this.cardholderAddress);	
		}
	}
	
	public void setCardholderCity(String cardholderCity) {
		this.cardholderCity = cardholderCity;
		if(cardholderCity.contentEquals("Auto")) {
			this.cardholderCity = faker.address().cityName();
		}		
		if(!cardholderCity.toLowerCase().equals("false")) {
			jsonObject.put("cardholderCity", cardholderCity.equals("null") ? JSONObject.NULL : this.cardholderCity);	
		}		
	}
	
	public void setCardholderState(String cardholderState) {
		this.cardholderState = cardholderState;
		if(cardholderState.contentEquals("Auto")) {
			this.cardholderState = faker.address().state();
		}		
		if(!cardholderState.toLowerCase().equals("false")) {
			jsonObject.put("cardholderState", cardholderState.equals("null") ? JSONObject.NULL : this.cardholderState);	
		}
	}
	
	public void setCardholderCountry(String cardholderCountry) {
		this.cardholderCountry = cardholderCountry;
		if(cardholderCountry.contentEquals("Auto")) {
			this.cardholderCountry = faker.address().country();
		}		
		if(!cardholderCountry.toLowerCase().equals("false")) {
			jsonObject.put("cardholderCountry", cardholderCountry.equals("null") ? JSONObject.NULL : this.cardholderCountry);	
		}
	}
	
	public void setCardholderZipCode(String cardholderZipCode) {
		this.cardholderZipCode = cardholderZipCode;
		if(cardholderZipCode.contentEquals("Auto")) {
			this.cardholderZipCode = faker.address().zipCode();
		}		
		if(!cardholderZipCode.toLowerCase().equals("false")) {
			jsonObject.put("cardholderZipCode", cardholderZipCode.equals("null") ? JSONObject.NULL : this.cardholderZipCode);	
		}		
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		if(firstName.contentEquals("Auto")) {
			this.firstName = faker.name().firstName();
		}		
		if(!firstName.toLowerCase().equals("false")) {
			jsonObject.put("firstName", firstName.equals("null") ? JSONObject.NULL : this.firstName);	
		}
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		if(lastName.contentEquals("Auto")) {
			this.lastName = faker.name().lastName();
		}		
		if(!lastName.toLowerCase().equals("false")) {
			jsonObject.put("lastName", lastName.equals("null") ? JSONObject.NULL : this.lastName);	
		}
	}
	
	public void setEmail(String email) {
		this.email = email;
		if(email.contentEquals("Auto")) {
			this.email = faker.internet().emailAddress();
		}		
		if(!email.toLowerCase().equals("false")) {
			jsonObject.put("email", email.equals("null") ? JSONObject.NULL : this.email);	
		}
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		if(dateOfBirth.contentEquals("Auto")) {
			this.dateOfBirth = GenericMethods.getCurrentDateTime("dd-MM-yyyy");
		}		
		if(!dateOfBirth.toLowerCase().equals("false")) {
			jsonObject.put("dateOfBirth", dateOfBirth.equals("null") ? JSONObject.NULL : this.dateOfBirth);	
		}
	}
	
	public void setGender(String gender) {
		this.gender = gender;
		if(gender.contentEquals("Auto")) {
			this.gender = "Male";
		}		
		if(!gender.toLowerCase().equals("false")) {
			jsonObject.put("gender", gender.equals("null") ? JSONObject.NULL : this.gender);	
		}
	}
	
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
		if(alternateEmailId.contentEquals("Auto")) {
			this.alternateEmailId = faker.internet().emailAddress();
		}		
		if(!alternateEmailId.toLowerCase().equals("false")) {
			jsonObject.put("alternateEmailId", alternateEmailId.equals("null") ? JSONObject.NULL : this.alternateEmailId);	
		}
	}
	
	public void setSorDisable(String sorDisable) {
		this.sorDisable = sorDisable.toLowerCase().equals("true")? true : false;
		if(sorDisable.contentEquals("Auto")) {
			this.sorDisable = false;
		}		
		if(!sorDisable.toLowerCase().equals("false")) {
			jsonObject.put("sorDisable", sorDisable.equals("null") ? JSONObject.NULL : this.sorDisable);	
		}
	}
	
	public String encryptRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	public String updateProfileRequestBody(String token) {
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
