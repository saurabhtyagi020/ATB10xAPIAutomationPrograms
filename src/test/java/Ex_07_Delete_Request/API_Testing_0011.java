package Ex_07_Delete_Request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Testing_0011 {


    @Test
    public void deleteRequestCheck()
    {
        String token="be02ed2bcf970c4";
        String bookingId="3948";


        RequestSpecification rs;
        Response response;
        ValidatableResponse vr;

        rs= RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com/");
        rs.basePath("/booking/"+bookingId);
        rs.cookie("token",token);
        rs.log().all();

        response =rs.when().delete();
        rs.log().all();

        vr = response.then().log().all();
        vr.statusCode(201);


    }

    }

