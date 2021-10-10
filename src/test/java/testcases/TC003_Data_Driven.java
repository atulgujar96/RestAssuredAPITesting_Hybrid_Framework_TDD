package testcases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import utilities.XLUtils;

public class TC003_Data_Driven extends TestBase
{
   @DataProvider(name="test_data")
   public String[][] getData() throws IOException
   {
	   String path=System.getProperty("user.dir")+"\\src\\main\\resources\\test_data.xlsx";
	   System.out.println(path);
	   int rownum=XLUtils.getRowCount(path, "sheet1");
	   int colcount=XLUtils.getCellCount(path,"sheet1",1);
	   String testdata[][]=new String[rownum][colcount];
	   for(int i=1;i<=rownum;i++)
	   {
		   for(int j=0;j<colcount;j++)
		   {
			   testdata[i-1][j]=XLUtils.getCellData(path, "sheet1", i, j);
		   }
	   }
	   return testdata;
   }
   
   @Test(dataProvider="test_data")
   public void createUsers(String name,String salary, String age) throws InterruptedException
   {
	   
	   logger.info("started test case TC003_Data_Driven");
	   RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	   httprequest=RestAssured.given();
	   JSONObject requestparams=new JSONObject();
	   requestparams.put("name", name);
	   requestparams.put("salary", salary);
	   requestparams.put("age", age);
	   httprequest.header("Content-Type","application/json");
	   httprequest.body(requestparams.toJSONString());
	   
	   response=httprequest.request(Method.POST,"/create");
	   String responsebody=response.getBody().asString();
	   System.out.println(responsebody);
	   int statuscode=response.getStatusCode();
	   System.out.println(statuscode);
	   Assert.assertEquals(200,statuscode);
	   
   }
   
   
}
