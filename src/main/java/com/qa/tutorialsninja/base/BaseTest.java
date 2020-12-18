package com.qa.tutorialsninja.base;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import com.qa.tutorialsninja.pages.AccountsPage;

import com.qa.tutorialsninja.pages.LoginPage;
import com.qa.tutorialsninja.pages.ProductInfoPage;
import com.qa.tutorialsninja.pages.RegisterPage;

public class BaseTest {
	public BasePage basePage;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	public Properties prop;
	public WebDriver driver;
	@BeforeTest
	public void setUp() {
		
		basePage=new BasePage();
		prop=basePage.init_prop();
		String browser=prop.getProperty("browser");
	    driver=	basePage.init_driver(browser);
	  //  demoWebShopPage=new DemoWebShopPage(driver);
	    loginPage=new LoginPage(driver);
	    driver.get(prop.getProperty("url"));
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
