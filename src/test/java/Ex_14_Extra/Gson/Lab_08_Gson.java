package Ex_14_Extra.Gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Lab_08_Gson {


    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse vr;

    @Test
    public void test_create_Booking()
    {
          Booking booking = new Booking();
          booking.setFirstname("Raghav");
          booking.setLastname("Singh");
          booking.setDepositpaid(true);
          booking.setTotalprice(345);
          booking.setAdditionalneeds("Breakfast");

          Bookingdates bookingdates = new Bookingdates();
          bookingdates.setCheckin("2019-09-01");
          bookingdates.setCheckout("2020-10-12");

          booking.setBookingdates(bookingdates);

          Gson gson = new Gson();
          String jsonString=gson.toJson(booking);

          requestSpecification = RestAssured.given();
          requestSpecification.baseUri("https://restful-booker.herokuapp.com").basePath("/booking")
                  .contentType(ContentType.JSON).body(jsonString);

          response = requestSpecification.when().log().all().post();
          String jsonStringResponse= response.asString();

          vr=response.then().log().all().statusCode(200);

          BookingResponse bookingResponse=gson.fromJson(jsonStringResponse,BookingResponse.class);

        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBookingid());











    }
}
