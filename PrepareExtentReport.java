package com.ppi.api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ppi.api.endpoints.Constants;
import com.ppi.api.endpoints.Routes;
import com.ppi.pages.ExtentReportPage;
import com.ppi.utilities.GenericMethods;

public class PrepareExtentReport extends BaseTest {
	
	public static Logger log = LogManager.getLogger(PrepareExtentReport.class.getName());
	public ExtentReportPage erPage;
	
	@BeforeMethod
	public void beforeMethodPrepareExtentReport() {
		log.info("beforeMethodPrepareExtentReport");
		String fileName = GenericMethods.getExtentReportLatestFileName();
		driver.get(Routes.extentReportsPath+fileName);
	}
	
	@Test
	public void prepareExtentReport() {
		System.out.println("prepareExtentReport Function");
		erPage = new ExtentReportPage(driver);
		
		String apiName = null;
		String startTime;
		String endTime;
		String testsPasses;
		String testsFailed;
		
		
		/*
		 * System.out.println("erPage.getApiNames().size()"+erPage.getApiNames().size())
		 * ; for(int i=0; i<erPage.getApiNames().size();i++) {
		 * System.out.println(erPage.getApiNames().get(i).getText()); }
		 */
		
	
		if(erPage.getApiNames().size()>0)
			apiName = erPage.getApiNames().get(erPage.getApiNames().size()-1).getText().replace("test", "");
		
		
		/*
		 * System.out.println("erPage.getApiNamesFailed().size()"+erPage.
		 * getApiNamesFailed().size()); for(int i=0;
		 * i<erPage.getApiNamesFailed().size();i++) {
		 * System.out.println(erPage.getApiNamesFailed().get(i).getText()); }
		 */
		
		if(apiName == null || apiName.contentEquals("") ) {
			if(erPage.getApiNamesFailed().size()>0)
				apiName = erPage.getApiNamesFailed().get(erPage.getApiNamesFailed().size()-1).getText().replace("test", "");			
				
		}
		
		erPage.getBarChat().click();
		GenericMethods.waitExplicitely(2);
		startTime = erPage.getStartTime().getText();
		endTime = erPage.getEndTime().getText();
		testsPasses = erPage.getTestsPassed().getText();
		testsFailed = erPage.getTestsFailed().getText();
		
		System.out.println("ApiName::"+ apiName);
		System.out.println("StartTime::"+ startTime);
		System.out.println("EndTime::"+ endTime);
		System.out.println("Tests Passed::"+ testsPasses);
		System.out.println("Tests Failed::"+ testsFailed);
		
		//Date	ApiName	StartDateTime	EndDateTime	TestsPassed	TestsFailed
		
		int rowCount = GenericMethods.getRowCount(Constants.extentReportSummaryFile,
				Constants.extentReportSummaryFileSheetName);
		rowCount++;
		GenericMethods.writingToExcel(Constants.extentReportSummaryFile, Constants.extentReportSummaryFileSheetName,
				"Date", rowCount, GenericMethods.getCurrentDateTime("dd-MM-yyyy"));
		GenericMethods.writingToExcel(Constants.extentReportSummaryFile, Constants.extentReportSummaryFileSheetName,
				"ApiName", rowCount, apiName);
		
		GenericMethods.writingToExcel(Constants.extentReportSummaryFile, Constants.extentReportSummaryFileSheetName,
				"StartDateTime", rowCount, startTime);
		
		GenericMethods.writingToExcel(Constants.extentReportSummaryFile, Constants.extentReportSummaryFileSheetName,
				"EndDateTime", rowCount, endTime);
		
		GenericMethods.writingToExcel(Constants.extentReportSummaryFile, Constants.extentReportSummaryFileSheetName,
				"TestsPassed", rowCount, testsPasses);
		
		GenericMethods.writingToExcel(Constants.extentReportSummaryFile, Constants.extentReportSummaryFileSheetName,
				"TestsFailed", rowCount, testsFailed);
		
		Assert.assertTrue(true);
	}
	
	@AfterMethod
	public void afterMethodPrepareExtentReport() {
		log.info("afterMethodPrepareExtentReport");
		System.out.println("afterMethodPrepareExtentReport");
	}
}
