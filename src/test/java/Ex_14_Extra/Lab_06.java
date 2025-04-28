package Ex_14_Extra;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Lab_06 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse vr;
    @Test
    public void test_Create_Post()
    {
        Booking booking = new Booking();
        booking.setFirstname("Saurabh");
        booking.setLastname("Tyagi");
        booking.setTotalprice("600");
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("breakfast");

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingdates);
        System.out.println(booking);



        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/").basePath("/booking").contentType(ContentType.JSON)
                .body(booking);

        response = requestSpecification.when().log().all().post();

        vr= response.then().log().all().statusCode(200);
    }
}
