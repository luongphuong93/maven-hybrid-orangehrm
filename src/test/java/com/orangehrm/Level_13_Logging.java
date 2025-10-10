package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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

public class Level_13_Logging extends BaseTest {
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
        log.info("NewEmployee - STEP 01: Enter to Username and Password with info: " + adminUsername + " | " + adminPassword);
        loginPage.enterToUserNameTextbox(adminUsername);
        loginPage.enterToUserPasswordTextbox(adminPassword);

        log.info("NewEmployee - STEP 02: Navigate to Dashboard page");
        dashboardPage = loginPage.clickToLoginButton();
        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        log.info("NewEmployee - STEP 03: Navigate to Employee Search page");
        employeeListPage = dashboardPage.clickToPIMModule();
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        log.info("NewEmployee - STEP 04: Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        log.info("NewEmployee - STEP 05: Enter to FirstName and LastName with info: " + employeeFirstName + " | " + employeeLastName);
        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        log.info("NewEmployee - STEP 06: Navigate to Personal Details page");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        log.info("NewEmployee - STEP 07: Verify FisrtName is displayed: " + employeeFirstName);
        verifyEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);

        log.info("NewEmployee - STEP 08: Verify LastName is displayed: " + employeeLastName);
        verifyEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);

        log.info("NewEmployee - STEP 09: Verify Employee ID is displayed: " + employeeID);
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
