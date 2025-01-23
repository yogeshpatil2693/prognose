package com.prognose.qa.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.prognose.qa.base.BaseTest;
import com.prognose.qa.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class LoginPageTest extends BaseTest{
	
	@BeforeMethod
	public void loginPageSetup() {
		loginPage.navigateToRolesPage(prop.getProperty("url"));
	}
	
	@DataProvider
	public Object[][] getvalidLoginTestData(){
		return ExcelUtil.getTestData("loginpage");
	}
	
	@Description("Login to the application with valid credentials ")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 1 ,dataProvider = "getvalidLoginTestData")
	public void validLoginTest(String emailID, String password) {
		loginPage.doLogin(emailID, password);
		assertTrue(dashboardPage.isDashboardDisplayed());
		loginPage.doLogout();
	}
	
	@DataProvider
	public Object[][] getinValidLoginTestData(){
		return ExcelUtil.getTestData("inValidLoginTest");
	}
	@Description("Login to the application with Invalid credentials and validating authentication alert")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2 ,dataProvider = "getinValidLoginTestData")
	public void inValidLoginTest(String emailID, String password) {
		loginPage.doLogin(emailID, password);
		assertTrue(loginPage.authFailedAlert().contains("Authentication failed!"));
	}
	


}
