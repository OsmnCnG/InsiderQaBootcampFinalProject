package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.NoSuchElementException;

public class JobListPage extends BaseLibrary {

    public JobListPage(WebDriver driver){
        super(driver);
    }

    // =================== Job List Page Elements ===================

    @FindBy(id = "select2-filter-by-location-container")
    WebElement filterByLocationDropdown;

    @FindBy(id = "select2-filter-by-location-results")
    WebElement filterByLocationResults;

    @FindBy(xpath="//li[text()='Istanbul, Turkiye']")
    WebElement istanbulTurkiyeOption;

    @FindBy(id = "career-position-filter")
    WebElement filterSection;

    @FindBy(id = "career-position-list")
    WebElement jobListSection;

    @FindBy(id = "page-head")
    WebElement pageHeadSection;

    @FindBy(className = "select2-results__options")
    WebElement filterByLocationDropdownOptions;

    @FindBy(id = "select2-filter-by-department-container")
    WebElement filterByDepartmentDropdown;

    @FindBy(xpath = "//li[text()='Quality Assurance']")
    WebElement qualityAssuranceOption;

    @FindBy(id = "select2-filter-by-department-results")
    WebElement filterByDepartmentDropdownOptions;

//    @FindBy(className = "position-list-item")
//    List<WebElement> qaJobListingItems;

    @FindBy(css = ".position-list-item.qualityassurance")
    List<WebElement> qaJobListingItems;

    @FindBy(className = "position-title")
    WebElement positionTitle;

    @FindBy(className = "position-department")
    WebElement positionDepartment;

    @FindBy(className = "position-location")
    WebElement positionLocation;

    @FindBy(css = "a[href*='jobs.lever.co']")
    WebElement viewRoleBtns;

    // =================== Job List Page Methods ===================

    public void waitJobListPageLoaded(){
        waitForElementToBeVisible(filterSection);
        waitForElementToBeVisible(jobListSection);
        waitForElementToBeVisible(pageHeadSection);
    }

    @Step("Filter QA Jobs")
    public void filterQaJobs(){
        selectLocationFilter();
        selectDepartmentFilter();
        waitForElementsToBeVisible(qaJobListingItems);
        screenshot();
    }

    @Step("Select location filter")
    public void selectLocationFilter(){
        waitForElementToBeVisible(filterByLocationDropdown);
        waitForElementToBeClickable(filterByLocationDropdown);
        clickElementActionShowStep(filterByLocationDropdown,"filterByLocationDropdown");
        waitForElementToBeVisible(filterByLocationResults);
        waitForElementToBeVisible(filterByLocationDropdownOptions);
        waitForElementToBeClickable(filterByLocationDropdownOptions);
        clickElementActionShowStep(istanbulTurkiyeOption,"istanbulTurkiyeOption");
    }

    @Step("Select department filter")
    public void selectDepartmentFilter(){
        waitForElementToBeClickable(filterByDepartmentDropdown);
        clickElementActionShowStep(filterByDepartmentDropdown,"filterByDepartmentDropdown");
        waitForElementToBeVisible(qualityAssuranceOption);
        waitForElementToBeVisible(filterByDepartmentDropdownOptions);
        waitForElementToBeClickable(qualityAssuranceOption);
        clickElementActionShowStep(qualityAssuranceOption,"qualityAssuranceOption");
    }

    @Step("Verify '{expected}' department selected")
    public void verifyDepartmentSelected(String expected){
        assertEqualsText(filterByDepartmentDropdown.getAttribute("textContent").replace("×", "").trim(),expected,"Department text does not match expected value!");
    }

    @Step("Verify '{expected}' location selected")
    public void verifyLocationSelected(String expected){
        assertEqualsText(filterByLocationDropdown.getAttribute("textContent").replace("×", "").trim(),expected,"Location text does not match expected value!");
    }

    @Step("Check Job Details are correct")
    public void verifyJobDetailPositionDepartmentLocation(String expPositionTitle, String expPositionDepartment, String expPositionLocation){

        waitForElementsToBeVisible(qaJobListingItems);
        scrollToElement(qaJobListingItems.get(0));

        for(WebElement jobItem : qaJobListingItems){

            WebElement titleElement = jobItem.findElement(By.className("position-title"));
            WebElement departmentElement = jobItem.findElement(By.className("position-department"));
            WebElement locationElement = jobItem.findElement(By.className("position-location"));

            String actualTitle = titleElement.getText();
            String actualDepartment  = departmentElement.getText();
            String actualLocation  = locationElement.getText();

            Assert.assertTrue(
                    actualTitle.contains(expPositionTitle),
                    String.format("Position Title mismatch => expected: '%s' but was: '%s'",expPositionTitle,actualTitle));

            Assert.assertTrue(
                    actualDepartment.contains(expPositionDepartment),
                    String.format("Department mismatch => expected: '%s' but was: '%s'",expPositionDepartment,actualDepartment));

            Assert.assertTrue(
                    actualLocation.contains(expPositionLocation),
                    String.format("Location mismatch => expected: '%s' but was: '%s'",expPositionLocation,actualLocation));
        }
    }

    @Step("Go to the Job Application Page")
    public JobApplicationPage goToJobApplicationPage(String position){

        for (WebElement jobItem : qaJobListingItems) {
            WebElement titleElement = jobItem.findElement(By.className("position-title"));
            String actualTitle = titleElement.getText();
            if (actualTitle.equals(position)) {

                scrollToElement(jobItem);
                hoverOverElement(jobItem);// Hover to reveal the 'View Role' button

                WebElement viewRoleButton = jobItem.findElement(By.cssSelector("a[href*='jobs.lever.co']"));
                waitForElementToBeClickable(viewRoleButton);
                clickElementWithJs(viewRoleButton);

                switchToNewTab();
                return new JobApplicationPage(driver);
            }
        }
        throw new NoSuchElementException("No position found: " + position);
    }

}
