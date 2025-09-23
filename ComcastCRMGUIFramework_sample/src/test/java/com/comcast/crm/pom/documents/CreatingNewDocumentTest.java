package com.comcast.crm.pom.documents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewDocumentPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatingNewDocumentTest {

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
		String titleName = excelU.getDataFromExcel("Doc", 3, 2) + javau.getRandonNumber();
		String folderName = excelU.getDataFromExcel("Doc", 3, 3);
		
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
		
		//Navigate to document module
		HomePage hp=new HomePage(driver);
		hp.getDocumentslink().click();
		
		CreatingNewDocumentPage cndp=new CreatingNewDocumentPage(driver);
		cndp.getCreateNewDocBtn().click();
		
		cndp.createNewDocs(titleName, folderName);

		//Verify Header msg expected result 
		String actTitleName = cndp.getHeaderText().getText();
		if(actTitleName.contains(titleName))
		{
			System.out.println(titleName + "name is varified == PASS");
		}
		else {
			System.out.println(titleName + "name is not varified == FAIL");
		}
		
		//logout
		hp.logout();
		driver.quit();
	}

}
