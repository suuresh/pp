package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class CardHolder {
	private String cardholderFirstName;
	private String cardholderMiddleName;
	private String cardholderLastName;
	private String cardholderMobile;
	private String cardholderEmail;
	private String cardholderAddress;
	private String cardholderCity;
	private String cardholderState;
	private String cardholderCountry;
	private String cardholderZipCode;
	private String cardholderDateOfBirth;
	private String identityProof;
	private String identityProofNumber;
	private String employeeId;
	private String ovdName;
	private String isPanValid;
	private String ovdType;
	private String ovdNumber;
	
	public JSONObject jsonObject = new JSONObject();
	public Faker faker = new Faker();
		
	public String getCardholderFirstName() {
		return cardholderFirstName;
	}
	public String getCardholderMiddleName() {
		return cardholderMiddleName;
	}
	public String getCardholderLastName() {
		return cardholderLastName;
	}
	public String getCardholderMobile() {
		return cardholderMobile;
	}
	public String getCardholderEmail() {
		return cardholderEmail;
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
	public String getCardholderDateOfBirth() {
		return cardholderDateOfBirth;
	}
	public String getIdentityProof() {
		return identityProof;
	}
	public String getIdentityProofNumber() {
		return identityProofNumber;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public String getOvdName() {
		return ovdName;
	}
	public String getIsPanValid() {
		return isPanValid;
	}
	public String getOvdType() {
		return ovdType;
	}
	public String getOvdNumber() {
		return ovdNumber;
	}
	
	public void setCardholderFirstName(String cardholderFirstName) {
		this.cardholderFirstName = cardholderFirstName;
		if(cardholderFirstName.contentEquals("Auto")) {
			this.cardholderFirstName = faker.name().firstName();
		}		
		if(!cardholderFirstName.toLowerCase().equals("false")) {
			jsonObject.put("cardholderFirstName", cardholderFirstName.equals("null") ? JSONObject.NULL : this.cardholderFirstName);	
		}
	}
	public void setCardholderMiddleName(String cardholderMiddleName) {
		this.cardholderMiddleName = cardholderMiddleName;
		if(cardholderMiddleName.contentEquals("Auto")) {
			this.cardholderMiddleName = faker.name().nameWithMiddle();
		}		
		if(!cardholderMiddleName.toLowerCase().equals("false")) {
			jsonObject.put("cardholderMiddleName", cardholderMiddleName.equals("null") ? JSONObject.NULL : this.cardholderMiddleName);	
		}		
	}
	public void setCardholderLastName(String cardholderLastName) {
		this.cardholderLastName = cardholderLastName;
		if(cardholderLastName.contentEquals("Auto")) {
			this.cardholderLastName = faker.name().lastName();
		}		
		if(!cardholderLastName.toLowerCase().equals("false")) {
			jsonObject.put("cardholderLastName", cardholderLastName.equals("null") ? JSONObject.NULL : this.cardholderLastName);	
		}		
	}
	public void setCardholderMobile(String cardholderMobile) {
		this.cardholderMobile = cardholderMobile;
		if(cardholderMobile.contentEquals("Auto")) {
			this.cardholderMobile = "60" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!cardholderMobile.toLowerCase().equals("false")) {
			jsonObject.put("cardholderMobile", cardholderMobile.equals("null") ? JSONObject.NULL : this.cardholderMobile);	
		}
	}
	
	public void setCardholderEmail(String cardholderEmail) {
		this.cardholderEmail = cardholderEmail;
		if(cardholderEmail.contentEquals("Auto")) {
			this.cardholderEmail = faker.internet().emailAddress();
		}		
		if(!cardholderEmail.toLowerCase().equals("false")) {
			jsonObject.put("cardholderEmail", cardholderEmail.equals("null") ? JSONObject.NULL : this.cardholderEmail);	
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
	
	public void setCardholderDateOfBirth(String cardholderDateOfBirth) {
		this.cardholderDateOfBirth = cardholderDateOfBirth;
		if(cardholderDateOfBirth.contentEquals("Auto")) {
			this.cardholderDateOfBirth = GenericMethods.getCurrentDateTime("dd-MM-yyyy");
		}		
		if(!cardholderDateOfBirth.toLowerCase().equals("false")) {
			jsonObject.put("cardholderDateOfBirth", cardholderDateOfBirth.equals("null") ? JSONObject.NULL : this.cardholderDateOfBirth);	
		}		
	}
	
	public void setIdentityProof(String identityProof) {
		this.identityProof = identityProof;
		if(identityProof.contentEquals("Auto")) {
			this.identityProof = "Aadhar";
		}		
		if(!identityProof.toLowerCase().equals("false")) {
			jsonObject.put("identityProof", identityProof.equals("null") ? JSONObject.NULL : this.identityProof);	
		}		
	}
	
	public void setIdentityProofNumber(String identityProofNumber) {
		this.identityProofNumber = identityProofNumber;
		if(identityProofNumber.contentEquals("Auto")) {
			this.identityProofNumber = faker.number().randomNumber(20, true)+"";
		}		
		if(!identityProofNumber.toLowerCase().equals("false")) {
			jsonObject.put("identityProofNumber", identityProofNumber.equals("null") ? JSONObject.NULL : this.identityProofNumber);	
		}		
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
		if(employeeId.contentEquals("Auto")) {
			this.employeeId = faker.number().randomNumber(4, true)+"";
		}		
		if(!employeeId.toLowerCase().equals("false")) {
			jsonObject.put("employeeId", employeeId.equals("null") ? JSONObject.NULL : this.employeeId);	
		}		
	}
	
	public void setOvdName(String ovdName) {
		this.ovdName = ovdName;
		if(ovdName.contentEquals("Auto")) {
			this.ovdName = faker.name().name();
		}		
		if(!ovdName.toLowerCase().equals("false")) {
			jsonObject.put("ovdName", ovdName.equals("null") ? JSONObject.NULL : this.ovdName);	
		}		
	}
	
	public void setIsPanValid(String isPanValid) {
		this.isPanValid = isPanValid;
		if(isPanValid.contentEquals("Auto")) {
			this.isPanValid = "1";
		}		
		if(!isPanValid.toLowerCase().equals("false")) {
			jsonObject.put("isPanValid", isPanValid.equals("null") ? JSONObject.NULL : this.isPanValid);	
		}		
	}
	
	public void setOvdType(String ovdType) {
		this.ovdType = ovdType;
		if(ovdType.contentEquals("Auto")) {
			this.ovdType = "";
		}		
		if(!ovdType.toLowerCase().equals("false")) {
			jsonObject.put("ovdType", ovdType.equals("null") ? JSONObject.NULL : this.ovdType);	
		}
	}
	
	public void setOvdNumber(String ovdNumber) {
		this.ovdNumber = ovdNumber;
		if(ovdNumber.contentEquals("Auto")) {
			this.ovdNumber = "1";
		}		
		if(!ovdNumber.toLowerCase().equals("false")) {
			jsonObject.put("ovdNumber", ovdNumber.equals("null") ? JSONObject.NULL : this.ovdNumber);	
		}		
	}
	
	public JSONObject getJsonObject() {
		//System.out.println("Cardholder::"+jsonObject);
		return jsonObject;
	}
}
