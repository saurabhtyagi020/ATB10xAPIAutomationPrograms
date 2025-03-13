import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Ex_06_PATCH_Request {

    @Test
    public void pathRequestForRest()
    {
        String token="8c49c1b7ded09da";
        String bookingId="1098";
        String payload="{\n" +
                "    \"firstname\" : \"Tyagi\",\n" +
                "    \"lastname\" : \"JIII\",\n" +
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

        rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking/"+bookingId);
        rs.contentType(ContentType.JSON);
        rs.cookie("token",token);
       // rs.auth().basic("admin","password123");
        rs.body(payload).log().all();

        response =rs.when().patch();

        vr= response.then().log().all();
        vr.statusCode(200);

    }
}
