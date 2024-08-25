import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Обучение\\JavaAppiumAutomation\\apks\\org.wikipedia_50492_apps.evozi.com.apk");

        driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDowm()
    {
        driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input2",
                5
        );

//        Старое начало теста для истории
//        WebElement element = driver.findElementByXPath("//*[contains(@text,'Skip')]");
//        element.click();

//        WebElement element_to_init_search = waitForElementPresentByXpath(
//                "//*[contains(@text,'Search Wikipedia')]",
//                "Cannot find search input"
//        );
//                driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");

//        element_to_init_search.click();

//        WebElement element_to_enter_search_line = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
//        WebElement element_to_enter_search_line = waitForElementPresentByXpath(
//                "//*[contains(@text,'Search Wikipedia')]",
//                "Cannot find search input2"
//        );

//                driver.findElementById("org.wikipedia:id/search_src_text");

//        element_to_enter_search_line.sendKeys("Java");
//      Поиск через @class родительского слоя и необходимому тексту в нужном элементе
//        System.out.println("First test run");
//        waitForElementPresentByXpath(
//                "//*[@class='android.view.ViewGroup']//*[@text='Object-oriented programming language']",
//                "Cannot find 'Object-oriented programming language' topick searching by 'Java'",
//                15
//        );

//      Поиск через @resource-id и  нужному тексту
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topick searching by 'Java'",
                15
        );
    }

    @Test
    public void testCanselSearch ()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick (
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input2",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

//        Клик по назад
        waitForElementAndClick (
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Navigate up",
                5
        );

//        Проверка отсутствия элемента по Xpath
        waitForElementNotPresent(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Navigate up is still on the page",
                5
        );

//      Проверка отсутствия элемента по ID - не сработало (подумать почему)
//        waitForElementNotPresentById(
//                "org.wikipedia:id/search_empty_message",
//                "We still in search",
//                5
//        );

    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input2",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find element 'Java (programming language)'",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                20
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Object-oriented programming language",
                article_title
        );

    }

    @Test
    public void testCompareTextInSearchField ()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia1",
                "Cannot find expected text Search Wikipedia",
                5
        );

    }

    @Test
    public void testSearchAndCancle ()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input2",
                5
        );

        //      Поиск через @resource-id и  нужному тексту
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topick searching by 'Java'",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find cancel search button",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Cannot find empty search message",
                5
        );

    }

    @Test
    public void testSearchAndCompareResult ()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input2",
                5
        );

        assertElementsContainText(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Java",
                "Not all search results contain the word 'Java'",
                15
        );

    }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input2",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']"),
                "Cannot find element 'Automation for Apps'",
                5
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of article",
                20
                );

    }

    @Test
    public void testOnbordingSwipe()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        swipeUpToFindElement(
                By.id("org.wikipedia:id/footerActionButton"),
                "Cannot find button-link 'More top read'",
                20
        );
    }

    @Test
    public void addAndDeleteListOfArticles ()
    {
        //кликаем на скип
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        //кликаем в поисковую строку
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        //Пишем в поисковой строке Java
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input2",
                5
        );

        //Заходим в нужную статью
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find element 'Automation for Apps'",
                5
        );

        //Кликаем на Save
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find element 'Save'",
                5
        );

        //Кликаем на Add to List
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find element 'Add to List'",
                5
        );
        //название созданного списка
        String name_of_list = "Test_list";

        //Задаем данные в Name of this list
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_list,
                "Cannot find Name of this list",
                5
        );

        //кликаем на Ok
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find button Ok",
                5
        );

        //Выходим на страницу поиска
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find button Navigate up",
                5
        );

        //Выходим на главную страницу
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find button Navigate up in Search",
                5
        );

        //Выходим в сохраненные объекты
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"Saved\"]/android.widget.FrameLayout/android.widget.ImageView"),
                "Cannot find Saved objects",
                5
        );

        //Прокрутим до нужного списка
        swipeUpToFindElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title' and @text='" + name_of_list + "']"),
                "Cannot find Test_list",
                20
        );

        //Заходим в нужный список статей
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title' and @text='" + name_of_list + "']"),
                "Cannot find Test_list",
                5
        );

        //Убеждаемся что есть нужная статья
        assertElementHasText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Java (programming language)",
                "Cannot find right article",
                15
        );

        //Свайпаем элемент налево для удаления
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        //кликаем на скип
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        //кликаем в поисковую строку
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        String search_line = "Linkin Park discography";
        //Пишем в поисковой строке 'Linkin Park discography'
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input2",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch()
    {
        //кликаем на скип
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        //кликаем в поисковую строку
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        String search_line = "sdafdsgndhgjr";
        //Пишем в поисковой строке 'Linkin Park discography'
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input2",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationSearchResults ()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
        //кликаем на скип
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        //кликаем в поисковую строку
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        String search_line = "Java";
        //Пишем в поисковой строке 'Java'
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_line,
                "Cannot find search input2",
                5
        );

        //Заходим в нужную статью
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find element 'Automation for Apps' topic searching by " + search_line,
                15
        );

        //Ищем заголовок статьи
