package com.ppi.api.payloads.mone;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.ppi.utilities.GenericMethods;

public class Nominee {
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String email;
	private String mobile;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	
	private JSONObject jsonObject = new JSONObject();	
	public Faker faker = new Faker();	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getZipCode() {
		return zipCode;
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
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		if(dateOfBirth.contentEquals("Auto")) {
			this.dateOfBirth = GenericMethods.getCurrentDateTime("dd-MM-yyyy");
		}		
		if(!dateOfBirth.toLowerCase().equals("false")) {
			jsonObject.put("dateOfBirth", dateOfBirth.equals("null") ? JSONObject.NULL : this.dateOfBirth);	
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
	public void setMobile(String mobile) {
		this.mobile = mobile;
		if(mobile.contentEquals("Auto")) {
			this.mobile = "60" + faker.phoneNumber().cellPhone().replace("-", "").replace(" ", "").replace("(", "").replace(")", "").replace(".", "");
		}		
		if(!mobile.toLowerCase().equals("false")) {
			jsonObject.put("mobile", mobile.equals("null") ? JSONObject.NULL : this.mobile);	
		}
	}
	public void setAddress(String address) {
		this.address = address;
		if(address.contentEquals("Auto")) {
			this.address = faker.address().fullAddress();
		}		
		if(!address.toLowerCase().equals("false")) {
			jsonObject.put("address", address.equals("null") ? JSONObject.NULL : this.address);	
		}
	}
	public void setCity(String city) {
		this.city = city;
		if(city.contentEquals("Auto")) {
			this.city = faker.address().cityName();
		}		
		if(!city.toLowerCase().equals("false")) {
			jsonObject.put("city", city.equals("null") ? JSONObject.NULL : this.city);	
		}
	}
	public void setState(String state) {
		this.state = state;
		if(state.contentEquals("Auto")) {
			this.state = faker.address().state();
		}		
		if(!state.toLowerCase().equals("false")) {
			jsonObject.put("state", state.equals("null") ? JSONObject.NULL : this.state);	
		}
	}
	public void setCountry(String country) {
		this.country = country;
		if(country.contentEquals("Auto")) {
			this.country = faker.address().country();
		}		
		if(!country.toLowerCase().equals("false")) {
			jsonObject.put("country", country.equals("null") ? JSONObject.NULL : this.country);	
		}
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
		//jsonObject.put("zipCode", zipCode == null ? JSONObject.NULL : zipCode);
		if(zipCode.contentEquals("Auto")) {
			this.zipCode = faker.address().zipCode();
		}		
		if(!zipCode.toLowerCase().equals("false")) {
			jsonObject.put("zipCode", zipCode.equals("null") ? JSONObject.NULL : this.zipCode);	
		}
	}
	
	public JSONObject getJsonObject() {
		//System.out.println("JsonObject::"+this.jsonObject);
		return this.jsonObject;
		//String jsonString = this.jsonObject.toString();
		//System.out.println(jsonString);
		//return jsonString;
	}
	
}
