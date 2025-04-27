package Ex_01_RestAssured;

import io.restassured.RestAssured;

public class APITesting_001 {
    public static void main(String[] args) {

        // Gherkins Syntax
        // Given() -> Pre Req. - URL, Headers, Auth, Body....
        // When() -> HTTP method? - GET/POST/PUT/PATCH, DELETE...
        // Then() -> Validation -> 200 oK, firstname == Saurabh


        RestAssured.given().baseUri("https://api.zippopotam.us")
                .basePath("/IN/110094").when().log().all()
                .get().
                then().log().all()
                .statusCode(200);
    }
}
