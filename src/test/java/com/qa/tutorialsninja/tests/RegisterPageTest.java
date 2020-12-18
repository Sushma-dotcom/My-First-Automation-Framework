package com.qa.tutorialsninja.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.tutorialsninja.base.BaseTest;
import com.qa.tutorialsninja.utils.Constants;
import com.qa.tutorialsninja.utils.ExcelUtil;



public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegisterationTest(String firstname, String lastname, String email, String telephone,
			String password, String subscribe) {

		registerPage.accountRegistration(firstname, lastname, email, telephone, password, subscribe);

	}


}
