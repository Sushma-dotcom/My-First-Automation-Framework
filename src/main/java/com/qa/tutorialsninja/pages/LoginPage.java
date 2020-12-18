package com.qa.tutorialsninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.tutorialsninja.base.BasePage;
import com.qa.tutorialsninja.utils.Constants;
import com.qa.tutorialsninja.utils.ElementUtil;

import io.qameta.allure.Step;


public class LoginPage extends BasePage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login' and @type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//private By registerLink = By.linkText("Register");

	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 3. page actions: features(Behvaior) of the page in the form methods:
	@Step("Getting page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitlePresent(Constants.Login_Page_Title, 10);
	}
	@Step("forgot password link exist")
public boolean isForgotPasswordLinkExist() {
	//return driver.findElement(forgotPwdLink).isDisplayed();
	return elementUtil.doIsDisplay(forgotPwdLink);
}
	@Step("logn with user name : {0} and password : {1}")
public AccountsPage doLogin(String un, String pwd) {
	System.out.println("Login with : "+ un + " and "+ pwd );
	//driver.findElement(emailId).sendKeys(un);
	//driver.findElement(password).sendKeys(pwd);
	//driver.findElement(loginButton).click();
	elementUtil.doSendKeys(emailId, un);
	elementUtil.doSendKeys(password, pwd);
	elementUtil.doClick(loginButton);
	
	return new AccountsPage(driver);
	}
	@Step("Nevigate to register page")
public RegisterPage navigateToRegisterPage() {
	elementUtil.doClick(registerLink);
	return new RegisterPage(driver);
}


}
