package helpers;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class Asserts {

    public static void assertElementHasText(WebElement element, String expectedValue, String error_message) {
        Assert.assertEquals(error_message, element.getAttribute("text"), expectedValue);
    }
}
