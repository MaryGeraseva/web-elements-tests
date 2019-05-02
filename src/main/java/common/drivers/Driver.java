package common.drivers;

import org.openqa.selenium.WebDriver;

public class Driver {

    private static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();

    private Driver() {}

    public static WebDriver getDriver() {

        if (threadLocalWebDriver.get() == null) {
            threadLocalWebDriver.set(createWebDriver());
        }
        return threadLocalWebDriver.get();
    }

    public static void resetDriver() {
        threadLocalWebDriver.set(null);
    }

    private static WebDriver createWebDriver() {
        return new DriverFactory().createDriver();
    }

}
