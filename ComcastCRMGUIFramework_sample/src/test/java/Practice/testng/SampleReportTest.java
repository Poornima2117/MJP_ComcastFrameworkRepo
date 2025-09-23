package Practice.testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleReportTest {
	public ExtentReports report;
	
	@BeforeSuite
	public void configBS() {
		//Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./Advancereport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add ENV information & create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");	
	}
	@AfterSuite
	public void configAS() {
		report.flush();
	}
@Test
public void createContactTest() {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.get("http://49.249.28.218:8888/");
	//Take screenshot
	TakesScreenshot eDriver=(TakesScreenshot)driver;
	String filepath = eDriver.getScreenshotAs(OutputType.BASE64);
	

	ExtentTest test = report.createTest("createContactTest");
	
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact");
	test.log( Status.INFO, "create contact");
	if("HDFC".equals("HDFC")) {
		test.log(Status.PASS, "contact is created");
	}else {
		test.addScreenCaptureFromBase64String(filepath, "ErrorFile");
	}
	report.flush();
}
@Test
public void createContactWithORG() {

	ExtentTest test = report.createTest("createContactwithORG");

	
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact");
	test.log( Status.INFO, "create contact");
	if("HDFC".equals("HDFC")) {
		test.log(Status.PASS, "contact is created");
	}else {
		test.log(Status.FAIL, "contact is not created");
	}
	report.flush();
}

@Test
public void createContactWithPhoneNumber() {

	ExtentTest test = report.createTest("createContacteithORG");

	
	test.log(Status.INFO, "login to app");
	test.log(Status.INFO, "navigate to contact");
	test.log( Status.INFO, "create contact");
	if("HDFC".equals("HDFC")) {
		test.log(Status.PASS, "contact is created");
	}else {
		test.log(Status.FAIL, "contact is not created");
	}
	report.flush();
}
}