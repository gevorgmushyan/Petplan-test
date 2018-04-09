package accountPortal.login;

import core.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.accountPortal.login.LoginPageObject;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private Driver driver = new Driver();
    private WebDriver webDriver;
    private LoginPageObject login;
    private String url = "https://account.gopetplan.com/";

    @BeforeMethod
    private  void beforeTest(){
        webDriver = driver.getDriver();
        login=new LoginPageObject(webDriver);
        login.getBaseUrl(url);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    private   void afterTest(){
        webDriver.close();
        webDriver.quit();
    }
    @Test   //PP-818
    private void clickOnLogo(){
        login.ClickOnLogo();
        assert (webDriver.getCurrentUrl().equals("https://www.gopetplan.com/"));
    }
    @Test   //PP-113
    private void loginWithWrongCredentials(){
        login.setEmailAddress("example@example.com");
        assert (login.getEmailAddressLabel().equals("email address"));
        login.setPassword("wrong password");
        assert (login.getPasswordLabel().equals("password"));
        login.clickOnSingIn();
        assert (login.getValidationMessage().equals("Sorry, the email and password you entered does not match our records. Please try again."));
    }

}
