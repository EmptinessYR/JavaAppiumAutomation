import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;

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
    public void testCompareTextinSearchField ()
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
                "Jva",
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

}
