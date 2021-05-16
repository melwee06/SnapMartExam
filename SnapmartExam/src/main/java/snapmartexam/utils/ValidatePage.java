package snapmartexam.utils;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidatePage {
	ExplicitWait wait = new ExplicitWait();
	
	public boolean validatePage(WebDriver driver, String identifier, String locator, int waitTimeElement, String expectedUrl, int waitTimeUrl) {
		boolean validPage = false;
		if(identifier!="" && locator!="" && waitTimeElement!=0) {
			WebElement waitElement = wait.waitElementVisibility(driver, identifier, locator, waitTimeElement);
			//check if element is displayed on the page
			Assert.assertNotEquals(waitElement, null);
			validPage = true;
		}
		
		if(expectedUrl!="" && waitTimeUrl!=0) {
			boolean waitUrl = wait.waitElementUrlToBe(driver, expectedUrl, waitTimeUrl);
			//check if page url valid
			Assert.assertEquals(true, waitUrl);
			validPage = true;
		}
		return validPage;
	}
	
}
