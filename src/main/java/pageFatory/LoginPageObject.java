package pageFatory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    // define locator/ element
    // Nhớ là phải khai báo element trước, rồi mới đến dòng code gán Annotation sau

    //cách 1:
    @CacheLookup
    @FindBy(how = How.CSS, using = "input[name='username']")
    private WebElement usernameTextbox;

    //cách 2:
    @CacheLookup
    @FindBy(name="password")
    private WebElement passwordTextbox;

    //cách 3:
    @CacheLookup
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    // bắt buộc locator/ element là kiểu dữ liệu WebElement
    // Sự thay đổi về common Class BasePage
    public void enterToUserNameTextbox(String username) {
        waitElementVisible(driver,usernameTextbox);
        sendKeyToElement(usernameTextbox,username);
    }

    public void enterToUserPasswordTextbox(String password) {
        waitElementVisible(driver,passwordTextbox);
        sendKeyToElement(passwordTextbox,password);
    }

    public void clickToLoginButton() {
        waitElementClickable(driver,loginButton);
        clickToElement(loginButton);
    }
}

