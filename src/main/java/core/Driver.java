package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import static config.Config.BASE_URL;
import static config.Config.CURRENT_DRIVER;
import static core.TAGS.*;
import static model.OSInfo.*;

public class Driver {
    private int drive = CURRENT_DRIVER;
    private WebDriver driver;

    public WebDriver getDriver() {
        driver();
        return driver;
    }

    private void driver() {
        String webdriver = getWebDriver();
        switch (drive) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", webdriver);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver =new ChromeDriver(options);
                break;
            case IE:
                System.setProperty("webdriver.ie.driver", webdriver);
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver",webdriver);
                DesiredCapabilities capabilities=DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                driver = new FirefoxDriver(capabilities);
                break;
            case OPERA:
                System.setProperty("webdriver.opera.driver", webdriver);
                OperaOptions option = new OperaOptions();
                option.addArguments("--start-maximized");
                driver =new OperaDriver(option);
                break;
            case EDGE:
                System.setProperty("webdriver.edge.driver", webdriver);
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
        }
        driver.get(BASE_URL);
    }

    private String getWebDriver(){
        String webdriver = "";
        if (Windows && drive == CHROME) {
            webdriver = path + "chromedriver.exe";
        } else if (Linux && arch64 && drive == CHROME) {
            webdriver = path + "chromedriver64linux";
        } else if (Linux  && arch32 && drive == CHROME) {
            webdriver = path + "chromedriver32linux";
        } else if (Windows && arch64 && drive == OPERA) {
            webdriver = path + "operadriver.exe";
        } else if (Windows && arch32 && drive == OPERA) {
            webdriver = path + "operadriver32.exe";
        } else if (Linux && arch64 && drive == OPERA) {
            webdriver = path + "operadriver64";
        } else if (Linux && arch32 && drive == OPERA) {
            webdriver = path + "operadriver32";
        } else if (Windows && drive == EDGE) {
            webdriver = path + "edge.exe";
        } else if (Linux && arch64 && drive == FIREFOX) {
            webdriver = path + "geckodriver64";
        } else if (Linux && arch32 && drive == FIREFOX) {
            webdriver = path + "geckodriver32";
        } else if (Windows && drive == IE) {
            webdriver = path + "iedriver.exe";
        } else if (Windows && drive == FIREFOX) {
            webdriver = path + "geckodriver.exe";
        }
        return webdriver;
    }
}
