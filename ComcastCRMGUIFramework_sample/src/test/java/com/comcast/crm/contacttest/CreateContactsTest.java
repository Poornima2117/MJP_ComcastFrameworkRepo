package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.comcast.crm.basetest.BaseClass;

public class CreateContactsTest extends BaseClass {

@Test
public void createContact() throws Throwable {
		// Read Test Script data from Excel File
		String lastName = excelU.getDataFromExcel("Con", 1, 2) + javau.getRandonNumber();

		// Navigate to organization module
		driver.findElement(By.linkText("Contacts")).click();

		// Click on Create organization button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Header contact info expected result
		String actlastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actlastName.equals(lastName)) {
			System.out.println(lastName + "Information is created===Pass");
		} else {
			System.out.println(lastName + "Information is not created===Fail");
		}

	}

}
