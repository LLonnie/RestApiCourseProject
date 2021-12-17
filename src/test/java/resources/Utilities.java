package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utilities {

	public static RequestSpecification requestSpec;

	public RequestSpecification requestSpecification() throws IOException {

		if (requestSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("request-response.txt"));
			requestSpec = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
		}
		return requestSpec;
	}

	public static String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/global.properties");
		properties.load(fileInputStream);
		return properties.getProperty(key);
	}

	public static String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath jsonPath = new JsonPath(responseString);
		return jsonPath.get(key).toString();
	}
}
