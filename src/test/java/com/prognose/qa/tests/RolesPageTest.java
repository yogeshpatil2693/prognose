package com.prognose.qa.tests;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.prognose.qa.base.BaseTest;
import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class RolesPageTest extends BaseTest {
	
	Faker faker = new Faker();
	
	@BeforeClass
	public void rolePageSetUp() {
		loginPage.navigateToRolesPage(prop.getProperty("url"));
		loginPage.doLogin(prop.getProperty("adminemail"), prop.getProperty("adminpassword"));
	}
	
	@DataProvider
	public Object[][] addRoleTestData(){
		return ExcelUtil.getTestData("addRoleTest");
	}
	@Description("A new Role is added ")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1 , dataProvider = "addRoleTestData")
	public void addRoleTest(String new_role) {
		
		rolesPage.clickOnAddRoleBtn();
		rolesPage.enterRoleTitle(new_role);
		rolesPage.clickContactorChkBx();
		rolesPage.clickOnUseSettingFromDropdown();
		rolesPage.selectExistingRoleFromDropdownList();
		rolesPage.enterHoulyRate(faker.number().digits(2));
		rolesPage.clickOnSaveBtn();
		System.out.println(rolesPage.roleList());
		assertTrue(rolesPage.roleList().contains(new_role));
	}
	
	@DataProvider
	public Object[][] editRoleTestData(){
		return ExcelUtil.getTestData("editRoleTest");
	}
	@Description("Existing role is edited")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 2 , dataProvider = "editRoleTestData")
	public void editRoleTest(String edited_Role) {
		
		rolesPage.clickOnEditRolesBtn();
		rolesPage.enterNewTitle(edited_Role);
		rolesPage.clickContactorChkBx();
		rolesPage.enterNewHoulyRateForEditRole(faker.number().digits(2));
		rolesPage.clickOnSaveBtn();
		System.out.println(rolesPage.newRoleList());
		assertTrue(rolesPage.newRoleList().contains(edited_Role));
	}
	
	@Description("Edited role is deleted ")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 3)
	public void deleteRoleTest() {
		
		rolesPage.clickOnDeleteRoleBtn();
		rolesPage.clickOnRoleBtn();
		System.out.println(rolesPage.updatedRoleList());
		assertFalse(rolesPage.updatedRoleList().contains(AppConstants.EDITED_ROLE_TITLE));
		
	}
	
	/**
	 * User HR is given permission to view Project Commercials tab by admin 
	 * @return
	 */
	@DataProvider
	public Object[][] getroleTakingEffectData(){
		return ExcelUtil.getTestData("roleTakingEffect");
	}
	@Description("Hr role is given access to view Project commercials")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 4 , dataProvider = "getroleTakingEffectData")
	public void roleTakingEffect(String userHRemail , String userHRpassword) {
		
		rolesPage.clickOnPermissionBtn();
		rolesPage.tickOnAccessProjectCommercialsTabChkBx();
		rolesPage.clickEditSaveBtn();
		loginPage.doLogout();
		loginPage.doLogin(userHRemail, userHRpassword);
		dashboardPage.clickOnProjectsBtn();
		dashboardPage.selectProject();
		System.out.println(dashboardPage.tabList());
		assertTrue(dashboardPage.tabList().contains("Project Commercials"));
		
	}
	
	
	@AfterClass
	public void rolePageLogOut() {
		loginPage.doLogout();	
	}
	
}
