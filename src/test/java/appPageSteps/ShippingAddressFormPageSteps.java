package appPageSteps;

import appPages.ShippingAddressFormPage;
import io.cucumber.java.en.Given;
import io.cucumber.java8.En;
import utils.AppiumWrapper;

public class ShippingAddressFormPageSteps implements En {

    private static final String FULL_NAME = "Rebecca Winter";
    private static final String ADDRESS_LINE_1 = "Mandorley 112";
    private static final String ADDRESS_LINE_2 = "Entrance 1";
    private static final String CITY = "Truro";
    private static final String ZIP_CODE = "89750";
    private static final String STATE = "Cornwall";
    private static final String COUNTRY = "United Kingdom";

    public ShippingAddressFormPageSteps(StepsDataState stepsDataState) {
        Given("I have redirected to Shipping Address form page to complete my purchase", () -> {
            ShippingAddressFormPage page = new ShippingAddressFormPage(AppiumWrapper.getInstance().getDriver());
            page.waitForPageLoad();
        });

        And("I entered my personal data into the shipping form", () -> {
            ShippingAddressFormPage page = new ShippingAddressFormPage(AppiumWrapper.getInstance().getDriver());
            page.waitForPageLoad();

            page.fillShippingAddressForm(FULL_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, CITY, STATE, ZIP_CODE, COUNTRY);
        });

        And("I clicked to Payment button to proceed to payment page", () -> {
            ShippingAddressFormPage page = new ShippingAddressFormPage(AppiumWrapper.getInstance().getDriver());
            page.waitForPageLoad();

            page.clickProceedToPayment();
        });
    }
}
