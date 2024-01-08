package com.ppi.api.endpoints;

import java.io.IOException;
import java.util.Properties;

import com.ppi.utilities.GenericMethods;

public class Constants {
	public static final String[] cardProfileIds = new String[] { "15", "150", "300" };
	public static final String[] blockTypes = new String[] { "Custom", "Temporary", "Permanent", "Debit", "Credit", "CreditDebit" };
	public static final String[] fundFlowType = new String[] {"O","I","IO","OR","IR","IOR"};
	public static final String[] kycProfileIds = new String[] {"30", "150", "300"};
	public static final String[] riskCategories = new String[] {"LOW", "MEDIUM", "HIGH" };
	
	
	public static String clientId = "2000";
	public static String bankId = "7000";
	public static String entityId = "100";
	public static String secureCode = "mCgO7hucFFgseo4trayRyZ";
	public static String X_Consumer_Custom_ID = "44bd014ed8575b8e8406eab86ec3e6cd";
	public static String x_api_key = "Xy6XdMLwlb";

	public static void assignConstants(String client) throws IOException {

		// Read data from properties file and assign the values
		GenericMethods generic = new GenericMethods();
		Properties prop = generic.readPropertiesFile(client + ".properties");
		// System.out.println("clientId: "+ prop.getProperty("clientId"));
		// System.out.println("bankId: "+ prop.getProperty("bankId"));
		clientId = prop.getProperty("clientId");
		bankId = prop.getProperty("bankId");
		entityId = prop.getProperty("entityId");
		secureCode = prop.getProperty("secureCode");
		X_Consumer_Custom_ID = prop.getProperty("X_Consumer_Custom_ID");
		x_api_key = prop.getProperty("x_api_key");

	}
	
	public static String extentReportSummaryFile = "ExtentReportsSummary.xlsx";
	public static String extentReportSummaryFileSheetName = "Reports";
}
