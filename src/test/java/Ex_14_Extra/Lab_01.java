package Ex_14_Extra;

import io.restassured.RestAssured;

public class Lab_01 {

    public static void main(String[] args) {
//https://api.zippopotam.us/IN/110094
        RestAssured.given().baseUri("https://api.zippopotam.us")
                .basePath("/IN/110094").when().log().all().get()
                .then().statusCode(200).log().all();

    }
}
