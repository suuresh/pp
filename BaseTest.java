package com.ppi.api.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ppi.api.endpoints.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseTest {	

	public String browser;
	public String os;
	public String client;
	public String typeoftest;
	public String xlFileName;
	public WebDriver driver;
	
	@Parameters({"browser-name", "operating-system", "client", "typeoftest", "xlFileName"})	
	@BeforeTest
	public void preCondition(String browser, String os, String client, String typeoftest, String xlFileName) throws IOException {
		//System.out.println("Base Test");
		this.browser = browser;
		this.os = os;
		this.client = client;
		this.typeoftest = typeoftest;
		this.xlFileName = xlFileName;
		
		Constants.assignConstants(client);
		
		//To set the download location
		//WebDriverManager.chromedriver().cachePath("/path/to/my/folder").setup();
		
		if(browser.contentEquals("Chrome")) {
			driver = WebDriverManager.chromedriver().create(); 
		} else if(browser.contentEquals("Firefox")) {
			driver = WebDriverManager.firefoxdriver().create();
		} else if(browser.contentEquals("Edge")) {
			driver = WebDriverManager.edgedriver().create();
		}	
		
		if(driver != null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().window().maximize();		
		}	
		
	}
	
	@AfterTest
	public void postCondition() {
		driver.quit();
	}
}
