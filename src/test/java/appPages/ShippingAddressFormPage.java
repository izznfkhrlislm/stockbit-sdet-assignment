package appPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.UserInterfaceInteractionHelper;

public class ShippingAddressFormPage {

    @AndroidFindBy(id = "header")
    private MobileElement appHeader;

    @AndroidFindBy(id = "checkoutTitleTV")
    private MobileElement checkoutTitleTextView;

    @AndroidFindBy(id = "fullNameET")
    private MobileElement fullNameTextBox;

    @AndroidFindBy(id = "address1ET")
    private MobileElement addressLine1TextBox;

    @AndroidFindBy(id = "address2ET")
    private MobileElement addressLine2TextBox;

    @AndroidFindBy(id = "cityET")
    private MobileElement cityTextBox;

    @AndroidFindBy(id = "stateET")
    private MobileElement stateTextBox;

    @AndroidFindBy(id = "zipET")
    private MobileElement zipCodeTextBox;

    @AndroidFindBy(id = "countryET")
    private MobileElement countryTextBox;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Saves user info for checkout\"]")
    private MobileElement proceedToPaymentButton;

    public ShippingAddressFormPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void waitForPageLoad() {
        UserInterfaceInteractionHelper.waitUntil(webDriver ->
                appHeader.isDisplayed() && checkoutTitleTextView.isDisplayed() && proceedToPaymentButton.isDisplayed());
    }

    public void fillShippingAddressForm(String fullName, String addressLine1, String addressLine2, String city,
                                        String state, String zip, String country) {

        fullNameTextBox.clear();
        fullNameTextBox.sendKeys(fullName);

        addressLine1TextBox.clear();
        addressLine1TextBox.sendKeys(addressLine1);

        addressLine2TextBox.clear();
        addressLine2TextBox.sendKeys(addressLine2);

        cityTextBox.clear();
        cityTextBox.sendKeys(city);

        stateTextBox.clear();
        stateTextBox.sendKeys(state);

        zipCodeTextBox.clear();
        zipCodeTextBox.sendKeys(zip);

        countryTextBox.clear();
        countryTextBox.sendKeys(country);
    }

    public void clickProceedToPayment() {
        proceedToPaymentButton.click();
    }
}
