package pageUIs.jquery;

public class HomePageUI {
    public static final String DYNAMIC_PAGE_BY_NUMBER = "XPATH=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String DYNAMIC_PAGE_ACTIVED_BY_NUMBER = "XPATH=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String DYNAMIC_HEADER_TEXTBOX_BY_NAME = "XPATH=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String DYNAMIC_PAGE_INFO = "XPATH=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' " +
            "and text()='%s']/following-sibling::td[@data-key='males' " +
            "and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
}
