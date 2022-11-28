package runners.uiautomation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resource/gherkinSyntax"},
        plugin = {"pretty", "json:target/UIAutomation.json", "junit:target/UIAutomation.xml"},
        glue = {"hooks", "appPageSteps", "runners.uiautomation"})
public class CucumberUIAutomationRunner {

}
