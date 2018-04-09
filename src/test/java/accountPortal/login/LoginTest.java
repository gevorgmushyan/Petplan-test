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
        System.out.printf("4444");
    }
    @Test() //PP-3_1
    private  void loginWithEmptyFields(){
        login.isInitialized();
        login.login("","","check");
        login.isMessageDisplayed("required",2);
    }
    @Test()  //PP-3_2
    private  void loginWithInvalidCredentials(){
        login.isInitialized();
        login.login("asdfas","asdfsd","check");
        login.isMessageDisplayed("invalidEmail",1);
    }
    @Test()  //PP-3_3
    private  void loginWithWrongCredentials(){
        login.isInitialized();
        login.login("asdfghj@sda","123456","check");
        login.isMessageDisplayed("invalidCredentials",1);
    }
    @Test()  //PP-3_4
    private  void loginWithWrongPassword(){
        login.isInitialized();
        login.login("validmail@mailinator.com","123456","check");
        login.isMessageDisplayed("invalidCredentials",1);
    }
    @Test()  //PP-3_5
    private  void loginWithValidEmailWrongPassword(){
        login.isInitialized();
        login.login("validmail@mailinator.com","123455555555555555555555555555556","check");
        login.isMessageDisplayed("invalidCredentials",1);
    }
    @Test()  //PP-3_6
    private  void loginWithValidCredentials(){
        login.isInitialized();
        login.login("validmail@mailinator.com","password123","check");
        login.isLoggedIn();
    }
}
