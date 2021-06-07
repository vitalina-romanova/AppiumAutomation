package tests;

import helpers.DriverUtil;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;


public class BaseTest {
    public AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        driver = DriverUtil.getDiver();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
