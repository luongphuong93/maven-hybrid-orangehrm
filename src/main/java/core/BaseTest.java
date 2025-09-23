package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;

    protected WebDriver getBrowserDriver(String browserName, String appURL) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }
        driver.get(appURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        return driver;
    }

    protected void closeBrowser(){
        if(!(null == driver)){
            driver.quit();
        }
    }

    protected void closeBrowser(WebDriver driver){
        if(!(null == driver)){
            driver.quit();
        }
    }

    protected int getRandomNumber(){
        return new Random().nextInt(99999);
    }
}
