package appPageSteps;

import appPages.CartPage;
import io.cucumber.java8.En;
import org.junit.Assert;
import utils.AppiumWrapper;
import utils.ExpectedConditionsHelper;
import utils.UserInterfaceInteractionHelper;

public class CartPageSteps implements En {

    public CartPageSteps(StepsDataState stepsDataState) {
        Given("I have successfully landed in Cart page with the desired product already in there", () -> {
            CartPage cartPage = new CartPage(AppiumWrapper.getInstance().getDriver());
            cartPage.waitForPageLoaded();
            if (!cartPage.isCartPageNotEmpty()) {
                Assert.fail("The cart page is empty!");
            }

            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                    cartPage.isProductWithDesiredNameDisplayed(stepsDataState.getProductName())));
        });

        When("I see that the product information is already correct", () -> {
            CartPage cartPage = new CartPage(AppiumWrapper.getInstance().getDriver());
            cartPage.waitForPageLoaded();
            if (!cartPage.isCartPageNotEmpty()) {
                Assert.fail("The cart page is empty!");
            }

            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                    cartPage.isProductWithDesiredNameDisplayed(stepsDataState.getProductName())));
            int productIndex = cartPage.getProductNames().indexOf(stepsDataState.getProductName());
            Assert.assertTrue(cartPage.getProductNames().get(productIndex).equals(stepsDataState.getProductName()));
        });

        And("I click Proceed to Checkout button", () -> {
            CartPage cartPage = new CartPage(AppiumWrapper.getInstance().getDriver());
            cartPage.waitForPageLoaded();

            cartPage.goToCheckoutPage();
        });
    }
}
