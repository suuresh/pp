package com.ppi.api.endpoints;

public class Routes {
	
	// Configs
	
	public static final String xlPath = System.getProperty("user.dir") + "/testData/";
	public static final String propertiesPath = System.getProperty("user.dir") + "/src/test/resources/";
	public static final String extentReportsPath = System.getProperty("user.dir") + "/extentReports/";
	// Common Url's
	public static String base_url = "https://kong-pp-qa.pc.enstage-sas.com";	
	public static String spiceMoney_base_url = "http://192.168.105.123:2084";
	public static String encrypt_url = base_url + "/mob1/api/v2/encrypt";
	public static String decrypt_url = base_url + "/mob1/api/v2/decrypt";
	
	
	// Mobility Url's	
	public static String createCard_url = base_url + "/mob1/api/issuance/v1/createCard";
	public static String activateCard_url = base_url + "/mob1/api/issuance/v2/activate";
	public static String changeCardStatus_url = base_url + "/mob1/api/onboarding/v1/change-card-status";
	public static String cardInquiry_url = base_url + "/mob1/api/onboarding/v1/cardInquiry";
	public static String updateProfile_url = base_url + "/mob1/api/onboarding/v1/updateProfile";
	
	public static String retirveCustRecord_url = base_url + "/mob1/api/onboarding/v2/retrieveCustRecord";
	public static String blockCard_url = base_url + "/mob1/api/onboarding/v1/block";
	public static String unblockCard_url = base_url + "/mob1/api/onboarding/v1/unblock";
	public static String verifyCardholder_url = base_url + "/mob1/api/onboarding/v1/verifyCardholder";
	public static String sendOtpMobile_url = base_url + "/mob1/api/onboarding/v1/sendOtpMobile";
	public static String verifyOtpMobile_url = base_url + "/mob1/api/onboarding/v1/verifyMobileOtp";
	public static String sendOtpEmail_url = base_url + "/mob1/api/onboarding/v1/sendOtpEmail";
	public static String verifyOtpEmail_url = base_url + "/mob1/api/onboarding/v1/verifyEmailOtp";
	public static String load_url = base_url + "/mob1/api/onboarding/v1/creditAccount";
	public static String unload_url = base_url + "/mob1/api/onboarding/v1/debitAccount";
	public static String transactionProfile_url = base_url + "/mob1/api/onboarding/v1/transactionProfile";
	public static String checkStatus_url = base_url + "/mob1/api/onboarding/v1/checkStatus";
	public static String resetPin_url = base_url + "/mob1/api/onboarding/v1/resetPin";
	public static String statementInquiry_url = base_url + "/mob1/api/onboarding/v1/statementInquiry";
	public static String transactionInquiry_url = base_url + "/mob1/api/onboarding/v1/transactionInquiry";

	
	// Spice Money URL's
	public static String spice_money_base_url = "http://192.168.105.123:2084";
	public static String spice_money_encrypt_url = spice_money_base_url + "/api/v2/encrypt";
	public static String spice_money_decrypt_url = spice_money_base_url + "/api/v2/decrypt";
	public static String registerCustomer_url = base_url + "/qa/sm/api/issuance/v1/enroll";
	public static String spice_money_load_url = base_url + "/qa/sm/api/onboarding/v3/loadAccount";
	public static String spice_money_unload_url = base_url + "/qa/sm/api/onboarding/v3/debitAccount";
	public static String spice_money_fund_transfer_url = base_url + "/qa/sm/api/onboarding/v2/fundTransfer";
}
