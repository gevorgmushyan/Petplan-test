package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static config.Config.BASE_URL;

public class PageObject {
    protected WebDriver driver;
    public PageObject(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void getBaseUrl(){
        driver.get(BASE_URL);
    }
    public void getCurrentPageTitle(){
        driver.getTitle();
    }
}
