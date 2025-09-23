package com.comcast.crm.pom.opportunities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOpportunityPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OpportunitiesInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.SwitchToWindowPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunitiesTest {
	
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
		String oppName = excelU.getDataFromExcel("Oppo", 1, 2) + javau.getRandonNumber();
		
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
		
		hp.getOpportunitieslink().click();
		
		CreatingNewOpportunityPage cnp=new CreatingNewOpportunityPage(driver);
		cnp.getCreateNewOpp().click();
		cnp.getOppNameEdt().sendKeys(oppName);
		cnp.getSelecticon().click();
		
		//switch to chiled window
				webu.switchToTabOnURL(driver, "module=Accounts");
				
				SwitchToWindowPage sw=new SwitchToWindowPage(driver);
				sw.getSearchText().sendKeys(orgName);
				sw.getSearchBtn().click();
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				
				//switch to parent window
				
				webu.switchToTabOnURL(driver, "Contacts&action");
				cnp.getSaveBtn().click();
				
				//Verify Header msg expected result 
				OpportunitiesInfoPage oip1=new OpportunitiesInfoPage(driver);
				String HeaderInfo = oip1.getHeaderMsg1().getText();
						if(HeaderInfo.contains(oppName))
						{
							System.out.println(oppName+ " Header verified===Pass");
						}
						else {
							System.out.println(oppName+ " Header not verified===Fail");
						}
						
				//logout
				driver.quit();
}

}
