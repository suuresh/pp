package com.ppi.api.payloads.spicemoney;

import org.json.JSONObject;

import com.github.javafaker.Faker;

public class AddressDetails {
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String country;
	public String zipCode;
	
	public JSONObject jsonObject = new JSONObject();
    public Faker faker = new Faker();
	
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
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
	
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
		if(address1.contentEquals("Auto")) {
			this.address1 = faker.address().fullAddress();
		}		
		if(!address1.toLowerCase().equals("false")) {
			jsonObject.put("address1", address1.equals("null") ? JSONObject.NULL : this.address1);	
		}
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
		if(address2.contentEquals("Auto")) {
			this.address2 = faker.address().fullAddress();
		}		
		if(!address2.toLowerCase().equals("false")) {
			jsonObject.put("address2", address2.equals("null") ? JSONObject.NULL : this.address2);	
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
		if(zipCode.contentEquals("Auto")) {
			this.zipCode = faker.address().zipCode();
		}		
		if(!zipCode.toLowerCase().equals("false")) {
			jsonObject.put("zipCode", zipCode.equals("null") ? JSONObject.NULL : this.zipCode);	
		}		
	}
	
	
	
}
