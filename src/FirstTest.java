import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

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
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Skip')]",
                "Cannot find button Skip",
                5
        );

        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find search input",
                5
        );

        waitForElementByXpathAndSendKeys(
                "//*[contains(@text,'Search Wikipedia')]",
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
        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topick searching by 'Java'",
                15
        );
    }

    @Test
    public void testCanselSearch ()
    {
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Skip')]",
                "Cannot find button Skip",
                5
        );

        waitForElementByIdAndClick (
                "org.wikipedia:id/search_container",
                "Cannot find search input",
                5
        );

//        Клик по назад
        waitForElementByXpathAndClick (
                "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
                "Cannot find Navigate up",
                5
        );

//        Проверка отсутствия элемента по Xpath
//        waitForElementNotPresentByXpath(
//                "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
//                "Navigate up is still on the page",
//                5
//        );

//      Проверка отсутствия элемента по ID
        waitForElementNotPresentById(
                "org.wikipedia:id/search_empty_message",
                "We still in search",
                5
        );

    }

    private WebElement waitForElementPresentByXpath (String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath (String xpath, String error_message)
    {
        return waitForElementPresentByXpath(xpath,error_message,5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentByXpath(xpath,error_message,timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentByXpath(xpath,error_message,timeoutInSeconds);
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

    private boolean waitForElementNotPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
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


}
