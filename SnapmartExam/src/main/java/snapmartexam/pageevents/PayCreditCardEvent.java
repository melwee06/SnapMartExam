package snapmartexam.pageevents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import snapmartexam.utils.ExplicitWait;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.FetchElement;
import snapmartexam.utils.ValidatePage;

public class PayCreditCardEvent {
	FetchElement element = new FetchElement();
	ExplicitWait waitElement = new ExplicitWait();
	ValidatePage validatePage = new ValidatePage();
	private static String address, expUrlPurchaseBasket, expUrlSelectAddress, addressDetails, addressCountry,
		delCity, delAddressDetails, delAddressCountry, expUrlDeliveryAddress, deliverySpeed, creditCardName, 
		expUrlPaymentOption, expUrlOrderSummary, expUrlOrderCompletion;

	public void payOrdersUsingCreditCard(WebDriver driver, ExtentReporter reporter) {
		element.getWebElement(driver, "XPATH", "(//span[contains(.,'Your Basket')])[2]").click();
		try {
			waitElement.waitElementVisibility(driver, "XPATH", "//mat-row", 5);
		}catch(TimeoutException te) {
			//check if there are listed items
			boolean noItems = element.getWebElements(driver, "XPATH", "//button[contains(@id, 'checkoutButton') and not(contains(@disabled, 'true'))]").size()<=0;
			if(noItems) {
				reporter.logExtentReport(driver, "fail", "", "No Items on the basket.");
				reporter.logExtentReport(driver, "screenShot", "noItemsOnBasketFail", "");
				Assert.assertNotEquals("No items on the basket.", true, noItems);
			}
		}
		
		
		//validate if redirected to purchase basket page
		boolean purchaseBasketPage = validatePage.validatePage(driver, "XPATH", "//app-purchase-basket", 5, expUrlPurchaseBasket, 5);
		if(purchaseBasketPage) {
			reporter.logExtentReport(driver, "pass", "", "Purhase basket page was loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "purchaseBasketPage", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Purhase basket page was not loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "purchaseBasketPageFail", "");
			Assert.assertEquals("Purchase basket page was not loaded properly.", true, purchaseBasketPage);
		}
		
		//checkout order
		element.getWebElement(driver, "XPATH", "//button[contains(@id, 'checkoutButton')]").click();
		
		//----------SELECT ADDRESS
		//validate if redirected to select address page
		boolean selectAddress = validatePage.validatePage(driver, "XPATH", "//mat-card", 5, expUrlSelectAddress, 10);
		if(selectAddress) {
			reporter.logExtentReport(driver, "pass", "", "Select address page was loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "selectAddressPage", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Select address page was not loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "selectAddressPageFail", "");
			Assert.assertEquals("Select address page was not loaded properly.", true, selectAddress);
		}
		
		//select address then click continue
		waitElement.waitElementVisibility(driver, "XPATH", "//mat-cell[contains(.,'"+address+"')]/preceding-sibling::mat-cell/mat-radio-button", 5);
		element.getWebElement(driver, "XPATH", "//mat-cell[contains(.,'"+address+"')]/preceding-sibling::mat-cell/mat-radio-button").click();
		reporter.logExtentReport(driver, "screenShot", "selectAddress", "");
		
		//get address to validate delivery address
		addressDetails = element.getWebElement(driver, "XPATH", "//mat-cell[contains(text(), '"+address+"')]/following-sibling::mat-cell").getText();
		addressCountry = element.getWebElement(driver, "XPATH", "//mat-cell[contains(text(), '"+address+"')]/following-sibling::mat-cell/following-sibling::mat-cell").getText();

		element.getWebElement(driver, "XPATH", "//span[contains(.,'Continue')]").click();
		
		//----------DDELIVERY ADDRESS
		//validate if redirected to delivery address page
		boolean deliveryAddress = validatePage.validatePage(driver, "XPATH", "//mat-card", 5, expUrlDeliveryAddress, 5);
		if(deliveryAddress) {
			reporter.logExtentReport(driver, "pass", "", "Delivery address page was loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "deliveryAddressPage", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Select address page was not loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "deliveryAddressPageFail", "");
			Assert.assertEquals("Delivery address page was not loaded properly.", true, deliveryAddress);
		}
		
		delCity = element.getWebElement(driver, "XPATH", "(//div[contains(@class,'addressCont')]/div)[1]").getText();
		delAddressDetails = element.getWebElement(driver, "XPATH", "(//div[contains(@class,'addressCont')]/div)[2]").getText();
		delAddressCountry = element.getWebElement(driver, "XPATH", "(//div[contains(@class,'addressCont')]/div)[3]").getText();
		
		if(address.equals(delCity)) {
			reporter.logExtentReport(driver, "pass", "", "City is correct based on the address selected.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "City is incorrect based on the address selected.");
		}
		if(addressDetails.equals(delAddressDetails)) {
			reporter.logExtentReport(driver, "pass", "", "Address details is correct based on the address selected.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Address details is incorrect based on the address selected.");
		}
		if(addressCountry.equals(delAddressCountry)) {
			reporter.logExtentReport(driver, "pass", "", "Country is correct based on the address selected.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Country is incorrect based on the address selected.");
		}

		//select delivery speed then continue
		element.getWebElement(driver, "XPATH", "//mat-cell[contains(text(), '"+deliverySpeed+"')]/preceding-sibling::mat-cell/mat-radio-button").click();
		reporter.logExtentReport(driver, "screenShot", "deliverySpeed", "");
		element.getWebElement(driver, "XPATH", "//span[contains(.,'Continue')]").click();
		
		//----------PAYMENT OPTION
		//validate if redirected to payment option page
		boolean paymentOption = validatePage.validatePage(driver, "XPATH", "//mat-card", 5, expUrlPaymentOption, 5);
		if(deliveryAddress) {
			reporter.logExtentReport(driver, "pass", "", "Payment option page was loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "paymentOption", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Payment option page was not loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "paymentOptionFail", "");
			Assert.assertEquals("Payment option page was not loaded properly.", true, deliveryAddress);
		}
		
		//select credit card then continue
		element.getWebElement(driver, "XPATH", "//mat-cell[contains(text(), '"+creditCardName+"')]/preceding-sibling::mat-cell/mat-radio-button").click();
		reporter.logExtentReport(driver, "screenShot", "creditCardPage", "");
		element.getWebElement(driver, "XPATH", "//span[contains(.,'Continue')]").click();
		
		//----------ORDER SUMMARY
		//validate if redirected to order summary page
		boolean orderSummary = validatePage.validatePage(driver, "XPATH", "//mat-card", 5, expUrlOrderSummary, 5);
		if(orderSummary) {
			reporter.logExtentReport(driver, "pass", "", "Order summary page was loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "orderSummary", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Order summary page was not loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "orderSummaryFail", "");
			Assert.assertEquals("Order summary page was not loaded properly.", true, deliveryAddress);
		}
		
		//*NOTE: This is not the right way to do this.
		//Since I don't have access to the database, I have no option but to rely on UI to get the data by element which is not
		//a good practice. The right way to do this is by using the database to compare it to the output on UI.
		
		//check computation if correct
		String itemName;
		int itemQuantity = 0;
		double itemPrice = 0;
		double pricePerItem = 0;
		double expTotalItemsPrice = 0;
		//create list for item deatails
		ArrayList<List<Object>> allItemList = new ArrayList<List<Object>>();
		//lists that will be added to itemList
		ArrayList<Object> list = null;
		
		int rowItems = element.getWebElements(driver, "XPATH", "//mat-row").size();
		for(int i=1; i<=rowItems; i++) {
			int cellItem = element.getWebElements(driver, "XPATH", "(//mat-row)["+i+"]/mat-cell").size();
			list = new ArrayList<>();
			for(int c=1; c<=cellItem; c++) {
				if(c==2) { //get item name
					itemName = element.getWebElement(driver, "XPATH", "(//mat-row)["+i+"]/mat-cell["+c+"]").getText();
					list.add(itemName);
				}
				if(c==3) { //get item quantity
					itemQuantity = Integer.parseInt(element.getWebElement(driver, "XPATH", "(//mat-row)["+i+"]/mat-cell["+c+"]").getText());
					list.add(itemQuantity);
				}
				if(c==4) { //get item price
					itemPrice = Double.parseDouble(element.getWebElement(driver, "XPATH", "(//mat-row)["+i+"]/mat-cell["+c+"]").getText().replace("¤", ""));
					list.add(itemPrice);
				}
			}
			pricePerItem = (double) (itemQuantity*itemPrice);
			
			list.add(pricePerItem);
			
			expTotalItemsPrice += pricePerItem;

			//add all items to the list
			allItemList.add(list);
		}
		
		//check total item price
		double totalItemsPrice = Double.parseDouble(element.getWebElement(driver, "XPATH", "(//td[contains(@class, 'price')])[1]").getText().replace("¤", ""));
		if(expTotalItemsPrice==totalItemsPrice) {
			reporter.logExtentReport(driver, "pass", "", "Total price of items is correct.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Total price of items is incorrect.");
			reporter.logExtentReport(driver, "screenShot", "wrongTotalItemPrice", "");
			Assert.assertEquals("total price of items is not equal to expected.", expTotalItemsPrice, totalItemsPrice, 0.001);
		}
		
		///check total item price plus delivery
		double deliveryPrice = Double.parseDouble(element.getWebElement(driver, "XPATH", "(//td[contains(@class, 'price')])[2]").getText().replace("¤", ""));
		double itemPlusDelivery = deliveryPrice+expTotalItemsPrice;
		double total = Double.parseDouble(element.getWebElement(driver, "XPATH", "(//td[contains(@class, 'price')])[4]").getText().replace("¤", ""));
		
		if(itemPlusDelivery==total) {
			reporter.logExtentReport(driver, "pass", "", "Total price of items plus delivery is correct.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Total price of items plus delivery is incorrect.");
			reporter.logExtentReport(driver, "screenShot", "totalPrice", "");
			Assert.assertEquals("total price of items plus delivery is not equal to expected.", expTotalItemsPrice, totalItemsPrice, 0.001);
		}	

		reporter.logExtentReport(driver, "screenShot", "summaryOption", "");
		
		//click place order
		element.getWebElement(driver, "XPATH", "//span[contains(.,'Place your order and pay')]").click();
		
		//----------ORDER COMPLETION
		//validate if redirected to order completion page
		boolean orderCompletion = validatePage.validatePage(driver, "XPATH", "//mat-card", 5, expUrlOrderCompletion, 5);
		
		if(orderCompletion) {
			reporter.logExtentReport(driver, "pass", "", "Order completion page was loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "orderSummary", "");
		}else {
			reporter.logExtentReport(driver, "fail", "", "Order completion page was not loaded properly.");
			reporter.logExtentReport(driver, "screenShot", "orderCompletionFail", "");
			Assert.assertEquals("Order completion page was not loaded properly.", true, orderCompletion);
		}
		
		//validate order details
		for(int i=0; i<allItemList.size(); i++) {
			String orderItemName = null;
			for(int x=0; x<allItemList.get(i).size(); x++) {
				//compare item name
				if (x==0){
					orderItemName = (String) allItemList.get(i).get(x);
					if(allItemList.get(i).get(x).equals(element.getWebElement(driver, "XPATH", "(//mat-row)["+(i+1)+"]/mat-cell["+(x+1)+"]").getText())) {
						reporter.logExtentReport(driver, "pass", "", orderItemName + " item ordered successfully.");
					}else {
						reporter.logExtentReport(driver, "fail", "", orderItemName + " did not appear on the order list.");
					}
				}
				//compare item quantity
				if (x==1) {
					if(allItemList.get(i).get(x).equals(Integer.parseInt(element.getWebElement(driver, "XPATH", "(//mat-row)["+(i+1)+"]/mat-cell["+(x+2)+"]").getText()))){
						reporter.logExtentReport(driver, "pass", "", "The quantity for "+orderItemName+" is correct.");
					}else {
						reporter.logExtentReport(driver, "fail", "", "The quantity for "+orderItemName+" is incorrect.");
					}
				}
				//compare item price
				if (x==2) {
					if(allItemList.get(i).get(x).equals(Double.parseDouble(element.getWebElement(driver, "XPATH", "(//mat-row)["+(i+1)+"]/mat-cell["+(x)+"]").getText().replace("¤", "")))){
						reporter.logExtentReport(driver, "pass", "", "The price for "+orderItemName+" is correct.");
					}else {
						reporter.logExtentReport(driver, "fail", "", "The price for "+orderItemName+" is incorrect.");
					}
				}
				//compare total price per item
				if (x==3) { 
					if(allItemList.get(i).get(x).equals(Double.parseDouble(element.getWebElement(driver, "XPATH", "(//mat-row)["+(i+1)+"]/mat-cell["+(x+1)+"]").getText().replace("¤", "")))){
						reporter.logExtentReport(driver, "pass", "", "The total price for "+orderItemName+" is correct.");
					}else {
						reporter.logExtentReport(driver, "fail", "", "The total price for "+orderItemName+" is incorrect.");
					}
				}
			}
		}
		//compare total price of ordered items
		if(expTotalItemsPrice==Double.parseDouble(element.getWebElement(driver, "XPATH", "(//tr[contains(@class, 'mat-row')])[5]").getText().replace("¤", ""))) {
			reporter.logExtentReport(driver, "pass", "", "The total price for ordered items is correct.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "The total price for ordered items is incorrect.");
		}
		
		//compare delivery price
		if(deliveryPrice==Double.parseDouble(element.getWebElement(driver, "XPATH", "(//tr[contains(@class, 'mat-row')])[6]").getText().replace("¤", ""))) {
			reporter.logExtentReport(driver, "pass", "", "The delivery price for ordered items is correct.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "The delivery price for ordered items is incorrect.");
		}
		
		//compare total price
		if(total==Double.parseDouble(element.getWebElement(driver, "XPATH", "(//tr[contains(@class, 'mat-row')])[8]").getText().replace("¤", ""))) {
			reporter.logExtentReport(driver, "pass", "", "The total price for ordered items is correct.");
		}else {
			reporter.logExtentReport(driver, "fail", "", "The total price for ordered items is incorrect.");
		}

		reporter.logExtentReport(driver, "screenShot", "orderItemsCompleted", "");
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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		PayCreditCardEvent.address = address;
	}

	public String getExpUrlDeliveryAddress() {
		return expUrlDeliveryAddress;
	}

	public void setExpUrlDeliveryAddress(String expUrlDeliveryAddress) {
		PayCreditCardEvent.expUrlDeliveryAddress = expUrlDeliveryAddress;
	}

	public String getDeliverySpeed() {
		return deliverySpeed;
	}

	public void setDeliverySpeed(String deliverySpeed) {
		PayCreditCardEvent.deliverySpeed = deliverySpeed;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		PayCreditCardEvent.creditCardName = creditCardName;
	}
	
	
	public String getExpUrlPaymentOption() {
		return expUrlPaymentOption;
	}

	public void setExpUrlPaymentOption(String expUrlPaymentOption) {
		PayCreditCardEvent.expUrlPaymentOption = expUrlPaymentOption;
	}

	public String getExpUrlOrderSummary() {
		return expUrlOrderSummary;
	}

	public void setExpUrlOrderSummary(String expUrlOrderSummary) {
		PayCreditCardEvent.expUrlOrderSummary = expUrlOrderSummary;
	}

	public String getExpUrlOrderCompletion() {
		return expUrlOrderCompletion;
	}

	public void setExpUrlOrderCompletion(String expUrlOrderCompletion) {
		PayCreditCardEvent.expUrlOrderCompletion = expUrlOrderCompletion;
	}
	
	
}
