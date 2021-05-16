package snapmartexam.test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import snapmartexam.pageevents.LandingPageEvent;
import snapmartexam.pageevents.LoginEvent;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.HandleTestData;

@RunWith(Parameterized.class)
public class LoginWithIncorrectCredentialsTest {
	private static CuongnguyenTest baseTest;
	private static HandleTestData htd;
	public static ExtentReporter reporter;
	private static LandingPageEvent lp;
	private static LoginEvent le;

	private static final String filePath = "C:\\Users\\mrongavilla\\Documents\\CuongnguyenTestData\\";
	private static final String fileName = "LoginWithIncorrectCredentialsTest.xlsx";
	private static final String sheetName = "LoginSheet";
	private static final String reportName = "LoginWithIncorrectCredentialsTest";
	private static final String testName = "LoginWithIncorrectCredentialsTest";
	private static final String testDesc = "Testing login with incorrect credentials.";
	private String incorrectUsername, incorrectPassword, expUrlLogin;
	
	public LoginWithIncorrectCredentialsTest(String webBrowser, String webUrl, String incorrectUsername, String incorrectPassword, String expUrlLogin) {
		baseTest = new CuongnguyenTest(webBrowser, webUrl);
		reporter = new ExtentReporter();
		reporter.createExtentReport(reportName, testName, testDesc);
		this.incorrectUsername = incorrectUsername;
		this.incorrectPassword = incorrectPassword;
		this.expUrlLogin = expUrlLogin;
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
	}
	
	@Test
	public void loginWithIncorrectCredentialsTest() {
		le.setUsername(incorrectUsername); //wrong email
		le.setPassword(incorrectPassword); //wrong password
		le.setExpUrlLogin(expUrlLogin);
		//validate if still on login page
		Assert.assertEquals("loginInorrectCredentials", true, le.loginInorrectCredentials(baseTest.driver, reporter));
	}
	
	@After
	public void afterTest() {
		baseTest.driver.quit();
	}
}
