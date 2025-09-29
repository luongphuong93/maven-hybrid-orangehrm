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
import pageUIs.jquery.HomePageUI;

public class Level_12_DataTable extends BaseTest {
    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName,appURL);

        homePage = PageGenerator.getPage(HomePageObject.class,driver);

    }

    @Test(enabled = false)
    public void Table_01_Paging(){
        // 1 - mở ra 1 page bất kỳ dựa vào số trang truyền vào
        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageActiveByNumber("5"));

        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));

        homePage.openPageByNumber("24");
        Assert.assertTrue(homePage.isPageActiveByNumber("24"));
    }

    @Test
    public void Table_02_Search(){
        // 2 - search ở bất kỳ 1 header textbox nào dựa vào tên cột
        homePage.enterToHeaderTextBoxByName("Country","Afghanistan");
        homePage.sleepInSecond(3);
        // 3 - verify bất kỳ thông tin của 1 country nào
        Assert.assertTrue(homePage.isPageInfoDisplayed("384187","Afghanistan","407124","791312"));
        homePage.refreshPage(driver);


//        homePage.enterToHeaderTextBoxByName("Females","384187");
//        homePage.refreshPage(driver);
//        homePage.sleepInSecond(3);
//
//        homePage.enterToHeaderTextBoxByName("Males","803");
//        homePage.refreshPage(driver);
//        homePage.sleepInSecond(3);


        // 4- có thể xoá/edit bất kỳ 1 country nào dựa vào tên country
    }


    @AfterClass
    public void afterClass(){
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
}
