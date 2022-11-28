package appPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.UserInterfaceInteractionHelper;

public class CheckoutCompletePage {

    @AndroidFindBy(id = "completeTV")
    private MobileElement checkoutCompleteTextView;

    public CheckoutCompletePage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void waitForPageLoaded() {
        UserInterfaceInteractionHelper.waitUntil(webDriver -> checkoutCompleteTextView.isDisplayed());
    }
}
