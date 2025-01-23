package com.prognose.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	WebDriver driver;
	private ElementUtil elementUtils;

	// private locators

	private By emailInput = By.id("email");
	private By passwordInput = By.id("password");
	private By signinBtn = By.xpath("//button[text()='Sign in']");
	private By userRolesBtn = By.xpath("//a[text()='User Roles']");
	private By userDropdown = By.id("user-dropdown");
	private By signOutBtn = By.xpath("//*[text()=' Sign Out']");
	private By authAlert = By.xpath("//div[@role='alert']");
	
	// public constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtil(driver);
	}

	// public actions

	@Step("Login to the app with username {0} password {1} ")
	public void doLogin(String emailId, String Password) {

		elementUtils.waitForElementVisible(emailInput, AppConstants.SHORT_TIME_OUT).sendKeys(emailId);
		elementUtils.doSendKeys(passwordInput, Password);
		elementUtils.doClick(signinBtn);

	}

	@Step("HR User is logged in with email: {0} and password: {1}")
	public void doLoginHRUser(String emailId, String Password) {

		elementUtils.waitForElementVisible(emailInput, AppConstants.SHORT_TIME_OUT).sendKeys(emailId);
		elementUtils.doSendKeys(passwordInput, Password);
		elementUtils.doClick(signinBtn);

	}

	@Step("Consultant user is logged in with email: {0} and password: {1}")
	public void doLoginCONUser(String emailId, String Password) {

		elementUtils.waitForElementVisible(emailInput, AppConstants.SHORT_TIME_OUT).sendKeys(emailId);
		elementUtils.doSendKeys(passwordInput, Password);
		elementUtils.doClick(signinBtn);

	}

	/**
	 * user is navigated to Rolespage
	 * 
	 * @param emailId
	 * @param Password
	 */

	@Step("navigating to roles page with url: {0} ")
	public void navigateToRolesPage(String url) {
		elementUtils.navigateTo(url);
	}

	/**
	 * user is navigated to user roles page
	 *
	 */

	@Step("navigating to UserRoles page with username {0} and password {1} ")
	public void navigateToUserRolesPage(String emailId, String Password) {

		
		elementUtils.waitForElementVisible(emailInput, AppConstants.SHORT_TIME_OUT).sendKeys(emailId);
		elementUtils.doSendKeys(passwordInput, Password);
		elementUtils.doClick(signinBtn);
		elementUtils.waitForElementAndClick(userRolesBtn, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT);

	}

	/**
	 * Logout Actions
	 */
	public void clickOnUserDropdown() {
		elementUtils.doClick(userDropdown);
	}

	public void clickOnSignOut() {
		elementUtils.doClick(signOutBtn);
	}

	@Step("Logging out by clicking signout button from userDropdown")
	public void doLogout() {
		elementUtils.doClick(userDropdown);
		elementUtils.doClick(signOutBtn);
	}
	
	@Step("Navigating to the setting page url: {0}")
	public void navigateSettingPage(String url) {
		elementUtils.navigateTo(url);
	}
	
	public String authFailedAlert() {
		elementUtils.waitForElementVisible(authAlert, AppConstants.MEDIUM_TIME_OUT);
		return elementUtils.doElementGetText(authAlert);
	}
	
	public void navigateToTeamembersPage(String url) {
		elementUtils.navigateTo(url);
	}
	
	

}
