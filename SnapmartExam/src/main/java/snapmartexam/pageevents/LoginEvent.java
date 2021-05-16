package snapmartexam.pageevents;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import snapmartexam.utils.ExplicitWait;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.FetchElement;
import snapmartexam.utils.ValidatePage;

public class LoginEvent {
	
	private static String username, password, expUrlLogin, expUrlSearch;
	FetchElement element = new FetchElement();
	ExplicitWait waitElement = new ExplicitWait();
	ValidatePage validatePage = new ValidatePage();
		
	public boolean accessLogin(WebDriver driver, ExtentReporter reporter) {
		element.getWebElement(driver, "XPATH", "(//span[contains(.,'Account')])[2]").click();
		element.getWebElement(driver, "XPATH", "(//span[contains(.,'Login')])[2]").click();
		
		//validate login page
		boolean validateLoginPage = validatePage.validatePage(driver, "XPATH", "//mat-card[contains(@class,'mat-card')]", 5, expUrlLogin, 0);
		if(validateLoginPage) {
			reporter.logExtentReport(driver, "pass", "", "Login page was successfully loaded, form is displayed.");
			reporter.logExtentReport(driver, "screenShot", "loginPageTest", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Login page was not loaded properly.");
			reporter.logExtentReport(driver, "screenShotFail", "loginPageTestFail", "");
		}
		Assert.assertEquals("Login page was not loaded properly", true, validateLoginPage);
		return validateLoginPage;
	}
	
	public boolean loginCorrectCredentials(WebDriver driver, ExtentReporter reporter) {
		element.getWebElement(driver, "ID", "email").sendKeys(username);
		element.getWebElement(driver, "ID", "password").sendKeys(password);
		reporter.logExtentReport(driver, "screenShot", "loginCorrectCredentialsTest", "");
		element.getWebElement(driver, "ID", "loginButton").click();
		
		//validate if redirected to search page
		boolean validateRedirectToSearch = validatePage.validatePage(driver, "XPATH", "//div[contains(@class, 'table-container custom-slate')]", 5, expUrlSearch, 5);
		if(validateRedirectToSearch) {
			reporter.logExtentReport(driver, "pass", "", "Login was successful, redirected to search page.");
			reporter.logExtentReport(driver, "screenShot", "loginCorrectCredentialsTestClicked", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Login not successful.");
			reporter.logExtentReport(driver, "screenShotFail", "loginCorrectCredentialsTest", "");
		}
		Assert.assertEquals("Search page was not loaded properly", true, validateRedirectToSearch);
		return validateRedirectToSearch;
	}
	
	public boolean loginInorrectCredentials(WebDriver driver, ExtentReporter reporter) {
		element.getWebElement(driver, "ID", "email").sendKeys(username);
		element.getWebElement(driver, "ID", "password").sendKeys(password);
		reporter.logExtentReport(driver, "screenShot", "loginInorrectCredentials", "");
		element.getWebElement(driver, "ID", "loginButton").click();
		//validate if still on login page
		boolean validateFailedLogin = validatePage.validatePage(driver, "XPATH", "//div[contains(@class, 'error ng-star-inserted')]", 5, expUrlLogin, 5);
		if(validateFailedLogin) {
			reporter.logExtentReport(driver, "pass", "", "Error message displayed, stayed on Login page.");
			reporter.logExtentReport(driver, "screenShot", "loginInorrectCredentialsClicked", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Error message was not displayed.");
			reporter.logExtentReport(driver, "screenShotFail", "loginInorrectCredentialsFail", "");
		}
		Assert.assertEquals("Should still be in Login Page after error", true, validateFailedLogin);
		return validateFailedLogin;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		LoginEvent.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		LoginEvent.password = password;
	}

	public String getExpUrlLogin() {
		return expUrlLogin;
	}

	public void setExpUrlLogin(String expUrlLogin) {
		LoginEvent.expUrlLogin = expUrlLogin;
	}

	public String getExpUrlSearch() {
		return expUrlSearch;
	}

	public void setExpUrlSearch(String expUrlSearch) {
		LoginEvent.expUrlSearch = expUrlSearch;
	}

	
}
