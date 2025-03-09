package Ex_02_RestAssWithTestng;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
public class APITesting_003_MTfix {


    @Test
    @Description("Verify the GET request positive")
    public void positiveTestcase()
    {
        String pinCode="110094";
        RestAssured.given().baseUri("https://api.zippopotam.us/IN/110094")
                .basePath("/IN/"+pinCode)
                .when().log().all()
                .then().log().all().statusCode(200);
    }



    @Test
    @Description("Verify the GET request with INVALID data")
    public void negativeTestcase()
    {
        String pinCode="@";
        RestAssured.given().baseUri("https://api.zippopotam.us/IN/110094")
                .basePath("/IN/"+pinCode)
                .when().log().all()
                .then().log().all().statusCode(200);
    }

}
