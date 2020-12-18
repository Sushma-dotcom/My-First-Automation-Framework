 package com.qa.tutorialsninja.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	public static final String Login_Page_Title="Account Login";
	public static final String Accounts_Page_Title="My Account";
	public static final String Accounts_Page_Header="Your Store";
	public static final int Accounts_Section=4;
	public static final String ACCOUNT_SUCCESS_MESSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "registration";
	public static final String Product_Quantity="2";
	public static List<String> getAccountSectionList() {
		List<String> accountList=new ArrayList<String>();
		accountList.add("My Account");
		accountList.add("My Orders");	
		accountList.add("My Affiliate Account");
		accountList.add("Newsletter");
		return accountList;
			}
	
}
