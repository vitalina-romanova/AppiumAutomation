package tests;

import helpers.Asserts;
import helpers.Waiters;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeworkEx3Tests extends BaseTest{
    Waiters waiters = new Waiters();
    Asserts asserts = new Asserts();

    @Test
    public void equalsTextTest(){

        WebElement elementSearch = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        Asserts.assertElementHasText(
                elementSearch,
                "Search Wikipedia",
                "Cannot fine 'Search Wikipedia'"
        );
    }

    @Test
    public void cancelSearchTest() throws Exception {
        waiters.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                2
        );

        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Cannot find search input",
                10
        );

        waiters.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find Result list"
        );
        List<WebElement> itemTitle = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
        Assert.assertTrue(itemTitle.size() > 0);

        waiters.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        waiters.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Result list is still present on the page",
                5);
    }

    @Test
    public void findAndCheckTextTest() throws Exception {
        waiters.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                2 );

        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                10 );

        waiters.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find Result list"
        );
        List<WebElement> itemTitle = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));
        for (WebElement element : itemTitle){
            String titleText = element.getAttribute("text");
            Assert.assertTrue(titleText.contains("Java"));
        }
    }
}
