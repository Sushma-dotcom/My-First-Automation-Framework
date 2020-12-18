package com.qa.tutorialsninja.tests;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.tutorialsninja.base.BaseTest;
import com.qa.tutorialsninja.utils.Constants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority = 1 , enabled = true)
	public void verifyProductInfoPageTitleTest_iMac() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"),"iMac");
		
	}
	
	
	@Test(priority = 3, enabled = true)
	public void verifyProductInfoTest_iMac() {
		String productName = "iMac";
		//Assert.assertTrue(accountsPage.doSearch(productName));
		productInfoPage = accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.getProductImages()==3);
		Map<String,String> productInfoMap=productInfoPage.getProductInformation();
		System.out.println(productInfoMap);
		Assert.assertEquals(productInfoMap.get("name"), "iMac");
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("price"), "$100.00");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 14");		
		Assert.assertEquals(productInfoMap.get("Availability"), "In Stock");	
	
	}
	
//	@Test(priority = 4)
//	public void verifyProductQuantity() {
//		//accountsPage.doSearch("iMac");
//		//productInfoPage = accountsPage.selectProductFromResults("iMac");
//		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"),"iMac");
//		Assert.assertTrue(productInfoPage.selectQuantity(Constants.Product_Quantity));
//		
//	}
	@Test(priority = 5)
	public void verifyAddToCart() {
		productInfoPage.addToCart();
		
		
		
	}
}

