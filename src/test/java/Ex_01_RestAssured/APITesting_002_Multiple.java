package Ex_01_RestAssured;

import io.restassured.RestAssured;

public class APITesting_002_Multiple {
    public static void main(String[] args) {

        //Drawback of use multiple test case in same method
        //after first fail test case another will not execute.

        String pinCode="110094";
        //Positive Test case
        RestAssured.given().baseUri("https://api.zippopotam.us").
                basePath("/IN/"+pinCode).when().log().all().get()
                .then().log().all().statusCode(200);

        //Negative test case when pincode is special character

        pinCode = "@";

        RestAssured.given().baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pinCode)
                .when()
                .log().all().get()
                .then().statusCode(200);

        //When pincode is negative

        pinCode = "-100";

        RestAssured.given().baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pinCode)
                .when()
                .log().all().get()
                .then().statusCode(200);



    }
}
