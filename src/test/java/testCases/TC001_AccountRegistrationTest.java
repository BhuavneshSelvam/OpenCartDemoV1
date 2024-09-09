package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration()
	{
		logger.info("******Starting TC001_AccountRegistrationTest******");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("****Clicked On MyAccount Link****");
		
		hp.clickRegister();
		
		logger.info("****Clicked On Register Link****");
		
		AccountRegistrationPage ar = new AccountRegistrationPage(driver);
		
		
		logger.info("****Providing Customer Details****");
		
		ar.enterFirstName(randomString().toUpperCase());
		ar.enterLastName(randomString().toUpperCase());
		ar.enterEmail(randomString()+"@gmail.com");
		ar.enterTelephone(randomNumber());
		
		String password = randomAlphanumeric();
		ar.enterPassword(password);
		ar.confirmPassword(password);
		ar.giveConsent();
		ar.accountRegister();
		
		logger.info("****Validating Expected Message****");
		
		String confirmationMsg = ar.getConfirmationMsg();
		if(confirmationMsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("*****Test Failed*****");
			logger.debug("*****Debug Logs*****");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");
		
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC001_AccountRegistrationTest****");
	}
	
}
