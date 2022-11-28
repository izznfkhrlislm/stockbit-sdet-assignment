package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class AppiumWrapper {

    private static AppiumWrapper instance;

    private AppiumDriverLocalService appiumLocalService;

    private AppiumDriver driver;

    private AppiumWrapper() {

    }

    public static AppiumWrapper getInstance() {
        if (instance == null) {
            synchronized (AppiumWrapper.class) {
                instance = new AppiumWrapper();
            }
        }

        return instance;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void launchApp() {
        if (appiumLocalService == null) {
            startService();
        }

        if (driver == null) {
            startAppiumDriver();
        } else {
            driver.resetApp();
        }
    }

    public void restartAppiumLocalService() {
        System.out.println("Appium Local Service is restarting. Time executed: " + System.currentTimeMillis());

        closeAppiumDriver();
        stopService();
        startAppiumDriver();
        startService();
    }

    private void startService() {
        String logLevel = AppiumConfigManager.getInstance().getLogLevel();
        appiumLocalService = new AppiumServiceBuilder()
                .usingPort(4723)
                .withArgument(GeneralServerFlag.LOG_LEVEL, logLevel)
                .build();

        appiumLocalService.start();
    }

    private void stopService() {
        if (appiumLocalService != null) {
            try {
                appiumLocalService.stop();
            } catch (WebDriverException wde) {
                wde.printStackTrace();
            } finally {
                appiumLocalService = null;
            }
        }
    }

    private void startAppiumDriver() {
        DesiredCapabilities appiumDesiredCapabilities = AppiumConfigManager.getInstance().getAppiumDesiredCapabilities();
        driver = new AndroidDriver(appiumLocalService.getUrl(), appiumDesiredCapabilities);
    }

    private void closeAppiumDriver() {
        if (driver != null) {
            try {
                driver.close();
            } catch (WebDriverException wde) {
                wde.printStackTrace();
            } finally {
                driver = null;
            }
        }
    }
}
