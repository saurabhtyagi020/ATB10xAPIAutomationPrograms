package Ex_08_GET;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Testing_012 {

    @Test
    public void getBookingId()
    {
        RequestSpecification rs;
        Response response;
        ValidatableResponse vr;

        rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com/");
        rs.basePath("/booking");
        rs.log().all();

        response = rs.when().get();
        rs.log().all();

        vr = response.then().log().all();
        vr.statusCode(200);
    }
}
