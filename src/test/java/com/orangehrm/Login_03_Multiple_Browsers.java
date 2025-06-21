package com.orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_03_Multiple_Browsers extends BaseTest {
    private WebDriver driver;
    private BasePage basePage;
    private String appUrl;

    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        this.appUrl = appURL;
        basePage = BasePage.getInstance();
        driver = getBrowserDriver(browserName,appURL);
    }

    @Test
    public void Login_01_Empty(){
        basePage.openPageURL(driver,appUrl);

        basePage.sendKeyToElement(driver,"//input[@name='username']","");
        basePage.sendKeyToElement(driver,"//input[@name='password']","");
        basePage.clickToElement(driver,"//button[@type='submit']");

        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='password']/parent::div/following-sibling::span"),"Required");
    }
    @Test
    public void Login_02_Invalid_Username(){
        basePage.openPageURL(driver,appUrl);

        basePage.sendKeyToElement(driver,"//input[@name='username']","abc");
        basePage.sendKeyToElement(driver,"//input[@name='password']","admin123");
        basePage.clickToElement(driver,"//button[@type='submit']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"),"Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password(){
        basePage.openPageURL(driver,appUrl);

        basePage.sendKeyToElement(driver,"//input[@name='username']","Admin");
        basePage.sendKeyToElement(driver,"//input[@name='password']","123");
        basePage.clickToElement(driver,"//button[@type='submit']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"),"Invalid credentials");

    }

    @Test
    public void Login_04_Valid_Username_Password(){
        basePage.openPageURL(driver,appUrl);

        basePage.sendKeyToElement(driver,"//input[@name='username']","Admin");
        basePage.sendKeyToElement(driver,"//input[@name='password']","admin123");
        basePage.clickToElement(driver,"//button[@type='submit']");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(basePage.getElementText(driver,"//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]"),"Dashboard");
    }

    public boolean isAllLoadingSpinnerInvisible(){
        return basePage.waitElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
