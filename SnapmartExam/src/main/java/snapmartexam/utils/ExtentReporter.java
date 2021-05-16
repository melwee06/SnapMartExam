package snapmartexam.utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	private static ExtentReports extent;
	private static ExtentTest test;
	private static TakeScreenCapture tsc;
	
	public void createExtentReport(String reportName, String testName, String testDesc) {
		// start reporters
	    ExtentSparkReporter report = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + reportName + ".html");
	    report.config().setTheme(Theme.DARK);

	    // create ExtentReports and attach reporter(s)
	    extent = new ExtentReports();
	    extent.attachReporter(report);
	    
	    // creates a toggle for the given test, adds all log events under it    
	    test = extent.createTest(testName, testDesc);
	}
	
	public void logExtentReport(WebDriver driver, String log, String ssName, String logMessage) {
		tsc = new TakeScreenCapture();
		switch(log) {
		case "screenShot":
			test.addScreenCaptureFromPath(tsc.getScreenShot(driver, ssName));
			break;
		case "screenShotFail":
			test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(tsc.getScreenShot(driver, ssName)).build());
			break;
		case "pass":
			test.pass(logMessage);
			break;
		case "fail":
			test.fail(logMessage);
			break;
		}
		extent.flush();
	}
}
