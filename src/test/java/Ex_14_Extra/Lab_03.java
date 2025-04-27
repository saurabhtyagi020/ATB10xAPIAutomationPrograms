package Ex_14_Extra;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Lab_03 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse vr;


    ///https://api.zippopotam.us/IN/110094
    @Test(priority = 1)
    public void test_Positive()
    {
        String pincode="110094";
        requestSpecification = RestAssured.given()
                .baseUri("https://api.zippopotam.us").basePath("IN/"+pincode);

        response = requestSpecification.when().log().all().get();

        vr= response.then().log().all().statusCode(200);

    }

    @Test(priority = 2)
    public void test_Negative()
    {
        String pincode="-11";
        requestSpecification = RestAssured.given()
                .baseUri("https://api.zippopotam.us").basePath("IN/"+pincode);

        response = requestSpecification.when().log().all().get();

        vr= response.then().log().all().statusCode(200);
    }
}
