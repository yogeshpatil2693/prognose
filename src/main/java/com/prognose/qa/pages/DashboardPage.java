package com.prognose.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.prognose.qa.constants.AppConstants;
import com.prognose.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class DashboardPage {
	
	WebDriver driver;
	private ElementUtil elementUtils;
	
	// public constructor
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtil(driver);
	}
	
	// private Locators
	
	private By projectsListBtn = By.xpath("//span[text()='Projects']");
//	private By projectBtn = By.xpath("//a[text()='"+AppConstants.PROJECT_NAME+"']");
	
	private By projectBtn = By.xpath("//a[text()='"+AppConstants.PROJECT_NAME+" ']");
	private By tabList = By.xpath("//ul[@id='project-tabs']//li");
	private By moduleList = By.xpath("//span[@class='menu-text ']");
	private By scroller = By.xpath("/html/body/div[3]/div[1]/div/div[10]/div");
	private By dashboard = By.xpath("//span[text()='Dashboard']");
	
	// public actions
	
	@Step("Projects button is clicked from dashboard page")
	public void clickOnProjectsBtn() {
		elementUtils.waitForElementAndClick(projectsListBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
	}
	
	@Step("Project is selected from the project list")
	public void selectProject() {
		elementUtils.waitForElementAndClick(projectBtn, AppConstants.LONG_TIME_OUT, AppConstants.SHORT_TIME_OUT);
		
	}
	
	@Step("List Tabs available after selecting a project")
	public List<String> tabList() {
		elementUtils.waitForElementsPresence(tabList, AppConstants.LONG_TIME_OUT);
		return elementUtils.getElementsTextList(tabList);
	}
	
	@Step("List of module on dashboard page")
	public List<String> moduleList() {
		elementUtils.waitForElementsPresence(moduleList, AppConstants.LONG_TIME_OUT);
		return elementUtils.getElementsTextList(moduleList);
	}

	public void clickOnSettingBtn() {
		elementUtils.doClick(dashboard);	
		elementUtils.waitForScrollAndClick(scroller);
		elementUtils.scrollDown(scroller);
	}
	
	public boolean isDashboardDisplayed() {
		return elementUtils.checkElementIsDisplayed(dashboard);
	}
	
	
}
