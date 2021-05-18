package snapmartexam.utils;

import org.openqa.selenium.WebDriver;

public class ValidatePage {
	ExplicitWait wait = new ExplicitWait();
	
	public boolean validatePage(WebDriver driver, String identifier, String locator, int waitTimeElement, String expectedUrl, int waitTimeUrl) {
		boolean validPage = false;
		if(identifier!="" && locator!="" && waitTimeElement!=0) {
			wait.waitElementVisibility(driver, identifier, locator, waitTimeElement);
			validPage = true;
		}
		
		if(expectedUrl!="" && waitTimeUrl!=0) {
			validPage = wait.waitElementUrl(driver, expectedUrl, waitTimeUrl);
		}
		return validPage;
	}
	
}
