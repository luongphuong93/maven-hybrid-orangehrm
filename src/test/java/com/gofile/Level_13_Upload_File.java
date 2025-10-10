package com.gofile;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.gofile.HomePageObject;

public class Level_13_Upload_File extends BaseTest {
    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName,appURL);

        homePage = PageGenerator.getPage(HomePageObject.class,driver);
    }

    @Test
    public void Upload_01_Multiple(){
        Assert.assertTrue(homePage.isLoadingIconDisappear());
        homePage.uploadMultipleFiles(driver,image1,image2,image3);
        Assert.assertTrue(homePage.isProgressBarIconDisappear());
        String successUrl = homePage.getSuccessLink();
        homePage.openPageURL(driver,successUrl);
        Assert.assertTrue(homePage.isLoadingIconDisappear());
        homePage.sleepInSecond(2);
        Assert.assertTrue(homePage.isFileUploadedSuccess(image1));
        Assert.assertTrue(homePage.isFileUploadedSuccess(image2));
        Assert.assertTrue(homePage.isFileUploadedSuccess(image3));
    }

    @AfterClass
    public void afterClass(){
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
    String image1 = "image1.jpg";
    String image2 = "image2.jpg";
    String image3 = "image3.jpg";

}
