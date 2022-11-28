package appPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import utils.UserInterfaceInteractionHelper;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    @AndroidFindBy(id = "titleTV")
    private List<MobileElement> productNameTextView;

    @AndroidFindBy(id = "productIV")
    private List<MobileElement> productNameImageView;

    @AndroidFindBy(id = "header")
    private MobileElement appHeader;

    public HomePage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public List<String> getProductNames() {
        return productNameTextView.stream().map(RemoteWebElement::getText).collect(Collectors.toList());
    }

    public void waitForProductListLoaded() {
        UserInterfaceInteractionHelper.waitUntil(webDriver ->
                (!productNameImageView.isEmpty() && !productNameTextView.isEmpty()));
    }

    public boolean isProductWithDesiredNameDisplayed(String desiredProductName) {
        List<String> productNames = getProductNames();
        if (productNames.contains(desiredProductName)) {
            return productNameTextView.
                    get(productNames.indexOf(desiredProductName)).
                    isDisplayed();
        }

        return false;
    }

    public void tapOnDesiredProductCard(int productIndex) {
        productNameImageView.get(productIndex).click();
    }

    public void waitUntilPageLoaded() {
        UserInterfaceInteractionHelper.waitUntil(webDriver -> appHeader.isDisplayed());
    }
}
