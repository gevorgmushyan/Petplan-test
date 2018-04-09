package pageObject.accountPortal.login;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class LoginPageObject extends PageObject {

    public LoginPageObject(WebDriver driver){
        super(driver);
    }
}

