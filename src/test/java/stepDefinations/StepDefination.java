package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

public class StepDefination extends Utilities {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;

	static String place_id;

	TestDataBuild Data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException

	{

		res = given().spec(requestSpecifications()).body(Data.addPlacePayLoad(name, language, address));
	}

	@When("User calls {string} with {string} http Request")
	public void user_calls_with_http_request(String Resource, String Method) {

		APIResources ResourceAPI = APIResources.valueOf(Resource);
		System.out.println(ResourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (Method.equalsIgnoreCase("POST"))
			response = res.when().post(ResourceAPI.getResource());
		else if (Method.equalsIgnoreCase("GET"))
			response = res.when().get(ResourceAPI.getResource());

	}

	@Then("The API Call is Success with Status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in Response body is {string}")
	public void in_resonse_body_is(String Keyvalue, String Expectedvalue) {

		assertEquals(getJsonPath(response, Keyvalue), Expectedvalue);
	}

	@Then("verify Place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {

		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecifications()).queryParam("place_id", place_id);

		user_calls_with_http_request(resource, "GET");
		String ActualName = getJsonPath(response, "name");
		assertEquals(ActualName, ExpectedName);
	}

	@Given("DeletePlace Payload")
	public void deleteplace_payload() throws IOException {

		res = given().spec(requestSpecifications()).body(Data.deletePlacePayload(place_id));
	}

}
