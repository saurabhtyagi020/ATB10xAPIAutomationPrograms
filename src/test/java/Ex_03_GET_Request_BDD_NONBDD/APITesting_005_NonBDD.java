package Ex_03_GET_Request_BDD_NONBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_005_NonBDD {

    RequestSpecification rs;
    Response response;
    ValidatableResponse vr;

    @Test
    public void postiveTestCase()
    {
        String pincode = "110094";
        rs = RestAssured.given();
        rs.baseUri("https://api.zippopotam.us");
        rs.basePath("/IN/110094");

        response = rs.when().log().all().get();

        vr= response.then().log().all().statusCode(200);
    }

    @Test
    public void negativeTestCase()
    {
        rs=RestAssured.given();
        rs.baseUri("https://api.zippopotam.us");
        rs.basePath("/IN/-11");

        response=rs.when().log().all().get();

        vr= response.then().log().all().statusCode(404);
    }
}
