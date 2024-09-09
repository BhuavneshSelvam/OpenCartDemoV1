package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	////a[normalize-space()='Login'] - linkLogin
	
	//input[@id='input-email'] - usrname
	
	//input[@id='input-password']  - pwd
	
	//input[@value='Login'] - bttnLogin
	
	//constructor
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locator
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_emailAdd;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_psswd;
	@FindBy(xpath="//input[@value='Login']") WebElement bttn_Login;
	
	//Action Methods
	
	public void enterEmailAddress(String emailAddress)
	{
		txt_emailAdd.sendKeys(emailAddress);
	}
	
	public void enterPassword(String passwrd)
	{
		txt_psswd.sendKeys(passwrd);
	}
	
	public void Login()
	{
		bttn_Login.click();
	}

}
