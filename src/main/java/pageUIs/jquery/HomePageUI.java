package pageUIs.jquery;

public class HomePageUI {
    // Site JQuery table
    public static final String DYNAMIC_PAGE_BY_NUMBER = "XPATH=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String DYNAMIC_PAGE_ACTIVED_BY_NUMBER = "XPATH=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String DYNAMIC_HEADER_TEXTBOX_BY_NAME = "XPATH=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String DYNAMIC_PAGE_INFO = "XPATH=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' " +
            "and text()='%s']/following-sibling::td[@data-key='males' " +
            "and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
    public static final String DYNAMIC_ACTION_BY_COUNTRY_NAME = "XPATH=//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[contains(@class,'%s')]";
    public static final String LOAD_DATA_BUTTON = "CSS=button#load";
    public static final String DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME = "XPATH=//th[text()='%s']/preceding-sibling::th";
    public static final String DYNAMIC_TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX = "XPATH=//tr[%s]//td[%s]/input";
    public static final String DYNAMIC_DROPDOWN_BY_ROW_INDEX_AND_COLUMN_INDEX = "XPATH=//tr[%s]//td[%s]//select";
    public static final String DYNAMIC_CHECKBOX_BY_ROW_INDEX_AND_COLUMN_INDEX = "XPATH=//tr[%s]//td[%s]//input";
    public static final String DYNAMIC_ACTION_BY_ROW_INDEX = "XPATH=//tr[%s]//td[contains(@id,'rowButton')]//button[contains(@title,'%s')]";

    public static final String ALL_PAGE = "XPATH=//li[@class='qgrd-pagination-page']/a";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME = "XPATH=//div[text()='%s']/ancestor::th/preceding-sibling::th";
    public static final String DYNAMIC_COLUMN_INDEX = "XPATH=//td[%s]";

    // Site JQuery upload file
    public static final String IS_FILE_LOADED = "XPATH=//p[@class='name' and text()='%s']";
    public static final String IS_FILE_UPLOADED = "XPATH=//p[@class='name']/a[text()='%s']";
    public static final String START_UPLOAD_BUTTON = "Css=table button.start";
}
