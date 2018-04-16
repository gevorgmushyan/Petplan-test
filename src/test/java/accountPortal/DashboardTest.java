package accountPortal;

import core.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.accountPortal.DashboardPageObject;
import pageObject.accountPortal.login.LoginPageObject;

import java.util.concurrent.TimeUnit;

import static core.TAGS.LOGIN_PAGE_URL;
import static core.Wait.sleep;

public class DashboardTest {
    private Driver driver = new Driver();
    private WebDriver webDriver;
    private LoginPageObject login;
    private DashboardPageObject dashobard;
    private String validEmailAddress =  "gevorgmio2@yandex.ru";
    private String password = "aa250812aa";

    @BeforeMethod
    private void beforeTest(){
        webDriver = driver.getDriver();
        login = new LoginPageObject(webDriver);
        login.getBaseUrl(LOGIN_PAGE_URL);
        loginToAccount();
        dashobard = new DashboardPageObject(webDriver);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    private void afterTest() {
        sleep(2000);
        webDriver.close();
        webDriver.quit();
    }
    private void loginToAccount(){
        login.waitForPageLoad();
        login.loginToAccount(validEmailAddress, password);
    }
    @Test
    private void uploadAPhoto(){
        dashobard.clickUpdateAPhoto();
        sleep(2000);
        dashobard.uploadAFile("C:\\Temp\\Test.jpg");
        dashobard.moveSlider(0);
    }

}
