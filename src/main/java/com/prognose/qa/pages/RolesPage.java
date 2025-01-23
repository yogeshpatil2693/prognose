package com.prognose.qa.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ElementUtil;

import io.qameta.allure.Step;


public class RolesPage  {
	
	private WebDriver driver;
	private ElementUtil elementUtils;
	
	
	// private  by locators 
	
	private By roleBtn = By.xpath("//a[text()='Roles']");
	private By addRoleBtn = By.xpath("//a[text()=' Add role']");
	private By addRolePopUptext = By.id("ajaxModalTitle");
	private By roleTitle = By.xpath("//input[@placeholder='Title']");
	private By isContractorChkbx = By.xpath("//input[@id='is_contractor']");
	private By useSettingsFromDropdown = By.xpath("//span[@role='presentation']");
	private By selectFromExistingRoles = By.xpath("//div[@role='option']");
	private By enterHourlyRate = By.xpath("//input[contains(@value, '"+AppConstants.HOURLY_CURRENCY+"')]/parent::div/following-sibling::div/child::input");
	private By saveBtn = By.xpath("//*[text()=' Save']");
	private By rolesList = By.xpath("//table/tbody/tr/td/a[@class='role-row link']");
	private By newlyAddedRole = By.xpath("//a[text()='"+AppConstants.NEW_ROLE+"']");
	private By newlyAddedRoleTitle = By.xpath("//a[text()='"+AppConstants.EDITED_ROLE_TITLE+"']");
	private By editRoleBtn = By.xpath("//a[text()='"+AppConstants.NEW_ROLE+"']/parent::td/following-sibling::td/child::a[@data-title='Edit role']");
	private By deleteRoleBtn = By.xpath("//a[text()='"+AppConstants.EDITED_ROLE_TITLE+"']/parent::td/following-sibling::td/child::a[@data-action='delete']");
	private By newEditedRole = By.xpath("//a[text()='"+AppConstants.EDITED_ROLE_TITLE+"']");
	private By undoMsgPopUp = By.xpath("//*[@id=\"app-alert-3boaa\"]/a/strong"); 
	private By rolePermissionBtn = By.xpath("//a[text()='"+AppConstants.HR_EXISTENT_ROLE+"']/parent::td/following-sibling::td/a[@class='edit']");
	private By scroller = By.xpath("//div[@class='scrollable-page main-scrollable-page ps ps--active-y']//div[@class='ps__thumb-y']");
	private By canViewprdpriceChkBx = By.xpath("//label[text()='"+AppConstants.PROJECT_PRICE_VIEW_PERMISSION+"']/preceding-sibling::input");
	private By setUpBtn = By.xpath("//div[contains(text(),'Setup')]");
	private By canAccsCommerTabChkBx = By.xpath("//label[text()='"+AppConstants.COMMERCIAL_TAB_ACCESS_PERMISSION+"']/preceding-sibling::input");
	private By accessPermission = By.xpath("//div[normalize-space()='Access Permission']");
	
	// public add role page actions
	
	@Step("Add Role button is clicked")
	public void clickOnAddRoleBtn() {
		elementUtils.doClick(addRoleBtn);
	}
	
	@Step("New role: {0} title is entered")
	public void enterRoleTitle(String new_role) {
		elementUtils.waitForElementAndEnterValue(roleTitle, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT,new_role);
	}
	
	@Step("is contracter checkbox is clicked")
	public void clickContactorChkBx() {
		elementUtils.doClick(isContractorChkbx);
	}
	
	@Step("use settings from is clicked")
	public void clickOnUseSettingFromDropdown() {
		elementUtils.doClick(useSettingsFromDropdown);
	}
	
	@Step("existing role is selected from dropdown")
	public void selectExistingRoleFromDropdownList() {
		elementUtils.doSelectDropDownValueUsingLocator(selectFromExistingRoles,AppConstants.HR_EXISTENT_ROLE);
	}
	
	@Step("hourly rate: {0} is entered")
	public void enterHoulyRate(String hourlyRate) {
		elementUtils.doSendKeys(enterHourlyRate, hourlyRate);
	
	}
	

