package snapmartexam.test;

import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import snapmartexam.pageevents.LandingPageEvent;
import snapmartexam.utils.ExtentReporter;
import snapmartexam.utils.HandleTestData;

@RunWith(Parameterized.class)
public class AccessLandingPageTest {
	private static CuongnguyenTest baseTest;
	private static HandleTestData htd;
	private static ExtentReporter reporter;
	private static LandingPageEvent lp;
	
	private static final String filePath = "C:\\Users\\mrongavilla\\Documents\\CuongnguyenTestData\\";
	private static final String fileName = "AccessLandingPageTest.xlsx";
	private static final String sheetName = "LandingSheet";
	private static final String reportName = "AccessLandingPageTest";
	private static final String testName = "AccessLandingPageTest";
	private static final String testDesc = "Tetsing landing page if being loaded properly.";
	
	public AccessLandingPageTest(String webBrowser, String webUrl) {
		baseTest = new CuongnguyenTest(webBrowser, webUrl);
		reporter = new ExtentReporter();
		reporter.createExtentReport(reportName, testName, testDesc);
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
	
	@Test
	public void accessLandingPageTest() {
		lp = new LandingPageEvent();
		lp.accessLandingPage(baseTest.driver, reporter);
	}
	
	@After
	public void afterTest() {
		baseTest.driver.quit();
	}
	
}
