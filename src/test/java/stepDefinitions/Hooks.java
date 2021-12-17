package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//write code that will get place id
		//execute when only place id is null
		StepDefinitions stepDefinitions = new StepDefinitions();
		if(StepDefinitions.placeID == null) {
			stepDefinitions.add_place_payload("Demo Place", "English", "55 SouthEast");
			stepDefinitions.user_calls_with_request("AddPlaceApi", "POST");
			stepDefinitions.verify_placeId_created_maps_to_using("Demo Place", "GetPlaceApi");
		}
	}
}
