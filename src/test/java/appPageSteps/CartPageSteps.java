package appPageSteps;

import appPages.CartPage;
import io.cucumber.java8.En;
import org.junit.Assert;
import utils.AppiumWrapper;

public class CartPageSteps implements En {

    public CartPageSteps(StepsDataState stepsDataState) {
        Given("I have successfully landed in Cart page with the desired product already in there", () -> {
            CartPage cartPage = new CartPage(AppiumWrapper.getInstance().getDriver());
            cartPage.waitForPageLoaded();
            if (!cartPage.isCartPageNotEmpty()) {
                Assert.fail("The cart page is empty!");
            }


        });
    }
}
