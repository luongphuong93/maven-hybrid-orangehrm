package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

public class Level_12_Assert_Verify extends BaseTest {
    @Parameters({"browser","appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName,appURL);

        loginPage = PageGenerator.getPage(LoginPageObject.class,driver);

        adminUsername = "luongphuong93";
        adminPassword = "Simple1993@";
        employeeFirstName = "employee5";
        employeeLastName = "Luong";
    }

    @Test
    public void Employee_01_CreateNewEmployee(){
        // Action of Login Page
        loginPage.enterToUserNameTextbox(adminUsername);
        loginPage.enterToUserPasswordTextbox(adminPassword);
        dashboardPage = loginPage.clickToLoginButton();

        // Action of Dashboard Page
        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        employeeListPage = dashboardPage.clickToPIMModule();

        // Action of Employee List Page
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        // Action of Add Employee Page
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
        personalDetailPage = addEmployeePage.clickToSaveButton();

        // Action of Personal Detail Page
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        verifyEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        verifyEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        verifyEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
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
