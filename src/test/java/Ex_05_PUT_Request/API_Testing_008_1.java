package Ex_05_PUT_Request;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Testing_008_1 {

    @Description("Verify the PUT Request for the Restful Booker APIs")
    @Test
    public void test_put_non_bdd(){

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



        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
//        requestSpecification.auth().basic("admin","password123");


        requestSpecification.cookie("token",token);
        requestSpecification.body(payloadPUT).log().all();

        Response response = requestSpecification.when().put();


        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);



    }

}
