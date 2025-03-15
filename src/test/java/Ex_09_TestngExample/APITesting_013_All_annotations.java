package Ex_09_TestngExample;

import org.testng.annotations.*;

public class APITesting_013_All_annotations {

    @BeforeSuite
    public void test1()
    {
        System.out.println("I am before suite");
    }

    @BeforeTest
    public void test2()
    {
        System.out.println("I am before test");
    }
    @BeforeClass
    public void test3()
    {
        System.out.println("I am BeforeClass");
    }

    @BeforeMethod
    public void test4()
    {
        System.out.println("I am BeforeMethod");
    }

    @Test
    public void test5()
    {
        System.out.println("I am Test!");
    }

    @AfterMethod

    public void test6()
    {
        System.out.println("I am After method");
    }

    @AfterClass

    public void test7()
    {
        System.out.println("I am After class");
    }

    @AfterTest
    public void test8()
    {
        System.out.println("I am After Test");
    }

    @AfterSuite
    public void test9()
    {
        System.out.println("I am After Suite");
    }





}
