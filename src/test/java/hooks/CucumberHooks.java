package hooks;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriverException;
import utils.AppiumWrapper;
import utils.ScreenshotHelper;

public class CucumberHooks {

    @Before
    public void scenarioSetUp(Scenario gherkinScenario) {
        AppiumWrapper.getInstance().launchApp();
        System.out.println("Scenario " + gherkinScenario.getName() + " has been started. Execution time: " + System.currentTimeMillis());
    }

    @After
    public void scenarioTearDown(Scenario gherkinScenario) {
        System.out.println("Scenario " + gherkinScenario.getName() + " has ended. Execution time: " + System.currentTimeMillis());
        if (gherkinScenario.isFailed()) {
            try {
                ScreenshotHelper.takeScreenshot(gherkinScenario);
            } catch (WebDriverException e) {
                AppiumWrapper.getInstance().restartAppiumLocalService();
            }
        }
    }
}
