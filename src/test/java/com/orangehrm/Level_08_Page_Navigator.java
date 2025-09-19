package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.editNavigation.*;

public class Level_08_Page_Navigator extends BaseTest {
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
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        employeeListPage = dashboardPage.clickToPIMModule();

        // Action of Employee List Page
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        // Action of Add Employee Page
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
        personalDetailPage = addEmployeePage.clickToSaveButton();

        // Action of Personal Detail Page
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }

    @Test
    public void Employee_02_Page_Navigator(){
        // Personal Detail -> Contact Detail
        contactDetailPage = personalDetailPage.openContactDetailPage();

        // Contact -> Job
        jobPage = contactDetailPage.openJobPage();

        // Job -> Dependents
        dependentsPage = jobPage.openDependentsPage();

        // Dependents -> Personal
        personalDetailPage = dependentsPage.openPersonalDetailPage();

        // Personal -> Job
        jobPage = personalDetailPage.openJobPage();
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
    private ContactDetailPageObject contactDetailPage;
    private JobPageObject jobPage;
    private DependentsPageObject dependentsPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeLastName;
}
