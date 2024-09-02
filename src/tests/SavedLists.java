package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SavedObjects;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SavedLists extends CoreTestCase {

    @Test
    public void testAddAndDeleteListOfArticles()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        //название созданного списка
        String name_of_list = "Test_list";
        ArticlePageObject.addArticleToMyList(name_of_list);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMySavedObjects();

        SavedObjects SavedObjects = new SavedObjects(driver);
        SavedObjects.openListByName(name_of_list);
        SavedObjects.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesInList ()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title_to_delete = ArticlePageObject.getArticleTitle();
        String name_of_list = "Test_list";
        ArticlePageObject.addArticleToMyList(name_of_list);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInputWithoutSkip();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement();
        String article_title_check = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToCreatedList(name_of_list);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMySavedObjects();
        SavedObjects SavedObjects = new SavedObjects(driver);
        SavedObjects.openListByName(name_of_list);
        SavedObjects.swipeByArticleToDelete2(article_title_to_delete);
        SavedObjects.waitForArticleToAppearByTitle(article_title_check);
    }
}
