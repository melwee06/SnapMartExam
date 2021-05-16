package snapmartexam.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FetchElement {
	
	public WebElement getWebElement(WebDriver driver, String identifier, String locator) {
		switch(identifier) {
		case "ID":
			return driver.findElement(By.id(locator));
		case "CSS":
			return driver.findElement(By.cssSelector(locator));
		case "CLASS":
			return driver.findElement(By.className(locator));
		case "PARTIAL_LINK_TEXT":
			return driver.findElement(By.partialLinkText(locator));
		case "LINK_TEXT":
			return driver.findElement(By.linkText(locator));
		case "TAGNAME":
			return driver.findElement(By.tagName(locator));
		case "XPATH":
			return driver.findElement(By.xpath(locator));
		default:
			return null;
		}
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String identifier, String locator) {
		switch(identifier) {
		case "ID":
			return driver.findElements(By.id(locator));
		case "CSS":
			return driver.findElements(By.cssSelector(locator));
		case "CLASS":
			return driver.findElements(By.className(locator));
		case "PARTIAL_LINK_TEXT":
			return driver.findElements(By.partialLinkText(locator));
		case "LINK_TEXT":
			return driver.findElements(By.linkText(locator));
		case "TAGNAME":
			return driver.findElements(By.tagName(locator));
		case "XPATH":
			return driver.findElements(By.xpath(locator));
		default:
			return null;
		}
	}
	
}
