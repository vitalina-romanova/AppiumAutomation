package helpers;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Asserts {

    AppiumDriver driver;

    public Asserts() {
        try {
            this.driver = DriverUtil.getDiver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void assertElementHasText(WebElement element, String expectedValue, String error_message) {
        Assert.assertEquals(error_message, element.getAttribute("text"), expectedValue);
    }

    public int getAmountOfElement (By by) throws Exception {
        List elements = driver.findElements(by);
        return elements.size();
    }
    public void assertElementNotPresent(By by,String error_message) throws Exception {
        int amountOfElements = getAmountOfElement(by);
        if (amountOfElements > 0){
            String defaultMessage = "An element " + by.toString() + " supposed to be present ";
            throw new AssertionError(defaultMessage + "" + error_message);
        }
    }
}
