package pageObject.accountPortal.login;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

public class LoginPageObject extends PageObject {
    @FindBy(xpath = "//a[@href='http://www.gopetplan.com']")
    private WebElement logo;
    @FindBy(name = "email")
    private WebElement emailAddress;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(id = "remember")
    private WebElement rememberMe;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signIn;
    @FindBy(linkText = "forgot password?")
    private WebElement forgotPassword;
    @FindBy(partialLinkText = "start a quote")
    private WebElement startAQuote;
    @FindBy(xpath = "//label[@class='floating-label' and @for='email']")
    private WebElement emailAddressLabel;
    @FindBy(xpath = "//label[@class='floating-label' and @for='password']")
    private WebElement passwordLabel;
    @FindBy(xpath = "//p[@ng-message = 'email']")
    private WebElement invalidEmailError;
    @FindBy(xpath = "//p[@ng-message='invalidCredentials']")
    private WebElement validationMessage;

    public LoginPageObject(WebDriver driver){
        super(driver);
    }
    public void ClickOnLogo(){
        logo.click();
    }
    public void setEmailAddress(String email){
        emailAddress.clear();
        emailAddress.sendKeys(email);
    }
    public void setPassword(String pas){
        password.clear();
        password.sendKeys(pas);
    }
    public String getEmailAddressLabel(){
        return emailAddressLabel.getText();
    }
    public String getPasswordLabel(){
        return passwordLabel.getText();
    }
    public void clickOnSingIn(){
        signIn.click();
    }
    public String getValidationMessage(){
        return validationMessage.getText();
    }
}

