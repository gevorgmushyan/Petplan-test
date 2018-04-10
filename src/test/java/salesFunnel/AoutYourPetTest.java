package salesFunnel;

import core.Driver;
import core.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
    private String password = "aa250812aa";

    private String validationRegisteredEmail = "This email is taken, please either choose another or login";

    @BeforeMethod
    private void beforeTest(){
        webDriver = driver.getDriver();
        wait = new Wait(webDriver);
        aboutPet = new AboutYourPetPageObject(webDriver);
        aboutPet.getBaseUrl(url);
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
        //aboutPet.selectBreed("Afghan Hound");
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
    private void loginWithRegisteredEmail(){
        aboutPet.setEmailAddress(registeredEmailAddress);
        assert (aboutPet.getPopUpEmail().equals(registeredEmailAddress));
        aboutPet.closePopUp();
        wait.waitForElementText(aboutPet.getLoginLink(),"login", 10);
        wait.waitForElementText(aboutPet.getRegisteredEmailErrorMessage(),"This email is taken, please either choose another or", 10);
        assert (aboutPet.getRegisteredEmailError().equals(validationRegisteredEmail)); //To Do for colors
        aboutPet.clickOnLoginLink();
        assert (aboutPet.getPopUpEmail().equals(registeredEmailAddress));
       // assert ()

    }
}
