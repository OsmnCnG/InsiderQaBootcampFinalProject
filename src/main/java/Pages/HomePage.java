package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseLibrary {

    public HomePage(WebDriver driver){
        super(driver);
    }

    // =================== Home Page Elements ===================

    @FindBy(id = "wt-cli-accept-all-btn")
    WebElement clickAcceptCookies;

    // =================== Home Page Methods ===================

    @Step("Go to the Home page")
    public void goToHomePage(){
        driver.get(homePageUrl);
    }

    @Step("Click Accept-Cookies-Button")
    public void clickAcceptCookie() {
        clickAcceptCookies.click();
    }

    @Step("Check Home Page loaded successfully")
    public void checkHomePageAccessible(){
        assertEqualsText(driver.getTitle(),homePageTitle,"Home Page düzgün yüklenmiyor!");
    }


}
