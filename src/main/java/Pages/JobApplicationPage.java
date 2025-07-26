package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class JobApplicationPage extends BaseLibrary {

    public JobApplicationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // =================== Job Application Page Elements ===================

    @FindBy(css = "a[href*='/apply']")
    List<WebElement> applyForThisJobBtns;

    @FindBy(className = "posting-headline")
    WebElement jobHeadline;

    // =================== Job Application Page Methods ===================

    @Step("Verify redirection to Lever application page")
    public void verifyRedirectionToLeverUrl(){
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                actualUrl.startsWith(leverPageUrl),
                "Redirect URL mismatch. Expected: " + leverPageUrl + " but was: " + actualUrl);
    }

    @Step("Check job title matches")
    public void checkJobTitleMatch(String expectedTitle){
        String actualJobTitle = jobHeadline.findElement(By.tagName("h2")).getText().trim();
        assertEqualsText(actualJobTitle, expectedTitle,
                "Job Title does not match! Expected: '" + expectedTitle + "', but found: '" + actualJobTitle + "'");
    }

    public void waitJobApplicationPageLoaded(){
        waitForElementToBeVisible(applyForThisJobBtns);
        waitForElementToBeVisible(jobHeadline);
        screenshot();
    }

    public void applyForThisJob(){
        waitForElementToBeVisible(applyForThisJobBtns);
        waitForElementsToBeClickable(applyForThisJobBtns);
        applyForThisJobBtns.get(0).click();
    }

}
