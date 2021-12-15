package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import mapsPojos.AddPlace;
import mapsPojos.AddPlace_Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitions {

	@Given("Add Place payload")
	public void add_place_payload() {
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.build();

		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		AddPlace_Location location = new AddPlace_Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);

		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");

		AddPlace place = new AddPlace();
		place.setLocation(location);
		place.setAccuracy(50);
		place.setName("Frontline house");
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress("29, side layout, cohen 09");
		place.setTypes(types);
		place.setWebsite("http://google.com");
		place.setLanguage("French-IN");

		RequestSpecification request = given()
				.spec(requestSpec)
				.body(place);
		throw new io.cucumber.java.PendingException();
	}

	@When("User calls \"([^\"]*)\" with POST request")
	public void user_calls_with_post_request(String apiCall) {
		Response response = request
				.when()
				.post("/maps/api/place/add/json")
				.then()
				.spec(responseSpec)
				.extract().response();
		throw new io.cucumber.java.PendingException();
	}

	@Then("The API call is successful with status code \"([^\"]*)\"")
	public void the_api_call_is_successful_with_status_code(Integer statusCode) {
		String responseString = response.asString();
		throw new io.cucumber.java.PendingException();
	}

	@And("\"([^\"]*)\" in response body is \"([^\"]*)\"")
	public void in_response_body_is(String key, String value) {
		String responseString = response.asString();
		throw new io.cucumber.java.PendingException();
	}
}
