package snapmartexam.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
	private static WebDriverWait wait;
	
	public WebElement waitElementVisibility(WebDriver driver, String identifier, String locator, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		switch(identifier) {
		case "ID":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
		case "CSS":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
		case "CLASS":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
		case "PARTIAL_LINK_TEXT":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locator)));
		case "LINK_TEXT":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
		case "TAGNAME":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator)));
		case "XPATH":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		default:
			return null;
		}
	}
	
	public boolean waitElementUrlToBe(WebDriver driver, String url, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	
}
