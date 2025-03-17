package Ex_10_Assertion;


import static org.assertj.core.api.Assertions.assertThat;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;





public class APITesting_018_All_Assert {

    @Test
    public void testCreateBookingPost()
    {
        RequestSpecification rs;
        Response response;
        ValidatableResponse vr;

        String payload="{\n" +
                "    \"firstname\" : \"Raja\",\n" +
                "    \"lastname\" : \"Ram\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        rs= RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON);
        rs.body(payload).log().all();
        response = rs.when().post();
        rs.log().all();

        vr=response.then().statusCode(200);
        vr.log().all();

        //RestAssured assertion

        vr.body("booking.firstname", Matchers.equalTo("Raja"));
        vr.body("booking.lastname",Matchers.equalTo("Ram"));
        vr.body("booking.depositpaid",Matchers.equalTo(true));
        vr.body("bookingid",Matchers.notNullValue());



        // Extract the value from  response
        String firstname=response.then().extract().path("booking.firstname");
        String lastname=response.then().extract().path("booking.lastname");
        Integer bookingId = response.then().extract().path("bookingid");

        //TestNg Assertion

       // Assert.assertEquals(firstname,"Raja");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(firstname,"Raja");
        softAssert.assertEquals(lastname,"Ram");
        softAssert.assertNotNull(bookingId);

        //Assertj

        assertThat(bookingId).isNotNull().isNotZero().isPositive();
        assertThat(firstname).isNotNull().isNotBlank().isNotEmpty().isEqualTo("Raja");
        assertThat(lastname).isNotNull().isNotBlank().isNotEmpty().isEqualTo("Ram");





    }
}
