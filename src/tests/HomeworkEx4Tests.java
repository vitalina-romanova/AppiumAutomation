package tests;

import helpers.Asserts;
import helpers.Swipe;
import helpers.Waiters;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class HomeworkEx4Tests extends BaseTest {

    private final String ARTICLE_ONE = "Java";
    private final String ARTICLE_TWO = "Appium";
    private final String SEARCH_WIKIPEDIA = "//*[contains(@text, 'Search Wikipedia')]";
    private final String SEARCH = "//*[contains(@text, 'Search…')]";
    private final String ITEM_OBJECT_ORIENTED = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']";
    private final String NAME_LIST = "Test list";
    private final String TITLE_TEXT = "org.wikipedia:id/view_page_title_text";
    private final String MORE_OPTIONS = "//android.widget.ImageView[@content-desc = 'More options']";
    private final String READING_LIST = "//*[@text = 'Add to reading list']";
    private final String ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button";
    private final String TEXT_INPUT = "org.wikipedia:id/text_input";
    private final String OK = "//*[@text= 'OK']";
    private final String NAVIGATE_UP = "//android.widget.ImageButton[@content-desc = 'Navigate up']";
    private final String ITEM_APPIUM = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Appium']";
    private final String ITEM_CONTAINER = "org.wikipedia:id/item_container";
    private final String ADD_READING_LIST = "//*[@content-desc='Add this article to a reading list']";
    private final String MY_LIST = "//android.widget.FrameLayout[@content-desc = 'My lists']";
    private final String IMAGE_CONTAINER = "org.wikipedia:id/item_image_container";
    private final String APPIUM = "//*[@text='Appium']";
    private final String JAVA_ARTICLE = "//*[@text='Java (programming language)']";


    Waiters waiters = new Waiters();
    Swipe swipe = new Swipe();
    Asserts asserts = new Asserts();

    @Test
    public void saveArticleTest() throws Exception {

        waiters.waitForElementAndClick(
                By.xpath(SEARCH_WIKIPEDIA),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.xpath(SEARCH),
                ARTICLE_ONE,
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath(ITEM_OBJECT_ORIENTED),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementPresent(
                By.id(TITLE_TEXT),
                "Cannot find article title",
                15
        );

        waiters.waitForElementAndClick(
                By.xpath(MORE_OPTIONS),
                "Cannot find Search More options",
                5
        );

        Thread.sleep(200);
        waiters.waitForElementAndClick(
                By.xpath(READING_LIST),
                "Cannot find Search 'Add to reading list'",
                10
        );

        waiters.waitForElementAndClick(
                By.id(ONBOARDING_BUTTON),
                "Cannot find Search 'Onboarding button'",
                5
        );

        waiters.waitForElementAndClear(
                By.id(TEXT_INPUT),
                "Cannot find input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.id(TEXT_INPUT),
                NAME_LIST,
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath(OK),
                "Cannot find Search 'Ok button'",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath(NAVIGATE_UP),
                "Cannot close article, cannot find X",
                5
        );

        //Добавление второй статьи

        waiters.waitForElementAndClick(
                By.xpath(SEARCH_WIKIPEDIA),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.xpath(SEARCH),
                ARTICLE_TWO,
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath(ITEM_APPIUM),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementPresent(
                By.id(TITLE_TEXT),
                "Cannot find article title",
                15
        );

        Thread.sleep(100);
        waiters.waitForElementAndClick(
                By.xpath(ADD_READING_LIST),
                "Cannot find Search 'Add to reading list'",
                10
        );

        waiters.waitForElementAndClick(
                By.id(ITEM_CONTAINER),
                "Cannon search folder 'Test list'",
                5
        );

        //Переход в папку со статьями
        waiters.waitForElementAndClick(
                By.xpath(NAVIGATE_UP),
                "Cannot close article, cannot find X",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath(MY_LIST),
                "Cannot find navigation button to My lists",
                5
        );

        waiters.waitForElementAndClick(
                By.id(IMAGE_CONTAINER),
                "Cannot find 'Test list'",
                5
        );

        swipe.swipeElementToLeft(
                By.xpath(JAVA_ARTICLE),
                "Cannot find saved article"
        );

        waiters.waitForElementNotPresent(
                By.xpath(JAVA_ARTICLE),
                "Cannot delete saved article",
                5
        );
        String articleOnList = waiters.waitForElementAndAttribute(
                By.xpath(APPIUM),
                "text",
                "error_message",
                15
        );
        waiters.waitForElementAndClick(
                By.xpath(APPIUM),
                "Cannot find article 'Appium'",
                5
        );

        String articleTitle = waiters.waitForElementAndAttribute(
                By.id(TITLE_TEXT),
                "text",
                "Cannot find article title",
                15
        );

        Assert.assertEquals(articleOnList, articleTitle);
    }

    @Test
    public void assertTitleTest() throws Exception {
        waiters.waitForElementAndClick(
                By.xpath(SEARCH_WIKIPEDIA),
                "Cannot find Search Wikipedia input",
                5
        );

        waiters.waitForElementAndSendKeys(
                By.xpath(SEARCH),
                ARTICLE_ONE,
                "Cannot find search input",
                5
        );

        waiters.waitForElementAndClick(
                By.xpath(ITEM_OBJECT_ORIENTED),
                "Cannot find Search Wikipedia input",
                5
        );

        asserts.assertElementPresent(By.id(TITLE_TEXT),
        "Cannot find title"
        );

        waiters.waitForElementAndClick(
                By.xpath(NAVIGATE_UP),
                "Cannot close article, cannot find X",
                5
        );
    }

    @Test
    public void screenRotationTest() throws Exception {
        waiters.waitForElementAndClick(
                By.xpath(SEARCH_WIKIPEDIA),
                "Cannot find Search Wikipedia input",
                5
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
}


