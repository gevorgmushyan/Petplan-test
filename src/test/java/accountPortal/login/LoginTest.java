package accountPortal.login;

import core.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.accountPortal.login.LoginPageObject;

import java.util.concurrent.TimeUnit;
import static core.TAGS.*;
import static core.Wait.sleep;

public class LoginTest {
    private Driver driver = new Driver();
    private WebDriver webDriver;
    private LoginPageObject login;
    private String validEmailAddress =  "example@example.com";
    private String wrongPassword = "wrong password";

    @BeforeMethod
    private void beforeTest(){
        webDriver = driver.getDriver();
        login = new LoginPageObject(webDriver);
        login.getBaseUrl(LOGIN_PAGE_URL);
        login.waitForPageLoad();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    private void afterTest() {
        sleep(2000);
        webDriver.close();
        webDriver.quit();
    }
    @Test   //PP-818
    private void clickOnLogo(){
        login.clickOnLogo();
        assert (webDriver.getCurrentUrl().equals(LEGACY_CMS_Url));
    }
    @Test   //PP-113
    private void loginWithWrongCredentials(){
        login.setEmailAddress(validEmailAddress);
        assert (login.getEmailAddressLabel().equals(EMAIL_ADDRESS));
        login.setPassword(wrongPassword);
        assert (login.getPasswordLabel().equals(PASSWORD));
        login.clickOnSingIn();
        assert (login.getValidationMessage().equals(VALIDATION_MESSAGE_FOR_WRONG_CREDENTIALS));
    }
}
