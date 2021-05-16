package snapmartexam.test;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Parameterized.class)
public class CuongnguyenTest {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public CuongnguyenTest(String webBrowser, String webUrl){
		setupDriver(webBrowser);
		driver.get(webUrl);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public static void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
	}
	
////	@Test
//	public void payUsingCreditCardTest() {
//		loginWithCorrectCredentialsTest();
//		PayCreditCardEvent pce = new PayCreditCardEvent();
//		//Selecting Address
//		pce.setExpUrlPurchaseBasket("https://test.cuongnguyen.online/#/basket");
//		pce.setExpUrlSelectAddress("https://test.cuongnguyen.online/#/address/select");
//		pce.setAddress("Laguna");
//		pce.selectAddress();
//	}
	
}
