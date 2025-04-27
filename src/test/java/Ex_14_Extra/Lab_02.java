package Ex_14_Extra;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Lab_02 {

    public static void main(String[] args) {
//https://api.zippopotam.us/IN/110094
        RequestSpecification requestSpecification = RestAssured.given().baseUri("https://api.zippopotam.us")
                .basePath("/IN/110094");

          Response response=requestSpecification.when().log().all().get();

        ValidatableResponse vr = response.then().log().all().statusCode(200);



    }
}
