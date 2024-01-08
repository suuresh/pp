package com.ppi.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExtentReportPage {
	public WebDriver driver;
	public ExtentReportPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * @FindBy(xpath = "//h5[@class='test-status text-pass'][0]") public WebElement
	 * apiName;
	 * 
	 * public WebElement getApiName() { return apiName; }
	 */

	@FindAll({
	@FindBy(xpath = "//h5[@class='test-status text-pass']")
	})
	public List<WebElement> apiNames;
	public List<WebElement> getApiNames() {
		return apiNames;
	}
	
	@FindAll({
	@FindBy(xpath = "//h5[@class='test-status text-fail']")
	})
	public List<WebElement> apiNamesFailed;
	public List<WebElement> getApiNamesFailed() {
		return apiNamesFailed;
	}
	
	//fa fa-bar-chart
	
	@FindBy(xpath = "//i[@class='fa fa-bar-chart']")
	public WebElement barChat;
	
	@FindBy(xpath = "//p[contains(text(),'Started')]/following-sibling::h3")
	public WebElement startTime;
	
	@FindBy(xpath = "//p[contains(text(),'Ended')]/following-sibling::h3")
	public WebElement endTime;
	
	@FindBy(xpath = "//p[contains(text(),'Tests Passed')]/following-sibling::h3")
	public WebElement testsPassed;
	
	@FindBy(xpath = "//p[contains(text(),'Tests Failed')]/following-sibling::h3")
	public WebElement testsFailed;
	public WebElement getBarChat() {
		return barChat;
	}

	public WebElement getStartTime() {
		return startTime;
	}

	public WebElement getEndTime() {
		return endTime;
	}

	public WebElement getTestsPassed() {
		return testsPassed;
	}

	public WebElement getTestsFailed() {
		return testsFailed;
	}
	
	
}
