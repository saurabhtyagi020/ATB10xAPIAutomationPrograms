package Ex_14_Extra;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Lab_05_PUT {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse vr;


    @Test
    public void test_Post()
    {

        String payload="{\n" +
                "    \"firstname\" : \"Brown\",\n" +
                "    \"lastname\" : \"New\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        String token= "3b831580a285129";
        String bookingid ="436";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/").basePath("/booking/"+bookingid).contentType(ContentType.JSON).log()
                .all().body(payload).cookie("token",token);

        response = requestSpecification.when().log().all().put();

        vr= response.then().log().all().statusCode(200);

    }
}
