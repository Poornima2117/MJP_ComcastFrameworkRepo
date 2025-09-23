package com.comcast.crm.pom.orgtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoHealing {
	
	@FindBy(name = "user_name")
	private WebElement username;
	
	@FindBy(name = "user_password")
	private WebElement password;
	
	@FindAll({@FindBy(id = "sub"),@FindBy(xpath = "//input[@type='submit']")})
	private WebElement loginBtn; // it will work if any locator is correct (Autohealing)
	
	//@FindBys({@FindBy(id = "sub"),@FindBy(xpath = "//input[@type='submit']")})
	//private WebElement loginBtn; // it will not work if any one locator is not correct (Not Autohealing)
	
	@Test
	public void login()
	{
		WebDriver driver=null;
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.get("http://49.249.28.218:8888/");
		
		AutoHealing ah=PageFactory.initElements(driver, AutoHealing.class);
		ah.username.sendKeys("admin");
		ah.password.sendKeys("admin");
		ah.loginBtn.click();
	}

}
