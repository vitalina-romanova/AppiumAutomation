package tests;

import helpers.DriverUtil;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;


public class BaseTest {
    public AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        driver = DriverUtil.getDiver();
    }

    @Before
    public  void rotate() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

//    @After
//    public void tearDown(){
//        driver.quit();
//    }
}
