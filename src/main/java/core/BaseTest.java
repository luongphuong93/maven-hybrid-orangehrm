package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;
    protected final Logger log;
    public BaseTest() {
        this.log = LogManager.getLogger(getClass());
    }

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
        log.info("===================== INIT BROWSER & DRIVRER =====================");
        return driver;
    }

    protected void closeBrowser(){
        if(null != driver){
            driver.quit();
            log.info("====================== CLOSE BROWSER & DRIVRER ========================");
        }
    }

    protected void closeBrowser(WebDriver driver){
        if(null != driver){
            driver.quit();
        }
        log.info("====================== CLOSE BROWSER & DRIVRER ========================");
    }

    protected int getRandomNumber(){
        return new Random().nextInt(99999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("--------------------- PASSED ---------------------");
        } catch (Throwable e) {
            pass = false;

            //Lấy hết các lỗi đang có của testcase hiện tại
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            //Set vào Report TestNG/ReportNG
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("--------------------- FAILED ---------------------");
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info("--------------------- PASSED ---------------------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("--------------------- FAILED ---------------------");
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("--------------------- PASSED ---------------------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("--------------------- FAILED ---------------------");
        }
        return pass;
    }
}
