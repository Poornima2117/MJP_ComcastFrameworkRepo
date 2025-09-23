package com.comcast.crm.orgtest;

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

public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {
		
		//Create Objects
		FileUtility fileU=new FileUtility();
		ExcelUtility excelU=new ExcelUtility();
		JavaUtility javau=new JavaUtility();
		WebDriverUtility webu=new WebDriverUtility();
				
		//Read Data frome property File
		String BROWSER = fileU.getDataFromPropertiesFile("browser");
		String URL = fileU.getDataFromPropertiesFile("url");
		String USER = fileU.getDataFromPropertiesFile("username");
		String PASS = fileU.getDataFromPropertiesFile("password");
		
		//Read Test Script data from Excel File
		String orgName = excelU.getDataFromExcel("Org", 1, 2) + javau.getRandonNumber();
		
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
		driver.findElement(By.linkText("Organizations")).click();
		
		//Click on Create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Header msg expected result 
		String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(HeaderInfo.contains(orgName))
		{
			System.out.println(orgName+ "is created===Pass");
		}
		else {
			System.out.println(orgName+ "is not created===Fail");
		}
		
		//Verify Header organization name info expected result
		String actorgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actorgName.equals(orgName))
		{
			System.out.println(orgName+ "Information is created===Pass");
		}
		else {
			System.out.println(orgName+ "Information is not created===Fail");
		}
		
		//logout
		driver.quit();

	}

}
