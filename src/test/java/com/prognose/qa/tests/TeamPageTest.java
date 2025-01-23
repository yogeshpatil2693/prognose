package com.prognose.qa.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.prognose.qa.base.BaseTest;

public class TeamPageTest extends BaseTest {
	
	Faker faker = new Faker();
	
	@BeforeClass
	public void setUpTeamPage() {
		loginPage.navigateToTeamembersPage(prop.getProperty("teamembersPage"));
		loginPage.doLogin(prop.getProperty("adminemail"), prop.getProperty("adminpassword"));
	}
	
	@Test(invocationCount = 1)
	public void addMemberToTeam() {
		
		teamPage.clickOnAddMemberBtn();
		teamPage.enterFirstName(faker.address().firstName());
		teamPage.enterLastName(faker.address().lastName());
		teamPage.clickOnSalariedEmployeeChkBx();
		teamPage.clickOnNextBtn();
		teamPage.enterTitle("Manager");
		teamPage.enterPAN(faker.numerify("AB##CD"));
		teamPage.clickOnNextBtn();
		teamPage.enterEmail(faker.internet().emailAddress());
		System.out.println(faker.internet().emailAddress());
		teamPage.enterPassword("Test@12345");
		teamPage.clickOnEmailDetails();
		teamPage.clickOnSaveBtn();
		teamPage.refreshPage();
		
	}
	
	

}
