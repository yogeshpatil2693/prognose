package com.prognose.qa.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ElementUtil;

import io.qameta.allure.Step;


public class UserRolesPage {
	// public page constructor
			public UserRolesPage(WebDriver driver) {
				this.driver = driver;
				elementUtils = new ElementUtil(driver);
			}
			
		private WebDriver driver;
		private ElementUtil elementUtils;
		// private  by locators 
		
		private By activeMember = By.xpath("(//div[@class='btn-group filter']//label)[1]");
		private By inActiveMemberBtn = By.xpath("//label[contains(@class, 'btn btn-default') and contains(text(), 'Inactive members')]");
		private By activeMemberBtn = By.xpath("//div[@class='btn-group filter']//label[normalize-space(text())='Active members']");
		private By printBtn = By.xpath("//span[text()='Print']");
		private By usersfullList = By.xpath("/html/body/table");
		private By searchField = By.xpath("//input[@placeholder='Search']");
		private By closeEyeIcon = By.xpath("//button[@data-bs-toggle='popover']");
		private By EditLink = By.xpath("(//a[@title='Edit user role' and @data-title='Edit user role'])[1]");
		private By editUserRolePopUp = By.id("ajaxModalTitle");
		private By editRoleDropdown = By.xpath("//span[@id='select2-chosen-10']");
		private By selectRole = By.xpath("//li[contains(@class, 'select2-result-selectable')]"+ "//div[contains(text(), 'GRAPHIC DESIGNER')]");
		private By saveEditUserRoleBtn = By.xpath("//*[text()=' Save']");
		private By roleBtn = By.xpath("//a[text()='Roles']");
		private By teamMembersText = By.xpath("//tbody/tr/td/a[contains(@href,'http://beta.prognose24.com/index.php/team_members/view/')]");
		private By presentationDropdownBtn = By.xpath("//span[@class='select2-arrow']");
		private By defaultMemberValue = By.id("select2-chosen-2");
		private By dropdownValueList = By.xpath("//ul[@id='select2-results-2']//li");
		private By userRolesBtn = By.xpath("//a[text()='User Roles']");
		private By usersOnpage = By.xpath("//tbody//tr");
		private By userNameList = By.xpath("//tbody//tr/td/a[contains(@href,'http://beta.prognose24.com/index.php/team_members/view')]");
		private By roleName = By.xpath("(//a[text()='"+AppConstants.USER+"']/parent::td//following-sibling::td)[1]"); 
		private By editRoleBtn = By.xpath("//a[text()='"+AppConstants.USER+"']/parent::td//following-sibling::td[@class=' text-center option w100']/a");
		private By roleDropdownArrow = By.xpath("//*[@id=\"s2id_user-role\"]");
		private By inputBox = By.xpath("(//input[@type='text' and @role='combobox'])[2]");
		private By totalUsers = By.xpath("//div[@id='user-roles-table_info']");
		private By totalPageList = By.className("paginate_button");
		private By dummyUserRoles = By.xpath("//h4[text()=' User Roles']");
		private By eyeIconOff = By.xpath("//button[@class='btn btn-default column-show-hide-popover ml15']");
		private By teamMemeberBtn = By.xpath("//li[text()='Team members']");
		private By teamMemberHeader = By.xpath("//th[text()='Team members']");
		private By roleText = By.xpath("(//a[text()='"+AppConstants.USER+"']/parent::td/following-sibling::td)[1]");
		private By headerList = By.xpath("//thead//tr//th");
		
		@Step("Verifying the active member button is selected by default")
		public boolean isActiveMemberBtnSelectedByDefault() {
			boolean flag = false;
		   String activememberbtnAttribute =  elementUtils.getElementAttribute(activeMember, "class");
		   if(activememberbtnAttribute.contains("active")) {
			    flag = true;
		   }
		   return flag;
		      
		}
		
