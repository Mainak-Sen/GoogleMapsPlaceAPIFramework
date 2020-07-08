package Resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {
	private static RequestSpecification req_spec;
	private static ResponseSpecification res_spec;
	private static JsonPath js;
	
public RequestSpecification requestSpecification() throws IOException {
	

	
	if(req_spec==null)
	{
	PrintStream ps=new PrintStream(new FileOutputStream("log.txt"));
	req_spec=new RequestSpecBuilder().setBaseUri(Specs.get_Global_Values("BaseUri"))
			.addQueryParam("key","qaclick123").setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(ps))
			.addFilter(ResponseLoggingFilter.logResponseTo(ps)).build();
	}
	return req_spec;
}

public ResponseSpecification responseSpecification(){
	res_spec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	return res_spec;
}

public static String get_Global_Values(String key) throws IOException {
Properties prop= new Properties();	
FileInputStream fis=new FileInputStream("./utility.properties");
prop.load(fis);
return prop.getProperty(key);
}

public String extract_response_details(Response response,String key)
{
	js=new JsonPath(response.asString());
	if(js.getString(key)!=null)
	{
	 String value=js.getString(key);
	 return value;
	}
	else
	{
		return "";
	}
}
}
