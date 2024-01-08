package com.ppi.api.payloads.spicemoney;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class CustomerDetails {
	public String firstName;
	public String middleName;
	public String lastName;
	public String gender;
	public String dateOfBirth;
	public String mobileNumber;
	public String emailAddress;
	
	public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
	
	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
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
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
		if(middleName.contentEquals("Auto")) {
			this.middleName = faker.name().nameWithMiddle();
		}		
		if(!middleName.toLowerCase().equals("false")) {
			jsonObject.put("middleName", middleName.equals("null") ? JSONObject.NULL : this.middleName);	
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
			this.gender = "M";
		}		
		if(!gender.toLowerCase().equals("false")) {
			jsonObject.put("gender", gender.equals("null") ? JSONObject.NULL : this.gender);	
		}		
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		if(mobileNumber.contentEquals("Auto")) {
			this.mobileNumber = "91" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!mobileNumber.toLowerCase().equals("false")) {
			jsonObject.put("mobileNumber", mobileNumber.equals("null") ? JSONObject.NULL : this.mobileNumber);	
		}
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		if(emailAddress.contentEquals("Auto")) {
			this.emailAddress = faker.internet().emailAddress();
		}		
		if(!emailAddress.toLowerCase().equals("false")) {
			jsonObject.put("emailAddress", emailAddress.equals("null") ? JSONObject.NULL : this.emailAddress);	
		}		
	}
}
