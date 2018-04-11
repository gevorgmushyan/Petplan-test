package salesFunnel;

import core.Driver;
import core.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.salesFunnel.AboutYourPetPageObject;

import static core.TAGS.*;
import static core.Wait.sleep;

import java.util.concurrent.TimeUnit;

public class AoutYourPetTest {
    private Driver driver;
    private WebDriver webDriver;
    private AboutYourPetPageObject aboutPet;
    private JavascriptExecutor js;

    private String petName = "Pet1";
    private int selectedBreedIndex = 10;
    private int selectedAgeIndex = 19;
    private String validZipCode = "80246";
    private String validEmailAddress = "example@example.com";
    private String registeredEmailAddress = "gevorgmio2@yandex.ru";
    private String password = "a";

    @BeforeMethod
    private void beforeTest(){
        driver = new Driver();
        webDriver = driver.getDriver();
        aboutPet = new AboutYourPetPageObject(webDriver);
        aboutPet.getBaseUrl(ACCOUNT_PORTAL_URL);
        new Wait(webDriver).waitForPageLoad();
        js = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    private void afterTest()  {
        sleep(2000);
        webDriver.close();
        webDriver.quit();
    }
    @Test   //PP-192
    private void getYoutQuoteWithValidData() {
        aboutPet.setPetName(petName);
        aboutPet.selectCatButton();
        aboutPet.selectBreed(selectedBreedIndex);
        aboutPet.selectAge(selectedAgeIndex);
        aboutPet.setZipCode(validZipCode);
        aboutPet.setEmailAddress(validEmailAddress);
        js.executeScript("window.scrollBy(0,200)");
       // sleep(1000); //To Do
        aboutPet.clickOnGetYourQuote();
       // sleep(1000); //To Do
        assert (webDriver.getCurrentUrl().equals(CHOOSE_AND_CUSTOMIZE_URL));
    }
    @Test   //PP-207
    private void loginWithRegisteredEmail() throws InterruptedException {
        aboutPet.setEmailAddress(registeredEmailAddress);
        aboutPet.waitForPopUpAppear();
        assert (aboutPet.getPopUpEmail().equals(registeredEmailAddress));
        aboutPet.closePopUp();
        aboutPet.waitForPopUpDisappear();
        aboutPet.waitForExistingEmailValidatinMessage();
        assert (aboutPet.getRegisteredEmailError().equals(VALIDATION_MESSAGE_FOR_REGISTERED_EMAIL)); //To Do for colors
        aboutPet.clickOnLoginLink();
        aboutPet.waitForPopUpAppear();
        assert (aboutPet.getPopUpEmail().equals(registeredEmailAddress));
        assert (aboutPet.getPopUpEmailLabel().equals(EMAIL_ADDRESS));
        aboutPet.setPopUpPassword(password);
        assert (aboutPet.getPopUpPasswordLabel().equals(PASSWORD));
        aboutPet.clickOnPopUpLogin();
        assert (aboutPet.isLoggedIn());
        assert (aboutPet.isZipCodeDisabled());
        assert (aboutPet.isEmailDisabled());
    }
    @Test   //PP-208
    private void catButtonWithCatsList(){
        aboutPet.selectCatButton();
        assert (aboutPet.isCatsBreedListDisplyed());
    }
    @Test   //PP-2843
    private void getYourQuoteWithEmptyFields(){
        js.executeScript("window.scrollBy(0,200)");
        aboutPet.clickOnGetYourQuote();
        assert(aboutPet.getEmptyPetNameError().equals(PET_NAME_EMPTY_ERROR_MESSAGE));
        assert(aboutPet.getEmptyBreedError().equals(PET_BREED_EMPTY_ERROR_MESSAGE));
        assert(aboutPet.getEmptyAgeError().equals(PET_AGE_EMPTY_ERROR_MESSAGE));
        assert(aboutPet.getEmptyZipCodeError().equals(ZIP_CODE_EMPTY_ERROR_MESSAGE));
        assert(aboutPet.getEmptyEmailError().equals(EMAIL_ADDRESS_EMPTY_ERROR_MESSAGE));
    }
}
