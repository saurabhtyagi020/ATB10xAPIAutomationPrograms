package Ex_10_Assertion;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting_017_TestNG_Assertion {

    //Hard assert
    @Test(enabled = false)
    public void method1()
    {
        System.out.println("Enter in the method");
        Assert.assertTrue(false);
        System.out.println("Exit from the program");
    }


    //Soft assert

    @Test
    public void method2()
    {
        System.out.println("Enter in the method");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
        System.out.println("Exit from the program");
        softAssert.assertAll();
    }


}
