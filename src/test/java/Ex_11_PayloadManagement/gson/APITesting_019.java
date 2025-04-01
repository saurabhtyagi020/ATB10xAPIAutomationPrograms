package Ex_11_PayloadManagement.gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITesting_019 {

    RequestSpecification rs;
    Response response;
    ValidatableResponse vr;
    @Test
    public void testCreateBooking() {

        // Step1 - POST
        // URL -> Base URI + base Path
        // HEADER
        // BODY
        // Auth - NO

        // Step 2
        // prepare the Payload ( Object -> JSON String)
        // send the request

        //Step 3
        // Validate Response ( JSON String -> Object)
        // FirstName,
        // Status Code
        // Time Response


        Booking booking  = new Booking();
        booking.setFirstname("Saurabh");
        booking.setLastname("Tyagi");
        booking.setDepositpaid(true);
        booking.setTotalprice(123);
        booking.setAdditionalneeds("Lunch");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-06");
        booking.setBookingdates(bookingdates);

        // Java Object -> JSON

        Gson gson = new Gson();
        String jsonStringbooking = gson.toJson(booking);
        System.out.println(jsonStringbooking);

        rs= RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com/");
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON);
        rs.body(jsonStringbooking).log().all();

        response =rs.when().post();
        String jsonResponseString  = response.asString();

        vr= response.then().statusCode(200);
        vr.log().all();

        // Case1 - extract() - Direct Extraction

       //  String bookingId1=response.then().extract().path("bookingid");
           String firstname1=response.then().extract().path("booking.firstname");
        String checkin1=response.then().extract().path("booking.bookingdates.checkin");
       // System.out.println(bookingId1);
        System.out.println(firstname1);
        System.out.println(checkin1);

        Assert.assertEquals(firstname1,"Saurabh");
        Assert.assertEquals(checkin1,"2024-02-01");
      //  Assert.assertNotNull(bookingId1);

        assertThat(firstname1).isNotNull().isNotEmpty().isNotBlank().isEqualTo("Saurabh");

        // Case 2 - jsonPath.getString("")  JSON Path Extraction

        JsonPath jsonpath = new JsonPath(response.asString());
        String bookingId =jsonpath.getString("bookingid");
        String checkin=jsonpath.getString("booking.bookingdates.checkin");
        System.out.println(bookingId);
        System.out.println(checkin);

        assertThat(checkin).isNotEmpty().isNotEmpty().isNotNull();


        // Server - JSONString> Java Object( BookingResponse) - getter to verify

        // Case 3 - DeSer - Extraction
        //  Response - De Ser another Response Class

        BookingResponse bookingResponse=gson.fromJson(jsonResponseString,BookingResponse.class);
        System.out.println("ResponseVerify");
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getLastname());
        System.out.println(bookingdates.getCheckin());
        System.out.println(bookingResponse.getBooking().getDepositpaid());









    }

}
