package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QualityAssurancePage extends BaseLibrary {

    public QualityAssurancePage(WebDriver driver){
        super(driver);
    }

    // =================== QA Page Elements ===================

    @FindBy(css = "a[href*='qualityassurance']")
    WebElement seeAllQaJobsBtn;

    // =================== QA Page Methods ===================

    @Step("Go to the job list Page")
    public JobListPage goToJobListPage(){
        waitForElementToBeVisible(seeAllQaJobsBtn);
        waitForElementToBeClickable(seeAllQaJobsBtn);
        clickElementWithJs(seeAllQaJobsBtn);
        return new JobListPage(driver);
    }

}
