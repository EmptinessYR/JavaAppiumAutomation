package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "pcs-edit-section-title-description",
        FOOTER_ELEMENT = "//*[@text='View article in browser']",
        SAVE_BUTTON = "org.wikipedia:id/page_save",
        ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
        MY_LIST_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "android:id/button1",
        CREATED_LIST_ID = "org.wikipedia:id/item_title",
        TITLE_DESCRIPTION_XPATH = "@resource-id='pcs-edit-section-title-description'",
        NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList (String name_of_list)
    {
        //Кликаем на Save
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find element 'Save'",
                5
        );

        //Кликаем на Add to List
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),
                "Cannot find element 'Add to List'",
                5
        );

        //Задаем данные в Name of this list
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_INPUT),
                name_of_list,
                "Cannot find Name of this list",
                5
        );

        //кликаем на Ok
        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Cannot find button Ok",
                5
        );
    }

    public void addArticleToCreatedList (String name_of_list)
    {
        //Кликаем на Save
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find element 'Save'",
                5
        );

        //Кликаем на Add to List
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),
                "Cannot find element 'Add to List'",
                5
        );

        //Выбираем нужный нам список
        this.waitForElementAndClick(
                By.id(CREATED_LIST_ID),
                "Cannot find list " + name_of_list,
                5
        );
    }

    public void closeArticle()
    {
        //Выходим на страницу поиска
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot find button Navigate up",
                5
        );

        //Выходим на главную страницу
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot find button Navigate up in Search",
                5
        );
    }
    public void assertElementPresentArticleTitleDescription()
    {
        // Проверяем наличие элемента pcs-edit-section-title-description, так как у Заголовка нет ID
        this.assertElementPresent(
                By.xpath(TITLE_DESCRIPTION_XPATH),
                "Title description element is not present"
        );
    }
}
