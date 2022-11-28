package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class UserInterfaceInteractionHelper {
    private static final long ONE_SEC = 1000;
    private static final int WAIT_ELEMENT_LOAD_TIMEOUT = 15;
    private static final int SCROLL_TIMEOUT_IN_SEC = 120;
    private static final double SCREEN_HEIGHT_MULTIPLIER = 0.3;
    private static final int SCROLL_DURATION = 1200;

    private UserInterfaceInteractionHelper() {

    }

    public static void goToPreviousPage() {
        getAppiumDriver().navigate().back();
    }

    public static AppiumDriver getAppiumDriver() {
        return AppiumWrapper.getInstance().getDriver();
    }

    public static Dimension getWindowSize() {
        return getAppiumDriver().manage().window().getSize();
    }

    public static void waitUntil(ExpectedCondition<?> desiredCondition) {
        waitUntil(desiredCondition, WAIT_ELEMENT_LOAD_TIMEOUT);
    }

    public static void waitUntil(ExpectedCondition<?> desiredCondition, int timeout) {
        WebDriverWait wait = new WebDriverWait(getAppiumDriver(), timeout);
        wait.ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(desiredCondition);
    }

    public static void scrollDownToCondition(ExpectedConditionsHelper conditionsHelper) {
        long scrollDownStartTimeInMillis = System.currentTimeMillis();

        while (conditionsHelper.apply(getAppiumDriver()) != Boolean.TRUE) {
            scrollDownThroughScreen();
            long elapsed = (System.currentTimeMillis() - scrollDownStartTimeInMillis) / ONE_SEC;
            if (elapsed > SCROLL_TIMEOUT_IN_SEC) {
                Assert.fail("The desired conditions not met while scrolling has already timed out.");
            }
        }
    }

    private static void scrollDownThroughScreen() {
        Dimension screenSize = getWindowSize();
        int xAxis = screenSize.getWidth() / 2;

        int scrollStartingPoint = (int) (screenSize.getHeight() * SCREEN_HEIGHT_MULTIPLIER);
        int scrollEndingPoint = 0;

        doScrolling(xAxis, scrollStartingPoint, xAxis, scrollEndingPoint);
    }

    private static void doScrolling(int xAxisStart, int yAxisStart, int xAxisEnd, int yAxisEnd) {
        TouchAction scrollTouchAction = new TouchAction(getAppiumDriver());
        Duration duration = Duration.ofMillis(SCROLL_DURATION);

        scrollTouchAction.press(PointOption.point(xAxisStart, yAxisStart))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(xAxisEnd, yAxisEnd))
                .release();

        scrollTouchAction.perform();
    }

    private static void waitForElementLoaded(By identifier) {
        WebDriverWait wait = new WebDriverWait(getAppiumDriver(), WAIT_ELEMENT_LOAD_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(identifier));
    }
}
