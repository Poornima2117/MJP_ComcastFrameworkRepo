package com.comcast.crm.pom.calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CalendarPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarChaneOwnerTest {
	
	public static void main(String[] args) throws Throwable {
		
		String SubjectName="Disucssion";

	//Create Objects
			FileUtility fileU=new FileUtility();
			ExcelUtility excelU=new ExcelUtility();
			WebDriverUtility webu=new WebDriverUtility();
					
			//Read Data frome property File
			String BROWSER = fileU.getDataFromPropertiesFile("browser");
			String URL = fileU.getDataFromPropertiesFile("url");
			String USER = fileU.getDataFromPropertiesFile("username");
			String PASS = fileU.getDataFromPropertiesFile("password");
			
			//Read Test Script data from Excel File
			String ownerGroup = excelU.getDataFromExcel("Cal", 1, 0);
			
			WebDriver driver=null;
			if(BROWSER.equals("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if (BROWSER.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else if (BROWSER.equals("edge"))
			{
				driver=new EdgeDriver();
			}
			else {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			
			//Login to app
			webu.waitForPageToLoad(driver);
			//driver.get(URL);
			
			LoginPage lp=new LoginPage(driver);
			lp.loginToApp(URL,USER, PASS);
			
			//Navigate to organization module
			
			HomePage hp=new HomePage(driver);
			hp.getCalendarlink().click();
			
			CalendarPage cp=new CalendarPage(driver);
			cp.getAllEventslink().click();
			driver.findElement(By.xpath("//a[text()='"+SubjectName+"']/../../td[1]")).click();
			cp.getChangeOwnerBtn().click();
			
			cp.selectoptions(ownerGroup);
			

			hp.logout();
			driver.quit();
}
}
