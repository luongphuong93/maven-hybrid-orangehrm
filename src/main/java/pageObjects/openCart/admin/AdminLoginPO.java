package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUsername(String adminUser) {
        waitElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, adminUser);
    }

    public void enterToPassword(String adminPassword) {
        waitElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitElementClickable(driver,AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(AdminDashboardPO.class,driver);
    }
}
