package appPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import utils.UserInterfaceInteractionHelper;

public class ProductDetailPage {

    @AndroidFindBy(id = "header")
    private MobileElement appHeader;

    @AndroidFindBy(id = "productTV")
    private MobileElement productTitleTextView;

    @AndroidFindBy(id = "productIV")
    private MobileElement productImageView;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Blue color\"]")
    private MobileElement blueColorSwitcherImageView;

    @AndroidFindBy(id = "noTV")
    private MobileElement qtyTextView;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private MobileElement increaseQtyImageView;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private MobileElement addToCartButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]")
    private MobileElement goToCartPageImageView;

    @AndroidFindBy(id = "cartTV")
    private MobileElement cartQtyTextView;

    public ProductDetailPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void waitForPageLoaded() {
        UserInterfaceInteractionHelper.waitUntil(webDriver ->
                (appHeader.isDisplayed() && productImageView.isDisplayed() && productTitleTextView.isDisplayed()));
    }

    public boolean isQtyEditorElementDisplayed() {
        return qtyTextView.isDisplayed();
    }

    public boolean isIncreaseQtyElementDisplayed() {
        return increaseQtyImageView.isDisplayed();
    }

    public boolean isBlueColorSwitcherElementDisplayed() {
        return blueColorSwitcherImageView.isDisplayed();
    }

    public boolean isAddToCartButtonElementDisplayed() {
        return addToCartButton.isDisplayed();
    }

    public void setProductColorToBlue() {
        blueColorSwitcherImageView.click();
    }

    public void setQty(Integer qty) {
        for (int i = 1; i < qty; i++) {
            increaseQtyImageView.click();
        }
    }

    public void verifyProductTitle(String desiredProductTitle) {
        if (!productTitleTextView.getText().equals(desiredProductTitle)) {
            Assert.fail("THe product title: " + desiredProductTitle + " is not available in this page!");
        }
    }

    public void goToCartPage() {
        goToCartPageImageView.click();
    }

    public void addProductToCart() {
        addToCartButton.click();
    }

    public void verifyCartQty() {
        if (!cartQtyTextView.isDisplayed()) {
            Assert.fail("The cart qty balloon is not available in this page!");
        }
    }
}
