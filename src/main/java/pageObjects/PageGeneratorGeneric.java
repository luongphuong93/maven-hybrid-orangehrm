package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGeneratorGeneric {

    public static <T extends BasePage> T getPage(Class<T> pageClass, WebDriver driver){
        try{
            // lấy constructor nhận Driver
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            // tạo instance mới của page class
            return constructor.newInstance(driver);
        }catch (Exception e){
            throw new RuntimeException("Can not init Page Object class: " + pageClass.getSimpleName(), e);
        }
    }
}
