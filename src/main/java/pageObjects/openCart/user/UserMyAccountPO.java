package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserMyAccountPageUI;

public class UserMyAccountPO extends BasePage {
    private WebDriver driver;

    public UserMyAccountPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMyAccountPageDisplay() {
        waitElementVisible(driver, UserMyAccountPageUI.MY_ACCOUNT_BREADCRUMB);
        return isElementDisplay(driver,UserMyAccountPageUI.MY_ACCOUNT_BREADCRUMB);
    }
}
