package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	/*Scenarios:
	 * 
	 * Data is valid - login success - test pass - logout
	 * 
	 * Data is valid - login unsuccessful - test fail - no need to logout
	 * 
	 * Data is invalid - login success - test fail - logout
	 * 
	 * Data is invalid - login unsuccessful - test pass - no need to logout
	 * 
	 */
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven")
	public void verify_LoginDDTesting(String email, String pwd, String exp)
	{
		logger.info("***Starting TC003_LoginDDTTest***");
		
		try 
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		
		lp.enterEmailAddress(email);
		lp.enterPassword(pwd);
		lp.Login();
		
		MyAccountPage ap= new MyAccountPage(driver);
		boolean targetPage = ap.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage == true)
			{
				ap.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				ap.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC003_LoginDDTTest****");
		
	}
}