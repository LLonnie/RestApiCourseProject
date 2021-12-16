package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
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
}
