package Ex_09_TestngExample;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_Parameter_016 {

    @Parameters("browser")
    @Test
    public void paraMethod1(String value)
    {
        if(value.equalsIgnoreCase("chrome"))
        {
            System.out.println("launch chrome!");
        }
        if(value.equalsIgnoreCase("firefox"))
        {
            System.out.println("launch firefox!");
        }

    }

}
