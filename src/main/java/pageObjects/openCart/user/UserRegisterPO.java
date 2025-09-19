package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToFirstName(String userFirstname) {
        waitElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver,UserRegisterPageUI.FIRST_NAME_TEXTBOX,userFirstname);
    }

    public void enterToLastName(String userLastname) {
        waitElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver,UserRegisterPageUI.LAST_NAME_TEXTBOX,userLastname);
    }

    public void enterEmailAdress(String userEmailAddress) {
        waitElementVisible(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(driver,UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX,userEmailAddress);
    }

    public void enterPassword(String userPassword) {
        waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,UserRegisterPageUI.PASSWORD_TEXTBOX,userPassword);
    }

    public void acceptPrivacyCheckbox() {
        scrollToElementOnTop(driver,UserRegisterPageUI.AGREE_RADIOBUTTON);
        waitElementClickable(driver,UserRegisterPageUI.AGREE_RADIOBUTTON);
        checkToCheckBoxRadio(driver,UserRegisterPageUI.AGREE_RADIOBUTTON);
    }

    public void clickContinueButton() {
        waitElementClickable(driver,UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver,UserRegisterPageUI.CONTINUE_BUTTON);
    }

    public boolean isSuccessMessageDisplay() {
        waitElementVisible(driver,UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
        return isElementDisplay(driver,UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
    }
}
