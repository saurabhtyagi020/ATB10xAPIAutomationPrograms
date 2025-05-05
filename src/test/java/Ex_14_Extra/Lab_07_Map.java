package Ex_14_Extra;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.codehaus.groovy.util.ListHashMap;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Lab_07_Map {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse vr;

    @Test
    public void test_create_Booking()
    {

        Map<String,Object> jsonmapString = new LinkedHashMap<>();
        jsonmapString.put("firstname","Saurabh");
        jsonmapString.put("lastname","Tyagi");
        jsonmapString.put("totalprice",123);
        jsonmapString.put("depositpaid",true);
        jsonmapString.put("additionalneeds","lunch");

        Map<String,Object> bookingDates = new LinkedHashMap<>();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");

        jsonmapString.put("bookingdates", bookingDates);
        System.out.println(jsonmapString);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com").basePath("/booking")
                .contentType(ContentType.JSON).body(jsonmapString).log().all();

        response=requestSpecification.log().all().when().post();
        vr= response.then().log().all().statusCode(200);

    }

}
