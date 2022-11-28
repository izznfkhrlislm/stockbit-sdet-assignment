package utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

public final class AppiumConfigManager {

    private static final String LOG_LEVEL = "APPIUM_LOG_LEVEL";
    private static final String DEVICE_NAME = "DEVICE_NAME";
    private static final String PLATFORM_VERSION = "PLATFORM_VERSION";
    private static final String PLATFORM_NAME = "PLATFORM_NAME";
    private static final String APP_PATH = "APP_PATH";
    private static final String APP_WAIT_ACTIVITY = "APP_WAIT_ACTIVITY";
    private static final String APP_WAIT_ACTIVITY_APPIUM_KEY = "appWaitActivity";
    private static final String AUTOMATOR = "AUTOMATOR";
    private static final int NEW_COMMAND_TIMEOUT = 300;

    private static AppiumConfigManager instance;
    private final Properties properties;

    private AppiumConfigManager() {
        properties = new Properties();
        try {
            loadPropertiesFile("testConfig.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AppiumConfigManager getInstance() {
        if (instance == null) {
            instance = new AppiumConfigManager();
        }

        return instance;
    }

    public String getLogLevel() {
        return getPropertiesValueByKey(LOG_LEVEL);
    }

    public DesiredCapabilities getAppiumDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.APP, getPropertiesValueByKey(APP_PATH));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getPropertiesValueByKey(DEVICE_NAME));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getPropertiesValueByKey(PLATFORM_NAME));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPlatformVersion());
        desiredCapabilities.setCapability(APP_WAIT_ACTIVITY_APPIUM_KEY, getPropertiesValueByKey(APP_WAIT_ACTIVITY));
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getPropertiesValueByKey(AUTOMATOR));
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, NEW_COMMAND_TIMEOUT);

        return desiredCapabilities;
    }

    private String getPlatformVersion() {
        return getPropertiesValueByKey(PLATFORM_VERSION);
    }

    private String getPropertiesValueByKey(String propertiesKey) {
        return properties.getProperty(propertiesKey);
    }

    private void loadPropertiesFile(String filePath) {
        try {
            properties.load(AppiumConfigManager.class.getClassLoader().getResourceAsStream(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
