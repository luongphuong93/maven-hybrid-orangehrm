package com.orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Login_04_Page_Object extends BaseTest {
    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName,appURL);

        loginPage = new LoginPageObject();
    }

    @Test
    public void Employee_01_CreateNewEmployee(){
        // Action of Login Page
        loginPage.enterToUserNameTextbox("Admin");
        loginPage.enterToUserPasswordTextbox("admin123");
        loginPage.clickToLoginButton();

        // Action of Dashboard Page
        dashboardPage = new DashboardPageObject();
        dashboardPage.clickToPIMModule();

        // Action of Employee List Page
        employeeListPage = new EmployeeListPageObject();
        employeeListPage.clickToAddEmployeeButton();

        // Action of Add Employee Page
        addEmployeePage = new AddEmployeePageObject();
        addEmployeePage.enterToFirstNameTextbox();
        addEmployeePage.enterToLastNameTextbox();
        getEmployeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickToSaveButton();

        // Action of Personal Detail Page
        personalDetailPage = new PersonalDetailPageObject();
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),getEmployeeID);
    }

    @Test
    public void Employee_02_EditEmployee(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String getEmployeeID;
}
