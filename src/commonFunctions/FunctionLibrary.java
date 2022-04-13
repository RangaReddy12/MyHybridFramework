package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil{
//method for login
	public static boolean verifyLogin(String username,String password)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("Objpass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
		Thread.sleep(4000);
		String expected ="adminflow";
		String actual = driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login success::"+expected+"   "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+expected+"   "+actual,true);
			return false;
		}
	}
	//method for click branches
	public static void clickBraches()
	{
		driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
	}
	//method for branch creation
	public static boolean verifynewBranch(String branchname,String address1,String address2,String addres3,
			String Area,String zipcode,String country,String state,String city)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(branchname);
		driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(address1);
		driver.findElement(By.xpath(config.getProperty("Objaddress2"))).sendKeys(address2);
		driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(addres3);
		driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
		driver.findElement(By.xpath(config.getProperty("Objzip"))).sendKeys(zipcode);
		new Select(driver.findElement(By.xpath(config.getProperty("Objcountry")))).selectByVisibleText(country);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(config.getProperty("Objstate")))).selectByVisibleText(state);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(city);
		Thread.sleep(3000);
		driver.findElement(By.xpath(config.getProperty("Objsubmit"))).click();
		//capture alert message
		String expectedalertmessage =driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String actualalertmessage = "New Branch with";
		if(expectedalertmessage.toLowerCase().contains(actualalertmessage.toLowerCase()))
		{
			Reporter.log(expectedalertmessage,true);
			return true;
		}
		else
		{
			Reporter.log("New Branch Creation Fail",true);
			return false;
		}
		
	}
	//method for branch updation
	public static boolean verifyBranchUpdation(String branch,String address,String zip)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("Objedit"))).click();
		Thread.sleep(4000);
		WebElement updatebranch =driver.findElement(By.xpath(config.getProperty("ObjBranch")));
		updatebranch.clear();
		updatebranch.sendKeys(branch);
		Thread.sleep(2000);
		WebElement updateaddress =driver.findElement(By.xpath(config.getProperty("ObjAddress")));
		updateaddress.clear();
		updateaddress.sendKeys(address);
		Thread.sleep(2000);
		WebElement updatezip =driver.findElement(By.xpath(config.getProperty("Objzipcode")));
		updatezip.clear();
		updatezip.sendKeys(zip);
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getProperty("ObjUpdate"))).click();
		Thread.sleep(4000);
		String expectedalert = driver.switchTo().alert().getText();
		Thread.sleep(4000);
		driver.switchTo().alert().accept();
		String actualalert = "Branch updated";
		if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
		{
			Reporter.log(expectedalert,true);
			return true;
		}
		else
		{
			Reporter.log("Branch update is fail",true);
			return false;
		}
		
	}
	//method for logout
	public static boolean verifyLogout()throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("Objlogout"))).click();
		Thread.sleep(4000);
		if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed())
		{
			Reporter.log("Logout success",true);
			return true;
		}
		else
		{
			Reporter.log("Logout Fail",true);
			return false;
		}
	}
}









