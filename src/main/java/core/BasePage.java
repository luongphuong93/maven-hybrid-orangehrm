package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // Đúng nguyên tắc thì mình phải để là protected,
    // nhưng để tạm public để demo bài hôm nay

    // đây là hàm static có nhiệm vụ lấy ra chính instance của class này
    // 1 biến static/ hàm static có thể gọi ra trực tiếp từ phạm vi clas
    public static BasePage getInstance(){
        return new BasePage();
    }

    public void openPageURL(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getPageURL(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

    private Alert waitToAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver){
        waitToAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver){
        waitToAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend){
        waitToAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver){
        return waitToAlertPresence(driver).getText();
    }

    public void sleepInSecond(int timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentWindowID(WebDriver driver){
        return driver.getWindowHandle();
    }

    public void openURLByNewTAB(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    public void openURLByNewWindow(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            driver.switchTo().window(window);
            String pageTitle = driver.getTitle();
            if (pageTitle.equals(title)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByContainTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(title)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void closeAllExceptMain(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    private WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver,locator).clear();
        getWebElement(driver,locator).sendKeys(valueToSend);
    }

    public void selectItemDropdown(WebDriver driver, String locator, String textItem){
        new Select(getWebElement(driver, locator)).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem) {
        clickToElement(driver,parentLocator);
        sleepInSecond(1);

        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

        List<WebElement> allItems = getListWebElement(driver,childLocator);

        for (WebElement item: allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).isMultiple();
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaByRGBA(String rgbaValue){
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String locator){
        return getListWebElement(driver,locator).size();
    }

    public void checkToCheckBoxRadio(WebDriver driver, String locator){
        if(!isElementSelected(driver, locator)){
            getWebElement(driver,locator).click();
        }
    }

    public void uncheckToCheckBox(WebDriver driver, String locator){
        if(isElementSelected(driver, locator)){
            getWebElement(driver,locator).click();
        }
    }

    public boolean isElementDisplay(WebDriver driver, String locator){
        return getWebElement(driver,locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver,locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver,locator).isEnabled();
    }

    public WebDriver switchToIframe(WebDriver driver, String locator){
        return driver.switchTo().frame(getWebElement(driver,locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver){
        return driver.switchTo().defaultContent();
    }

    public void moveToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void doubleClick(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void righClick(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver,sourceLocator),
                getWebElement(driver,targetLocator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator){
        new Actions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver, locator),key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript){
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getDomain(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.domain;");
    }

    public void scrollToBottomPage(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("windown.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = getElementDOMAttribute(driver,locator,"style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
        sleepInSecond(1);
    }

    public void scrollToElementToDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }


    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).
                executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined'" +
                        " && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    // OrangeHRM
    public boolean isLoadingSpinnerDisappear(WebDriver driver){
        return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

    // OpenCart
    public UserHomePO clickToLogoutLinkAtUserSite(WebDriver driver) {
        waitElementClickable(driver,BasePageUI.USER_MY_ACCOUNT_HEADER);
        clickToElement(driver,BasePageUI.USER_MY_ACCOUNT_HEADER);

        waitElementClickable(driver,BasePageUI.USER_LOGOUT_LINK_ITEM);
        clickToElement(driver,BasePageUI.USER_LOGOUT_LINK_ITEM);

        return PageGenerator.getPage(UserHomePO.class,driver);
    }

    public AdminLoginPO clickToLogoutLinkAtAdminSite(WebDriver driver) {
        waitElementClickable(driver,BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        clickToElement(driver,BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(AdminLoginPO.class,driver);
    }

    // Bởi vì khi mở trang admin site thì tuỳ thuộc vào tiền điều kiện trước đó sẽ mở ra các trang khác nhau nên hàm này để kiểu void,
    // và khi trả về trang nào thì sẽ khởi tạo trang đó tại class test
    // Ví dụ chưa login thì trả về trang login, còn nếu trước đó đã login rồi thì phải trả về trang Dashboard mới đúng
    public void openAdminSite(WebDriver driver, String adminURL) {
        openPageURL(driver, adminURL);
    }

    // Còn với trang user thì khi mở ra sẽ luôn là mở ra trang homepage nên trả về của hàm này để luôn là UserHomePO
    public UserHomePO openUserSite(WebDriver driver, String userURL) {
        openPageURL(driver, userURL);
        return PageGenerator.getPage(UserHomePO.class,driver);
    }

    public UserHomePO openHomeLogo(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.USER_HOMEPAGE_LOGO);
        clickToElement(driver,BasePageUI.USER_HOMEPAGE_LOGO);
        return PageGenerator.getPage(UserHomePO.class,driver);
    }
    private final int LONG_TIMEOUT = 30;
    private final int SHORT_TIMEOUT = 10;
}