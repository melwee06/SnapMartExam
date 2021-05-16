package snapmartexam.pageevents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;
import snapmartexam.utils.ExplicitWait;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.FetchElement;

public class LandingPageEvent {
	FetchElement element = new FetchElement();
	ExplicitWait waitElement = new ExplicitWait();
	
	public boolean accessLandingPage(WebDriver driver, ExtentReporter reporter) {
		WebElement modalElement = waitElement.waitElementVisibility(driver, "XPATH", "//mat-dialog-container", 5);
		boolean modalExists = modalElement.isDisplayed();
		//check modal if displayed
		if(modalExists) {
			element.getWebElement(driver, "XPATH", "//mat-dialog-container//button[contains(@class,'close-dialog')]").click();
		}
		//accept cookie
		if(element.getWebElements(driver, "XPATH", "//div[contains(@class, 'cc-compliance')]/a").size()!=0) {
			element.getWebElement(driver, "XPATH", "//div[contains(@class, 'cc-compliance')]/a").click();
		}
		
		//check landing page if loaded properly
		boolean itemTable = element.getWebElements(driver, "XPATH", "//div[contains(@class, 'table-container custom-slate')]").size()!=0;
		if(itemTable) {
			reporter.logExtentReport(driver, "pass", "", "Landing page was successfully loaded, items are displayed.");
			reporter.logExtentReport(driver, "screenShot", "landingPageTest", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Landing pafe was not loaded properly.");
			reporter.logExtentReport(driver, "screenShotFail", "landingPageTestFail", "");
		}
		Assert.assertEquals("Landing page was not loaded properly", true, itemTable);
		return itemTable;
	}
}