	@Step("save button is clicked")
	public void clickOnSaveBtn() {
		elementUtils.waitForElementAndClick(saveBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	public List<String> roleList() {
		elementUtils.waitForElementsPresence(newlyAddedRole, AppConstants.LONG_TIME_OUT);
		return elementUtils.getElementsTextList(rolesList);
		
	}
	
	
	
	
	// public edit role page actions
	
	@Step("Edit role button is clicked")
	public void clickOnEditRolesBtn() {
		elementUtils.waitForElementAndClick(editRoleBtn, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	@Step("New title: {0} is entered")
	public void enterNewTitle(String new_Role) {
		elementUtils.waitForElementVisible(roleTitle, AppConstants.MEDIUM_TIME_OUT);
		elementUtils.getElement(roleTitle).clear();
		elementUtils.waitForElementAndEnterValue(roleTitle, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT,new_Role);
	}
	
	@Step("New Hourly rate :{0} is entered")
	public void enterNewHoulyRateForEditRole(String newHourlyRate) {
		elementUtils.doSendKeys(enterHourlyRate, newHourlyRate);
	}
	
	public List<String> newRoleList() {
		elementUtils.waitForElementsPresence(newlyAddedRoleTitle, AppConstants.MEDIUM_TIME_OUT);
		return elementUtils.getElementsTextList(rolesList);
		
	}
	
	
	// public delete role page actions
	
	@Step("Roles button is clicked")
	public void clickOnRoleBtn() {
		elementUtils.doClick(roleBtn);
	}
	
	@Step("Newely edited role is deleted by clicking on delete button")
	public void clickOnDeleteRoleBtn() {
		elementUtils.waitForElementPresence(newEditedRole, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
		elementUtils.waitForElementAndClick(deleteRoleBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	
	public boolean isPopUpMsgDisplayed() {
	    try {
	        WebElement popup = elementUtils.getElement(undoMsgPopUp);
	        if (popup.isDisplayed()) {
	            popup.click();
	            return true; 
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Pop-up message not displayed.");
	    }
	    return false;
	}
	
	public List<String> updatedRoleList() {
		elementUtils.waitForElementsVisible(rolesList, AppConstants.LONG_TIME_OUT);
		return elementUtils.getElementsTextList(rolesList);
		
	}
	
	//Role taking effect with permissions
	@Step("Permission button is clicked")
	public void clickOnPermissionBtn() {
		elementUtils.waitForElementAndClick(setUpBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
		elementUtils.waitForElementAndClick(rolePermissionBtn, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	@Step("Project price permission checkbox is selected")
	public void tickOnViewPrdPricePermissionChkBx() {
		
		elementUtils.pressKeyDown();
		elementUtils.waitForElementVisible(canViewprdpriceChkBx, AppConstants.MEDIUM_TIME_OUT);
		elementUtils.waitForElementAndClick(canViewprdpriceChkBx, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
		
	}
	
	@Step("Save button is clicked")
	public void clickEditSaveBtn() {
		
		elementUtils.waitForElementVisible(scroller, AppConstants.MEDIUM_TIME_OUT);
		elementUtils.scrollUp(scroller);
		elementUtils.waitForElementAndClick(saveBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}

	@Step("Project Commercials checkbox is selected")
	public void tickOnAccessProjectCommercialsTabChkBx() {
		
		elementUtils.pressKeyDown();;
		elementUtils.waitForElementVisible(canAccsCommerTabChkBx, AppConstants.MEDIUM_TIME_OUT);
		elementUtils.tickCheckBox(canAccsCommerTabChkBx);
		
	}
	
	@Step("Access permission button is clicked")
	public void clickOnAcsessPermissionBtn() {
		elementUtils.waitForScrollAndClick(accessPermission);
	}
	
	@Step("Roles button is clicked ")
	public void clickOnRolesBtn() {
		elementUtils.waitForScrollAndClick(roleBtn);
	}
	
	// public page constructor
			public RolesPage(WebDriver driver) {
				this.driver = driver;
				elementUtils = new ElementUtil(driver);
			}
	
	
	
}
