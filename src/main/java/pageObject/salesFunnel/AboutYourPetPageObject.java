package pageObject.salesFunnel;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;

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


    public AboutYourPetPageObject(WebDriver driver){
        super(driver);
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
        if( i < 0 || i == 3 || i > breedList.size()){
            throw new IllegalArgumentException("Bad index for selecting breed");
        }
        breedButton.click();
        breedList.get(i).click();
    }
    public void selectBreed(String breed){
        breedInput.sendKeys(breed);
        breedButton.sendKeys(Keys.RETURN);
    }
    public void selectAge(int i){
        if( i < 0 || i > ageList.size()){
            throw new IllegalArgumentException("Bad index for selecting age");
        }
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
    public String getPopUpEmail(){
        return popUpEmail.getText();
    }
    public String getRegisteredEmailError(){
        return registeredEmailErrorMessage.getText() + loginLink.getText();
    }
    public void clickOnLoginLink(){
        loginLink.click();
    }
}
