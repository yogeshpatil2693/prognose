package com.prognose.qa.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prognose.qa.constants.AppConstants;

import io.qameta.allure.Step;


	 public class ElementUtil {

	 	private WebDriver driver;
	 	private Actions act;


	 	public ElementUtil(WebDriver driver) {
	 		this.driver = driver;
	 		act = new Actions(driver);
	 	}
	 	
	 	public void clearSerachFields(By locator) {
			getElement(locator).clear();
		}

		public void refreshPage() {
			driver.navigate().refresh();

		}
	 	
	 	
	 	public void doSendKeys(By locator, String value) {
	 		getElement(locator).clear();
	 		getElement(locator).click();
	 		if (value == null) {
	 			System.out.println("value can not be null while using sendKeys method");
	 		}

	 		getElement(locator).sendKeys(value);
	 	}
	 	
	 	public void navigateTo(String URL) {
	 		driver.get(URL);
	 	}

	 	public void doClick(By locator) {
	 		getElement(locator).click();
	 	}

	 	public String doElementGetText(By locator) {
	 		String eleText = getElement(locator).getText();
	 		return eleText;
	 	}
	 	
	 	
	 	public WebElement getLinkEleByText(String linkText) {
	 		return driver.findElement(By.linkText(linkText));
	 	}

	 	public boolean checkElementIsDisplayed(By locator) {
	 		return getElement(locator).isDisplayed();
	 	}

	 	public String getElementAttribute(By locator, String attrName) {
	 		return getElement(locator).getDomAttribute(attrName);
	 	}

	 	public boolean checkElementIsDisabled(By locator) {
	 		String disabledValue = getElement(locator).getAttribute("disabled");
	 		if (disabledValue.equals("true")) {
	 			return true;
	 		}
	 		return false;
	 	}

	 	
	 	public WebElement getElement(By locator) {
	 		WebElement element = driver.findElement(locator);
	 		return element;
	 	}

	 	public int getElementsCount(By locator) {
	 		return getElements(locator).size();
	 	}

	 	public List<WebElement> getElements(By locator) {
	 		return driver.findElements(locator);
	 	}

	 	public List<String> getElementsTextList(By locator) {
	 		List<WebElement> eleList = getElements(locator);
	 		List<String> eleTextList = new ArrayList<String>();// pc=0
	 		for (WebElement e : eleList) {
	 			String text = e.getText();
	 			if (!text.isEmpty()) {
	 				eleTextList.add(text);
	 			}
	 		}
	 		return eleTextList;
	 	}
	 	
	 	public List<String> getElementscrollingTextList(By locator) {
	 		JavascriptExecutor js = (JavascriptExecutor) driver;
	 		List<WebElement> eleList = getElements(locator);
	 		List<String> eleTextList = new ArrayList<String>();// pc=0
	 		for (WebElement e : eleList) {
	 			js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", e);
	 			String text = e.getText();
	 			if (!text.isEmpty()) {
	 				eleTextList.add(text);
	 			}
	 		}
	 		return eleTextList;
	 	}
	 	
	 	

	 	public void clickOnElementWithText(By locator, String linkText) {
	 		List<WebElement> linksList = getElements(locator);
	 		for (WebElement e : linksList) {
//	 			String text = e.getText();
	 			if (e.getText().equals(linkText)) {
	 				System.out.println("Selected value in the list " + linkText);
	 				e.click();
	 				break;
	 			}
	 		}
	 	}
	 	

	 	public void doSearch(By searchLocator, By searchSuggLocator, String searchKey, String suggName)
	 			throws InterruptedException {
	 		doSendKeys(searchLocator, searchKey);
	 		Thread.sleep(4000);
	 		List<WebElement> suggList = getElements(searchSuggLocator);
	 		System.out.println(suggList.size());
	 		for (WebElement e : suggList) {
	 			String text = e.getText();
	 			System.out.println(text);
	 			if (text.contains(suggName)) {
	 				e.click();
	 				break;
	 			}
	 		}
	 	}
	 	
	 	
	 	public void tickCheckBox(By locator) {
	 		waitForElementVisible(locator, AppConstants.LONG_TIME_OUT);
	 		if (getElement(locator).isSelected()) {
	 			System.out.println("CheckBox is checked");
	 		}else {
	 			getElement(locator).click();
	 		}
	 	}

	 	// ****************************Drop Down Utils************************//
	 	public void doSelectDropDownByIndex(By locator, int index) {
	 		if (index < 0) {
	 			System.out.println("please pass the right (+ve) index");
	 			return;
	 		}
	 		Select select = new Select(getElement(locator));
	 		select.selectByIndex(index);
	 	}

	 	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
	 		if (visibleText == null) {
	 			System.out.println("please pass the right visible text and it can not be null");
	 			return;
	 		}
	 		Select select = new Select(getElement(locator));
	 		select.selectByVisibleText(visibleText);
	 	}

	 	public void doSelectDropDownByValue(By locator, String value) {
	 		if (value == null) {
	 			System.out.println("please pass the right visible text and it can not be null");
	 			return;
	 		}
	 		Select select = new Select(getElement(locator));
	 		select.selectByValue(value);
	 	}

	 	public int getDropDownOptionsCount(By locator) {
	 		Select select = new Select(getElement(locator));
	 		return select.getOptions().size();
	 	}

	 	public List<String> getDropDownTextList(By locator) {
	 		Select select = new Select(getElement(locator));
	 		List<WebElement> optionsList = select.getOptions();
	 		List<String> optionsTextList = new ArrayList<String>();
	 		for (WebElement e : optionsList) {
	 			String text = e.getText();
	 			System.out.println(text);
	 			optionsTextList.add(text);
	 		}
	 		return optionsTextList;
	 	}

	 	public void doSelectDropDownValue(By locator, String dropDownValue) {
	 		Select select = new Select(getElement(locator));
	 		List<WebElement> optionsList = select.getOptions();

	 		for (WebElement e : optionsList) {
	 			String text = e.getText();
	 			System.out.println(text);
	 			if (text.equals(dropDownValue)) {
	 				e.click();
	 				break;
	 			}
	 		}
	 	}

	 	public void doSelectDropDownValueUsingLocator(By locator, String dropDownValue) {
	 		List<WebElement> optionsList = getElements(locator);
	 		for (WebElement e : optionsList) {
	 			String text = e.getText();
	 			if (text.equals(dropDownValue)) {
	 				e.click();
	 				break;
	 			}
	 		}
	 	}

	 	// ****************Actions utils********************//
	 	
	 	
	 	public void doActionsClick(By locator) {
	 		act.click(getElement(locator)).perform();
	 	}
	 	
	 	public void doActionsSendKeys(By locator, String value) {
	 		act.sendKeys(getElement(locator), value).perform();
	 	}
	 	
	 	

	 	public void selectRightClickOption(By contextMenuLocator, String optionValue) {
	 		act.contextClick(getElement(contextMenuLocator)).perform();
	 		By optionLocator = By.xpath("//*[text()='" + optionValue + "']");
	 		doClick(optionLocator);
	 	}
	 	
	 	public void scrolljavascript() {
	 		JavascriptExecutor js = (JavascriptExecutor) driver;
	 		js.executeScript("document.querySelector('scroll-container-selector').scrollBy(0, 300)");
	 	}
	 	
	 	public boolean isEnabled(By locator) {
	 		return getElement(locator).isEnabled();
	 	}
	 	
	 	public void scrollDown(By locator) {
			Actions actions = new Actions(driver);
//			waitForElementsVisible(locator, AppConstants.MEDIUM_TIME_OUT);
			actions.clickAndHold(getElement(locator)).moveByOffset(0, 300).pause(1000).release().perform();
	 	}
	 	
	 	public void scrollUp(By locator) {
			Actions actions = new Actions(driver);
			waitForElementsVisible(locator, AppConstants.MEDIUM_TIME_OUT);
			actions.clickAndHold(getElement(locator)).moveByOffset(0, -300).pause(1000).release().perform();
	 	}
	 	
	 	public void scrollByAmount() {
	 		Actions action = new Actions(driver);
	 		action.scrollByAmount(0, 300);
	 	}
	 	
	 	public void scrollToElement(By locator) {
	 		JavascriptExecutor js = (JavascriptExecutor) driver;
	 		// Scroll to the element
	 		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", locator);
	 	}
	 	
	 	public void pressKeyDown() {
	        try {
	            Actions actions = new Actions(driver);
	            actions.sendKeys(Keys.PAGE_DOWN).perform();
	            System.out.println("Successfully pressed PAGE_DOWN.");
	        } catch (Exception e) {
	            System.err.println("Error while pressing PAGE_DOWN: " + e.getMessage());
	        }
	    }
	 	
	 	public void zoomIn() {
	 		
	 		JavascriptExecutor js = (JavascriptExecutor) driver;
	 		js.executeScript("document.body.style.zoom='80%'");
	 		
	 	}
	 	
	 	public void pressKeyUp() {
	        try {
	            Actions actions = new Actions(driver);
	            actions.sendKeys(Keys.PAGE_UP).perform();
	            System.out.println("Successfully pressed PAGE_UP.");
	        } catch (Exception e) {
	            System.err.println("Error while pressing PAGE_UP: " + e.getMessage());
	        }
	    }
	 	
	 	public void pressKeyEnter() {
	        try {
	            Actions actions = new Actions(driver);
	            actions.sendKeys(Keys.ENTER).perform();
	        } catch (Exception e) {
	            System.err.println("Error while pressing EnterBtn: " + e.getMessage());
	        }
	    }
	 	

	 	/**
	 	 * this method will handle the menu upto two levels
	 	 * @param level1MenuLocator
	 	 * @param level2MenuLocator
	 	 * @throws InterruptedException
	 	 */
	 	public void multiLevelMenuHandling(By level1MenuLocator, By level2MenuLocator) throws InterruptedException {
	 		act.moveToElement(getElement(level1MenuLocator)).perform();
	 		Thread.sleep(1500);
	 		doClick(level2MenuLocator);
	 	}
	 	
	 	public void multiLevelMenuHandling(By level1Locator, String level2, String level3, String level4) throws InterruptedException {
	 		act.moveToElement(getElement(level1Locator)).perform();
	 		Thread.sleep(1500);
	 		act.moveToElement(getLinkEleByText(level2)).perform();
	 		Thread.sleep(1500);
	 		act.moveToElement(getLinkEleByText(level3)).perform();
	 		Thread.sleep(1500);
	 		getLinkEleByText(level4).click();

	 	}
	 	
	 	public void multiLevelMenuHandling(By level1Locator, String level2, String level3) throws InterruptedException {
	 		act.moveToElement(getElement(level1Locator)).perform();
	 		Thread.sleep(1500);
	 		act.moveToElement(getLinkEleByText(level2)).perform();
	 		Thread.sleep(1500);
	 		getLinkEleByText(level3).click();
	 	}
	 	
	 	
	 	//**************WaitUtils*************************//
	 	
	 	
	 	
	 	/**
	 	 * An expectation for checking that an element is present on the DOM of a page. 
	 	 * This does not necessarily mean that the element is visible.
	 	 * @param locator
	 	 * @param timeOut
	 	 * @return
	 	 */
	 	public WebElement waitForElementPresence(By locator, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	 	}
	 	
	 	public WebElement waitForElementPresence(By locator, int timeOut, int pollingTime) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
	 		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	 	}
	 	
	 	
	 	/**
	 	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 	 *  Visibility means that the element is not only displayed 
	 	 *  but also has a height and width that is greater than 0.
	 	 * @param locator
	 	 * @param timeOut
	 	 * @return
	 	 */
	
	 	public WebElement waitForElementVisible(By locator, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	 		return element;
	 	}
	 	
	 	public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
	 		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	 		
	 	}
	 	
	 	
	 	
	 	
	 	public void waitForScrollAndClick(By locator) {
	 	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 	    JavascriptExecutor js = (JavascriptExecutor) driver;

	 	    // Wait until JavaScript scroll execution completes
	 	    wait.until(driver1 -> {
	 	        try {
	 	            WebElement element = getElement(locator);
	 	            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
	 	            return element.isDisplayed();  // Ensure element is visible
	 	        } catch (Exception e) {
	 	            return false; // Keep waiting if script fails
	 	        }
	 	    });

	 	    // Wait until element is clickable
	 	    wait.until(ExpectedConditions.elementToBeClickable(locator));

	 	    // Click the element after scroll is completed
	 	   doClick(locator);
	 	}
	 	
	 	public void waitForScroll(By locator) {
	 	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 	    JavascriptExecutor js = (JavascriptExecutor) driver;

	 	    // Wait until JavaScript scroll execution completes
	 	    wait.until(driver1 -> {
	 	        try {
	 	            WebElement element = getElement(locator);
	 	            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
	 	            return element.isDisplayed();  // Ensure element is visible
	 	        } catch (Exception e) {
	 	            return false; // Keep waiting if script fails
	 	        }
	 	    });

//	 	    // Wait until element is clickable
//	 	    wait.until(ExpectedConditions.elementToBeClickable(locator));

	 	}

	 	
	 	
	 	/**
	 	 * An expectation for checking that there is at least one element present on a web page.
	 	 * @param locator
	 	 * @param timeOut
	 	 * @return
	 	 */
	 	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	 	}
	 	
	 	/**
	 	 * An expectation for checking that all elements present on the web page that match the locator are visible.
	 	 *  Visibility means that the elements are not only displayed 
	 	 *  but also have a height and width that is greater than 0.
	 	 * @param locator
	 	 * @param timeOut
	 	 * @return
	 	 */
	 	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	 	}
	 	
	 	
	 	/**
	 	 * An expectation for checking that the title contains a case-sensitive substring.
	 	 * @param titleFraction
	 	 * @param timeOut
	 	 * @return
	 	 */
	 	public String waitForTitleContains(String titleFraction, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		try {
	 			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
	 				return driver.getTitle();
	 			} else {
	 				System.out.println(titleFraction + " title value is not present...");
	 				return null;
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 			System.out.println(titleFraction + " title value is not present...");
	 			return null;
	 		}
	 	}
	 	

	 	public String waitForTitleIs(String titleValue, int timeOut) {
	 	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 	    try {
	 	        wait.until(ExpectedConditions.titleIs(titleValue));
	 	        return driver.getTitle();
	 	    } catch (TimeoutException e) {
	 	        System.out.println("Timeout: The title '" + titleValue + "' is not present within " + timeOut + " seconds.");
	 	    } catch (Exception e) {
	 	        System.out.println("An error occurred while waiting for the title: " + e.getMessage());
	 	        e.printStackTrace();
	 	    }
	 	    return null;
	 	}
	 	
	 	/**
	 	 * An expectation for the URL of the current page to contain specific text.
	 	 * @param urlFraction
	 	 * @param timeOut
	 	 * @return
	 	 */
	 	public String waitForURLContains(String urlFraction, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		try {
	 			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
	 				return driver.getCurrentUrl();
	 			} else {
	 				System.out.println(urlFraction + " url value is not present...");
	 				return null;
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 			System.out.println(urlFraction + " url value is not present...");
	 			return null;
	 		}
	 	}
	 	
	 	/**
	 	 * An expectation for the URL of the current page to be a specific url.
	 	 * @param urlValue
	 	 * @param timeOut
	 	 * @return
	 	 */
	 	public String waitForURLToBe(String urlValue, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		try {
	 			if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
	 				return driver.getCurrentUrl();
	 			} else {
	 				System.out.println(urlValue + " url value is not present...");
	 				return null;
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 			System.out.println(urlValue + " url value is not present...");
	 			return null;
	 		}
	 	}

	 	
	 	/**
	 	 * An expectation for the alert (JS) to be appeared on the page.
	 	 * @param timeOut
	 	 * @return
	 	 */
	 	public Alert waitForJSAlert(int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		return wait.until(ExpectedConditions.alertIsPresent());
	 	}
	 	
	 	public Alert waitForJSAlert(int timeOut, int pollingTime) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
	 		return wait.until(ExpectedConditions.alertIsPresent());
	 	}
	 
	 	public void scrollDown() {
	 		JavascriptExecutor js = (JavascriptExecutor) driver;
	 		js.executeScript("window.scrollBy(0, 1000);");
	 	}
	 	
	 	
	 	
	 	public boolean waitForNumberOfBrowserWindows(int timeOut, int numberOfWindowsToBe) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		return wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindowsToBe));
	 	}
	 	
	 	
	 	public void waitForFrameByLocator(By frameLocator, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	 	}
	 	
	 	public void waitForFrameByIndex(int frameIndex, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	 	}
	 	
	 	public void waitForFrameByFrameNameOrID(String frameNameOrID, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrID));
	 	}
	 	
	 	public void waitForFrameByFrameElement(WebElement frameElement, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	 	}
	 	
	 	
	 	/**
	 	 * An expectation for checking an element is visible and enabled such that you can click it.
	 	 * @param locator
	 	 * @param timeOut
	 	 */
	 	public void clickElementWhenReady(By locator, int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	 	}
	 	
	 	//***********************FluentWait Utils**************//
	 	
	 	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
	 		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	 				.withTimeout(Duration.ofSeconds(timeOut))
	 				.pollingEvery(Duration.ofSeconds(pollingTime))
	 				.ignoring(NoSuchElementException.class)
	 				.ignoring(StaleElementReferenceException.class)
	 				.withMessage("----time out is done...element is not found..." + locator);	

	 			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	 	}
	 	
	 	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
	 		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	 				.withTimeout(Duration.ofSeconds(timeOut))
	 				.pollingEvery(Duration.ofSeconds(pollingTime))
	 				.ignoring(NoSuchElementException.class)
	 				.ignoring(StaleElementReferenceException.class)
	 				.withMessage("----time out is done...element is not found..." + locator);	

	 			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	 	}
	 		
	 	public Alert waitForJSAlertWithFluentWait(int timeOut, int pollingTime) {
	 		
	 		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	 				.withTimeout(Duration.ofSeconds(timeOut))
	 				.pollingEvery(Duration.ofSeconds(pollingTime))
	 				.ignoring(NoAlertPresentException.class)
	 				.withMessage("----time out is done...Js alert is not found...");
	 		return wait.until(ExpectedConditions.alertIsPresent());
	 	}
	 	
	 	public void waitForFrameWithFluentWait(String frameNameOrID, int timeOut, int pollingTime) {
	 		
	 		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	 				.withTimeout(Duration.ofSeconds(timeOut))
	 				.pollingEvery(Duration.ofSeconds(pollingTime))
	 				.ignoring(NoSuchFrameException.class)
	 				.withMessage("----time out is done...Frame is not found...with name or id: " + frameNameOrID);
	 		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrID));
	 	}
	 	
	 	public void waitForElementAndEnterValue(By locator, int timeOut, int pollingTime, String searchKey) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		wait
	 			.pollingEvery(Duration.ofSeconds(pollingTime))
	 				.ignoring(NoSuchElementException.class)
	 					.withMessage("----time out is done...element is not found..." + locator)
	 						.until(ExpectedConditions.presenceOfElementLocated(locator))
	 							.sendKeys(searchKey);
	 	}
	 	
	 	public void waitForElementAndClick(By locator, int timeOut, int pollingTime) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		wait
	 			.pollingEvery(Duration.ofSeconds(pollingTime))
	 				.ignoring(NoSuchElementException.class)
	 					.withMessage("----time out is done...element is not found..." + locator)
	 						.until(ExpectedConditions.presenceOfElementLocated(locator))
	 							.click();
	 	}
	 	
	 	//**************Custom Waits**************//
	 	
	 	public WebElement retryingElement(By locator, int timeOut) {

	 		WebElement element = null;
	 		int attempts = 0;

	 		while (attempts < timeOut) {//10
	 			try {
	 				element = getElement(locator);
	 				System.out.println("element is found...." + locator + " in attempt " + attempts);
	 				break;

	 			} catch (NoSuchElementException e) {
	 				System.out.println("element is not found...." + locator + " in attempt " + attempts);
	 				try {
	 					Thread.sleep(500);//default polling time = 500 ms
	 				} catch (InterruptedException e1) {
	 					e1.printStackTrace();
	 				}	
	 			}
	 			attempts++;

	 		}

	 		if (element == null) {
	 			System.out.println("element is not found....tried for " + timeOut  + " secs " + " with the interval of " + 500 + " milli secs" );
	 		}

	 		return element;

	 	}
	 	
	 	
	 	public WebElement retryingElement(By locator, int timeOut, long pollingTime) {

	 		WebElement element = null;
	 		int attempts = 0;

	 		while (attempts < timeOut) {//10
	 			try {
	 				element = getElement(locator);
	 				System.out.println("element is found...." + locator + " in attempt " + attempts);
	 				break;

	 			} catch (NoSuchElementException e) {
	 				System.out.println("element is not found...." + locator + " in attempt " + attempts);
	 				try {
	 					Thread.sleep(pollingTime);
	 				} catch (InterruptedException e1) {
	 					e1.printStackTrace();
	 				}	
	 			}
	 			attempts++;

	 		}

	 		if (element == null) {
	 			System.out.println("element is not found....tried for " + timeOut  + " secs " + " with the interval of " + pollingTime + " mill secs" );
	 		}

	 		return element;

	 	}
	 	
	 	
	 	public boolean isPageLoaded(int timeOut) {
	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	 		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'")).toString();
	 		return Boolean.parseBoolean(flag);
	 	}
	 	
	 	public void switchToNewWindowTab() {

			String originalTab = driver.getWindowHandle();
			// Wait for the new tab to open (the number of handles should increase after
			// clicking the print button)
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.SHORT_TIME_OUT));
			wait.until(driver -> driver.getWindowHandles().size() > 1); // Wait until a new tab is opened

			// Switch to the new tab
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalTab)) {
					driver.switchTo().window(windowHandle); // Switch to the new tab
					String title = driver.getTitle();
					System.out.println("Title of the new tab: " + title);
					driver.close();
					break;
				}
			}
			driver.switchTo().window(originalTab);
			driver.navigate().refresh();
		}
	 	

	 	
	 }




