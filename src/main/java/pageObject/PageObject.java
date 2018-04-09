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
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
    public String getCurrentPageTitle(){
        return driver.getTitle();
    }
    public void getBaseUrl(String url){
        driver.get(url);
    }
}
