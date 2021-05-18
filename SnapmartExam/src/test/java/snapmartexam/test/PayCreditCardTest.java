package snapmartexam.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import snapmartexam.pageevents.AddToBasketEvent;
import snapmartexam.pageevents.LandingPageEvent;
import snapmartexam.pageevents.LoginEvent;
import snapmartexam.pageevents.PayCreditCardEvent;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.HandleTestData;

@RunWith(Parameterized.class)
public class PayCreditCardTest {
	private static CuongnguyenTest baseTest;
	private static HandleTestData htd;
	private static ExtentReporter reporter;
	private static LandingPageEvent lp;
	private static LoginEvent le;
	private static PayCreditCardEvent pcc;

	private static String filePath = "C:\\Users\\mrongavilla\\Documents\\CuongnguyenTestData\\";
	private static String fileName = "PayCreditCardTest.xlsx";
	private static String sheetName = "PayCreditSheet";
	private static final String reportName = "PayCreditCardTest";
	private static final String testName = "PayCreditCardTest";
	private static final String testDesc = "Tetsing payment using credit card.";
	private String username, password, expUrlLogin, expUrlSearch, address, expUrlPurchaseBasket, expUrlSelectAddress, expUrlDeliveryAddress, 
		deliverySpeed, expUrlPaymentOption, creditCardName, expUrlOrderSummary, expUrlOrderCompletion;
	
	public PayCreditCardTest(String webBrowser, String webUrl, String username, String password, String expUrlLogin, String expUrlSearch,
			String expUrlPurchaseBasket, String expUrlSelectAddress, String address, String expUrlDeliveryAddress, 
			String deliverySpeed, String expUrlPaymentOption, String creditCardName, String expUrlOrderSummary,
			String expUrlOrderCompletion) {

		baseTest = new CuongnguyenTest(webBrowser, webUrl);
		reporter = new ExtentReporter();
		reporter.createExtentReport(reportName, testName, testDesc);
		this.username = username;
		this.password = password;
		this.expUrlLogin = expUrlLogin;
		this.expUrlSearch = expUrlSearch;
		this.address = address;
		this.expUrlPurchaseBasket = expUrlPurchaseBasket;
		this.expUrlSelectAddress = expUrlSelectAddress;
		this.expUrlDeliveryAddress = expUrlDeliveryAddress;
		this.deliverySpeed = deliverySpeed;
		this.expUrlPaymentOption = expUrlPaymentOption;
		this.creditCardName = creditCardName;
		this.expUrlOrderSummary = expUrlOrderSummary;
		this.expUrlOrderCompletion = expUrlOrderCompletion;
	}
	
	@Parameterized.Parameters
	public static Object[][] testData() throws IOException {
		htd = new HandleTestData();
		htd.setFilePath(filePath);
		htd.setFileName(fileName);
		htd.setSheetName(sheetName);
		htd.readTestData();
		System.out.println(htd.getTestData());
	    return htd.getTestData();
	}
	
	@Before
	public void accessLandingAndLogin() {
		lp = new LandingPageEvent();
		lp.accessLandingPage(baseTest.driver, reporter);
		le = new LoginEvent();
		le.setExpUrlLogin(expUrlLogin);
		le.accessLogin(baseTest.driver, reporter);
		
		le.setUsername(username);
		le.setPassword(password);
		le.setExpUrlSearch(expUrlSearch);
		le.loginCorrectCredentials(baseTest.driver, reporter);
	}
	
	@Test
	public void addItemToBasketThroughPaginationTest() {
		//Paying orders using credit card
		pcc = new PayCreditCardEvent();
		pcc.setAddress(address);
		pcc.setExpUrlPurchaseBasket(expUrlPurchaseBasket);
		pcc.setExpUrlSelectAddress(expUrlSelectAddress);
		pcc.setExpUrlDeliveryAddress(expUrlDeliveryAddress);
		pcc.setDeliverySpeed(deliverySpeed);
		pcc.setExpUrlPaymentOption(expUrlPaymentOption);
		pcc.setCreditCardName(creditCardName);
		pcc.setExpUrlOrderSummary(expUrlOrderSummary);
		pcc.setExpUrlOrderCompletion(expUrlOrderCompletion);
		pcc.payOrdersUsingCreditCard(baseTest.driver, reporter);
	}
	
	@After
	public void afterTest() {
		baseTest.driver.quit();
	}
}
