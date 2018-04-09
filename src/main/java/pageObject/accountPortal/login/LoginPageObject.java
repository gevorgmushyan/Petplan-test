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
    @FindBy(xpath = "//h3[contains(.,'log in to your account')]")
    private WebElement logInToAccount;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement Email;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;
    @FindBy(xpath = "//input[@id='remember']")
    private WebElement rememberMe;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signIn;
    @FindBy(xpath = "//a[contains(.,'forgot password?')]")
    private WebElement forgotPassword;
    @FindBy(xpath = "//a[contains(.,'start a quote')]")
    private WebElement startAQuote;
    @FindAll({@FindBy(xpath = "//p[@class='validation-message ng-scope']")})
    private List<WebElement> message;
    @FindBy(xpath = "//p[contains(.,'Please enter your email address.')]")
    private WebElement requiredEmail;
    @FindBy(xpath = "//p[contains(.,'Please enter your password.')]")
    private WebElement requiredPassword;
    @FindBy(xpath = "//p[contains(.,'Please enter a valid email address.')]")
    private WebElement invalidEmiail;
    @FindBy(xpath = "//p[@ng-message='invalidCredentials']")
    private WebElement invalidCredentials;
    @FindBy(xpath = "//p[contains(.,'Logged in as Test')]")
    private WebElement loggedIn;

    public void isInitialized() {
        logInToAccount.isDisplayed();
    }

    public void login(String email,String password_,String remember){

        Email.sendKeys(email);
        password.sendKeys(password_);
        if(remember.equals("check"))
            rememberMe.click();
        signIn.click();

    }
    public void isMessageDisplayed(String errorContent,int size){
     assertEquals(message.size(),size);
     switch (errorContent) {
         case "required":
             assert (requiredEmail.isDisplayed());
             assert (requiredPassword.isDisplayed());
             break;
         case "invalidEmail":
             assert (invalidEmiail.isDisplayed());
             break;
         case "invalidCredentials":
             assert (invalidCredentials.isDisplayed());
             break;
         }

    }
 public void isLoggedIn(){
    loggedIn.isDisplayed();
 }
}

