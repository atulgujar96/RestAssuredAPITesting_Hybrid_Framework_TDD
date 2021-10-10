package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employee extends TestBase
{
    
	@BeforeClass
	public void getAllEmployees() throws InterruptedException
	{
		
		logger.info("started test case execution");
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET,"/employees");
		Thread.sleep(2000);
	}
	
	@Test
	public void checkResponseBody()
	{
		logger.info("checking response body");
		String responsebody = response.getBody().asString();
		logger.info("response body "+responsebody);
		Assert.assertTrue(responsebody!=null);
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("checking status code");
		int statuscode = response.getStatusCode();
		logger.info("Status code is "+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	public void checkResponseTime()
	{
		logger.info("checking response time");
		long responsetime = response.getTime();
		logger.info("response time is "+responsetime);
		if(responsetime > 2000)
		{
			logger.warn("response time is greater than 2000");
		}
			Assert.assertTrue(responsetime<2000);
	}
	
	@Test
	public void checkStatusLine()
	{
		String statusline=response.getStatusLine();
		logger.info("status line is "+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void checkContentType()
	{
		String contenttype=response.header("Content-Type");
		logger.info("Content type is "+contenttype);
		Assert.assertEquals(contenttype, "application/json");
	}
	
	@Test
	public void checkServerType()
	{
		String servertype=response.header("server");
		logger.info("server is "+servertype);
		Assert.assertEquals(servertype, "cloudflare");
	}
	
	@Test
	public void checkContetEncoding()
	{
		String contentencoding=response.header("Content-Encoding");
		logger.info("content encoding is "+contentencoding);
		Assert.assertEquals(contentencoding, "gzip");
	}
	
	@Test
	public void checkContentLength()
	{
		String contentlength = response.header("Content-Length");
		logger.info("content length is "+contentlength);
		Assert.assertTrue(Integer.parseInt(contentlength)>80000);
	}
	
	/*@Test
	public void checkCookies()
	{
		String cookies = response.getCookie("PHPSESSID");
		logger.info("Cookies is"+cookies);
		Assert.assertEquals(cookies, "abc45678j");
	}
	*/
	
	@AfterClass
	public void tearDown()
	{
		logger.info("finished executing test cases");
	}
}
