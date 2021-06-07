package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverUtil {
    private static AppiumDriver driver;

    public static AppiumDriver getDiver() throws Exception {
        if(driver == null)
            driver = createDriver();
        return driver;
    }

    private static AppiumDriver createDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/vitalina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
