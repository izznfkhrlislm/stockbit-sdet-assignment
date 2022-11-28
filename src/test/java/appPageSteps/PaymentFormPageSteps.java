package appPageSteps;

import appPages.PaymentFormPage;
import io.cucumber.java.en.Given;
import io.cucumber.java8.En;
import utils.AppiumWrapper;

public class PaymentFormPageSteps implements En {

    private static final String USER_FULL_NAME = "Rebecca Winter";
    private static final String CARD_NUMBER = "3258125675687891";
    private static final String EXPIRATION_DATE = "0223";
    private static final String SECURITY_CODE = "456";

    public PaymentFormPageSteps(StepsDataState stepsDataState) {
        Given("I have redirected to Payment page", () -> {
            PaymentFormPage paymentFormPage = new PaymentFormPage(AppiumWrapper.getInstance().getDriver());
            paymentFormPage.waitForPageLoad();
        });

        And("I filled the payment information with stored credentials data", () -> {
            PaymentFormPage paymentFormPage = new PaymentFormPage(AppiumWrapper.getInstance().getDriver());
            paymentFormPage.waitForPageLoad();

            paymentFormPage.fillPaymentInformationForm(USER_FULL_NAME, CARD_NUMBER, EXPIRATION_DATE, SECURITY_CODE);
        });

        And("I clicked on Review Order button", () -> {
            PaymentFormPage paymentFormPage = new PaymentFormPage(AppiumWrapper.getInstance().getDriver());
            paymentFormPage.clickReviewOrder();
        });
    }
}
