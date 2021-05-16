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
public class LoginWithCorrectCredentialsTest {
	private static CuongnguyenTest baseTest;
	private static HandleTestData htd;
	public static ExtentReporter reporter;
	private static LandingPageEvent lp;
	private static LoginEvent le;

	private static final String filePath = "C:\\Users\\mrongavilla\\Documents\\CuongnguyenTestData\\";
	private static final String fileName = "LoginWithCorrectCredentialsTest.xlsx";
	private static final String sheetName = "LoginSheet";
	private static final String reportName = "LoginWithCorrectCredentialsTest";
	private static final String testName = "LoginWithCorrectCredentialsTest";
	private static final String testDesc = "Testing login with correct credentials.";
	private String username, password, expUrlLogin, expUrlSearch, incorrectUsername, incorrectPassword;
	
	public LoginWithCorrectCredentialsTest(String webBrowser, String webUrl, String username, String password, String expUrlLogin, String expUrlSearch) {
		baseTest = new CuongnguyenTest(webBrowser, webUrl);
		reporter = new ExtentReporter();
		reporter.createExtentReport(reportName, testName, testDesc);
		this.username = username;
		this.password = password;
		this.expUrlLogin = expUrlLogin;
		this.expUrlSearch = expUrlSearch;
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
	public void loginWithCorrectCredentialsTest() {
		le.setUsername(username);
		le.setPassword(password);
		le.setExpUrlSearch(expUrlSearch);
		//validate if redirected to search page
		Assert.assertEquals("loginCorrectCredentials", true, le.loginCorrectCredentials(baseTest.driver, reporter));
	}
	
	@After
	public void afterTest() {
		baseTest.driver.quit();
	}
}
