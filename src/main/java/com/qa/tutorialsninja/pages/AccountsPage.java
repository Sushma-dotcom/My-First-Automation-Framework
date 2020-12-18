package com.qa.tutorialsninja.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.tutorialsninja.base.BasePage;
import com.qa.tutorialsninja.utils.Constants;
import com.qa.tutorialsninja.utils.ElementUtil;

public class AccountsPage extends BasePage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By header = By.cssSelector("div #logo a");
	private By accountSectionHeaders = By.cssSelector("div #content h2");
	private By searchText = By.cssSelector("div #search input[name='search']");
	private By searchButton = By.cssSelector("div #search button[type='button']");
	private By searchItemsResult = By.cssSelector(".product-layout .product-thumb");
	private By resultItems = By.cssSelector(".product-thumb h4 a");
    public AccountsPage(WebDriver driver) {
	this.driver=driver;
	elementUtil = new ElementUtil(this.driver);
	}
    public String getAccountsPageTitle() {
    	return elementUtil.waitForTitlePresent(Constants.Accounts_Page_Title, 10);
	}
	public String getHeaderValue() {
		if(elementUtil.doIsDisplay(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
		
	}
    public int getAccountSectionCount() {
	   //return driver.findElements(accountSectionHeaders).size();
    	return elementUtil.getElements(accountSectionHeaders).size();
    }

	public List<String> getAccountSectionsList() {
		List<String> accountsList=new ArrayList<String>();
		List<WebElement> accountSectionList=elementUtil.getElements(accountSectionHeaders);
		for(WebElement e: accountSectionList) {
			System.out.println(e.getText());
			accountsList.add(e.getText());
		}
		return accountsList;
		}
	public boolean doSearch(String productName) {
		
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton); 
			if(elementUtil.getElements(searchItemsResult).size()>0){
			return true;
		}
		return false;
		
	}
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemList=elementUtil.getElements(resultItems);
		System.out.println("Total number of Items Display::" +resultItemList.size());
		for(WebElement e : resultItemList) {
			System.out.println(e.getText());
			if(e.getText().equals(productName)){
			
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
