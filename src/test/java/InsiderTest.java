import Pages.CareersPage;
import Pages.JobApplicationPage;
import Pages.JobListPage;
import Pages.QualityAssurancePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class InsiderTest extends BaseTest{

    @Test(description = "Verify homepage navigation, QA job filters, and application page redirection")
    public void qaJobApplyToInsiderTest() throws InterruptedException {

        CareersPage careersPage = homePage.goToCareersPage();
        careersPage.waitForCareersPageLoad();
        careersPage.checkLocationsTeamsLifeAtInsiderSectionsVisible();

        QualityAssurancePage qaTeamPage = careersPage.goToQaTeamPage();

        JobListPage jobListPage = qaTeamPage.goToJobListPage();
        Thread.sleep(5000);
        jobListPage.waitJobListPageLoaded();
        jobListPage.filterQaJobs();
        // The test case document states the location as "Istanbul, Turkey", but the UI displays it as "Istanbul, Turkiye"
        // Adjusting the verification accordingly to use "Istanbul, Turkiye"
        jobListPage.verifyLocationSelected("Istanbul, Turkiye");
        jobListPage.verifyDepartmentSelected("Quality Assurance");

        // The test case document states the location as "Istanbul, Turkey", but the UI displays it as "Istanbul, Turkiye"
        // Adjusting the verification accordingly to use "Istanbul, Turkiye"
        jobListPage.verifyJobDetailPositionDepartmentLocation(
                "Quality Assurance",
                "Quality Assurance",
                "Istanbul, Turkiye");

        JobApplicationPage jobApplicationPage = jobListPage.goToJobApplicationPage("Software Quality Assurance Engineer");
        jobApplicationPage.waitJobApplicationPageLoaded();

        jobApplicationPage.verifyRedirectionToLeverUrl();
        jobApplicationPage.checkJobTitleMatch("Software Quality Assurance Engineer");
    }


}
