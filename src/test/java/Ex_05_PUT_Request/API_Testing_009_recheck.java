package Ex_05_PUT_Request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Testing_009_recheck {

    @Test

    public void putRecheckAgain()
    {
        String token = "66efd6501f45e7a";
        String bookingid = "9884";

        String payloadPUT= "{\n" +
                "    \"firstname\" : \"Saurabh\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification rs;
        Response response;
        ValidatableResponse vr;

        rs= RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com/");
        rs.basePath("/booking/"+bookingid);
        rs.contentType(ContentType.JSON);
        rs.cookie("token",token);
        rs.body(payloadPUT);
        rs.log().all();

        response = rs.when().put();

        vr=response.then().log().all();
        vr.statusCode(200);

    }
}
