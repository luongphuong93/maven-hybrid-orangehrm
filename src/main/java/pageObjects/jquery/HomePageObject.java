package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;

public class HomePageObject extends BasePage {
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public void openPageByNumber(String pageNumber) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER, pageNumber);
        clickToElement(driver,HomePageUI.DYNAMIC_PAGE_BY_NUMBER, pageNumber);
    }

    public boolean isPageActiveByNumber(String pageNumber) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_NUMBER,pageNumber);
        return isElementDisplay(driver,HomePageUI.DYNAMIC_PAGE_ACTIVED_BY_NUMBER,pageNumber);
    }

    public void enterToHeaderTextBoxByName(String headerName, String value) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME,headerName);
        sendKeyToElement(driver,HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME,value,headerName);
        sleepInSecond(2);
        sendKeyToElement(driver,HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, Keys.ENTER,headerName);
        sleepInSecond(5);
    }

    public boolean isPageInfoDisplayed(String female, String country, String male, String total) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_INFO,female,country,male,total);
        return isElementDisplay(driver,HomePageUI.DYNAMIC_PAGE_INFO,female,country,male,total);
    }
}
