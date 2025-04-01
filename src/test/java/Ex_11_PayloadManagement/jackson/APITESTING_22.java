package Ex_11_PayloadManagement.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Ex_11_PayloadManagement.AI_03.BookingDates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITESTING_22 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void testJackson() throws JsonProcessingException {
        Booking booking = new Booking();
        booking.setFirstname("Tyagi");
        booking.setLastname("Saurabh");
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Require");
        booking.setTotalprice(876);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2027-09-01");
        bookingDates.setCheckout("2029-09-02");
        booking.setBookingdates(bookingDates);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsongStringbooking = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsongStringbooking).log().all();

        Response response = requestSpecification.when().post();
        String jsonResponseString  = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingjsonresponse = objectMapper.readValue(response.asString(), BookingResponse.class);

        assertThat(bookingjsonresponse.getBookingid()).isNotZero().isNotNull();
        assertThat(bookingjsonresponse.getBooking().getFirstname()).isEqualTo("Tyagi").isNotNull().isNotEmpty();



    }
}
