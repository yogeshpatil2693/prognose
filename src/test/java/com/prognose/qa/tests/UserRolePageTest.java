package com.prognose.qa.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.prognose.qa.base.BaseTest;
import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class UserRolePageTest extends BaseTest {
	
	
	@BeforeMethod
	public void userRolesPageSetUp() {
		loginPage.navigateToRolesPage(prop.getProperty("url"));
		loginPage.doLogin(prop.getProperty("adminemail"), prop.getProperty("adminpassword"));
		userRolesPage.clickOnUserRoleBtn();
	}
	
	@Description("Verify active members are displayed by default")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyActiveMembersIsDisplayedByDeafault() {
		assertTrue(userRolesPage.isActiveMemberBtnSelectedByDefault());
	}
	
	@Description("Verifying Inactive button functionality")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void verifyInActiveMembersBtnFunctional() {
		userRolesPage.clickOnInActiveMemberBtn();
		assertFalse(userRolesPage.isActiveMemberBtnSelectedByDefault());
	}

	@Description("Printing memberlist")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void printActiveOrInactiveMemberList(){
		userRolesPage.clickOnPrintBtn();
		assertEquals(userRolesPage.printedListonNewWindowTitle(),AppConstants.EXPECTED_ROLES_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getSearchKeyData(){
		return ExcelUtil.getTestData("searchkey");
	}
	@Description("Searching members from searchbar with full/partial/invalid search key")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4, dataProvider = "getSearchKeyData" )
	public void searchMemberFromSearchField(String searchKey) {
		
		userRolesPage.clickOnActiveMemberBtn();
		userRolesPage.enterSearchKey(searchKey);
		System.out.println(userRolesPage.getTeamMembersName());
		assertTrue(userRolesPage.checkMemberListContainsSearchMember(userRolesPage.getTeamMembersName(), searchKey));
	}
	
	@Description("Verifying number of members selected from dropdown are actually present on page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 5)
	public void userIsAbleToSelectNumberOfMembersFromPresentationDropdown() {
		userRolesPage.clickOnUserRoleBtn();
		userRolesPage.selectNumberOfMembersFromPresentationDropdown(AppConstants.MEMBER_LIST_VALUE);
		System.out.println("Total numbers of members present in the page " + userRolesPage.numberOfMembersInPage());
		if (Integer.parseInt(userRolesPage.numberOfMembersInPage()) >= Integer.parseInt(AppConstants.MEMBER_LIST_VALUE)) {
			assertEquals(userRolesPage.numberOfMembersInPage(), AppConstants.MEMBER_LIST_VALUE);
		}else {
			System.out.println("Members present on page are less than the dropdown value");
		}
		
	}
	
	@Description("Verifying edit role functionality from User Role page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 6)
	public void userIsAbleToEditUserRoles() {
		
		userRolesPage.clickOnUserRoleBtn();
		System.out.println("Existing Role for user " + AppConstants.USER + " is " + userRolesPage.currentRoleOfUser());
		userRolesPage.clickOnEditRolesBtn(AppConstants.USER);
		userRolesPage.selectExistingRoleFromDropdownList(AppConstants.UPDATED_USER_ROLE);
		userRolesPage.clickOnSaveBtn();
		System.out.println("Updated Role for user "+AppConstants.USER + " is " +userRolesPage.getRoleText());
		assertEquals(userRolesPage.getRoleText(), AppConstants.UPDATED_USER_ROLE);
	}
	
	@Description("Verifying total number of pages present based on drpdnValue selected")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 7)
	public void verifyTotalNumberOfPagesPresentBasedOnDrpdnValue() {
		
		userRolesPage.clickOnUserRoleBtn();
		userRolesPage.selectNumberOfMembersFromPresentationDropdown(AppConstants.MEMBER_LIST_VALUE);
		System.out.println("Total number of users " + userRolesPage.totalNumberOfUsers());
		System.out.println("Total number of pages expected " + userRolesPage.totalNumberPagesExpected());
		System.out.println("Total numbers of pages actual " + userRolesPage.totalNumberOfPagesActual());
		assertEquals(userRolesPage.totalNumberPagesExpected(), userRolesPage.totalNumberOfPagesActual());
	}
	
	@Description("Verifying whether total number of users value present on first page is equal to addition of members from each page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 8)
	public void verifyPagination() {
		
		userRolesPage.clickOnUserRoleBtn();
		userRolesPage.selectNumberOfMembersFromPresentationDropdown(AppConstants.MEMBER_LIST_VALUE);
		System.out.println("Total number of users expected " + userRolesPage.totalNumberOfUsers());
		System.out.println("Total number of pages actual " + userRolesPage.totalNumberOfPagesActual());
		System.out.println("Total number of users actual " + userRolesPage.getTotalNoOfUsersFromAllPages());
		assertEquals(userRolesPage.getTotalNoOfUsersFromAllPages(),userRolesPage.totalNumberOfUsers());
		
	}
	
	@Description("Sorting Team Members in ascending order")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 9)
	public void ascendingSortingOfTeamMembers() {
		userRolesPage.refreshUserRolePage();
		userRolesPage.selectNumberOfMembersFromPresentationDropdown(AppConstants.MEMBER_LIST_VALUE);
		System.out.println("Total number of users expected " + userRolesPage.totalNumberOfUsers());
		System.out.println(userRolesPage.listOfActualUsers());
		System.out.println(userRolesPage.sortedCopyOfActualUsers());
		assertEquals(userRolesPage.listOfActualUsers(),userRolesPage.sortedCopyOfActualUsers());
	}
	
	@Description("Hide Team members ")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 10)
	public void hideTeamMembersFunctionality() {
		
		userRolesPage.clickOnActiveMemberBtn();
		System.out.println("Headers displayed before clicking on Team members option are" + userRolesPage.getListOfHeaders());
		userRolesPage.clickOnClosedEyeIcon();
		userRolesPage.clickToHideTeamMembers();
		System.out.println("Headers displayed are" + userRolesPage.getListOfHeaders());
		assertFalse(userRolesPage.getListOfHeaders().contains("Team members"));
	}
	
	@AfterMethod
	public void UserRolePageLogout() {
		loginPage.doLogout();
	}

}