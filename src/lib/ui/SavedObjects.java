package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedObjects extends MainPageObject{

    public static final String
        LIST_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/item_title' and @text='{LIST_NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
        ARTICLE_TITLE_DESCRIPTION_ID = "org.wikipedia:id/page_list_item_description";


    private static String getListXpathByName (String name_of_list)
    {
        return LIST_BY_NAME_TPL.replace("{LIST_NAME}", name_of_list);
    }

    private static String getSavedArticleXpathByTitle (String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public SavedObjects (AppiumDriver driver)
    {
        super(driver);
    }

    public void openListByName(String name_of_list)
    {
        String folder_name_xpath = getListXpathByName(name_of_list);
        //Прокрутим до нужного списка
        this.swipeUpToFindElement(
                By.xpath(folder_name_xpath),
                "Cannot find list by name" + name_of_list,
                20
        );

        //Заходим в нужный список статей
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find list by name" + name_of_list,
                5
        );
    }

    public void waitForArticleToAppearByTitle (String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_title_xpath), "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle (String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_title_xpath), "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete (String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        System.out.println(article_title_xpath);
        //Убеждаемся что есть нужная статья
        this.assertElementHasText(
                By.id("org.wikipedia:id/page_list_item_description"),
                article_title,
                "Cannot find right article",
                15
        );

        //Свайпаем элемент налево для удаления
        this.swipeElementToLeft(
                By.xpath(article_title_xpath),
                "Cannot find saved article"
        );

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void swipeByArticleToDelete2 (String article_title) {
        String articleElement = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(articleElement),
                "Cannot find saved article");
    }

}