//        String titel_before_rotation = waitForElementAndGetAttribute(
//                By.xpath("(//android.widget.TextView[@package='org.wikipedia' and @class='android.widget.TextView'])[1]"),
//                "text",
//                "Cannot find title of article",
//                15
//        );
//
//        // Вывод значения переменной в консоль
//        System.out.println("Title before rotation: " + titel_before_rotation);

        //Ищем подзаголовок статьи
        String undertitel_before_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find undertitle of article",
                15
        );

        // Вывод значения переменной в консоль
        System.out.println("undertitle before rotation: " + undertitel_before_rotation);

        driver.rotate(ScreenOrientation.LANDSCAPE);

//        //Ищем заголовок статьи
//        String titel_after_rotation = waitForElementAndGetAttribute(
//                By.xpath("(//android.widget.TextView[@package='org.wikipedia' and @class='android.widget.TextView'])[1]"),
//                "text",
//                "Cannot find title of article",
//                15
//        );

        //Ищем подзаголовок статьи
        String undertitel_after_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find undertitle of article",
                15
        );

        // Вывод значения переменной в консоль
        System.out.println("undertitle after rotation: " + undertitel_after_rotation);

//        Assert.assertEquals(
//                "Article title have been changed after screen rotation",
//                titel_before_rotation,
//                titel_after_rotation
//        );

        Assert.assertEquals(
                "Article undertitle have been changed after screen rotation",
                undertitel_before_rotation,
                undertitel_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

//        //Ищем заголовок статьи
//        String titel_after_second_rotation = waitForElementAndGetAttribute(
//                By.xpath("(//android.widget.TextView[@package='org.wikipedia' and @class='android.widget.TextView'])[1]"),
//                "text",
//                "Cannot find title of article",
//                15
//        );

        //Ищем подзаголовок статьи
        String undertitel_after_second_rotation = waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "text",
                "Cannot find undertitle of article",
                15
        );

        // Вывод значения переменной в консоль
        System.out.println("undertitle after 2 rotation: " + undertitel_after_second_rotation);

//        Assert.assertEquals(
//                "Article title have been changed after screen rotation",
//                titel_before_rotation,
//                titel_after_second_rotation
//        );

        Assert.assertEquals(
                "Article undertitle have been changed after screen rotation",
                undertitel_before_rotation,
                undertitel_after_second_rotation
        );

    }

    @Test
    public void  testCheckSearchArticleInBackGround()
    {
        //кликаем на скип
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        //кликаем в поисковую строку
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        String search_line = "Java";
        //Пишем в поисковой строке 'Java'
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input2",
                5
        );

        //Заходим в нужную статью
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find element 'Automation for Apps' topic searching by " + search_line,
                15
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find article 'Automation for Apps' topic searching by " + search_line + " after running from background",
                15
        );

    }



//    private WebElement waitForElementPresent(String xpath, String error_message, long timeoutInSeconds)
//    {
//        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//        wait.withMessage(error_message + "\n");
//        By by = By.xpath(xpath);
//        return wait.until(
//                ExpectedConditions.presenceOfElementLocated(by)
//        );
//    }

    //Метод поиска по любому атрибуту
    private WebElement waitForElementPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by,error_message,5);
    }

    private WebElement waitForElementByXpathAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by,error_message,timeoutInSeconds);
        element.click();
        return element;
    }
    //Метод поиска и клика по любому атрибуту
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by,error_message,timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by,error_message,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresentById (String id, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentById(id,error_message,timeoutInSeconds);
        element.click();
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresentById(String id, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message,timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement assertElementHasText(By by, String expected_text, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message,timeoutInSeconds);
        String actual_text = element.getText();
        Assert.assertEquals(error_message, expected_text, actual_text);
        return element;
    }

    private void assertElementsContainText(By by, String expected_text, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );

        for (WebElement element : elements) {
            String actual_text = element.getText();
            Assert.assertTrue(error_message + ": " + actual_text, actual_text.contains(expected_text));
        }
    }

    protected void swipeUp (int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement (By by,String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes){
               waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
               return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft (By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    private int getAmountOfElements (By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent (By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

}
