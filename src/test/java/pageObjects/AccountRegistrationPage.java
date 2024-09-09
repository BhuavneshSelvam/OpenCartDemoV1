package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txt_firstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txt_lastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_eMail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txt_telePhone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_pwd;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txt_confirmPwd;
	@FindBy(xpath="//input[@name='agree']") WebElement checkBxConsent;
	@FindBy(xpath="//input[@value='Continue']") WebElement bttn_Continue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	public void enterFirstName(String fName)
	{
		txt_firstName.sendKeys(fName);
	}
	
	public void enterLastName(String lName)
	{
		txt_lastName.sendKeys(lName);
	}
	
	public void enterEmail(String eMailAddress)
	{
		txt_eMail.sendKeys(eMailAddress);
	}
	
	public void enterTelephone(String tel)
	{
		txt_telePhone.sendKeys(tel);
	}
	
	public void enterPassword(String password)
	{
		txt_pwd.sendKeys(password);
	}
	
	public void confirmPassword(String password)
	{
		txt_confirmPwd.sendKeys(password);
	}
	
	public void giveConsent()
	{
		checkBxConsent.click();
	}
	
	public void accountRegister()
	{
		bttn_Continue.click();
	}
	
	
	public String getConfirmationMsg()
	{
		try {
			return(msgConfirmation.getText());
		}
		catch (Exception e)
		{
			return(e.getMessage());
		}
	}
	
	
}
