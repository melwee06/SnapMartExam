package snapmartexam.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import snapmartexam.pageevents.AddToBasketEvent;
import snapmartexam.pageevents.LandingPageEvent;
import snapmartexam.pageevents.LoginEvent;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.HandleTestData;

@RunWith(Parameterized.class)
public class AddToBasketTest {
	private static CuongnguyenTest baseTest;
	private static HandleTestData htd;
	private static ExtentReporter reporter;
	private static LandingPageEvent lp;
	private static LoginEvent le;
	private static AddToBasketEvent atbe;

	private static String filePath = "C:\\Users\\mrongavilla\\Documents\\CuongnguyenTestData\\";
	private static String fileName = "AddToBasketTest.xlsx";
	private static String sheetName = "AddToBasketSheet";
	private static final String reportName = "AddToBasketTest";
	private static final String testName = "AddToBasketTest";
	private static final String testDesc = "Tetsing add to basket through pagination.";
	private String username, password, expUrlLogin, expUrlSearch, expUrlBasket, itemName, quantity;
	
	public AddToBasketTest(String webBrowser, String webUrl, String username, String password, String expUrlLogin, String expUrlSearch,
			String expUrlBasket, String itemName, String quantity) {

		baseTest = new CuongnguyenTest(webBrowser, webUrl);
		reporter = new ExtentReporter();
		reporter.createExtentReport(reportName, testName, testDesc);
		this.username = username;
		this.password = password;
		this.expUrlLogin = expUrlLogin;
		this.expUrlSearch = expUrlSearch;
		this.expUrlBasket = expUrlBasket;
		this.itemName = itemName;
		this.quantity = quantity;
		
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
		//validate login page
		Assert.assertEquals(true, le.accessLogin(baseTest.driver, reporter));
		
		le.setUsername(username);
		le.setPassword(password);
		le.setExpUrlSearch(expUrlSearch);
		//validate if redirected to search page
		Assert.assertEquals(true, le.loginCorrectCredentials(baseTest.driver, reporter));
	}
	
//	@Test
	public void addItemToBasketThroughPaginationTest() {
		//Adding Orders to Basket and Validating Order count
		atbe = new AddToBasketEvent();
		
		LinkedHashMap<String, Integer> orderList = new LinkedHashMap<>();
		
		String[] itemList = itemName.split("~");
		String[] quantityList = quantity.split("~");
		
		for(int i=0; i<itemList.length; i++) {
			orderList.put(itemList[i], Integer.parseInt(quantityList[i]));
		}
		System.out.println(orderList);
		atbe.setOrders(orderList);
		Assert.assertEquals("addItemsToBasketThroughPagination", true, atbe.addItemsToBasketThroughPagination(baseTest.driver));
	}
	
	@Test
	public void addItemToBasketThroughSearchTest() {
		//Adding Orders to Basket and Validating Order count
		atbe = new AddToBasketEvent();
		
		LinkedHashMap<String, Integer> orderList = new LinkedHashMap<>();
		
		String[] itemList = itemName.split("~");
		String[] quantityList = quantity.split("~");
		
		for(int i=0; i<itemList.length; i++) {
			orderList.put(itemList[i], Integer.parseInt(quantityList[i]));
		}
		System.out.println(orderList);
		atbe.setOrders(orderList);
		Assert.assertEquals("addItemsToBasketThroughSearch", true, atbe.addItemsToBasketThroughSearch(baseTest.driver));
	}
	
	@After
	public void afterTest() {
		//Validate Added Orders on Basket Page
		atbe.setExpUrlBasket(expUrlBasket);
		Assert.assertEquals("checkAddedItems", true, atbe.checkAddedItems(baseTest.driver));
		
		baseTest.driver.quit();
	}
}
