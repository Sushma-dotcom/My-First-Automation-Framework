package com.qa.tutorialsninja.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;
	public  ElementUtil(WebDriver driver) {
		this.driver=driver;
		
	}
	public  By getLocator(String value) {
		return By.id(value);

	}
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}
	public  WebElement getElement(By locator) {
		return driver.findElement(locator);

	}
	public  void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	public  void doClick(By locator) {
		getElement(locator).click();
	}
	public  String doGetText(By locator) {
		return getElement(locator).getText();
		
	}
	public  boolean doIsDisplay(By locator) {
		return getElement(locator).isDisplayed();
	}
	public  int getElementsCount(String tagName) {
		return driver.findElements(By.tagName(tagName)).size();

	}

	public  List<String> getAttributesList(String tagName, String attributeName) {
		List<String> attrList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.tagName(tagName));
		for (WebElement e : elementList) {
			String text = e.getAttribute(attributeName);
			attrList.add(text);
		}
		return attrList;
	}
	public  void doClickFromList(By locator, String linkText) {
		List<WebElement> footerList = getElements(locator);
		for (int i = 0; i < footerList.size(); i++) {
			String text = footerList.get(i).getText();
			if (text.equals(linkText)) {
				footerList.get(i).click();
				break;
			}
		}
	}
	// ***************************Drop Down Utils
		// ***********************************
	public  void doSelectByVisibleText(By locator, String text) {
		WebElement ele= getElement(locator);
		Select select=new Select(ele);
		select.selectByVisibleText(text);
	}
	public  void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public  void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}
	public void selectDropDownValueWithoutSelectClass(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);

		for (WebElement e : optionsList) {

			String text = e.getText();

			if (text.equals(value)) {
				e.click();
				break;
			}

		}
	}
	//******Actions Class util************
	public  void moveToElements(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
	}
	public  void doActionSendkeys(By locator, String value) {
		Actions actins = new Actions(driver);
		actins.sendKeys(getElement(locator), value).perform();
	}

	public  void doActinsClick(By locator) {
		Actions actins = new Actions(driver);
		actins.click(getElement(locator)).perform();
	}
	public  void doSendKeysWithMoveToElement(By locator, String value) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).sendKeys(value).build().perform();
	}
	
	public  void doClickWithMoveToElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).click().build().perform();
	}
	// *****************************wait utils ************************
	public List<WebElement> visibilityOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public  void getPageLinkText(By locator, int timeout) {
		visibilityOfAllElements(locator,timeout).stream().forEach(ele -> System.out.println(ele.getText()));	
	}
	public  void getPageLinkCount(By locator, int timeout) {
		visibilityOfAllElements(locator,timeout).size();	
	}
	
	public int getPageLinksCount(By locator, int timeOut) {
		return visibilityOfAllElements(locator, timeOut).size();
	}

	public String waitForTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	public String waitForTitlePresent(String titleValue, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut, intervalTime);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	public Alert waitForAlertToBePresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public boolean waitForUrl(String urlValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(urlValue));
	}
	public  WebElement waitForElementToBeLocated(By locator, int timeout) {
		WebDriverWait wait=new WebDriverWait(driver,10);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public  WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	// *******************Custom waits************************
	/**
	 * 
	 * This is Custom dynamic waits to find the element
	 * @return
	 */
	public  WebElement retryingElement(By locator) {
		WebElement element = null;
		int attepmts=0;
		while(attepmts<30) {
			try {
				element=driver.findElement(locator);
				break;
				
			}
			catch (StaleElementReferenceException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
			}
			catch (NoSuchElementException e) {	
				try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {

			}

			System.out.println("element is not found in attempt : " + (attepmts + 1));
			}
			attepmts++;
		}
		
		return element;
		
	}
	
	}



