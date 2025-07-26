import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest{

    public WebDriver driver;
    public HomePage homePage;

    @BeforeMethod
    public void openBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.get(homePageUrl);
        homePage = new HomePage(driver);
        homePage.goToHomePage();
        homePage.checkHomePageAccessible();
        homePage.clickAcceptCookie();
    }

    @AfterMethod
    public void afterBrowser(){
        driver.quit();
    }
}
