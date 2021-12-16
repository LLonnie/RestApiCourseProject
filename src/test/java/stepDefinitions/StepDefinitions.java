package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import resources.TestDataBuild;
import resources.Utilities;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utilities {

	RequestSpecification requestSpec;
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;

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

	@When("User calls {string} with POST request")
	public void user_calls_with_post_request(String apiCall) {
		response = request
				.when()
				.post("/maps/api/place/add/json")
				.then()
				.spec(responseSpec)
				.extract().response();
	}

	@Then("The API call is successful with status code {string}")
	public void the_api_call_is_successful_with_status_code(String statusCode) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String responseString = response.asString();
		JsonPath jsonPath = new JsonPath(responseString);
		Assert.assertEquals(jsonPath.get(keyValue.toLowerCase()).toString(), expectedValue);
	}
}
