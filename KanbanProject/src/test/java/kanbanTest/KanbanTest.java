package kanbanTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import extentReports.ExtentreportMethod;
import testbase.Testbase;
import utilityRead.ReadPropertiesFile;

public class KanbanTest extends Testbase {

	HttpURLConnection httpConn;
	ReadPropertiesFile readProp = new ReadPropertiesFile();
	URL link;

	static ExtentreportMethod extReport;
	ITestResult result;

	@BeforeTest
	void beforeTest() {

	}

	@BeforeClass
	void beforeclass() {
		extReport = new ExtentreportMethod();
		extReport.extentReportCall();
	}

	@Test
	public void verifyTextHeader() throws MalformedURLException {
		extReport.createEntryInReport("Verify Text Header");
		Testbase.intialization1();
		String page_heading = driver.findElement(By.xpath("//html/body/app-root/div/app-home/div/div[1]/h1")).getText();
		String compare_assert = page_heading.substring(0, 6);

		try {
			Assert.assertEquals("KANBAN", compare_assert);
			System.out.println("Test passed");
		} finally {
			result = Reporter.getCurrentTestResult();
		}
		
	}

	@Test
	public void verifyResponseStatusCode() throws MalformedURLException, IOException {

		extReport.createEntryInReport("Verify Response StatusCode");
		link = new URL(readProp.readPropertiesConfig("appURL"));
		httpConn = (HttpURLConnection) link.openConnection();
		httpConn.connect();
		System.out.println("Connected");
		try {
			if (httpConn.getResponseCode() == 200) {
				System.out.println(link + " - " + httpConn.getResponseMessage() + " and Response code is "
						+ httpConn.getResponseCode());
			} 
			else  
			{
			String responsecode=String.valueOf(httpConn.getResponseCode());
				Assert.assertEquals(responsecode, "200","malformed url");
			
			}
		} finally {
			result = Reporter.getCurrentTestResult();
		}

	}

	@AfterMethod
	void afterMethod() throws IOException {
		extReport.tearDown(result);

	}

	@AfterClass
	void afterclass() throws IOException
	{
		driver.close();

	}

	@AfterTest
	void afterTest() throws IOException {

	}

	@AfterSuite

	void afterSuit() throws IOException {
		extReport.flushReport();

	}

}
