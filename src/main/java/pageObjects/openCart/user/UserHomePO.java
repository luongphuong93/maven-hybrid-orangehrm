package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserHomePageUI;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToMyAccountAtFooter() {
        scrollToElementOnTop(driver,UserHomePageUI.FOOTER_MY_ACCOUNT_LINK);
        waitElementClickable(driver, UserHomePageUI.FOOTER_MY_ACCOUNT_LINK);
        clickToElement(driver,UserHomePageUI.FOOTER_MY_ACCOUNT_LINK);
    }
}
