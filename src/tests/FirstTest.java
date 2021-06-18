package tests;

import helpers.Asserts;
import helpers.Swipe;
import helpers.Waiters;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstTest extends BaseTest{

    Waiters waiters = new Waiters();
    Swipe swipe = new Swipe();
    Asserts asserts = new Asserts();

    @Test
    public void firstTest() throws Exception {

        waiters.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waiters.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );

        waiters.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search_src_text",
                5);
    }

    @Test
    public void testCancelSearch() throws Exception {

        waiters.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waiters.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waiters.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5);
    }

    @Test
    public void testCompareArticleTitle() throws Exception {
        waiters.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );

        WebElement titleElement = waiters.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        String articleTitle = titleElement.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                articleTitle);
    }

    @Test
    public void testSwipeArticle() throws Exception {
        waiters.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waiters.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        swipe.swipeUpToFindElement(
                By.id("org.wikipedia:id/page_external_link"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList() throws Exception{

        waiters.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        waiters.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find Search More options",
                5
        );

        Thread.sleep(100);
        waiters.waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find Search 'Add to reading list'",
                10
        );

        waiters.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find Search 'Onboarding button'",
                5
        );

        waiters.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Test list",
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath("//*[@text= 'OK']"),
                "Cannot find Search 'Ok button'",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article, cannot find X",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My lists",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath("//*[@text='Test list']"),
                "Cannot find 'Java (programming language)'",
                5
        );

        swipe.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waiters.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() throws Exception {
        waiters.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String searchLine = "linkin park discography";
        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine,
                "Cannot find search input",
                5
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/fragment_feed_feed']//*[@resource-id='org.wikipedia:id/search_container']";
        waiters.waitForElementPresent(By.xpath(searchResultLocator),
                "Cannot find anything by the request " + searchLine);

        int amountOfSearchResults = asserts.getAmountOfElement(
                By.xpath(searchResultLocator)
        );
        Assert.assertTrue(amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() throws Exception {
        waiters.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String searchLine = "zzzz2dd2dddr3r2";
        waiters.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchLine,
                "Cannot find search input",
                5
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/fragment_feed_feed']//*[@resource-id='org.wikipedia:id/search_container']";
        String emptyResultLabel = "//*[@text='No results found']";

        waiters.waitForElementPresent(
                By.xpath(emptyResultLabel),
                "Cannot find empty result label by the request " + searchLine);

        asserts.assertElementNotPresent(
                By.xpath(searchResultLocator),
                "error message"
        );
    }
}
