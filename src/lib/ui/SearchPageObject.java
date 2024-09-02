package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SKIP_BUTTON = "//*[contains(@text,'Skip')]",
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INIT_ELEMENT_ID = "org.wikipedia:id/search_src_text",
        SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT_ID = "org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
        SEARCH_CANCEL_CLOSE_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_EMPTY_RESULT_LABEL = "org.wikipedia:id/results_text",
        SEARCH_RESULT_LIST = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
        TITLE_AND_DESCRIPTION_TOGETHER_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/following-sibling::*[name()='android.widget.TextView' and @resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']",
        SEARCH_EMPTY_RESULT = "org.wikipedia:id/search_empty_message";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement (String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getResultTitleAndDescriptionTogether (String title, String description)
    {
        return TITLE_AND_DESCRIPTION_TOGETHER_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    /*TEMPLATES METHODS*/

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON),"Cannot find and click Skip-button",5 );
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void initSearchInputWithoutSkip()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void clickCancelSearchButton()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void clickCancelCloseSearchButton()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_CLOSE_BUTTON), "Cannot find and click search/cancel cancel button", 5);
    }

    public void typeSearchLine (String search_line)
    {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT_ID), search_line, "Cannot find and type into search input", 5);
    }
    public void waitForSearchResult (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.id(SEARCH_EMPTY_RESULT_LABEL), "Cannot find empty result label",15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"We supposed not to find any results");
    }

    public void waitForEmptySearchResult()
    {
        this.waitForElementPresent(By.id(SEARCH_EMPTY_RESULT),"Cannot find empty search message",15);
    }

    public void assertNotAllElementsContainsSearchParam(String search_param)
    {
        this.assertElementsContainText(
            By.xpath(SEARCH_RESULT_LIST),
                search_param,
            "Not all search results contain the word 'Java'",
            15
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String title_and_description_xpath = getResultTitleAndDescriptionTogether(title,description);
        this.waitForElementPresent(
                By.xpath(title_and_description_xpath),
                "Cannot find object with title ='" + title + "' and description ='" + description + "'",
                15);
    }
}
