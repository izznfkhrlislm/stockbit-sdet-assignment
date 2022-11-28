package appPageSteps;

import appPages.LoginPage;
import io.cucumber.java8.En;
import utils.AppiumWrapper;

public class LoginPageSteps implements En {

    private static final String USERNAME = "bod@example.com";
    private static final String PASSWORD = "10203040";

    public LoginPageSteps(StepsDataState stepsDataState) {
        Given("I have redirected to Login page", () -> {
            LoginPage loginPage = new LoginPage(AppiumWrapper.getInstance().getDriver());
            loginPage.waitForPageLoaded();
        });

        And("I filled the username and password form with stored credentials data", () -> {
            LoginPage loginPage = new LoginPage(AppiumWrapper.getInstance().getDriver());
            loginPage.waitForPageLoaded();

            stepsDataState.setUsername(USERNAME);
            stepsDataState.setPassword(PASSWORD);

            loginPage.fillLoginForm(USERNAME, PASSWORD);
        });

        And("I clicked on Login button", () -> {
            LoginPage loginPage = new LoginPage(AppiumWrapper.getInstance().getDriver());
            loginPage.clickLogin();
        });
    }
}
