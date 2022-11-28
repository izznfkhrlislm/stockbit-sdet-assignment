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

public class CartPage {

    @AndroidFindBy(id = "header")
    private MobileElement appHeader;

    @AndroidFindBy(id = "productTV")
    private MobileElement cartPageTitleTextView;

    @AndroidFindBy(id = "productRV")
    private MobileElement productsRecyclerView;

    @AndroidFindBy(id = "titleTV")
    private List<MobileElement> productTitleTextView;

    @AndroidFindBy(id = "productIV")
    private List<MobileElement> productImageView;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Confirms products for checkout\"]")
    private MobileElement goToCheckoutButton;

    public CartPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void waitForPageLoaded() {
        UserInterfaceInteractionHelper.waitUntil(webDriver ->
                appHeader.isDisplayed() && cartPageTitleTextView.isDisplayed());
    }

    public boolean isCartPageNotEmpty() {
        return productsRecyclerView.isDisplayed() && !productTitleTextView.isEmpty() && !productImageView.isEmpty();
    }

    public List<String> getProductNames() {
        return productTitleTextView.stream().map(RemoteWebElement::getText).collect(Collectors.toList());
    }

    public boolean isGoToCheckoutButtonElementDisplayed() {
        return goToCheckoutButton.isDisplayed();
    }

    public void goToCheckoutPage() {
        goToCheckoutButton.click();
    }
}
