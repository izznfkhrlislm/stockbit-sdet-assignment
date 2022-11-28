package appPageSteps;

import io.cucumber.java8.En;
import appPages.HomePage;
import utils.AppiumWrapper;
import utils.ExpectedConditionsHelper;
import utils.UserInterfaceInteractionHelper;

public class HomePageSteps implements En {

    public HomePageSteps(StepsDataState stepsDataState) {
        Given("I have successfully landed in Home Page, list of items has been showed", () -> {
            HomePage homePage = new HomePage(AppiumWrapper.getInstance().getDriver());
            homePage.waitUntilPageLoaded();
            homePage.waitForProductListLoaded();
        });

        When("I tap on a product card named: {string}", (String productName) -> {
            HomePage homePage = new HomePage(AppiumWrapper.getInstance().getDriver());
            homePage.waitUntilPageLoaded();
            homePage.waitForProductListLoaded();

            UserInterfaceInteractionHelper.scrollDownToCondition(ExpectedConditionsHelper.setCondition(webElement ->
                    homePage.isProductWithDesiredNameDisplayed(productName)));

            int productIndex = homePage.getProductNames().indexOf(productName);
            saveToStepsDataState(stepsDataState, homePage.getProductNames().get(productIndex));

            homePage.tapOnDesiredProductCard(productIndex);
        });
    }

    private void saveToStepsDataState(StepsDataState stepsDataState, String productName) {
        stepsDataState.setProductName(productName);
    }
}
