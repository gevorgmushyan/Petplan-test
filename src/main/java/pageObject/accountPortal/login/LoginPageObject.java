package pageObject.accountPortal.login;


import core.Wait;
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
    @FindBy(xpath = "//div[@class='login-status is-hidden-tablet']//p")
    private WebElement accountElement;

    private Wait wait;

    public LoginPageObject(WebDriver driver){
        super(driver);
        wait = new Wait(driver);
    }
    public void clickOnLogo(){
        logo.click();
    }
    public void clickOnSingIn(){
        signIn.click();
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
    public String getValidationMessage(){
        return validationMessage.getText();
    }
    public void waitForPageLoad(){
        wait.waitForPageLoad();
    }
    public void loginToAccount(String email, String password){
        waitForEmailAddressInputAppear();
        setEmailAddress(email);
        setPassword(password);
        clickOnSingIn();
        wait.waitForEelementAppear(accountElement, 20);
    }
    public void waitForEmailAddressInputAppear(){
        wait.waitForEelementAppear(emailAddress, 20);
    }
}