		@Step("Active member button is clicked")
		public void clickOnActiveMemberBtn() {
			elementUtils.waitForElementAndClick(activeMemberBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
		}
		
		@Step("Inactive member button is clicked")
		public void clickOnInActiveMemberBtn() {
			elementUtils.waitForElementAndClick(inActiveMemberBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
		}
		
		@Step("Print button is clicked")
		public void clickOnPrintBtn(){
			elementUtils.waitForElementAndClick(printBtn, AppConstants.SHORT_TIME_OUT, AppConstants.LONG_TIME_OUT);
		}
		
		public String printedListonNewWindowTitle(){
		
			elementUtils.switchToNewWindowTab();
			elementUtils.waitForTitleIs(AppConstants.EXPECTED_ROLES_PAGE_TITLE, AppConstants.MEDIUM_TIME_OUT);
			
			return driver.getTitle();
		}

		public boolean isFullListDisplayed() {
			return elementUtils.checkElementIsDisplayed(usersfullList);
		}
		
		@Step("searchKey: {0} is entered")
		public void enterSearchKey(String searchKey) {
		    elementUtils.doSendKeys(searchField, searchKey);
		}
		
		public List<String> getTeamMembersName() {
			return elementUtils.getElementsTextList(teamMembersText);
		    
		}
		
		@Step("MemberList: {0} is checked whether searchKey: {1} is present")
		public boolean checkMemberListContainsSearchMember(List<String> memberList , String searchKey) {
			
			if (searchKey == null || searchKey.isEmpty()) {
				System.out.println("Enter valid searchKey");
		        return false; // Handle null or empty cases safely
		    }
			
			for (String item : memberList) {
				if(memberList == null || memberList.isEmpty()) {
			    	System.out.println("No member found with " + searchKey);
			    	return true;
			    }
				else if (!item.contains(searchKey)) {
	                System.out.println("Match Not found: " + item);
	               return false;
	            }
	        }
			return true;
		}
		
		@Step("User Role button is clicked")
		public void clickOnUserRoleBtn() {
			elementUtils.waitForElementAndClick(userRolesBtn,AppConstants.MEDIUM_TIME_OUT,AppConstants.SHORT_TIME_OUT);
		}

		@Step("Eye icon is clicked")
		public void clickOnClosedEyeIcon() {
			elementUtils.doClick(closeEyeIcon);
		}
		
		public void clickOnEditLink() {
			elementUtils.doClick(EditLink);
		}
		
		public boolean isEditRolePagePopUpDisplayed() {
			return elementUtils.checkElementIsDisplayed(editUserRolePopUp);
		} 
		
		public void clickOnEditRoleDropdown() {
			elementUtils.doClick(editRoleDropdown);
		}
		
		public void selectAnyOneRole() {
			elementUtils.doClick(selectRole);
		}
		
		public void clickOnEditUserSaveRoleBtn() {
			elementUtils.doClick(saveEditUserRoleBtn);
		}
		
		public void clickOnRolebtn() {
			elementUtils.doClick(roleBtn);
		}
			
		@Step("MemberList value: {0} is selected")
		public void selectNumberOfMembersFromPresentationDropdown(String memberListValue) {
			
		    System.out.println("Default value of memberslist " + elementUtils.doElementGetText(defaultMemberValue));
		    
		    elementUtils.doClick(presentationDropdownBtn);
		    elementUtils.clickOnElementWithText(dropdownValueList, memberListValue);
		    
		}
		
		@Step("Getting number of members present on page")
		public String numberOfMembersInPage() {
			int memListInt = elementUtils.getElementsCount(usersOnpage);
			String memListString = String.valueOf(memListInt);
			return memListString;
			
		}
		
		public int numberOfUsersInPage() {
			int memListInt = elementUtils.getElementsCount(usersOnpage);
			return memListInt;
		}
		
		@Step("Edit Role option of User: {0} is clicked")
		public void clickOnEditRolesBtn(String userRole) {
//			elementUtils.waitForElementAndClick(editRoleBtn, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT);
			elementUtils.waitForScrollAndClick(editRoleBtn);		}

		@Step("Updated Role: {0} is selected")
		public void selectExistingRoleFromDropdownList(String updatedRole) {
			elementUtils.waitForElementAndClick(roleDropdownArrow, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
			elementUtils.doSendKeys(inputBox, updatedRole);
			elementUtils.pressKeyEnter();
		}
		@Step("Save button is clicked")
		public void clickOnSaveBtn() {
			elementUtils.waitForElementAndClick(saveEditUserRoleBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
			elementUtils.refreshPage();
			elementUtils.waitForElementVisible(roleName, AppConstants.MEDIUM_TIME_OUT);
		}
		
		@Step("getting the updated role text")
		public String getRoleText() {
			return elementUtils.doElementGetText(roleName);
			
		}
		
		@Step("Getting Total number of users")
		public int totalNumberOfUsers() {
			elementUtils.waitForScrollAndClick(totalUsers);
			String totalString = elementUtils.doElementGetText(totalUsers);
			int totalNumber = 0;
			int index = totalString.indexOf("/");
			if (index != -1) {
	            String result = totalString.substring(index + 1).trim();  // Trim to remove spaces
	            totalNumber = Integer.parseInt(result);
	        } else {
	            System.out.println("No '/' found in the string.");
	        }
			
			return totalNumber;
		}
		
		@Step("Total pages expected based on number of users divided by memberList dropdown value selected")
		public int totalNumberPagesExpected() {
			int numberOfPages = 0;
			if (AppConstants.MEMBER_LIST_VALUE.equals("All")) {
				return numberOfPages = 1;
			}else {
				int memberValueFromDrpdn = Integer.parseInt(AppConstants.MEMBER_LIST_VALUE);
				
				if(totalNumberOfUsers() == memberValueFromDrpdn  ) {
					numberOfPages = totalNumberOfUsers() / memberValueFromDrpdn;
					return numberOfPages;
				}else if (totalNumberOfUsers() > memberValueFromDrpdn ) {
					numberOfPages = totalNumberOfUsers() / memberValueFromDrpdn;
					return numberOfPages+1;
				}else {
					return numberOfPages = 1;
				}
			}
			
			
		}
		
		@Step("Actual number of pages present ")
		public int totalNumberOfPagesActual() {
			int actualPages = 0;
			elementUtils.waitForScroll(totalPageList);
			elementUtils.waitForElementsVisible(totalPageList, AppConstants.MEDIUM_TIME_OUT);
			List<WebElement> elements = elementUtils.getElements(totalPageList);
			int totalPagesPresent = elements.size();
			if (totalPagesPresent > 2) {
				actualPages = totalPagesPresent-2;
			}else {
				System.out.println("Page count is not present on page");
			}
			return actualPages;
		}
		
		@Step("Total number of users from all pages")
		public int getTotalNoOfUsersFromAllPages() {
			int totalUsers = 0;
			int totalPages = totalNumberOfPagesActual();
			List<WebElement> totalElements = elementUtils.getElements(usersOnpage);
			if (totalPages == 1) {
				totalUsers = totalElements.size();
				return totalUsers;
			}else if (totalPages > 1) {
				for (int i=1; i<=totalNumberOfPagesActual(); i++) {
					List<WebElement> pageLinks = elementUtils.getElements(totalPageList);
					pageLinks.get(i).click();
					List<WebElement> usersOnCurrentPage = elementUtils.getElements(usersOnpage);
		            totalUsers += usersOnCurrentPage.size(); 
				}
				return totalUsers;
			}
			return totalUsers;
		}
		
		@Step("List of total number of actual users")
		public List<String> listOfActualUsers() {
			int totalPages = totalNumberOfPagesActual();
			List<String> totalUsersNameList = new ArrayList<>();
			if (totalPages == 1) {
				elementUtils.waitForScroll(dummyUserRoles);
				totalUsersNameList.addAll(elementUtils.getElementscrollingTextList(userNameList));
			}else if (totalPages > 1) {
				for (int i=1; i<=totalPages; i++) {
					 List<WebElement> pageLinks = elementUtils.getElements(totalPageList);
			            if (i < pageLinks.size()) {  //Prevent IndexOutOfBoundsException
			                pageLinks.get(i).click();
			                elementUtils.waitForScroll(dummyUserRoles);
			                totalUsersNameList.addAll(elementUtils.getElementscrollingTextList(userNameList));
			            }
				}

			}
			return totalUsersNameList;
		}
		
		
//		public List<String> listOfActualUsers() {
//		    int totalPages = totalNumberOfPagesActual();
//		    List<String> totalUsersNameList = new ArrayList<>();
//
//		    // Ensure at least one page exists
//		    if (totalPages < 1) {
//		        return totalUsersNameList;
//		    }
//
//		    for (int i = 1; i <= totalPages; i++) {
//		        navigateToPage(i);
//		        totalUsersNameList.addAll(getVisibleUserNames());
//		    }
//
//		    return totalUsersNameList;
//		}
//
//		private void navigateToPage(int pageIndex) {
//		    List<WebElement> pageLinks = elementUtils.getElements(totalPageList);
//		    if (pageIndex < pageLinks.size()) {
//		        pageLinks.get(pageIndex).click();
//		        elementUtils.waitForScroll(dummyUserRoles);
//		    }
//		}
//
//		private List<String> getVisibleUserNames() {
//		    return elementUtils.getElementscrollingTextList(userNameList);
//		}

		@Step("Page is refreshed")
		public void refreshUserRolePage() {
			elementUtils.refreshPage();
		}
		
		@Step("List of sorted copy of actual users")
		public List<String> sortedCopyOfActualUsers(){
			List<String> sortedList = new ArrayList<>(listOfActualUsers());
			Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		    return sortedList;
		}
		
		
		public void clickOnEyeOff() {
			refreshUserRolePage();
			elementUtils.doClick(eyeIconOff);
		}
		
		/**
		 * This is customized retry mechanism for clicking on Team members option. Here in the second attempt page
		 * is refreshed and then made an attempt to click . So when an Team member hide option is clicked the attribute value of class changes with
		 * "active" string and with get attribute method we come to know whether button is clicked or not
		 */
		@Step("Hide Team members option is selected")
		public void clickToHideTeamMembers() {
			
			elementUtils.waitForElementVisible(teamMemeberBtn, AppConstants.LONG_TIME_OUT);
			elementUtils.doClick(teamMemeberBtn);

			String classAttr = elementUtils.getElementAttribute(teamMemeberBtn, "class");
			System.out.println(classAttr);

			int attempts = 0;
			while (!classAttr.contains("active") && attempts < 2) {  
			    refreshUserRolePage();
			    clickOnEyeOff();
			    elementUtils.waitForElementAndClick(teamMemeberBtn, AppConstants.MEDIUM_TIME_OUT, AppConstants.SHORT_TIME_OUT);
			    
			    classAttr = elementUtils.getElementAttribute(teamMemeberBtn, "class");
			    System.out.println(classAttr);
			    
			    attempts++;
			}

			if (classAttr.contains("active")) {
			    System.out.println("Team members hide button clicked successfully");
			} else {
			    System.out.println("Team Members option is not clickable");
			}

			
		}
		
		public boolean isTeamMembersHeaderDisplayed() {
			refreshUserRolePage();
			return elementUtils.checkElementIsDisplayed(teamMemberHeader);
		}
		
		@Step("List of headers present")
		public List<String> getListOfHeaders(){
			return elementUtils.getElementsTextList(headerList);
		}
		
		@Step("Getting the current role of User")
		public String currentRoleOfUser() {
			elementUtils.waitForScroll(roleText);
			return elementUtils.doElementGetText(roleText);
		}

}