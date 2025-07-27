import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest{

    public WebDriver driver;
    public HomePage homePage;

    @BeforeMethod
    public void openBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();
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
