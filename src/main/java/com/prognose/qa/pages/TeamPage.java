package com.prognose.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;
import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class TeamPage {
	
	WebDriver driver;
	private ElementUtil elementUtils;
	
	Faker faker = new Faker();
	
	
	public TeamPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtil(driver);
	}
	
	private By teamDropdown = By.xpath("//span[text()='Team']");
	private By teamMembersBtn = By.xpath("//*[text()='Team members']");
	private By teamMember2 = By.xpath("//a[text()='"+AppConstants.MEMBER2+" ']");
	private By generalInfoLink = By.xpath("//a[text()=' General Info']");
	private By firstNameInput = By.xpath("//input[@name='first_name']");
	private By saveBtn = By.xpath("//*[text()=' Save']");
    private By userNameText = By.xpath("(//h4[@class])[1]");
  	private By addMemberBtn = By.xpath("//a[@title='Add member']");
  	private By jobTitle = By.id("job_title");
  	private By nextBtn = By.id("form-next");
  	private By firstname = By.id("first_name");
  	private By lastname = By.id("last_name");
  	private By PAN = By.id("pan");
  	private By emailBy = By.id("email");
  	private By passBy = By.id("password");
  	private By salaEmpChBx = By.id("salaried_employee");
  	private By emailDetails = By.id("email_login_details");
  	
  	
	@Step("Team module is clicked from dashboard")
	public void clickOnTeamsDropdown() {
		elementUtils.doClick(teamDropdown);
	}
	
	@Step("Team members option is selected from Team dropdown")
	public void clickOnTeamMembersBtn() {
		elementUtils.doClick(teamMembersBtn);
	}

	@Step("Team member is selected")
	public void selectTeamMember() {
		elementUtils.waitForScrollAndClick(teamMember2);
	}
	
	@Step("General info header is clicked")
	public void clickOnGeneralInfoLink() {
		elementUtils.doClick(generalInfoLink);
	}
	
	@Step("First Name is updated with updated name: {0}")
	public void updateFirstName(String updatedName) {
		elementUtils.waitForElementsVisible(firstNameInput, AppConstants.MEDIUM_TIME_OUT);
		elementUtils.doClick(firstNameInput);
		elementUtils.doSendKeys(firstNameInput, updatedName);
		
	}
	@Step("clicked on save button")
	public void clickOnSaveBtn() {
		elementUtils.waitForScrollAndClick(saveBtn);
//		elementUtils.waitForElementVisible(addMemberBtn, AppConstants.LONG_TIME_OUT);
	}
	
	@Step("Existent User name is extracted")
	public String userNameExistentText() {
		elementUtils.waitForElementsVisible(userNameText, AppConstants.MEDIUM_TIME_OUT);
		String fullname = elementUtils.doElementGetText(userNameText);
		return fullname.split(" ")[0];
	}
	
	@Step("Updated User name is extracted")
	public String updatedUserNameText() {
		elementUtils.waitForElementsVisible(userNameText, AppConstants.MEDIUM_TIME_OUT);
		return elementUtils.doElementGetText(userNameText).split(" ")[0];
	}
	
	public void clickOnAddMemberBtn() {
		elementUtils.waitForElementAndClick(addMemberBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	public void enterFirstName(String firstName) {
		elementUtils.waitForElementVisible(firstname, AppConstants.VERY_LONG_TIME_OUT);
		elementUtils.doSendKeys(firstname, firstName);
	}
	
	public void enterLastName(String lastName) {
		elementUtils.doSendKeys(lastname, lastName);
	}
	
	public void enterPAN(String pan) {
		elementUtils.doSendKeys(PAN, pan);
	}
	
	public void enterTitle(String title) {
		elementUtils.waitForElementAndEnterValue(jobTitle, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT, title);
	}
	
	public void enterEmail(String email) {
		elementUtils.waitForElementAndEnterValue(emailBy, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT, email);
	}
	
	public void enterPassword(String password) {
		elementUtils.doSendKeys(passBy, password);
	}
	
	public void clickOnNextBtn() {
		elementUtils.waitForScrollAndClick(nextBtn);
	}
	
	public void clickOnSalariedEmployeeChkBx() {
		elementUtils.waitForScrollAndClick(salaEmpChBx);
	}
	
	public void clickOnEmailDetails() {
		elementUtils.doClick(emailDetails);
	}

	public void refreshPage() {
		elementUtils.refreshPage();
	}
	
	
	
	
}
