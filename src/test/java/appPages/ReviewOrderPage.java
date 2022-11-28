package appPages;

import appPageSteps.StepsDataState;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.UserInterfaceInteractionHelper;

public class ReviewOrderPage {

    @AndroidFindBy(id = "header")
    private MobileElement appHeader;

    @AndroidFindBy(id = "checkoutTitleTV")
    private MobileElement checkoutTitleTextView;

    @AndroidFindBy(id = "addressLL")
    private MobileElement deliveryAddressSection;

    @AndroidFindBy(id = "billingLL")
    private MobileElement billingSection;

    @AndroidFindBy(id = "fullNameTV")
    private MobileElement deliveryFullNameTextView;

    @AndroidFindBy(id = "addressTV")
    private MobileElement deliveryAddressTextView;

    @AndroidFindBy(id = "cityTV")
    private MobileElement deliveryCityTextView;

    @AndroidFindBy(id = "countryTV")
    private MobileElement deliveryCountryTextView;

    @AndroidFindBy(id = "cardHolderTV")
    private MobileElement paymentFullNameTextView;

    @AndroidFindBy(id = "cardNumberTV")
    private MobileElement paymentCardNumberTextView;

    @AndroidFindBy(id = "expirationDateTV")
    private MobileElement paymentCardExpirationDateTextView;

    @AndroidFindBy(id = "itemNumberTV")
    private MobileElement totalQtyTextView;

    @AndroidFindBy(id = "paymentBtn")
    private MobileElement placeOrderButton;

    public ReviewOrderPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public MobileElement getDeliveryAddressSection() {
        return deliveryAddressSection;
    }

    public MobileElement getBillingSection() {
        return billingSection;
    }

    public MobileElement getDeliveryFullNameTextView() {
        return deliveryFullNameTextView;
    }

    public MobileElement getDeliveryAddressTextView() {
        return deliveryAddressTextView;
    }

    public MobileElement getDeliveryCityTextView() {
        return deliveryCityTextView;
    }

    public MobileElement getDeliveryCountryTextView() {
        return deliveryCountryTextView;
    }

    public MobileElement getPaymentFullNameTextView() {
        return paymentFullNameTextView;
    }

    public MobileElement getPaymentCardNumberTextView() {
        return paymentCardNumberTextView;
    }

    public MobileElement getPaymentCardExpirationDateTextView() {
        return paymentCardExpirationDateTextView;
    }

    public MobileElement getTotalQtyTextView() {
        return totalQtyTextView;
    }

    public void waitForPageLoaded() {
        UserInterfaceInteractionHelper.waitUntil(webDriver ->
                appHeader.isDisplayed() && checkoutTitleTextView.isDisplayed()
                        && deliveryAddressSection.isDisplayed() && billingSection.isDisplayed());
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }
}
