package com.comcast.crm.pom.quickcreate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.ProductCreateInfoPage;
import com.comcast.crm.objectrepositoryutility.QuickCreatePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class QuickCreateTest {

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
				String productNameOpt = excelU.getDataFromExcel("QuickCreate", 1, 0);
				String productName = excelU.getDataFromExcel("QuickCreate", 1, 1) + javau.getRandonNumber();
				String productPrice = excelU.getDataFromExcel("QuickCreate", 1, 2);
				String productQty = excelU.getDataFromExcel("QuickCreate", 1, 3);
				String projectNameOpt = excelU.getDataFromExcel("QuickCreate", 2, 0);
				String projectName = excelU.getDataFromExcel("QuickCreate", 4, 1) + javau.getRandonNumber();
				
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
				
				//Login to application
				
				webu.waitForPageToLoad(driver);
				//driver.get(URL);
				
				LoginPage lp=new LoginPage(driver);
				lp.loginToApp(URL,USER, PASS);
				
				//Navigate to QuickCreate module
				HomePage hp=new HomePage(driver);
				hp.navogateToQuickCreateDD(productNameOpt);
				
				QuickCreatePage qp=new QuickCreatePage(driver);
				qp.createProduct(productName, productPrice, productQty);
				
				//Verify Header msg expected result 
				ProductCreateInfoPage pcp=new ProductCreateInfoPage(driver);
				String acproName = pcp.getHeaderText().getText();
				if(acproName.contains(productName))
				{
					System.out.println(productName + "name is varified == PASS");
				}
				else {
					System.out.println(productName + "name is not varified == FAIL");
				}
				
				qp.navogateToNewProjectDD(projectNameOpt);
				
				String startDate = javau.getSystemDate();
				String endDate = javau.getRequiredDare(30);
				
				qp.createProject(projectName, startDate, endDate);
				
				//Verify Header msg expected result 
				ProductCreateInfoPage pcp1=new ProductCreateInfoPage(driver);
				String acprojName = pcp1.getHeaderText1().getText();
				if(acprojName.contains(projectName))
				{
					System.out.println(projectName + "name is varified == PASS");
				}
				else {
					System.out.println(projectName + "name is not varified == FAIL");
				}
				
				qp.deletePriJ();
				System.out.println("Project deteted");
				
				//logout
				hp.logout();
				driver.quit();
	}

}
