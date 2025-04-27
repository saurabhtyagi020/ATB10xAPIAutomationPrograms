package Ex_13_Payload_GSON;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PUT_UPDATE_BOOKING {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String bookinid="449";
    String token="ad3692870c277e5";
    @Test
    public void test_update_booking()
    {
        Booking booking = new Booking();
        booking.setFirstname("Gaurav");
        booking.setLastname("Tyagi");
        booking.setDepositpaid(true);
        booking.setTotalprice(1000);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        Gson gson = new Gson();
        String jsonStingPayload =gson.toJson(booking);
        System.out.println(jsonStingPayload);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookinid);
        requestSpecification.body(jsonStingPayload);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.header("Cookie", "token=" + token);

        response =requestSpecification.when().put();
        String responseAsString =response.asString();

        validatableResponse = response.then().statusCode(200);
        validatableResponse.log().all();

        Booking bookingresponse= gson.fromJson(responseAsString,Booking.class);


        String updated_first_name=bookingresponse.getFirstname();
        System.out.println("updated_first_name: "+updated_first_name);

        Assert.assertEquals(updated_first_name,"Gaurav");





    }
}
