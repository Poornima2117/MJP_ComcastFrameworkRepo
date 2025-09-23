package com.comcast.crm.pom.orgtest;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObjrct;
import com.comcast.crm.listnerutility.ListnerimpClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void creatOrganization_Test() throws Throwable {
			
			
			UtilityClassObjrct.getTest().log(Status.INFO, "read data from Excel");
			
			// Read Test Script data from Excel File
			String orgName = excelU.getDataFromExcel("Org", 1, 2) + javau.getRandonNumber();

			// Navigate to organization module
			UtilityClassObjrct.getTest().log(Status.INFO, "Navigate to Org page");
			HomePage hp = new HomePage(driver);
			hp.getOrglink().click();

			// hp.navigateToCampaignsPage();

			// Click on Create organization button
			UtilityClassObjrct.getTest().log(Status.INFO, "Navigate to create Org page");
			OrganizationsPage op = new OrganizationsPage(driver);
			op.getCreateNewOrgBtn().click();

			// Enter all the details and create new organization
			UtilityClassObjrct.getTest().log(Status.INFO, "Create a new Org");
			CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);

			cop.createOrg(orgName);
			UtilityClassObjrct.getTest().log(Status.INFO, orgName + "====>Create a new Org<====");

			// Verify Header msg expected result
			OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
			String actorgName = oip.getHeaderMsg().getText();
			if (actorgName.contains(orgName)) {
				System.out.println(orgName + "name is varified == PASS");
			} else {
				System.out.println(orgName + "name is not varified == FAIL");
			}
	}

	@Test(groups = "regressionTest")
	public void CreateOrganizationWithPhoneNumber_Test() throws Throwable {

		// Read Test Script data from Excel File
		String orgName = excelU.getDataFromExcel("Org", 7, 2) + javau.getRandonNumber();
		String phone = excelU.getDataFromExcel("Org", 7, 3);

		// Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Click on Create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Enter all the details and create new organization

		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(orgName, phone);

		// Verify Header phone number info expected result
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		String actphone = oip.getHeaderMsg1().getText();
		if (actphone.equals(phone)) {
			System.out.println(phone + "Information is varified == PASS");
		} else {
			System.out.println(phone + "Information is not varified == FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void CreatOrganizationWithType_Test() throws Throwable {

		// Read Test Script data from Excel File
		String orgName = excelU.getDataFromExcel("Org", 4, 2) + javau.getRandonNumber();
		String industry = excelU.getDataFromExcel("Org", 4, 3);
		String type = excelU.getDataFromExcel("Org", 4, 4);

		// Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Click on Create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Enter all the details and create new organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(orgName, industry, type);

		// Verify the dropdown industry and type
		OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
		Thread.sleep(2000);
		String actindustry = oip.getIndustry().getText();
		if (actindustry.equals(industry)) {
			System.out.println(industry + "Information is Verified===Pass");
		} else {
			System.out.println(industry + "Information is not Verified===Fail");
		}

		String acttype = oip.getType().getText();
		if (acttype.equals(type)) {
			System.out.println(orgName + "Information is Verified===Pass");
		} else {
			System.out.println(orgName + "Information is not Verified===Fail");
		}

	}

}
