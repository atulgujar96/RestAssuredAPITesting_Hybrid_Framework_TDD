package base;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class TestBase 
{
  public static RequestSpecification httprequest;
  public static Response response;
  public static Logger logger=LogManager.getLogger(TestBase.class.getName());
  
  
  /* public void setup()
  {
	 // logger = LogManager.getLogger(TestBase.class.getName());
	  
  }*/
}
