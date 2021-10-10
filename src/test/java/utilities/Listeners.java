package utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter
{
	
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void onTestSuccess(ITestResult tr) {
		test.log(Status.PASS, "test case passed is"+tr.getName());
	}
	
	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.FAIL,"test case failed is"+tr.getName());
		test.log(Status.FAIL,"test case failed is"+tr.getThrowable());
	}
	
	public void onTestSkipped(ITestResult tr) {
		test=extent.createTest(tr.getName());
		test.log(Status.SKIP, "test case skipped is"+tr.getName());		
	}
	
	public void onStart(ITestContext testContext) {
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setReportName("Rest API Automation testing report");
		htmlreporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Hostname", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "atul");
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
}
