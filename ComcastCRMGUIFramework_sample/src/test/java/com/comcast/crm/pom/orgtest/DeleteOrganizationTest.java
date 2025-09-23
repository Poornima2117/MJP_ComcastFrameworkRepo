package com.comcast.crm.pom.orgtest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrganizationTest {

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
		String orgName = excelU.getDataFromExcel("Org", 10, 2) + javau.getRandonNumber();
		
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
		hp.getOrglink().click();
		
		//hp.navigateToCampaignsPage();
		
		//Click on Create organization button
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//Enter all the details and create new organization
		
		CreatingNewOrganizationPage cop=new CreatingNewOrganizationPage(driver);
		cop.createOrg(orgName);
		
		//Verify Header msg expected result 
		OrganizationsInfoPage oip=new OrganizationsInfoPage(driver);
		String actorgName = oip.getHeaderMsg().getText();
		if(actorgName.contains(orgName))
		{
			System.out.println(orgName + "name is varified == PASS");
		}
		else {
			System.out.println(orgName + "name is not varified == FAIL");
		}
		
		//go back to organizations page
		hp.getOrglink().click();
		
		//Search for organization
		op.getSearchEdt().sendKeys(orgName);
		webu.select(op.getSearchfieldDD(), "Organization Name");
		op.getSearchBtn().click();
		
		//In dynamic web table select and delete organization
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		//logout
		
		hp.logout();
		driver.quit();

	}

}
