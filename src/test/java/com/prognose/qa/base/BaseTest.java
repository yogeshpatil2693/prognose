
package com.prognose.qa.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.github.javafaker.Faker;
import com.prognose.qa.factory.DriverFactory;
import com.prognose.qa.pages.DashboardPage;
import com.prognose.qa.pages.LoginPage;
import com.prognose.qa.pages.PermissionsPage;
import com.prognose.qa.pages.RolesPage;
import com.prognose.qa.pages.TeamPage;
import com.prognose.qa.pages.UserRolesPage;




public class BaseTest {

	
	/**
	 * protected it is used to access page class out of the package
	 */
	WebDriver driver;
	protected LoginPage loginPage;
	DriverFactory df;
	protected Properties prop;
	protected RolesPage rolesPage;
	protected DashboardPage dashboardPage;
	protected PermissionsPage permissionsPage;
	protected TeamPage teamPage;
	protected UserRolesPage userRolesPage ;
	
	/**
	 * Initialize driver and Initialize page classes
	 */
	
	@BeforeTest
	public void setup() {
		
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		rolesPage= new RolesPage(driver);
		dashboardPage = new DashboardPage(driver);
		permissionsPage = new PermissionsPage(driver);
		teamPage = new TeamPage(driver);
		userRolesPage = new UserRolesPage(driver);
	}
	
	/**
	 * quit the driver
	 */
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
