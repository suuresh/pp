package com.ppi.api.payloads.mone;

import org.json.JSONObject;
import com.github.javafaker.Faker;
import com.ppi.api.endpoints.Constants;
import com.ppi.utilities.GenericMethods;

public class CreateCard {
	
	private String messageCode;	// M
	private String productId;// M
	private String clientTxnId;	//M min 12 digit
	private String requestDateTime; //M current date time
	private String firstName; // M
	private String lastName;	// M  random 4 digit number
	private String mobile;	//M 60 + 10 digit random number
	private String customerId;	//M 15 digit alpha numeric
	private String email;	// O random 6 digit alphabets with @wibmo.com
	private String dateOfBirth; // M
	private String cardProfileId; // M
	private Integer sourceAccountType; // O
	private String newCardStatus; // M
	private String loadAmount; // M
	private String eventid; // M
	
	private String sorCustomerId; 
	private String kitNo;
	private String gender;
	private String docType;
	private String docCountry;
	private String docExpiry;
	private String docNumber; 
	private Integer cKycDocNo;
	private boolean docConsent;
	private Long kycDateStamp;	
	private String sourceAccount; 
	private String addressline1;
	private String addressline2;
	private String addressline3;
	private String deliveryAddress;
	private String cardholderCity;	
	private String cardholderState; 
	private String cardholderCountry; 
	private String cardholderZipCode; 
	private boolean createQR;
	
	
	public JSONObject jsonObject = new JSONObject();
	public Faker faker = new Faker();
	public GenericMethods generic = new GenericMethods();		
	
	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
		if(messageCode.contentEquals("Auto")) {
			this.messageCode = "1010";
		}
		if(!messageCode.toLowerCase().equals("false")) {
			jsonObject.put("messageCode", messageCode.equals("null") ? JSONObject.NULL : this.messageCode);
		}		
	}

	public String getProductId() {
		return productId;
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

	public String getClientTxnId() {
		return clientTxnId;
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

	public String getRequestDateTime() {
		return requestDateTime;
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

	public String getFirstName() {
		return firstName;
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

	public String getLastName() {
		return lastName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
		if(mobile.contentEquals("Auto")) {
			this.mobile = "60" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!mobile.toLowerCase().equals("false")) {
			jsonObject.put("mobile", mobile.equals("null") ? JSONObject.NULL : this.mobile);	
		}
	}

	public String getCustomerId() {
		return customerId;
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

	public String getEmail() {
		return email;
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

	public String getDateOfBirth() {
		return dateOfBirth;
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

	public String getCardProfileId() {
		return cardProfileId;
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

	public Integer getSourceAccountType() {
		return sourceAccountType;
	}

	public void setSourceAccountType(String sourceAccountType) {
		this.sourceAccountType = Integer.parseInt(sourceAccountType);

		if (sourceAccountType.contentEquals("Auto")) {
			this.sourceAccountType = 11;
		}
		if (!sourceAccountType.toLowerCase().equals("false")) {
			jsonObject.put("sourceAccountType",
					sourceAccountType.equals("null") ? JSONObject.NULL : this.sourceAccountType);
		}
		 
		
	}

	public String getNewCardStatus() {
		return newCardStatus;
	}

	public void setNewCardStatus(String newCardStatus) {
		this.newCardStatus = newCardStatus;
		if(newCardStatus.contentEquals("Auto")) {
			this.newCardStatus = "Active";
		}		
		if(!newCardStatus.toLowerCase().equals("false")) {
			jsonObject.put("newCardStatus", newCardStatus.equals("null") ? JSONObject.NULL : this.newCardStatus);	
		}
	}

	public String getLoadAmount() {
		return loadAmount;
	}

	public void setLoadAmount(String loadAmount) {
		this.loadAmount = loadAmount;
		if(loadAmount.contentEquals("Auto")) {
			this.loadAmount = faker.number().numberBetween(0, 99999)+"";
		}		
		if(!loadAmount.toLowerCase().equals("false")) {
			jsonObject.put("loadAmount", loadAmount.equals("null") ? JSONObject.NULL : this.loadAmount);	
		}
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
		if(eventid.contentEquals("Auto")) {
			this.eventid = "303001";
		}		
		if(!eventid.toLowerCase().equals("false")) {
			jsonObject.put("eventid", eventid.equals("null") ? JSONObject.NULL : this.eventid);	
		}
	}

	public String getSorCustomerId() {
		return sorCustomerId;
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

	public String getKitNo() {
		return kitNo;
	}

	public void setKitNo(String kitNo) {
		this.kitNo = kitNo;
		if(kitNo.contentEquals("Auto")) {
			this.kitNo = faker.number().randomNumber(7, true)+"";
		}		
		if(!kitNo.toLowerCase().equals("false")) {
			jsonObject.put("kitNo", kitNo.equals("null") ? JSONObject.NULL : this.kitNo);	
		}
	}

	public String getGender() {
		return gender;
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

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
		if(docType.contentEquals("Auto")) {
			this.docType = "NA";
		}		
		if(!docType.toLowerCase().equals("false")) {
			jsonObject.put("docType", docType.equals("null") ? JSONObject.NULL : this.docType);	
		}
	}

	public String getDocCountry() {
		return docCountry;
	}

	public void setDocCountry(String docCountry) {
		this.docCountry = docCountry;
		if(docCountry.contentEquals("Auto")) {
			this.docCountry = "India";
		}		
		if(!docCountry.toLowerCase().equals("false")) {
			jsonObject.put("docCountry", docCountry.equals("null") ? JSONObject.NULL : this.docCountry);	
		}
	}

	public String getDocExpiry() {
		return docExpiry;
	}

	public void setDocExpiry(String docExpiry) {
		this.docExpiry = docExpiry;
		if(docExpiry.contentEquals("Auto")) {
			this.docExpiry = GenericMethods.getCurrentDateTime("yyyy")+"1231";
		}		
		if(!docExpiry.toLowerCase().equals("false")) {
			jsonObject.put("docExpiry", docExpiry.equals("null") ? JSONObject.NULL : this.docExpiry);	
		}
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
		if(docNumber.contentEquals("Auto")) {
			this.docNumber = faker.number().randomNumber(12, true)+"";
		}		
		if(!docNumber.toLowerCase().equals("false")) {
			jsonObject.put("docNumber", docNumber.equals("null") ? JSONObject.NULL : this.docNumber);	
		}
	}

	public Integer getcKycDocNo() {
		return cKycDocNo;
	}

	public void setcKycDocNo(String cKycDocNo) {
		this.cKycDocNo = Integer.parseInt(cKycDocNo);
		if(cKycDocNo.contentEquals("Auto")) {
			this.cKycDocNo = Integer.parseInt(faker.number().digits(5));
		}		
		if(!cKycDocNo.toLowerCase().equals("false")) {
			jsonObject.put("cKycDocNo", cKycDocNo.equals("null") ? JSONObject.NULL : this.cKycDocNo);	
		}
	}

	public boolean isDocConsent() {
		return docConsent;
	}

	public void setDocConsent(String docConsent) {
		this.docConsent = docConsent.toLowerCase().equals("true")? true : false;
		if(docConsent.contentEquals("Auto")) {
			this.docConsent = false;
		}		
		if(!docConsent.toLowerCase().equals("false")) {
			jsonObject.put("docConsent", docConsent.equals("null") ? JSONObject.NULL : this.docConsent);	
		}
	}

	public Long getKycDateStamp() {
		return kycDateStamp;
	}

	public void setKycDateStamp(String kycDateStamp) {
		this.kycDateStamp = Long.parseLong(kycDateStamp);
		if(kycDateStamp.contentEquals("Auto")) {
			this.kycDateStamp = Long.parseLong(GenericMethods.getCurrentDateTime("yyyyMMddHHmmss"));
		}		
		if(!kycDateStamp.toLowerCase().equals("false")) {
			jsonObject.put("kycDateStamp", kycDateStamp.equals("null") ? JSONObject.NULL : this.kycDateStamp);	
		}
	}

	public String getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
		if(sourceAccount.contentEquals("Auto")) {
			this.sourceAccount = faker.number().randomNumber(10, true)+"";
		}		
		if(!sourceAccount.toLowerCase().equals("false")) {
			jsonObject.put("sourceAccount", sourceAccount.equals("null") ? JSONObject.NULL : this.sourceAccount);	
		}	
	}

	public String getAddressline1() {
		return addressline1;
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

	public String getAddressline2() {
		return addressline2;
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

	public String getAddressline3() {
		return addressline3;
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

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
		if(deliveryAddress.contentEquals("Auto")) {
			this.deliveryAddress = faker.address().fullAddress();
		}		
		if(!deliveryAddress.toLowerCase().equals("false")) {
			jsonObject.put("deliveryAddress", deliveryAddress.equals("null") ? JSONObject.NULL : this.deliveryAddress);	
		}
	}

	public String getCardholderCity() {
		return cardholderCity;
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

	public String getCardholderState() {
		return cardholderState;
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

	public String getCardholderCountry() {
		return cardholderCountry;
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

	public String getCardholderZipCode() {
		return cardholderZipCode;
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

	public boolean isCreateQR() {
		return createQR;
	}

	public void setCreateQR(String createQR) {
		this.createQR = createQR.toLowerCase().equals("true")? true : false;
		if(createQR.contentEquals("Auto")) {
			this.createQR = false;
		}		
		if(!createQR.toLowerCase().equals("false")) {
			jsonObject.put("createQR", createQR.equals("null") ? JSONObject.NULL : this.createQR);	
		}
	}

	public String encryptCreateCardRequestBody() {
		//System.out.println("JsonObject::"+this.jsonObject);
		String jsonString = this.jsonObject.toString();
		//System.out.println(jsonString);
		return jsonString;
		
		/*
		 * String jsonbody = "{" + "    \"messageCode\": \"1010\"," +
		 * "    \"productId\": \"1\"," + "    \"clientTxnId\": \"Sreekumar0000ram228\","
		 * + "    \"requestDateTime\": \"2022061914720\"," +
		 * "    \"firstName\": \"Ram\"," + "    \"lastName\": \"Nandwani\"," +
		 * "    \"mobile\": \"609426221043\"," +
		 * "    \"customerId\": \"62508ram44911\"," +
		 * "    \"email\": \"aashika.singh@wibmo.com\"," +
		 * "    \"dateOfBirth\": \"06-01-2000\"," + "    \"cardProfileId\": \"300\"," +
		 * "    \"sourceAccountType\": 11," + "    \"newCardStatus\": \"Active\"," +
		 * "    \"loadAmount\": \"0\"," + "    \"eventid\": 303001" + "}"; return
		 * jsonbody;
		 */
	}
	
	public static String createCardRequestBody(String token) {
		String jsonbody = "{"
				+ "    \"token\": \""+token+"\""
				+ "}";
		return jsonbody;
	}
	
	public static String decryptRequestBody(String token) {
		String jsonbody = "{"
				+ "    \"token\": \""+token+"\""
				+ "}";
		return jsonbody;
	}
}
