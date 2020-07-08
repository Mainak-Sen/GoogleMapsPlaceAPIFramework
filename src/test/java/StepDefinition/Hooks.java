package StepDefinition;

import java.io.IOException;

import cucumber.api.java.Before;

public class Hooks {

@Before("@UpdatePlace or @DeletePlace")
public void before_Scenario() throws IOException {
	StepDefinition sd=new StepDefinition();
	if(StepDefinition.generated_placeID==null)
	{
		sd.add_Place_payload_with("Stoner_Mainak","(+91) 983 873 3037","Virginia,USA","German");
		sd.user_calls_with_http_request("ADD_PLACE_API","POST");
		sd.verify_if_the_placeID_has_mapped_to_the_desired_using("name","Stoner_Mainak","GET_PLACE_API");
	}
}
}
