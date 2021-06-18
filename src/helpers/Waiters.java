package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    WebDriver driver;

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) throws Exception {
        driver = DriverUtil.getDiver();
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    public WebElement waitForElementPresent(By by, String error_message) throws Exception {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) throws Exception {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String  value, String error_message, long timeoutInSeconds) throws Exception {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds ){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds ) throws Exception {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public String waitForElementAndAttribute(By by, String attribute, String error_message, long timeoutInSeconds) throws Exception {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
