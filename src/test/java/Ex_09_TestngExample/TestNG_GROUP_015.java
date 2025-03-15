package Ex_09_TestngExample;

import org.testng.annotations.Test;


public class TestNG_GROUP_015 {

    @Test(groups = {"sanity", "reg"})
    public void method1()
    {
        System.out.println("Sanity");
        System.out.println("Reg");
        System.out.println("P1");
    }

    @Test(groups = {"reg","p1"})
    public void method2()
    {
        System.out.println("reg");
        System.out.println("P1");
    }

    @Test(groups = {"p1","sanity"})
    public void method3()
    {
        System.out.println("P1");
        System.out.println("Sanity");
    }



}
