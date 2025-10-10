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

public class Level_09_Switch_Url_Role extends BaseTest {

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
        driver = getBrowserDriver(browserName,userURL);
        userHomePage = PageGenerator.getPage(UserHomePO.class,driver);
    }

    @Test(enabled = false)
    public void OpenCart_01_Login_And_Logout() {
        /*GIỐNG HÀNH VI CỦA USER*/
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterEmailAdress(userEmailAddress);
        userRegisterPage.enterPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplay());

        //Note: tất các page đều gọi logout được trừ login page, nên mình sẽ viết hàm logout ở BasePage
        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        // Note: hàm openAdminSite thì từ vị trị nào (page) gọi đến thì cũng quay về trang admin được nên sẽ viết trong BasePage
        // USER -> ADMIN
        userHomePage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);

                adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        // ADMIN -> USER
        userHomePage = adminLoginPage.openUserSite(driver, userURL);

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);
        userLoginPage.enterToEmailAdressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextBox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplay());

        // USER -> ADMIN
        userMyAccountPage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        // ADMIN -> USER
        userHomePage = adminDashboardPage.openUserSite(driver, userURL);
    }
    @Test(enabled = false)
    public void OpenCart_02_Login_Without_Logout(){
        /* TIỆN CHO VIỆC DEVELOP/TESTING */
        // User vào đăng ký acc rồi mua hàng...
        // User ko Logout
        // Chuyển qua trang Admin -> Login 1 lần
        // Admin vào verify đơn hàng...
        // Admin ko Logout
        // Chuyển qua trang Admin

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterEmailAdress(userEmailAddress);
        userRegisterPage.enterPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplay());

        // Ko Logout nên nó vẫn đang ở trang user register

        // USER -> ADMIN
        userRegisterPage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);
        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        // Ko Logout nên nó vẫn đang ở trang customer

        // ADMIN -> USER
        userHomePage = adminCustomerPage.openUserSite(driver, userURL);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplay());

        // USER -> ADMIN
        userMyAccountPage.openAdminSite(driver,adminURL);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class,driver);

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());
        // Fail vì đang có BUG của trang (vì đúng ra cần hiển thị được dashboard vì luồng trước đó ko hề logout khỏi admin

    }

    @Test
    public void OpenCart_03_Multiple_Tab(){
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterEmailAdress(userEmailAddress);
        userRegisterPage.enterPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplay());
        // Lấy ra user window ID
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        // Ko Logout nên nó vẫn đang ở trang user register

        // USER -> ADMIN
        userRegisterPage.openURLByNewTAB(driver,adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);
        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        // Lấy ra admin window ID
        adminWindowID = adminCustomerPage.getCurrentWindowID(driver);


        // Ko Logout nên nó vẫn đang ở trang customer

        // ADMIN -> USER
        adminCustomerPage.switchToWindowByID(driver,adminWindowID); // cần truyền vào ID của tab còn lại, thì sẽ switch qua được tab cần đến
        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class,driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplay());

        // USER -> ADMIN
        userMyAccountPage.switchToWindowByID(driver,userWindowID);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class,driver);

        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplay());
    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }

    private WebDriver driver;
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
