package snapmartexam.pageevents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import snapmartexam.utils.ExplicitWait;
import snapmartexam.utils.FetchElement;
import snapmartexam.utils.ValidatePage;

public class PayCreditCardEvent {
	FetchElement element = new FetchElement();
	ExplicitWait waitElement = new ExplicitWait();
	ValidatePage validatePage = new ValidatePage();
	private static String address, expUrlPurchaseBasket, expUrlSelectAddress;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		PayCreditCardEvent.address = address;
	}

	public void selectAddress(WebDriver driver) {
		element.getWebElement(driver, "XPATH", "(//span[contains(.,'Your Basket')])[2]").click();
		//validate if redirected to purchase basket page
		validatePage.validatePage(driver, "XPATH", "//app-purchase-basket", 5, this.getExpUrlPurchaseBasket(), 5);
		waitElement.waitElementVisibility(driver, "ID", "checkoutButton", 5);
		element.getWebElement(driver, "ID", "checkoutButton").click();
		//validate if redirected to select address page
		validatePage.validatePage(driver, "XPATH", "//mat-card", 5, this.getExpUrlSelectAddress(), 5);
		waitElement.waitElementVisibility(driver, "XPATH", "//mat-cell[contains(.,'"+this.getAddress()+"')]/preceding-sibling::mat-cell/mat-radio-button", 5);
		element.getWebElement(driver, "XPATH", "//mat-cell[contains(.,'"+this.getAddress()+"')]/preceding-sibling::mat-cell/mat-radio-button").click();
	}
	
	public String getExpUrlPurchaseBasket() {
		return expUrlPurchaseBasket;
	}

	public void setExpUrlPurchaseBasket(String expUrlPurchaseBasket) {
		PayCreditCardEvent.expUrlPurchaseBasket = expUrlPurchaseBasket;
	}

	public String getExpUrlSelectAddress() {
		return expUrlSelectAddress;
	}

	public void setExpUrlSelectAddress(String expUrlSelectAddress) {
		PayCreditCardEvent.expUrlSelectAddress = expUrlSelectAddress;
	}
}
