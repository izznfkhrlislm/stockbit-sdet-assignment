package utils;

import io.cucumber.core.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class ScreenshotHelper {

    public static final String WORK_DIR = System.getProperty("user.dir");
    public static final String PNG_MIME_TYPE = "image/png";

    public static void takeScreenshot(Scenario gherkinScenario) throws WebDriverException {
        String fileName = gherkinScenario.getId().replace("/", "") + ".png";
        String location = WORK_DIR + "/screenshots/" + fileName;

        File screenshotFile = UserInterfaceInteractionHelper.getAppiumDriver().getScreenshotAs(OutputType.FILE);
        gherkinScenario.embed(convertToByteArray(screenshotFile), PNG_MIME_TYPE);

        try {
            FileUtils.copyFile(screenshotFile, new File(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] convertToByteArray(File screenshotFile) {
        try {
            InputStream inputStream = Files.newInputStream(screenshotFile.toPath());
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
