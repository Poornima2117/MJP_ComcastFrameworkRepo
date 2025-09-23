package com.comcast.crm.pom.documents;

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
import com.comcast.crm.objectrepositoryutility.DocumentsPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DocumentsFolderCreationAndDeletion {

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
				String folderName = excelU.getDataFromExcel("Doc", 1, 2) + javau.getRandonNumber();
				String description = excelU.getDataFromExcel("Doc", 1, 3);
				
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
				webu.waitForPageToLoad(driver);;
				//driver.get(URL);
				
				LoginPage lp=new LoginPage(driver);
				lp.loginToApp(URL,USER, PASS);
				
				//Navigate to organization module
				HomePage hp=new HomePage(driver);
				hp.getDocumentslink().click();
				
				DocumentsPage dp=new DocumentsPage(driver);
				dp.addFolder(folderName, description);
				
				Thread.sleep(5000);
				dp.getViewEmptyFoldersBtn().click();
				Thread.sleep(5000);
				
				driver.findElement(By.xpath("//td[text()='"+folderName+"']/following-sibling::td/a")).click();
				
				//Alert alert = driver.switchTo().alert();
				//alert.accept();
				
				System.out.println("folder is deleted");
				
				//logout
				hp.logout();
				driver.quit();

	}

}
