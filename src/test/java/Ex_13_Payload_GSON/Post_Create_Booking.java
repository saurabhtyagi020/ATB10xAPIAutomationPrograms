package Ex_13_Payload_GSON;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Post_Create_Booking {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void post_create_positive_booking()
    {
        Booking booking = new Booking();
        booking.setFirstname("Saurabh");
        booking.setLastname("Tyagi");
        booking.setDepositpaid(true);
        booking.setTotalprice(75);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        Gson gson = new Gson();
        String bookingjsonstring=gson.toJson(booking);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.body(bookingjsonstring);
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().post();
        String jsonResponseString = response.asString();

        validatableResponse = response.then().statusCode(200);
        validatableResponse.log().all();

        BookingResponse bookingResponse = gson.fromJson(jsonResponseString,BookingResponse.class);
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBooking().getLastname());
        System.out.println(bookingResponse.getBooking().getDepositpaid());

        String fName=bookingResponse.getBooking().getFirstname();
        Assert.assertEquals(fName,"Saurabh");

        String cDate=bookingdates.getCheckin();
        System.out.println(cDate);

        Assert.assertEquals(cDate,"2018-01-01");





    }
}
