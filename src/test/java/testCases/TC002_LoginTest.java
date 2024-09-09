package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	
	
	@Test(groups={"Sanity", "Master"})
	public void VerifyAccountLogin()
	{
		
		logger.info("***Starting TC002_LoginTest***");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		
		lp.enterEmailAddress(p.getProperty("email"));
		lp.enterPassword(p.getProperty("password"));
		lp.Login();
		
		MyAccountPage ap= new MyAccountPage(driver);
		boolean targetPage = ap.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true, "Login Failed");
		}
		catch(Exception e)
		{
		  Assert.fail();
		}
		logger.info("****Finished TC002_LoginTest****");
		
	}
	

}
