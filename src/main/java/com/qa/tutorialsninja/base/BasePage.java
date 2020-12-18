package com.qa.tutorialsninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author Sushma Dewangan
 *
 */

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver> ();
	public WebDriver init_driver(String browser) {
		System.out.println("browser value is::"+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		//	driver=new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		else if(browser.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
		//driver=new InternetExplorerDriver();
			tlDriver.set(new InternetExplorerDriver());
		}
		else {
			System.out.println("Paas the correct browser value::"+browser);
		}
		geDriver().manage().deleteAllCookies();
		geDriver().manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return geDriver();
	}
	
	public static synchronized WebDriver geDriver() {
		return tlDriver.get();
		
	}
	/**
	 * This method loads the properties from config.properties file
	 * @returns properties prop reference
	 */
	public Properties init_prop() {
		 prop=new Properties();
		 try {
			FileInputStream ip = new FileInputStream(".\\src\\main\\java\\com\\qa\\tutorialsninja\\config\\config.properties");
		prop.load(ip);
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return prop;
		
	}
public String getScreenshot() {
	File src = ((TakesScreenshot) geDriver()).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
	File destination = new File(path);
	try {
		FileUtils.copyFile(src, destination);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return path;
}

}
