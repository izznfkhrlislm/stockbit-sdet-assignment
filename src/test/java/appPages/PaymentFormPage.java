package appPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.UserInterfaceInteractionHelper;

public class PaymentFormPage {

    @AndroidFindBy(id = "header")
    private MobileElement appHeader;

    @AndroidFindBy(id = "enterPaymentMethodTV")
    private MobileElement paymentTitleTextView;

    @AndroidFindBy(id = "nameET")
    private MobileElement fullNameTextBox;

    @AndroidFindBy(id = "cardNumberET")
    private MobileElement cardNumberTextBox;

    @AndroidFindBy(id = "expirationDateET")
    private MobileElement cardExpirationDateTextBox;

    @AndroidFindBy(id = "securityCodeET")
    private MobileElement securityCodeTextBox;

    @AndroidFindBy(id = "paymentBtn")
    private MobileElement reviewOrderButton;

    public PaymentFormPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void waitForPageLoad() {
        UserInterfaceInteractionHelper.waitUntil(webDriver ->
                appHeader.isDisplayed() && paymentTitleTextView.isDisplayed() && reviewOrderButton.isDisplayed());
    }

    public void fillPaymentInformationForm(String fullName, String cardNumber, String expirationDate, String securityCode) {
        fullNameTextBox.clear();
        fullNameTextBox.sendKeys(fullName);

        cardNumberTextBox.clear();
        cardNumberTextBox.sendKeys(cardNumber);

        cardExpirationDateTextBox.clear();
        cardExpirationDateTextBox.sendKeys(expirationDate);

        securityCodeTextBox.clear();
        securityCodeTextBox.sendKeys(securityCode);
    }

    public void clickReviewOrder() {
        reviewOrderButton.click();
    }
}
