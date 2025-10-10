package com.jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;

import java.util.List;

public class Level_13_Upload_File extends BaseTest {
    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName,appURL);

        homePage = PageGenerator.getPage(HomePageObject.class,driver);
    }

    @Test
    public void Upload_01_Single(){
        homePage.uploadMultipleFiles(driver,image1);
        homePage.uploadMultipleFiles(driver,image2);
        homePage.uploadMultipleFiles(driver,image3);

        Assert.assertTrue(homePage.isFileLoadedSuccess(image1));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image2));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image3));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(image1));
        Assert.assertTrue(homePage.isFileUploadedSuccess(image2));
        Assert.assertTrue(homePage.isFileUploadedSuccess(image3));
    }

    @Test
    public void Upload_02_Multiple(){
        homePage.refreshPage(driver);
        homePage.uploadMultipleFiles(driver,image1,image2,image3);

        Assert.assertTrue(homePage.isFileLoadedSuccess(image1));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image2));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image3));

        homePage.clickStartUpload();

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
    String[] fileName = {image1,image2,image3};
}
