package pageObject.salesFunnel;

import core.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.PageObject;

import java.util.List;
import java.util.function.Function;

import static core.TAGS.DOMESTIC_SHORTHAIR;

public class AboutYourPetPageObject extends PageObject {
    @FindBy(name = "petName")
    private WebElement petName;

    @FindBy(xpath = "//input[@value='1']")
    private WebElement dogButton;

    @FindBy(xpath = "//input[@value='2']")
    private WebElement catButton;

    @FindBy(xpath = "//div[@class='panel__grid']/div[1]//label[@class='selector-input']")
    private WebElement breedButton;

    @FindBy(xpath = "//input[@name='breed']")
    private WebElement breedInput;

    @FindBy(xpath = "//div[contains(@label-value,'what’s your pet’s breed?')]//li")
    private List<WebElement> breedList;

    @FindBy(xpath = "//div[@class='panel__grid']/div[2]//label[@class='selector-input']")
    private WebElement ageButton;

    @FindBy(xpath = "//input[@name='dateOfBirth']")
    private WebElement ageInput;

    @FindBy(xpath = "//div[contains(@label-value,'how old is your pet?')]//li")
    private List<WebElement> ageList;

    @FindBy(name = "zip")
    private WebElement zipCode;

    @FindBy(name = "email")
    private WebElement emailAddress;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement emailMeQuote;

    @FindBy(id = "chooseAndCustomize")
    private WebElement getYourQuote;

    @FindBy(xpath = "//strong[@ng-hide='account.loginModalIsOpen']")
    private WebElement registeredEmailErrorMessage;

    @FindBy(xpath = "//a[@class='login-warning']")
    private WebElement loginLink;

    // pop-up for already registered email
    @FindBy(xpath = "//div[@class='ngdialog-close ng-scope']")
    private WebElement popUpCloseButton;

    @FindBy(xpath = "//div[@class='input-group']//input[@type='text']")
    private WebElement popUpEmail;

    @FindBy(id = "modalPass")
    private WebElement popUpPassword;

    @FindBy(xpath = "//a[@class='anchor-link']")
    private WebElement popUpForgotPassword;

    @FindBy(xpath = "//a[@class='btn__link--primary btn__link--cta'][contains(text(),'login')]")
    private WebElement popUpLogin;

    @FindBy(xpath = "//div[@class='input-group']//label[@class='floating-label' and @for='email']")
    private WebElement popUpEmailLabel;

    @FindBy(xpath = "//label[@class='floating-label'][contains(text(),'password')]")
    private WebElement popUpPasswordLabel;

    @FindBy(xpath = "//li[@class='ng-binding']")
    private WebElement logedInAs;

    @FindBy(xpath = "//div[@name='forms.loginModalForm']")
    private WebElement popup;

    @FindBy(xpath = "//strong[contains(text(),'Please enter your pet‘s name.')]")
    private WebElement emptyPetNameError;

    @FindBy(xpath = "//strong[contains(text(),'Please enter your pet‘s breed.')]")
    private WebElement emptyBreedError;

    @FindBy(xpath = "//strong[contains(text(),'Please tell us your pet‘s age.')]")
    private WebElement emptyAgeError;

    @FindBy(xpath = "//strong[contains(text(),'Please enter your zip code.')]")
    private WebElement emptyZipCodeError;

    @FindBy(xpath = "//strong[contains(text(),'Please enter your email address.')]")
    private WebElement emptyEmailError;

    private String loggedInText = "logged in as";
    private Wait wait;

    public AboutYourPetPageObject(WebDriver driver){
        super(driver);
        wait = new Wait(driver);
    }
    public void setPetName(String name){
        petName.clear();
        petName.sendKeys(name);
    }
    public void selectDogButton(){
        dogButton.click();
    }
    public void selectCatButton(){
        catButton.click();
    }
    public void selectBreed(int i){
        assert (i >= 0 && i != 3 && i < breedList.size());
        breedButton.click();
        breedList.get(i).click();
    }
    public void selectBreed(String breed){
        breedInput.sendKeys(breed);
        breedButton.sendKeys(Keys.RETURN);
    }
    public void selectAge(int i){
        assert (i >=0 && i < ageList.size());
        ageButton.click();
        ageList.get(i).click();
    }
    public void setZipCode(String zip){
        zipCode.clear();
        zipCode.sendKeys(zip);
    }
    public void setEmailAddress(String email) {
        emailAddress.clear();
        emailAddress.sendKeys(email);
    }
    public void clickOnGetYourQuote(){
        getYourQuote.click();
    }
    public void closePopUp(){
        popUpCloseButton.click();

    }
    public String getFirstBreedItem(){
        String breedSpan = "//div[contains(@label-value,'what’s your pet’s breed?')]//li[1]/span";
        wait.elementTextIsNotEmpty(driver.findElement(By.xpath(breedSpan)),10);
        WebElement span = driver.findElement(By.xpath(breedSpan));
        return span.getText();
    }
    public String getPopUpEmail(){
        return popUpEmail.getAttribute("value");
    }
    public String getRegisteredEmailError(){
        return registeredEmailErrorMessage.getText();
    }
    public void clickOnLoginLink(){
        loginLink.click();
//        Actions actions = new Actions(driver);
//        actions.moveToElement(loginLink).click().perform();
    }
    public WebElement getRegisteredEmailErrorMessage(){
        return registeredEmailErrorMessage;
    }
    public WebElement getLoginLink(){
        return loginLink;
    }
    public String getPopUpEmailLabel(){
        return popUpEmailLabel.getText();
    }
    public String getPopUpPasswordLabel(){
        return popUpPasswordLabel.getText();
    }
    public String getEmptyPetNameError(){
        return emptyPetNameError.getText();
    }
    public String getEmptyBreedError(){
        return emptyBreedError.getText();
    }
    public String getEmptyAgeError(){
        return emptyAgeError.getText();
    }
    public String getEmptyZipCodeError(){
        return emptyZipCodeError.getText();
    }
    public String getEmptyEmailError(){
        return emptyEmailError.getText();
    }
    public void setPopUpEmail(String email){
        popUpEmail.clear();
        popUpEmail.sendKeys(email);
    }
    public void setPopUpPassword(String password){
        popUpPassword.clear();
        popUpPassword.sendKeys(password);
    }
    public void clickOnPopUpLogin(){
        popUpLogin.click();
    }
    public boolean isLoggedIn(){
        if(logedInAs.getText().contains(loggedInText))
            return true;
        return false;
    }
    public boolean isZipCodeDisabled(){
        return  zipCode.isDisplayed();
    }
    public boolean isCatsBreedListDisplyed(){
        WebElement element = driver.findElement(By.xpath("//div[contains(@label-value,'what’s your pet’s breed?')]//li[1]/span"));
        wait.waitForElementText(element, DOMESTIC_SHORTHAIR, 10);
        return true;
    }
    public boolean isEmailDisabled(){
        return emailAddress.isDisplayed();
    }
    public void waitForPopUpAppear(){
        wait.waitForEelementAppear(popup, 10);
    }
    public void waitForPopUpDisappear(){
        wait.waitForElementDisappear(By.xpath("//div[@name='forms.loginModalForm']"), 10);
    }
    public void waitForExistingEmailValidatinMessage(){
        wait.waitForElementText(registeredEmailErrorMessage,"This email is taken, please either choose another or", 2);
    }
}
