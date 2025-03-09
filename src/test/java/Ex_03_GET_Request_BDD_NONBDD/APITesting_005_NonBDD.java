package Ex_03_GET_Request_BDD_NONBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_005_NonBDD {

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Test
    public void postiveTestCase()
    {
        String pincode = "110094";
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/110094");

        response = r.when().log().all().get();

        vr= response.then().log().all().statusCode(200);
    }

    @Test
    public void negativeTestCase()
    {
        r=RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/-11");

        response=r.when().log().all().get();

        vr= response.then().log().all().statusCode(404);
    }
}
