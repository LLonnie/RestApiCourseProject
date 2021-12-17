package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utilities {

	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	static String placeID;

	TestDataBuild data = new TestDataBuild();

	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {


		responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		request = given()
				.spec(requestSpecification())
				.body(data.addPlacePayload(name, language, address));
	}

	@When("User calls {string} with {string} request")
	public void user_calls_with_request(String resource, String method) {
		APIResources apiCall = APIResources.valueOf(resource);

		if(method.equalsIgnoreCase("POST")) {
			response = request.when().post(apiCall.getResource());
		} else if(method.equalsIgnoreCase("GET")) {
			response = request.when().get(apiCall.getResource());
		} else if(method.equalsIgnoreCase("PUT")) {
			response = request.when().put(apiCall.getResource());
		} else if(method.equalsIgnoreCase("DELETE")) {
			response = request.when().delete(apiCall.getResource());
		}

	}

	@Then("The API call is successful with status code {string}")
	public void the_api_call_is_successful_with_status_code(String statusCode) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);
	}

	@And("Verify place_Id created maps to {string} using {string}")
	public void verify_placeId_created_maps_to_using(String expectedName, String resource) throws IOException {

		placeID = getJsonPath(response, "place_id");

		request = given()
				.spec(requestSpecification())
				.queryParam("place_id", placeID);

		user_calls_with_request(resource, "GET");

		String name = getJsonPath(response, "name");
		Assert.assertEquals(name, expectedName);
	}

	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {

		request = given()
				.spec(requestSpecification())
				.body(data.deletePlacePayload(placeID));
	}
}
