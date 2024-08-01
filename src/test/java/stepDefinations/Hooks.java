package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException

	{
		// execute this code only when place id is null
		// write a code that will give you place id

		StepDefination Hook = new StepDefination();
		
		if (StepDefination.place_id == null) {

			Hook.add_place_payload_with("DHANYATHA", "FRENCH", "MANGALORE");
			Hook.user_calls_with_http_request("AddPlaceAPI", "POST");
			Hook.verify_place_id_created_maps_to_using("DHANYATHA", "GetPlaceAPI");
		}

	}
}
