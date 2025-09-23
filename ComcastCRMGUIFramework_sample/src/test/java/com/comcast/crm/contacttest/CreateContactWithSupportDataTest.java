package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithSupportDataTest {

	public static void main(String[] args) throws Throwable {
		
		//Create Objects
		FileUtility fileU=new FileUtility();
		ExcelUtility excelU=new ExcelUtility();
		JavaUtility javau=new JavaUtility();
		WebDriverUtility webu=new WebDriverUtility();
				
		//Read Data from property File
		String BROWSER = fileU.getDataFromPropertiesFile("browser");
		String URL = fileU.getDataFromPropertiesFile("url");
		String USER = fileU.getDataFromPropertiesFile("username");
		String PASS = fileU.getDataFromPropertiesFile("password");
		
		//Read Test Script data from Excel File
		String lastName = excelU.getDataFromExcel("Con", 4, 2) + javau.getRandonNumber();
		
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
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USER);
		driver.findElement(By.name("user_password")).sendKeys(PASS);
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to organization module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on Create organization button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		String startDate = javau.getSystemDate();
		String endDate = javau.getRequiredDare(30);
		
		//Enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Startdate and EndDate
		String actstartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstartDate.equals(startDate))
		{
			System.out.println(startDate+ "Information is created===Pass");
		}
		else {
			System.out.println(startDate+ "Information is not created===Fail");
		}
		
		String actendtDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actendtDate.equals(endDate))
		{
			System.out.println(endDate+ "Information is created===Pass");
		}
		else {
			System.out.println(endDate+ "Information is not created===Fail");
		}
		
		//logout
		driver.quit();

	}

}
