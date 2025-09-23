package Practice.testng;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetProductinfoTest {
	
	@Test(dataProvider = "getData")
	public void getproductinfoTest(String brandName , String productName) {
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver =new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//capture product info
		String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span[1]/span/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
	}
	@DataProvider
	public Object[] [] getData() throws Throwable {
		ExcelUtility elib= new ExcelUtility();
		int rowcount = elib.getRowcount("product");
		
		Object[] [] objArr = new Object[rowcount] [2];
		
		for(int i=0; i<rowcount; i++) {
		objArr[i] [0] = elib.getDataFromExcel("product", i+1,0);
		objArr[i] [1] = elib.getDataFromExcel("product", i+1,1);
			
		}	
		return objArr;
	}

}
