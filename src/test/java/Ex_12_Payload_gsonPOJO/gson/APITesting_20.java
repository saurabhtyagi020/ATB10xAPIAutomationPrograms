package Ex_12_Payload_gsonPOJO.gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_20 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void testCreateBookingpositive()
    {
        Booking booking = new Booking();
        booking.setFirstname("Saurabh");
        booking.setLastname("Tyagi");
        booking.setDepositpaid(true);
        booking.setTotalprice(765);
        booking.setAdditionalneeds("Breakfast");


        Bookingdates bookingdates= new Bookingdates();
        bookingdates.setCheckin("2024-09-02");
        bookingdates.setCheckout("2025-01-02");
        booking.setBookingdates(bookingdates);

        Gson gson = new Gson();
        String jsonStringbooking= gson.toJson(booking);
        System.out.println(jsonStringbooking);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringbooking).log().all();

        response = requestSpecification.when().post();
        String jsonResponsestring = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = gson.fromJson(jsonResponsestring,BookingResponse.class);
        Integer idBooking = bookingResponse.getBookingid();
        System.out.println(idBooking);
        String fName = bookingResponse.getBooking().getFirstname();
        System.out.println(fName);




    }


}
