package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    // Hàm khởi tạo (Constructor Method)
    // Để map driver từ Test Class qua bên Page Object Class
    public LoginPageObject(WebDriver driver){
        // Trong TH 2 biến cùng tên, 1 biến là local, 1 biến global, nếu ko dùng từ khoá this thì nó sẽ luôn ưu tiên biến local
        this.driver = driver;
    }
    // Hàm này sẽ chạy đầu tiên khi Class này được gọi tới
    // Nếu ko viêt hàm khởi tạo thì trình biên dịch sẽ tạo ra cho Class này 1 hàm khởi tạo rỗng (Built-In)
    // Nếu viết thì nó sẽ dùng hàm do mình define (User Define)
    // Hàm khởi taọ sẽ cùng tên với tên Class chưá nó, và ko có gía trị trả về
    // Hàm khởi taọ có thể có 1 hoặc nhiều tham số, có 1 hoặc nhiều hàm khởi tạo
    // Thể hiện cho tính chất đa hình trong OOP
    public void enterToUserNameTextbox(String username) {
        waitElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, username);
    }

    public void enterToUserPasswordTextbox(String password) {
        waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public DashboardPageObject clickToLoginButton() {
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(DashboardPageObject.class,driver);
    }
}

