package pageObject.accountPortal.login;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class LoginPageObject extends PageObject {
    @FindBy(partialLinkText ="Petplan")
    private WebElement logo;
    @FindBy(name = "email")
    private WebElement emailAddress;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(id = "remember")
    private WebElement rememberMe;
    @FindBy(id = "imbLogin")
    private WebElement signIn;
    @FindBy(linkText = "forgot password?")
    private WebElement forgotPassword;
    @FindBy(partialLinkText = "start a quote")
    private WebElement startAQuote;

    public LoginPageObject(WebDriver driver){
        super(driver);
    }
    public void ClickOnLogin(){
        logo.click();
    }
}

