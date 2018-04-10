package accountPortal.login;

import core.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.accountPortal.login.LoginPageObject;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private Driver driver = new Driver();
    private WebDriver webDriver;
    private LoginPageObject login;
    private String url = "https://account.gopetplan.com/";

    private String validEmailAddress =  "example@example.com";
    private String wrongPassword = "wrong password";

    private String emailAddressLabel = "email address";
    private String passwordLabel = "password";
    private String validationMessage = "Sorry, the email and password you entered does not match our records. Please try again.";
    private String legacyUrl = "https://www.gopetplan.com/";

    @BeforeMethod
    private  void beforeTest(){
        webDriver = driver.getDriver();
        login=new LoginPageObject(webDriver);
        login.getBaseUrl(url);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    private   void afterTest() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.close();
        webDriver.quit();
    }
    @Test   //PP-818
    private void clickOnLogo(){
        login.ClickOnLogo();
        assert (webDriver.getCurrentUrl().equals(legacyUrl));
    }
    @Test   //PP-113
    private void loginWithWrongCredentials(){
        login.setEmailAddress(validEmailAddress);
        assert (login.getEmailAddressLabel().equals(emailAddressLabel));
        login.setPassword(wrongPassword);
        assert (login.getPasswordLabel().equals(passwordLabel));
        login.clickOnSingIn();
        assert (login.getValidationMessage().equals(validationMessage));
    }

}
