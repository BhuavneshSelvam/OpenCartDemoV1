package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"OS", "Browser"})
	public void setup(String OS, String Br) throws IOException
	{
		
		//Loading config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties"); //getting the file
		p = new Properties();
		
		p.load(file);
		
		
		
		logger = LogManager.getLogger(this.getClass()); //load log4j2.xml file into logger.
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
				{
					DesiredCapabilities capabilities = new DesiredCapabilities();
					
					//os
					 if(OS.equalsIgnoreCase("windows"))
					 {
						 capabilities.setPlatform(Platform.WIN11);
					 }
					 
					 else if(OS.equalsIgnoreCase("mac"))
					 {
						 capabilities.setPlatform(Platform.MAC);
					 }
					 else if(OS.equalsIgnoreCase("linux"))
					 {
						 capabilities.setPlatform(Platform.LINUX);
					 }
					 else
					 {
						 System.out.println("No Matching OS");
						 return;
					 }
					//browser
					 
					 switch(Br)
					 {
					 case "chrome": capabilities.setBrowserName("chrome"); break;
					 
					 case "edge": capabilities.setBrowserName("edge"); break;
					 
					 case "firefox": capabilities.setBrowserName("firefox"); break;
					 default: System.out.println("No Matching Browser"); return;
					 
					 }
					 
					 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				} 
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(Br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "firfox" : driver = new FirefoxDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			default: System.out.println("Invalid browser Name"); return;
			}
		}
		
		
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get(p.getProperty("appURL"));
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphanumeric()
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber1 = RandomStringUtils.randomNumeric(3);
		return(generatedString1+generatedNumber1);
	}

	
	public String captureScreen(String tname)
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenShot = (TakesScreenshot) driver;
		
		File sourceFile = takesScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "-" + timeStamp + ".png";
		
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
