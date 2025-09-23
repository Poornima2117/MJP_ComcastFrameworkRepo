package com.comcast.crm.basetest;

import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObjrct;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.experimental.UtilityClass;
//@Listeners(com.comcast.crm.listnerutility.ListnerimpClass.class)
public class BaseClass {
	// Create Objects
	public DatabaseUtility dblib = new DatabaseUtility();
	public FileUtility fileU = new FileUtility();
	public ExcelUtility excelU = new ExcelUtility();
	public JavaUtility javau = new JavaUtility();
	public WebDriverUtility webu = new WebDriverUtility();

	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	public ExtentSparkReporter spark;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("===Connect to DB , Report config===");
		dblib.getDbconnection();
		
	}

	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("==Launch the BROWSER==");
	String BROWSER = fileU.getDataFromPropertiesFile("browser");
		
		if (BROWSER.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObjrct.setDriver(driver);
	}

	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("==Login==");
		String USERNAME = fileU.getDataFromPropertiesFile("username");
		String PASSWORD = fileU.getDataFromPropertiesFile("password");
		String URL=fileU.getDataFromPropertiesFile("url");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("==Close the BROWSER==");
		driver.quit();
	}

	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void config() throws SQLException {
		System.out.println("===Close CB , Report backup===");
		dblib.closeDbconnection();
		
	}

}
