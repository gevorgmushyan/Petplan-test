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
        login.getBaseUrl(url);
        login.ClickOnLogo();
        assert (webDriver.getCurrentUrl().equals("https://www.gopetplan.com/"));
    }

}
