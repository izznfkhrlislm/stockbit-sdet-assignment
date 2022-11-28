package appPageSteps;

import appPages.ProductDetailPage;
import io.cucumber.java8.En;
import utils.AppiumWrapper;
import utils.ExpectedConditionsHelper;
import utils.UserInterfaceInteractionHelper;

public class ProductDetailPageSteps implements En {

    private static final String COLOR_BLUE = "Blue";

    public ProductDetailPageSteps(StepsDataState stepsDataState) {
        Given("I have successfully landed in Product Detail page with the desired product name", () -> {
            ProductDetailPage productDetailPage = new ProductDetailPage(AppiumWrapper.getInstance().getDriver());
            productDetailPage.waitForPageLoaded();
            productDetailPage.verifyProductTitle(stepsDataState.getProductName());
        });

        When("I set the product quantity to: {int}", (Integer productQuantity) -> {
            ProductDetailPage productDetailPage = new ProductDetailPage(AppiumWrapper.getInstance().getDriver());
            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                    productDetailPage.isQtyEditorElementDisplayed() && productDetailPage.isIncreaseQtyElementDisplayed()));

            stepsDataState.setProductQty(productQuantity);
            productDetailPage.setQty(productQuantity);
        });

        And("I set the product color to: {string}", (String color) -> {
            ProductDetailPage productDetailPage = new ProductDetailPage(AppiumWrapper.getInstance().getDriver());
            if (color.equals(COLOR_BLUE)) {
                UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                        productDetailPage.isBlueColorSwitcherElementDisplayed()));
                stepsDataState.setColor(color);
                productDetailPage.setProductColorToBlue();
            }
        });

        And("I click Add to Cart button", () -> {
            ProductDetailPage productDetailPage = new ProductDetailPage(AppiumWrapper.getInstance().getDriver());
            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webDriver ->
                    productDetailPage.isAddToCartButtonElementDisplayed()));
            productDetailPage.addProductToCart();
        });

        Then("I should see the cart icon has a quantity balloon", () -> {
            ProductDetailPage productDetailPage = new ProductDetailPage(AppiumWrapper.getInstance().getDriver());
            productDetailPage.verifyCartQty();
        });

        And("I click the Cart Icon", () -> {
            ProductDetailPage productDetailPage = new ProductDetailPage(AppiumWrapper.getInstance().getDriver());
            productDetailPage.goToCartPage();
        });
    }
}
