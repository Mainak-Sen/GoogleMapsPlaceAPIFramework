package StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Resources.APIResources;
import Resources.API_TestData;
import Resources.Specs;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Specs{
	private static RequestSpecification req_spec;
	private static Response req_response;
    static String generated_placeID;
	
	@Given("Add Place payload with {string} {string} {string} {string}")
	public void add_Place_payload_with(String name, String phone_number, String address, String language) throws IOException{
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		//GogleMaps Add place API with serialization
		req_spec=given().spec(requestSpecification()).body(new API_TestData().add_place_payload(name, phone_number, address, language));
	}
	@Given("UpdatePlace payload with {string} details")
	public void updateplace_payload_with_details(String updated_address) throws IOException {
		req_spec=given().spec(requestSpecification()).body(new API_TestData().update_place_payload(updated_address,generated_placeID));    
	}

	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
		req_spec=given().spec(requestSpecification()).body(new API_TestData().delete_place_payload(generated_placeID));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String api, String httpMethod) throws IOException{
		
		APIResources api_obj=APIResources.valueOf(api);//this method returns the Enum constant type(under APIResources)
		                                               //of the specified enumType with the specified name(in this case the type of api i.e
		                                               //ADD_PLACE_API/DELETE_PLACE_API etc (identical to creating an object)
		if(httpMethod.equalsIgnoreCase("get")) {
			req_spec=given().spec(requestSpecification()).queryParam("place_id",generated_placeID);
			req_response=req_spec.when().get(api_obj.get_resource());
		}
		else if(httpMethod.equalsIgnoreCase("post")) {
			req_response=req_spec.when().post(api_obj.get_resource());
		}
		else if(httpMethod.equalsIgnoreCase("put")) {
			req_response=req_spec.when().put(api_obj.get_resource());
		}
		//.then().spec(responseSpecification()).extract().response();
	}

	@Then("The API call returns status code {int}")
	public void the_API_call_returns_status_code(int status_code) {
		req_response=req_response.then().spec(responseSpecification()).extract().response();
	   Assert.assertEquals(req_response.getStatusCode(),status_code);
	}

	@Then("The {string} displayed in the response body should be {string}")
	public void the_displayed_in_the_response_body_should_be(String key, String value) {
		Assert.assertEquals(extract_response_details(req_response,key),value);
	}
	@Then("Verify if the placeID has mapped {string} to the desired {string} using {string}")
	public void verify_if_the_placeID_has_mapped_to_the_desired_using(String field, String field_value, String api) throws IOException{
	    if(!extract_response_details(req_response,"place_id").isEmpty())
		{generated_placeID=extract_response_details(req_response,"place_id");}
	    //we need to use the above placeID to hit the GET request
	    user_calls_with_http_request(api,"GET");
	    Assert.assertEquals(extract_response_details(req_response,field),field_value);
	}
	
}
