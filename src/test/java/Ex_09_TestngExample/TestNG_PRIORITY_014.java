package Ex_09_TestngExample;

import org.testng.annotations.Test;

public class TestNG_PRIORITY_014 {

    @Test(priority = 2)
    public void method1()
    {
        System.out.println("method1");
    }
    @Test(priority = 3)
    public void method2()
    {
        System.out.println("method2");
    }
    @Test(priority = 1)
    public void method3()
    {
        System.out.println("method3");
    }
}
