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

import java.util.List;

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

    @Test(enabled = false)
    public void Table_02_Search(){
        // 2 - search ở bất kỳ 1 header textbox nào dựa vào tên cột
        homePage.enterToHeaderTextBoxByName("Country","Afghanistan");
        homePage.sleepInSecond(3);
        // 3 - verify bất kỳ thông tin của 1 country nào
        Assert.assertTrue(homePage.isPageInfoDisplayed("384187","Afghanistan","407124","791312"));
        homePage.refreshPage(driver);


        // 4- có thể xoá/edit bất kỳ 1 country nào dựa vào tên country
    }

    @Test(enabled = false)
    public void Table_03_Action(){
        // 2 - search ở bất kỳ 1 header textbox nào dựa vào tên cột
        homePage.enterToHeaderTextBoxByName("Country","Afghanistan");
        homePage.sleepInSecond(3);

        // 4- có thể xoá/edit bất kỳ 1 country nào dựa vào tên country
        homePage.clickToActionByCountryName("Afghanistan","remove");
        homePage.refreshPage(driver);

        homePage.enterToHeaderTextBoxByName("Country","Angola");
        homePage.sleepInSecond(3);
        homePage.clickToActionByCountryName("Angola","edit");

    }

    @Test(enabled = false)
    public void Table_04_Index(){
        homePage.openPageURL(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();

        homePage.enterToTextboxByColumnNameAndRowIndex("Company","3","Vietnam");
        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","3","Phuong");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","3","15");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country","3","Hong Kong");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?","3");
        homePage.actionToRowByRowIndex("3","Move Up");
        homePage.sleepInSecond(3);

        homePage.enterToTextboxByColumnNameAndRowIndex("Company","4","Thailan");
        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person","4","Jon Jandai");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed","4","10");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country","4","Japan");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?","4");
        homePage.actionToRowByRowIndex("4","Insert");
        homePage.sleepInSecond(3);
    }

    @Test
    public void Table_05_Get_All_Page(){
        List<String> columnActualValue = homePage.getColumnAllValueByColumnName("Country");
        System.out.println(columnActualValue.size());

        columnActualValue = homePage.getColumnAllValueByColumnName("Females");
        System.out.println(columnActualValue.size());
    }

    @AfterClass
    public void afterClass(){
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
}
