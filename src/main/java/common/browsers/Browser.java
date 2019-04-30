package common.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class Browser {

    public static String browser = System.getProperty("selenium.browser");
    public static String device = System.getProperty("selenium.deviceName");
    public static String server = System.getProperty("selenium.server");

    public static boolean browserHaveOptions (String browser) {
        return ((browser.contains("headless")) ||
                (browser.contains("mobile")));
    }

    public abstract WebDriver getDriver();
    public abstract DesiredCapabilities getCapabilities();
}
