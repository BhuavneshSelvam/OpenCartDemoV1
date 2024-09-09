package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter; //set UI
	public ExtentReports extent; //populate common infos
	public ExtentTest test; // Add test results
	
	String repName;
	
	 public void onStart(ITestContext context) {
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt = new Date();
		 String timeStamp = df.format(dt);
		 
		 //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 
		 repName = "Test-Report-" + timeStamp + ".html";
		 
		 sparkReporter = new ExtentSparkReporter (".\\reports\\" + repName);
		 
		 sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		 sparkReporter.config().setReportName("OpenCart Functional Testing");
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Application", "OpenCart");
		 extent.setSystemInfo("Module", "Admin"); // in Key-value pair
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environment", "QA");
		 
		 String os = context.getCurrentXmlTest().getParameter("OS");
		 extent.setSystemInfo("Operating System", os);
		 
		 String br = context.getCurrentXmlTest().getParameter("Browser");
		 extent.setSystemInfo("Browser Name", br);
		 
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		 if(!includedGroups.isEmpty())
		 {
			 extent.setSystemInfo("Groups", includedGroups.toString());
		 }
		 
	 }
/*
	 public void onTestStart(ITestResult result) {
		    System.out.println("Test started...");
		    
		  //After each test method is executed...
		    
		  }
*/
	 
	public void onTestSuccess(ITestResult result) {
	   
		//result - will capture the test method
		
		test = extent.createTest(result.getTestClass().getName());//create a new entry in the report with name of the test class
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");
	  }
	
	 public void onTestFailure(ITestResult result) {
		    
		 test = extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.FAIL, result.getName() + " got failed");
		 test.log(Status.INFO, result.getThrowable().getMessage()); // captures the error message
		 
		 try {
			 String imgPath = new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgPath);
		 }
		 catch(Exception e1)
		 {
			 e1.printStackTrace();
		 }
		 
	 }

	 public void onTestSkipped(ITestResult result) {
		    
		test = extent.createTest(result.getTestClass().getName());
		
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage()); // captures the error message
		  }
	 
	 public void onFinish(ITestContext context) {
		    
		 extent.flush(); //Writes test information from the started reporters to their output view
		 
		 //to open the report automatically
		 
		 String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		 File extentReport = new File(pathOfExtentReport);
		 
		 try {
			 Desktop.getDesktop().browse(extentReport.toURI());
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}

}
