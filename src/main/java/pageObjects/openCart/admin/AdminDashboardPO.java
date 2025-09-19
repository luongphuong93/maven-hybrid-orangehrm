package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminCustomerPO openCustomerPage() {
        waitElementClickable(driver, AdminDashboardPageUI.CUSTOMER_MENU);
        clickToElement(driver,AdminDashboardPageUI.CUSTOMER_MENU);
        sleepInSecond(2);
        waitElementClickable(driver,AdminDashboardPageUI.CUSTOMER_LINK);
        clickToElement(driver,AdminDashboardPageUI.CUSTOMER_LINK);
        return PageGenerator.getPage(AdminCustomerPO.class,driver);
    }

    public boolean isDashboardHeaderDisplay() {
        waitElementVisible(driver,AdminDashboardPageUI.DASHBOARD_HEADER_TEXT);
        return isElementDisplay(driver,AdminDashboardPageUI.DASHBOARD_HEADER_TEXT);
    }

}
