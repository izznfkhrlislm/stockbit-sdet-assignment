package appPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.UserInterfaceInteractionHelper;

public class LoginPage {

    @AndroidFindBy(id = "header")
    private MobileElement appHeader;

    @AndroidFindBy(id = "nameET")
    private MobileElement userNameTextBox;

    @AndroidFindBy(id = "passwordET")
    private MobileElement passwordTextBox;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to login with given credentials\"]")
    private MobileElement loginButton;

    public LoginPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void waitForPageLoaded() {
        UserInterfaceInteractionHelper.waitUntil(webDriver ->
                appHeader.isDisplayed() && userNameTextBox.isDisplayed() && passwordTextBox.isDisplayed());
    }

    public void fillLoginForm(String username, String password) {
        userNameTextBox.clear();
        userNameTextBox.sendKeys(username);

        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}
