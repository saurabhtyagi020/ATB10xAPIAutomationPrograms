package Ex_03_GET_Request_BDD_NONBDD;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITesting_004 {


    @Test
    public void withBDDstyle()
    {
        String pincode ="110094";
        RestAssured.given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
                .when()
                .log().all()
                .then()
                .log().all()
                .statusCode(200);
    }
}
