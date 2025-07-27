package Base;

import Pages.CareersPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class BaseLibrary extends Data{

    protected WebDriver driver;

    public BaseLibrary(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // =================== NAVIGATION BAR ELEMENTS ===================

    @FindBy(linkText = "Company")
    WebElement companyMenu;

    @FindBy(linkText = "Careers")
    WebElement careerMenuItem;

    // =================== COMMON METHODS ===================

    @Step("Go to the Careers page")
    public CareersPage goToCareersPage(){
        waitForElementToBeClickable(companyMenu);
        clickElementWithJs(companyMenu);
        waitForElementToBeClickable(careerMenuItem);
        clickElementWithJs(careerMenuItem);
        return new CareersPage(driver);
    }

    /**
     * Returns the current URL of the web page
     */
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * Asserts that actual and expected values are equal.
     * If they are not, a screenshot will be captured and the test will fail with a message
     */
    public void assertEqualsText(String actual, String expected, String message) {
        if (!actual.equals(expected)) {
            Allure.step("Assertion failed: " + message);
            screenshot();
        }
        Assert.assertEquals(actual, expected, message);
    }

    /**
     * Captures a screenshot of the current browser view and attaches it
     * to the test report using Allure
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Waits until the given WebElement is clickable before proceeding
     */
    public void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the given WebElements is clickable before proceeding
     */
    public void waitForElementsToBeClickable(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        for (WebElement element : elements){
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    /**
     * Waits until the specified WebElement is visible on the page
     */
    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the specified WebElements is visible on the page
     */
    public void waitForElementsToBeVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        for(WebElement element : elements){
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }


    public boolean isElementVisible(WebElement element){
        try {
            return element.isDisplayed();
        }catch (Exception e){
            screenshot();
            return false;
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Hover element
     */
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    @Step("Switch to the new browser tab")
    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    /**
     * Click element with scroll
     */
    public void clickElementWithJs(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickElementAction(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    @Step("Click on: {elementName}")
    public void clickElementActionShowStep(WebElement element,String elementName) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }



}
