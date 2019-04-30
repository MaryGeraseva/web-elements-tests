package common.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Firefox extends Browser {

    private String pathFirefoxDriver = String.format("%s\\drivers\\geckodriver.exe", System.getProperty("user.dir"));

    @Override
    public WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver", pathFirefoxDriver);
        WebDriver driver;
        if (browserHaveOptions(browser)) {
            driver = new FirefoxDriver(getBrowserOptions());
        } else {
            driver = new FirefoxDriver();
        }
        return driver;
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        if (browserHaveOptions(browser)) {
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getBrowserOptions());
        }
        return capabilities;
    }

    private FirefoxOptions getBrowserOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        firefoxOptions.setBinary(firefoxBinary);
        return firefoxOptions;
    }
}
