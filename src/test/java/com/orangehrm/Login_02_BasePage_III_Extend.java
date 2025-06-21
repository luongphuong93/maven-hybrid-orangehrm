package com.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_02_BasePage_III_Extend extends BasePage{
    private WebDriver driver;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Empty(){
        openPageURL(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","");
        sendKeyToElement(driver,"//input[@name='password']","");
        clickToElement(driver,"//button[@type='submit']");

        Assert.assertEquals(getElementText(driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(getElementText(driver,"//input[@name='password']/parent::div/following-sibling::span"),"Required");
    }
    @Test
    public void Login_02_Invalid_Username(){
        openPageURL(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","abc");
        sendKeyToElement(driver,"//input[@name='password']","admin123");
        clickToElement(driver,"//button[@type='submit']");

        Assert.assertEquals(getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"),"Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password(){
        openPageURL(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","Admin");
        sendKeyToElement(driver,"//input[@name='password']","123");
        clickToElement(driver,"//button[@type='submit']");

        Assert.assertEquals(getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'oxd-alert-content-text')]"),"Invalid credentials");

    }

    @Test
    public void Login_04_Valid_Username_Password(){
        openPageURL(driver,appUrl);

        sendKeyToElement(driver,"//input[@name='username']","Admin");
        sendKeyToElement(driver,"//input[@name='password']","admin123");
        clickToElement(driver,"//button[@type='submit']");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(getElementText(driver,"//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]"),"Dashboard");
    }

    public boolean isAllLoadingSpinnerInvisible(){
        return waitElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
