package com.ppi.api.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ppi.api.endpoints.Constants;




public class InitTest {	

	public String browser;
	public String os;
	public String client;
	public String typeoftest;
	public String xlFileName;
	public WebDriver driver;
	
	@Parameters({"browser-name", "operating-system", "client", "typeoftest", "xlFileName"})	
	@BeforeTest
	public void preCondition(String browser, String os, String client, String typeoftest, String xlFileName) throws IOException {
		//System.out.println("Init Test");
		this.browser = browser;
		this.os = os;
		this.client = client;
		this.typeoftest = typeoftest;
		this.xlFileName = xlFileName;
		
		Constants.assignConstants(client);		
	}
	
	@AfterTest
	public void postCondition() {
		driver.quit();
	}
}
