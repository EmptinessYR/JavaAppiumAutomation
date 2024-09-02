package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppCondition extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationSearchResults ()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        //Ищем подзаголовок статьи
        String undertitel_before_rotation = ArticlePageObject.getArticleTitle();
        // Вывод значения переменной в консоль
        System.out.println("undertitle before rotation: " + undertitel_before_rotation);

        this.rotateScreenLandscape();

        //Ищем подзаголовок статьи
        String undertitel_after_rotation = ArticlePageObject.getArticleTitle();
        // Вывод значения переменной в консоль
        System.out.println("undertitle after rotation: " + undertitel_after_rotation);

        assertEquals(
                "Article undertitle have been changed after screen rotation",
                undertitel_before_rotation,
                undertitel_after_rotation
        );

        this.rotateScreenPortrait();

        //Ищем подзаголовок статьи
        String undertitel_after_second_rotation = ArticlePageObject.getArticleTitle();

        // Вывод значения переменной в консоль
        System.out.println("undertitle after 2 rotation: " + undertitel_after_second_rotation);

        assertEquals(
                "Article undertitle have been changed after screen rotation",
                undertitel_before_rotation,
                undertitel_after_second_rotation
        );

    }

    @Test
    public void  testCheckSearchArticleInBackGround()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(2);

        driver.runAppInBackground(2);

        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

/*  Тест для истории, todo сдеалать отдельный набор тестов с главной страницей
    @Test
    public void testOnbordingSwipe()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        MainPageObject.swipeUpToFindElement(
                By.id("org.wikipedia:id/footerActionButton"),
                "Cannot find button-link 'More top read'",
                20
        );
    }*/
}
