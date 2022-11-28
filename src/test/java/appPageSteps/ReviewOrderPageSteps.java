package appPageSteps;

import appPages.CheckoutCompletePage;
import appPages.ReviewOrderPage;
import io.cucumber.java8.En;
import org.junit.Assert;
import utils.AppiumWrapper;
import utils.ExpectedConditionsHelper;
import utils.UserInterfaceInteractionHelper;

public class ReviewOrderPageSteps implements En {

    public ReviewOrderPageSteps(StepsDataState stepsDataState) {
        Given("I have redirected to Review Order page", () -> {
            ReviewOrderPage page = new ReviewOrderPage(AppiumWrapper.getInstance().getDriver());
            page.waitForPageLoaded();
        });

        And("I have checked my delivery address information is correct", () -> {
            ReviewOrderPage page = new ReviewOrderPage(AppiumWrapper.getInstance().getDriver());
            page.waitForPageLoaded();

            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                    page.getDeliveryAddressSection().isDisplayed()));

            String formattedDeliveryCity = stepsDataState.getCity() + ", " + stepsDataState.getState();
            String formattedDeliveryCountry = stepsDataState.getCountry() + ", " + stepsDataState.getZip();

            Assert.assertTrue(page.getDeliveryFullNameTextView().getText().equals(stepsDataState.getUserFullName()));
            Assert.assertTrue(page.getDeliveryAddressTextView().getText().equals(stepsDataState.getAddressLine1()));
            Assert.assertTrue(page.getDeliveryCityTextView().getText().equals(formattedDeliveryCity));
            Assert.assertTrue(page.getDeliveryCountryTextView().getText().equals(formattedDeliveryCountry));
        });

        And("I have checked my billing information is correct", () -> {
            ReviewOrderPage page = new ReviewOrderPage(AppiumWrapper.getInstance().getDriver());
            page.waitForPageLoaded();

            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                    page.getPaymentCardExpirationDateTextView().isDisplayed()));

            String formattedCartExpiryDate = "Exp: " + stepsDataState.getExpirationDate().substring(0,2) + "/"
                    + stepsDataState.getExpirationDate().substring(2,4);

            Assert.assertTrue(page.getPaymentFullNameTextView().getText().equals(stepsDataState.getUserFullName()));
            Assert.assertTrue(page.getPaymentCardNumberTextView().getText().equals(stepsDataState.getCardNumber()));
            Assert.assertTrue(page.getPaymentCardExpirationDateTextView().getText().equals(formattedCartExpiryDate));
        });

        And("I have checked my total qty of shopping is correct", () -> {
            ReviewOrderPage page = new ReviewOrderPage(AppiumWrapper.getInstance().getDriver());
            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                    page.getTotalQtyTextView().isDisplayed()));

            String formattedTotalQty = stepsDataState.getProductQty().toString() + " Items";
            Assert.assertTrue(page.getTotalQtyTextView().getText().equals(formattedTotalQty));
        });

        And("I clicked on Place Order button", () -> {
            ReviewOrderPage page = new ReviewOrderPage(AppiumWrapper.getInstance().getDriver());
            page.clickPlaceOrder();
        });

        Then("I should see the Checkout Complete message as the sign of purchase successfully made", () -> {
            CheckoutCompletePage page = new CheckoutCompletePage(AppiumWrapper.getInstance().getDriver());
            page.waitForPageLoaded();
        });
    }
}
