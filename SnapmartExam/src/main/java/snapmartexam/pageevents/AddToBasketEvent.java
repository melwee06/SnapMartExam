package snapmartexam.pageevents;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import snapmartexam.utils.ExplicitWait;
import snapmartexam.utils.FetchElement;
import snapmartexam.utils.ValidatePage;

public class AddToBasketEvent {
	FetchElement element = new FetchElement();
	ExplicitWait waitElement = new ExplicitWait();
	ValidatePage validatePage = new ValidatePage();
	private static LinkedHashMap<String, Integer> orders, addedItemsMap;
	private static String expUrlBasket;
	private static int successAdded=0, count;
		
	public boolean addItemsToBasketThroughPagination(WebDriver driver) {
		boolean addItemCheckP = false;
		//get initial value of order count
		count = Integer.parseInt(element.getWebElement(driver, "XPATH", "//span[contains(@class,'warn-notification')]").getText());
		
		addedItemsMap = new LinkedHashMap<>();
		
		//pagination
		WebElement next = element.getWebElement(driver, "XPATH", "//button[contains(@aria-label,'Next page')]");
		WebElement prev = element.getWebElement(driver, "XPATH", "//button[contains(@aria-label,'Previous page')]");
		String nextDisabled, previousDisabled;
		String prevItem = "";
		
		for(Map.Entry<String, Integer> orders : orders.entrySet()) {
			boolean itemVisible = element.getWebElements(driver, "XPATH", "//div[contains(@class, 'item-name') and contains(text(),'"+orders.getKey()+"')]/../../following-sibling::div/button").size()!=0;
			
			do{
				boolean checkVisible = element.getWebElements(driver, "XPATH", "//div[contains(@class, 'item-name') and contains(text(),'"+orders.getKey()+"')]/../../following-sibling::div/button").size()!=0;
				
				//click only if not visible to the current page
				if(!checkVisible) {
					//if next enabled & prev name <= current name
					if((prevItem.compareTo(orders.getKey())<=0)) {
						next.click();
					}else {
						prev.click();
					}
				}
				
				//set navigation buttons value
				nextDisabled = next.getAttribute("disabled");
				previousDisabled = prev.getAttribute("disabled");
				
				//check if current item is already visible
				if(checkVisible) {
					prevItem=orders.getKey();
					//proceed to click add item
					break;
				}
			}while(!itemVisible);
			
			addItemCheckP = addNumberofItems(driver, orders.getKey(), orders.getValue());
		}
		return addItemCheckP;
	}
	
	public boolean addItemsToBasketThroughSearch(WebDriver driver) {
		boolean addItemCheckS = false;
		element.getWebElement(driver, "XPATH", "//mat-icon[contains(.,'search')]").click();
		addedItemsMap = new LinkedHashMap<>();
		
		for(Map.Entry<String, Integer> orders : orders.entrySet()) {
			WebElement searchInput = element.getWebElement(driver, "XPATH", "//input[@id='mat-input-0']");
			if(searchInput.getAttribute("value").equals("")) {
				searchInput.sendKeys(orders.getKey());
			}else {
				searchInput.sendKeys(Keys.CONTROL, "a");
				searchInput.sendKeys(orders.getKey());
			}
			searchInput.sendKeys(Keys.ENTER);
			
			//check item if searchable
			boolean notFound = element.getWebElements(driver, "XPATH", "//mat-card/img[contains(@alt,'No results found')]").size()!=0;
			if(!notFound) {
				addItemCheckS = addNumberofItems(driver, orders.getKey(), orders.getValue());
			}
		}
		return addItemCheckS;
	}
	
	public boolean addNumberofItems(WebDriver driver, String key, int value) {
		boolean countUpdated = true;
		//number of clicks referencing order count from orders value
		for(int i=0; i<value; i++) {
			//click order referencing order name from orders key
			element.getWebElement(driver, "XPATH", "//div[contains(@class, 'item-name') and contains(text(),'"+key+"')]/../../following-sibling::div/button").click();
			
			if(element.getWebElements(driver, "XPATH", "//snack-bar-container[contains(@class,'confirmBar')]").size()!=0) {
				final WebElement notifElement = element.getWebElement(driver, "XPATH", "//span[contains(@class,'warn-notification')]");
				//wait for ajax to kick in
				new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver input) {
						// TODO Auto-generated method stub
						return Integer.parseInt(notifElement.getText())>count;
					}});
				int newCount = Integer.parseInt(notifElement.getText());
				System.out.println("count: " + count + " | " + "newcount" + newCount);
				//check if count is being updated every addition of available item
				countUpdated = newCount>count;
				Assert.assertEquals("Count not being updated", true,countUpdated);
				count=newCount;
				successAdded++;
			}
			
		}
		//populate map of success addition
		if(successAdded>0) {
			addedItemsMap.put(key, successAdded);
			successAdded=0;
		}
		return countUpdated;
	}
	
	public boolean checkAddedItems(WebDriver driver) {
		boolean basketCheck = false;
		element.getWebElement(driver, "XPATH", "(//span[contains(.,'Your Basket')])[2]").click();
		//validate if redirected to purchase basket page
		validatePage.validatePage(driver, "XPATH", "//app-purchase-basket", 5, expUrlBasket, 5);
		
		//validate purchase basket list from the added items
		for(Map.Entry<String, Integer> orders : addedItemsMap.entrySet()) {
			System.out.println("key: " + orders.getKey() + " | value: " + orders.getValue());
			List<WebElement> item = element.getWebElements(driver, "XPATH", "//mat-cell[contains(.,'"+orders.getKey()+"')]");
			
			//check if added item is existing in the purchase basket
			if(item.size()!=0 && orders.getValue()>0) {
				basketCheck = true;
				int numberOfOrders = Integer.parseInt(element.getWebElement(driver, "XPATH", "//mat-cell[contains(.,'"+orders.getKey()+"')]/following-sibling::mat-cell/span").getText());
				boolean countUpdated = numberOfOrders>=orders.getValue();
				//check if added item count was updated
				basketCheck = countUpdated;
				Assert.assertEquals("Count was updated on basket page", true, countUpdated);
			}else {
				basketCheck = false;
				Assert.assertEquals("Available item is not displayed on the basket", true, basketCheck);
			}
			
		}
		return basketCheck;
	}
	
	public LinkedHashMap<String, Integer> getOrders() {
		return orders;
	}
	
	public void setOrders(LinkedHashMap<String, Integer> orders) {
		AddToBasketEvent.orders = orders;
	}
	
	public String getExpUrlBasket() {
		return expUrlBasket;
	}

	public void setExpUrlBasket(String expUrlBasket) {
		AddToBasketEvent.expUrlBasket = expUrlBasket;
	}
}
