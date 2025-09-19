package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_10_Multiple_Driver extends BaseTest {


    @Parameters({"userUrl","adminUrl","browser"})
    @BeforeClass
    public void beforeClass(String userURL, String adminURL, String browserName){
        //Gán dữ liệu
        this.userURL = userURL;
        this.adminURL = adminURL;
        adminUser = "luongphuong93";
        adminPassword = "Simple1993@";
        userFirstname = "Mary";
        userLastname = "Jane";
        userEmailAddress = "maryjane" + getRandomNumber() + "@gmail.com";
        userPassword = "Simple1993@";
        userWindowID = "";
        adminWindowID = "";

        // Khi mở browser lên sẽ là trang User
        userDriver = getBrowserDriver(browserName,userURL);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

        // Khi mở browser lên sẽ là trang Admin
        adminDriver = getBrowserDriver(browserName,adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,adminDriver);
    }

    @Test
    public void OpenCart_01_Multiple_Driver(){
        // Firefox của User chạy tiếp
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, userDriver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterEmailAdress(userEmailAddress);
        userRegisterPage.enterPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplay());

        // Firefox của Admin chạy tiếp
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplay());

        // Firefox của User chạy tiếp
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, userDriver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplay());
    }

    @AfterClass
    public void afterClass(){
        closeBrowser(userDriver);
        closeBrowser(adminDriver);
    }

    private WebDriver userDriver;
    private WebDriver adminDriver;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserLoginPO userLoginPage;
    private UserMyAccountPO userMyAccountPage;
    private String userURL, adminURL;
    private String adminUser, adminPassword;
    private String userWindowID, adminWindowID;
    private String userFirstname, userLastname, userEmailAddress, userPassword;
}
