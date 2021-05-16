package snapmartexam.test;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import snapmartexam.pageevents.LandingPageEvent;
import snapmartexam.pageevents.LoginEvent;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.HandleTestData;

@RunWith(Parameterized.class)
public class AccessLoginTest {
	private static CuongnguyenTest baseTest;
	private static HandleTestData htd;
	private static ExtentReporter reporter;
	private static LandingPageEvent lp;
	private static LoginEvent le;

	private static final String filePath = "C:\\Users\\mrongavilla\\Documents\\CuongnguyenTestData\\";
	private static final String fileName = "AccessLoginTest.xlsx";
	private static final String sheetName = "AccessLoginSheet";
	private static final String reportName = "AccessLoginTest";
	private static final String testName = "AccessLoginTest";
	private static final String testDesc = "Tetsing login page if being loaded properly.";
	private String expUrlLogin;
	
	public AccessLoginTest(String webBrowser, String webUrl, String expUrlLogin) {
		baseTest = new CuongnguyenTest(webBrowser, webUrl);
		reporter = new ExtentReporter();
		reporter.createExtentReport(reportName, testName, testDesc);
		this.expUrlLogin = expUrlLogin;
	}
	
	@Parameterized.Parameters
	public static Object[][] testData() throws IOException {
		htd = new HandleTestData();
		htd.setFilePath(filePath);
		htd.setFileName(fileName);
		htd.setSheetName(sheetName);
		htd.readTestData();
		System.out.println("testData: "+htd.getTestData());
	    return htd.getTestData();
	}
	
	@Before
	public void accessLandingPage() {
		lp = new LandingPageEvent();
		lp.accessLandingPage(baseTest.driver, reporter);
	}
	
	@Test
	public void accessLoginTest() {
		le = new LoginEvent();
		le.setExpUrlLogin(expUrlLogin);
		//validate login page
		Assert.assertEquals("accessLogin", true, le.accessLogin(baseTest.driver, reporter));
	}
	
	@After
	public void afterTest() {
		baseTest.driver.quit();
	}
}
