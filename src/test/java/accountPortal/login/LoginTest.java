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

    @BeforeMethod
    private  void beforeTest(){
        webDriver = driver.getDriver();
        login=new LoginPageObject(webDriver);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterMethod
    private   void afterTest(){
        webDriver.close();
        webDriver.quit();
    }
    @Test
    private void test1(){
        System.out.printf("Hello!!!");
    }

}
