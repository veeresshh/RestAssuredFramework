package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	public static RequestSpecification Request;

	public RequestSpecification requestSpecifications() throws IOException

	{

		if (Request == null) // VVIP Step to create two logs see video

		{

			PrintStream Log = new PrintStream(new FileOutputStream("logging.txt"));

			Request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(Log))
					.addFilter(ResponseLoggingFilter.logResponseTo(Log)).setContentType(ContentType.JSON).build();

			return Request;
		}

		return Request;

	}

	public static String getGlobalValue(String Key) throws IOException {

		Properties Property = new Properties();
		FileInputStream FIS = new FileInputStream(
				"C:\\Users\\VEERESH\\eclipse-workspace\\RestAssuredFramework\\src\\test\\java\\resources\\Global.properties");
		Property.load(FIS);

		return Property.getProperty(Key);

	}

	public String getJsonPath(Response response, String Key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(Key).toString();
	}

}
