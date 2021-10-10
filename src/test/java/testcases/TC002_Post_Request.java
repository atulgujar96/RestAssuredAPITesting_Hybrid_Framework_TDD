package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;


// https://reqres.in/api/users

public class TC002_Post_Request extends TestBase
{
    @Test
	public void createUser()
	{
    	
    	logger.info("started test case TC002_Post_Request");
		RestAssured.baseURI="https://reqres.in/api";
		httprequest=RestAssured.given();
		JSONObject requestparams=new JSONObject();
		requestparams.put("name", "atul");
		requestparams.put("job", "manager");
		httprequest.header("Content-Type","application/json");
		httprequest.body(requestparams.toJSONString());
		
		response=httprequest.request(Method.POST,"/users");
		
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 201);
		System.out.println(statuscode);
		
		String name=response.jsonPath().get("name");  // json path
		System.out.println(name);
		Assert.assertEquals(name, "atul");
		
		String job=response.jsonPath().get("job");
		System.out.println(job);
	    Assert.assertEquals(job, "manager");
	    
	    //validating headers
	    
	    String content_type=response.header("Content-Type");
	    System.out.println(content_type);
	    Assert.assertEquals(content_type, "application/json; charset=utf-8");
	    
	    String server=response.header("server");
	    System.out.println(server);
	    Assert.assertEquals(server, "cloudflare");
	    
	    
		//Printing all headers and their values
	    
	    Headers headers=response.headers();
	    for(Header header:headers)
	    {
	    	System.out.println(header.getName()+" : "+header.getValue());
	    }
	    
	    // validating responsebody
	    String responsebody=response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(responsebody.contains("name"),true);
	    
		// validating all fields of response body
		JsonPath jsonpath = response.jsonPath();
		Assert.assertEquals(jsonpath.get("name"), "atul");
		Assert.assertEquals(jsonpath.get("job"), "manager");
					
	}
}