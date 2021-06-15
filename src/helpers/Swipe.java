package helpers;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class Swipe {
    private AppiumDriver driver;
    Waiters waiters = new Waiters();

    public Swipe() {
        try {
            this.driver = DriverUtil.getDiver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void swipeUp(int timeOfSwipe) throws Exception {
        io.appium.java_client.TouchAction action = new io.appium.java_client.TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick() throws Exception {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) throws Exception {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if(already_swiped > max_swipes){
                waiters.waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void  swipeElementToLeft(By by, String error_message) throws Exception {
        WebElement element = waiters.waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        io.appium.java_client.TouchAction action = new io.appium.java_client.TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }
}
