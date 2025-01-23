package com.prognose.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class PermissionsPage {
	
	
	WebDriver driver;
    private ElementUtil elementUtils;
    
	//Public Constructor
	
	public PermissionsPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtil(driver);
	}
	
	// private locators
	
	private By hrRolePermissionBtn = By.xpath("//a[text()='"+AppConstants.HR_EXISTENT_ROLE+"']/parent::td/following-sibling::td/a[@class='edit']");
	private By yesSpecificMembersorTeamsBtn =  By.xpath("(//label[text()='"+AppConstants.YES_TO_SPECIFIC_MEMBERS_PERMISSION+"']/preceding-sibling::input)[1]");
	private By teamsDropdown = By.xpath("//input[@id='s2id_autogen1']");
	private By teamMember1 = By.xpath("//*[text()='"+AppConstants.MEMBER1+"']");
	private By teamMember2 = By.xpath("//*[text()=' "+AppConstants.MEMBER2+"']");
	private By saveBtn = By.xpath("//*[text()=' Save']");
	private By conRolePermissionBtn = By.xpath("//a[text()='"+AppConstants.CONSULTANT_EXISTENT_ROLE+"']/parent::td/following-sibling::td/a[@class='edit']");
	private By canAccessTalentAcqModuleYesBtn = By.xpath("//input[@id='talent_aquisition_yes']");
	private By removeBtn = By.className("select2-search-choice-close");
	private By noButton = By.id("team_member_update_permission_no");
	
	// Public actions
	
	@Step("HR Role is selected")
	public void clickOnHRrolePermissionBtn() {
		elementUtils.waitForElementAndClick(hrRolePermissionBtn, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	@Step("'YesSpecificMembers' radio button is selected")
	public void  selectYesSpecificMembersTeamsBtn(){
		elementUtils.waitForScrollAndClick(yesSpecificMembersorTeamsBtn);
	}
	
	@Step("Team members are selected from the dropdown")
	public void selectTeamMembersFromDropdown() {
		elementUtils.doClick(teamsDropdown);
		elementUtils.waitForScrollAndClick(teamMember1);
		elementUtils.doClick(teamsDropdown);
		elementUtils.waitForElementVisible(teamMember2, AppConstants.MEDIUM_TIME_OUT);
		elementUtils.waitForScrollAndClick(teamMember2);
	}

	@Step("save button is clicked")
	public void clickSaveBtn() {
		elementUtils.waitForScrollAndClick(saveBtn);
	}
	
	@Step("Consulant role is selected")
	public void clickOnConsultantRolePermissionBtn() {
		elementUtils.waitForElementAndClick(conRolePermissionBtn, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	@Step("select 'canAccessTalentAcqModuleYes' checkbox ")
	public void  selectcanAccessTalentAcqModuleYesBtn() {
		elementUtils.waitForScrollAndClick(canAccessTalentAcqModuleYesBtn);
	}
	
	public void removeMembers() {
		elementUtils.waitForScroll(removeBtn);
		List<WebElement> closeButtons = elementUtils.getElements(removeBtn);
		while(!closeButtons.isEmpty()) {
			closeButtons.get(0).click();
			closeButtons = elementUtils.getElements(removeBtn);
		}
		elementUtils.doClick(noButton);
	}
	
	
	
	
	
	
	
	
	
	
}
