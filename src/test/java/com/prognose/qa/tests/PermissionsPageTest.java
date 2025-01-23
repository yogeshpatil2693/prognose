package com.prognose.qa.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.prognose.qa.base.BaseTest;
import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class PermissionsPageTest extends BaseTest{
	
	Faker faker = new Faker();
	
	@BeforeMethod
	public void permissionsPageSetUp() {
		loginPage.navigateToRolesPage(prop.getProperty("url"));
		loginPage.doLogin(prop.getProperty("adminemail"), prop.getProperty("adminpassword"));
	}
	
	@DataProvider
	public Object[][] getteamsDropdownTestData(){
		return ExcelUtil.getTestData("teamsDropdownTest");
	}
	@Description("Verifying teams dropdown")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 1 , dataProvider = "getteamsDropdownTestData")
	public void teamsDropdownTest(String userHRemail , String userHRpassword ) {
		
		permissionsPage.clickOnHRrolePermissionBtn();
		permissionsPage.selectYesSpecificMembersTeamsBtn();
		permissionsPage.selectTeamMembersFromDropdown();
		permissionsPage.clickSaveBtn();
		loginPage.doLogout();
		loginPage.doLoginHRUser(userHRemail , userHRpassword);
		teamPage.clickOnTeamsDropdown();
		teamPage.clickOnTeamMembersBtn();
		teamPage.selectTeamMember();
		teamPage.clickOnGeneralInfoLink();
		String existentName = teamPage.userNameExistentText();
		System.out.println("Existing first name of user is = " + teamPage.userNameExistentText());
		teamPage.updateFirstName(faker.address().firstName());
		teamPage.clickOnSaveBtn();
		System.out.println("Updated first name of user is = " + teamPage.updatedUserNameText());
		String updatedName =  teamPage.updatedUserNameText();
		assertFalse(existentName.equals(updatedName));
		teamPage.updateFirstName(existentName);
		teamPage.clickOnSaveBtn();
		loginPage.doLogout();
		loginPage.navigateToRolesPage(prop.getProperty("url"));
		loginPage.doLogin(prop.getProperty("adminemail"), prop.getProperty("adminpassword"));
		permissionsPage.clickOnHRrolePermissionBtn();
		permissionsPage.removeMembers();
		permissionsPage.clickSaveBtn();
	}
	
	@DataProvider
	public Object[][] getpermissionsTakingEffectTestData(){
		return ExcelUtil.getTestData("permissionsTakingEffect");
	}
	@Description("Role is given access to Talent acquisition module")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2, dataProvider = "getpermissionsTakingEffectTestData" )
	public void permissionsTakingEffect(String userCONemail, String userCONPassword ) {
		
		permissionsPage.clickOnConsultantRolePermissionBtn();
		permissionsPage.selectcanAccessTalentAcqModuleYesBtn();
		permissionsPage.clickSaveBtn();
		loginPage.doLogout();
		loginPage.doLoginCONUser(userCONemail,userCONPassword);
		System.out.println(dashboardPage.moduleList());
		assertTrue(dashboardPage.moduleList().contains(AppConstants.TALENT_ACQUISITION_MODULE));
		
	}
	
	@AfterMethod
	public void permissionPageLogout() {
		loginPage.doLogout();
	}


}
