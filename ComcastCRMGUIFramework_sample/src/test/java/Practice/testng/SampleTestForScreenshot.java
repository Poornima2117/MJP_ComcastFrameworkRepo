package Practice.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTestForScreenshot {
	@Test
	public void amazonTest() throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		
		//step-1 : Create an object to EventFiring Webdriver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		//step-2 : use getScreenshotAs method to get file type of screenshot
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		
		//Step-3 :Store screenshot on local driver
		FileUtils.copyFile(srcFile, new File("./test-output/test.png"));
	}
}
