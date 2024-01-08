package com.ppi.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ppi.api.testcases.InitTest;

public class ExtentReportManager extends InitTest implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "ExtentReport-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\extentReports\\" + repName);
		sparkReporter.config().setDocumentTitle("HEXAPPI_API_Automation");
		sparkReporter.config().setReportName("PPIAutomation");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "PPIAutomation");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("user Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "SureshKumar");

	}

	/*
	 * public void onTestStart(ITestResult result) { test =
	 * extent.createTest(result.getName());
	 * test.assignCategory(result.getMethod().getGroups());
	 * test.createNode(result.getName()); test.log(Status.INFO, "Test Started"); }
	 */

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		if (result.getParameters().length > 0) {
			test.log(Status.PASS,
					"Test Passed:: TCID: " + result.getParameters()[0] + " Test desc: " + result.getParameters()[1]);
		} else {
			test.log(Status.PASS, "Test Passed");
		}
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		// System.out.println(result.getParameters().length);
		if (result.getParameters().length > 0) {
			test.log(Status.FAIL,
					"Test Failed:: TCID: " + result.getParameters()[0] + " Test desc: " + result.getParameters()[1]);
		} else {
			test.log(Status.FAIL, "Test Failed");
		}

		test.log(Status.FAIL, result.getThrowable().getMessage());
		if (driver != null) {
			String base64Screenshot = "data:image/png;base64,"
					+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			test.addScreenCaptureFromBase64String(base64Screenshot);
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		if (result.getParameters().length > 0) {
			test.log(Status.SKIP,
					"Test Skipped:: TCID: " + result.getParameters()[0] + " Test desc: " + result.getParameters()[1]);
		} else {
			test.log(Status.SKIP, "Test Skipped");
		}
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
