package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CareersPage extends BaseLibrary {

    public CareersPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    // =================== Careers Page Elements ===================

    @FindBy(id="career-our-location")
    WebElement locationsSection;

    @FindBy(css = "section[data-id='a8e7b90']")
    WebElement lifeAtInsiderSection;

    @FindBy(id="career-find-our-calling")
    WebElement teamsSection;

    @FindBy(className = "loadmore")
    WebElement seeAllTeamsBtn;

    @FindBy(linkText = "Quality Assurance")
    WebElement qualityAssuranceLink;


    // =================== Careers Page Methods ===================

    public void waitForCareersPageLoad(){
        waitForElementToBeVisible(locationsSection);
        waitForElementToBeVisible(lifeAtInsiderSection);
        waitForElementToBeVisible(teamsSection);
    }

    /**
     * Verify Locations, Teams, Life at Insider sections on Careers page are visible
     */
    public void checkLocationsTeamsLifeAtInsiderSectionsVisible(){
        verifyTeamsSectionVisible();
        verifyLifeAtInsiderSectionVisible();
        verifyLocationSectionVisible();
    }

    void verifyLocationSectionVisible(){
        Assert.assertTrue(isElementVisible(locationsSection),"Locations section is not visible");
    }

    void verifyLifeAtInsiderSectionVisible(){
        Assert.assertTrue(isElementVisible(lifeAtInsiderSection),"Life at Insider section is not visible");
    }

    void verifyTeamsSectionVisible(){
        Assert.assertTrue(isElementVisible(teamsSection),"Teams section is not visible");
    }

    @Step("Go to the Quality Assurance Page")
    public QualityAssurancePage goToQaTeamPage(){
        clickElementWithJs(seeAllTeamsBtn);
        waitForElementToBeClickable(qualityAssuranceLink);
        clickElementWithJs(qualityAssuranceLink);
        screenshot();
        return new QualityAssurancePage(driver);
    }
}
