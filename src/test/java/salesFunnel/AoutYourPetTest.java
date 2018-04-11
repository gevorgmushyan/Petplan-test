package salesFunnel;

import core.Driver;
import core.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.salesFunnel.AboutYourPetPageObject;

import java.util.concurrent.TimeUnit;

public class AoutYourPetTest {
    private Driver driver = new Driver();
    private WebDriver webDriver;
    private AboutYourPetPageObject aboutPet;
    private Wait wait;
    private JavascriptExecutor js;
    private String url = "http://account.gopetplan.com/beta#/AboutYourPet?Region=US";
    private String chooseAndCustomiztUrl = "https://account.gopetplan.com/beta#/ChooseAndCustomize";

    private String petName = "Pet1";
    private int selectedBreedIndex = 10;
    private int selectedAgeIndex = 19;
    private String validZipCode = "80246";
    private String validEmailAddress = "example@example.com";
    private String registeredEmailAddress = "gevorgmio2@yandex.ru";
    private String password = "a";

    private String validationRegisteredEmail = "This email is taken, please either choose another or login";
    private String popUpEmailLabel = "email address";
    private String popUpPasswoedLabel = "password";

    @BeforeMethod
    private void beforeTest(){
        webDriver = driver.getDriver();
        wait = new Wait(webDriver);
        aboutPet = new AboutYourPetPageObject(webDriver);
        aboutPet.getBaseUrl(url);
        new Wait(webDriver).waitForPageLoad();
        js = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    private void afterTest() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.close();
        webDriver.quit();
    }
    @Test   //PP-192
    private void getYoutQuoteWithValidData() throws InterruptedException {
        aboutPet.setPetName(petName);
        aboutPet.selectCatButton();
        aboutPet.selectBreed(selectedBreedIndex);
        aboutPet.selectAge(selectedAgeIndex);
        aboutPet.setZipCode(validZipCode);
        aboutPet.setEmailAddress(validEmailAddress);
        js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(1000); //To Do
        aboutPet.clickOnGetYourQuote();
        Thread.sleep(1000); //To Do
        assert (webDriver.getCurrentUrl().equals(chooseAndCustomiztUrl));
    }
    @Test   //PP-207
    private void loginWithRegisteredEmail() throws InterruptedException {
        aboutPet.setEmailAddress(registeredEmailAddress);
        aboutPet.waitForPouUpAppear();
        assert (aboutPet.getPopUpEmail().equals(registeredEmailAddress));
        aboutPet.closePopUp();
        aboutPet.waitForPouUpDisappear();
        wait.waitForElementText(aboutPet.getRegisteredEmailErrorMessage(),"This email is taken, please either choose another or", 2);
        assert (aboutPet.getRegisteredEmailError().equals(validationRegisteredEmail)); //To Do for colors
        aboutPet.clickOnLoginLink();//To DO
        aboutPet.waitForPouUpAppear();
        assert (aboutPet.getPopUpEmail().equals(registeredEmailAddress));
        assert (aboutPet.getPopUpEmailLabel().equals(popUpEmailLabel));
        aboutPet.setPopUpPassword(password);
        assert (aboutPet.getPopUpPasswordLabel().equals(popUpPasswoedLabel));
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
}
