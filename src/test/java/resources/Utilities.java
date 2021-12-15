package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	RequestSpecification requestSpec;

	public RequestSpecification requestSpecification() {
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.build();
		return requestSpec;
	}
}
