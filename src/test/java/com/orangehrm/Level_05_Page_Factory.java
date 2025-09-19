package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFatory.orangeHRM.*;


public class Level_05_Page_Factory extends BaseTest {
    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName,appURL);

        loginPage = new LoginPageObject(driver);
        adminUsername = "luongphuong93";
        adminPassword = "Simple1993@";
        employeeFirstName = "Phuong";
        employeeLastName = "Luong";
    }

    @Test
    public void Employee_01_CreateNewEmployee(){
        // Action of Login Page
        loginPage.enterToUserNameTextbox(adminUsername);
        loginPage.enterToUserPasswordTextbox(adminPassword);
        loginPage.clickToLoginButton();

        // Action of Dashboard Page
        dashboardPage = new DashboardPageObject(driver);
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear());
        dashboardPage.clickToPIMModule();

        // Action of Employee List Page
        employeeListPage = new EmployeeListPageObject(driver);
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear());
        employeeListPage.clickToAddEmployeeButton();

        // Action of Add Employee Page
        addEmployeePage = new AddEmployeePageObject(driver);
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear());
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickToSaveButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear());

        // Action of Personal Detail Page
        personalDetailPage = new PersonalDetailPageObject(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear());
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }


    @AfterClass
    public void afterClass(){
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeLastName;
}
