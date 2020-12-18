package com.qa.tutorialsninja.tests;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.tutorialsninja.base.BaseTest;
import com.qa.tutorialsninja.testlistners.ExtentReportListener;
import com.qa.tutorialsninja.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Listeners(ExtentReportListener.class)
@Epic("Epic 100 : Define login page feature")
@Story("US 100 : Design login page")
public class LoginPageTest extends BaseTest {
	@Description("verify login title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is : " + title);
		Assert.assertEquals(title, Constants.Login_Page_Title);
	}
	@Description("verify forgot pwd link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void verifyForgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	@Description("verify login page test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
}