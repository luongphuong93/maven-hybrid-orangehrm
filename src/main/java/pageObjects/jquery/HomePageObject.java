package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

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
        sendKeyToElement(driver,HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, Keys.ENTER,headerName);
    }

    public boolean isPageInfoDisplayed(String female, String country, String male, String total) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_PAGE_INFO,female,country,male,total);
        return isElementDisplay(driver,HomePageUI.DYNAMIC_PAGE_INFO,female,country,male,total);
    }

    public void clickToActionByCountryName(String countryName, String actionName) {
        waitElementClickable(driver,HomePageUI.DYNAMIC_ACTION_BY_COUNTRY_NAME,countryName,actionName);
        clickToElement(driver,HomePageUI.DYNAMIC_ACTION_BY_COUNTRY_NAME,countryName,actionName);
    }

    public void clickLoadDataButton() {
        waitElementClickable(driver,HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver,HomePageUI.LOAD_DATA_BUTTON);
    }

    public void enterToTextboxByColumnNameAndRowIndex(String columnName, String rowIndex, String valueToSendkey) {
        waitListElementVisible(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName);
        int columnIndex = getListElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName) + 1;

        waitElementVisible(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX,rowIndex, String.valueOf(columnIndex));
        sendKeyToElement(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX,valueToSendkey,rowIndex,String.valueOf(columnIndex));
    }

    public void selectToDropdownByColumnNameAndRowIndex(String columnName, String rowIndex, String valueToSelect) {
        waitListElementVisible(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName);
        int columnIndex = getListElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName) + 1;

        waitElementClickable(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX,rowIndex,String.valueOf(columnIndex));
        selectItemDropdown(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX,valueToSelect,rowIndex,String.valueOf(columnIndex));
    }

    public void checkToCheckboxByColumnNameAndRowIndex(String columnName, String rowIndex) {
        waitListElementVisible(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName);
        int columnIndex = getListElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName) + 1;

        waitElementClickable(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX,rowIndex,String.valueOf(columnIndex));
        checkToCheckBoxRadio(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX,rowIndex,String.valueOf(columnIndex));
    }

    public void actionToRowByRowIndex(String rowIndex, String actionName) {
        waitElementClickable(driver,HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX,rowIndex,actionName);
        clickToElement(driver,HomePageUI.DYNAMIC_ACTION_BY_ROW_INDEX,rowIndex,actionName);
    }


    public List<String> getColumnAllValueByColumnName(String columnName) {
        List<WebElement> allPage = getListWebElement(driver,HomePageUI.ALL_PAGE);
        List<String> columnAllValue = new ArrayList<String>();

        waitListElementVisible(driver,HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME,columnName);
        int columnIndex = getListElementSize(driver,HomePageUI.DYNAMIC_INDEX_BY_COLUMN_NAME,columnName) + 1;

        for (WebElement page:allPage){
            page.click();

            List<WebElement> columnAllValueElement = getListWebElement(driver,HomePageUI.DYNAMIC_COLUMN_INDEX, String.valueOf(columnIndex));

            for (WebElement value:columnAllValueElement){
                columnAllValue.add(value.getText());
            }
        }
        return columnAllValue;
    }

    public boolean isFileLoadedSuccess(String fileName) {
        waitElementVisible(driver,HomePageUI.IS_FILE_LOADED,fileName);
        return isElementDisplay(driver,HomePageUI.IS_FILE_LOADED,fileName);
    }

    public void clickStartUpload() {
        List<WebElement> startButtons = getListWebElement(driver,HomePageUI.START_UPLOAD_BUTTON);
        for (WebElement startButton:startButtons){
            waitElementClickable(driver,startButton).click();
            sleepInSecond(2);
        }

    }

    public boolean isFileUploadedSuccess(String fileName) {
        waitElementVisible(driver,HomePageUI.IS_FILE_UPLOADED,fileName);
        return isElementDisplay(driver,HomePageUI.IS_FILE_UPLOADED,fileName);
    }
}
