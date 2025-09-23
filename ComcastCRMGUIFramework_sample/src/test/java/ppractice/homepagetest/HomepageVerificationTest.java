package ppractice.homepagetest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomepageVerificationTest {
	@Test
	public void homePageTest(Method mtd) {
		
		System.out.println(mtd.getName() + "Test start");
		String expectedPage = "Home";
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("admin");
	driver.findElement(By.id("submitButton")).click();
		
	String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
	//Hard Assert	
    Assert.assertEquals(actTitle, expectedPage);
	driver.close();
	System.out.println(mtd.getName() + "Test End");
	}
	
	@Test
	public void verifyLogopageTest(Method mtd) {
		
		System.out.println(mtd.getName() + "Test start");
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		//Hard Assert
		Assert.assertTrue(status);
		driver.close();
		System.out.println(mtd.getName() + "Test End");
	}

}
