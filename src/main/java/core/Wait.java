package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wait {
    private WebDriver driver;
    public Wait(WebDriver driver){
        this.driver = driver;
    }
    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState"
                ).equals("complete");
            }
        });
    }
    public void waitForElementText(WebElement el, String text, int waitTime){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.textToBePresentInElement(el, text));
    }
    public void elementTextIsNotEmpty(WebElement el, int waitTime){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.textToBePresentInElement(el,""));
    }
    public void waitForEelementAppear(WebElement element, int waitTime){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementDisappear(By element, int waitTime){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
    }
    public static void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
