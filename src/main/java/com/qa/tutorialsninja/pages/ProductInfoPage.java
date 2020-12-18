package com.qa.tutorialsninja.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.tutorialsninja.base.BasePage;
import com.qa.tutorialsninja.utils.ElementUtil;



public class ProductInfoPage extends BasePage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By productHeader = By.cssSelector("#content h1");
	private By productMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	private By quantity= By.id("input-quantity");
	private By addToCartButton= By.id("button-cart");
	private By productImages= By.cssSelector(".thumbnails li img ");
	private By succesMessage = By.cssSelector(".alert-success");
	private By success = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By productSuccesMessage = By.cssSelector(".alert a:nth-of-type(1)");
	private By shoppingCartSuccesMessage = By.cssSelector(".alert a:nth-of-type(2)");
	

    public ProductInfoPage(WebDriver driver) {
	this.driver=driver;
	elementUtil = new ElementUtil(this.driver);
     }
    public Map<String, String> getProductInformation() {
	Map<String, String> productInfoMap= new HashMap<String, String>();
	productInfoMap.put("name", elementUtil.doGetText(productHeader).trim());
	List<WebElement> productMetaDataList=elementUtil.getElements(productMetaData);
	for(WebElement e : productMetaDataList)
	{
		productInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		
	}
	List<WebElement> productPriceList=elementUtil.getElements(productPrice);
		
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
	return productInfoMap;
    }
    public boolean selectQuantity(String qty) {
    elementUtil.getElement(quantity).clear();
	elementUtil.doSendKeys(quantity, qty);
	if(elementUtil.getElements(quantity).size()>=0){ 
		return true; 
		} 
	return false;
	 
    }
    
    public void addToCart() {
    	elementUtil.doClick(addToCartButton);
    	
    	 //String actual_msg=driver.findElement(succesMessage).getText();
        
    	//String str=driver.findElement(productSuccesMessage).getText();
    	//System.out.println(actual_msg);
    
    }
    

    
    public int getProductImages() {
    	int imagesCount= elementUtil.getElements(productImages).size();	
    	System.out.println("Total number of images is::"+imagesCount);
    	return imagesCount;
	}
	public String getProductInfoPageTitle(String productName) {
		return elementUtil.waitForTitlePresent(productName, 5);
		
	}
}
