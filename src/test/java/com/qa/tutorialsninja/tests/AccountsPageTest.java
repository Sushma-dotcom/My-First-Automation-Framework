package com.qa.tutorialsninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.tutorialsninja.base.BaseTest;
import com.qa.tutorialsninja.utils.Constants;



public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void homePageSetup() {
	accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}

	@Test(priority = 2)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("accounts page title is : " + title);
		Assert.assertEquals(title, Constants.Accounts_Page_Title);
	}

	@Test(priority = 1)
	public void verifyAccountsPageHeaderTest() {
		String headerValue = accountsPage.getHeaderValue();
		System.out.println("accounts page header is : " + headerValue);
		Assert.assertEquals(headerValue, Constants.Accounts_Page_Header);
	}

	@Test(priority = 3)
	public void verifyAccountSectionCountTest() {
    Assert.assertTrue(accountsPage.getAccountSectionCount()==Constants.Accounts_Section);		
	}
	@Test(priority = 4)
	public void verifyMyAccountSectionsListTest() {
  	
    Assert.assertEquals(accountsPage.getAccountSectionsList(), Constants.getAccountSectionList());
	}
	@Test(priority = 5)
	public void searchTest() {
		Assert.assertTrue(accountsPage.doSearch("iMac"));
	}

}