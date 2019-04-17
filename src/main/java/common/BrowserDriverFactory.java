package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class BrowserDriverFactory {

    private Logger log = LogInstance.getLogger();
    private WebDriver driver;

    public WebDriver createBrowser() {

        String browser = getBrowserType();
        String device = System.getProperty("selenium.deviceName");
        String server = System.getProperty("selenium.server");
        String serverURL = System.getProperty("server.url");


        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        String pathFirefox = String.format("%s\\drivers\\geckodriver.exe", System.getProperty("user.dir"));
        String pathChromeDriver = String.format("%s\\drivers\\chromedriver.exe", System.getProperty("user.dir"));

        log.info("creating webDriver: " + browser);

        switch (browser) {
            case(BrowserTypes.GOOGLECHROME_HEADLESS):
                chromeOptions.addArguments("--headless");
                break;

            case(BrowserTypes.FIREFOX_HEADLESS):
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                firefoxOptions.setBinary(firefoxBinary);
                break;

            case(BrowserTypes.GOOGLECHROME_MOBILE):
                String deviceName = (device != null) ? device : MobileDeviceName.PIXEL_2;
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", deviceName);
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                log.info("started googlechrome browser with " + deviceName + " emulation");
                break;
        }

        if (Objects.equals(server,"grid")) {
            DesiredCapabilities capabilities;
            switch (browser) {
                case (BrowserTypes.GOOGLECHROME):
                    capabilities = DesiredCapabilities.chrome();
                    break;
                case (BrowserTypes.GOOGLECHROME_HEADLESS):
                case (BrowserTypes.GOOGLECHROME_MOBILE):
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    break;
                case (BrowserTypes.FIREFOX):
                    capabilities = DesiredCapabilities.firefox();
                    break;
                case (BrowserTypes.FIREFOX_HEADLESS):
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                    break;
                default:
                    capabilities = DesiredCapabilities.chrome();
            }

            try {
                driver = new RemoteWebDriver(new URL(serverURL), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                log.error("MalformedURLException exception");
            }

            log.info("started webDriver on selenium grid server");
            log.info("server URL: " + serverURL);

        } else {
            switch (browser) {
                case (BrowserTypes.GOOGLECHROME):
                    System.setProperty("webdriver.chrome.driver", pathChromeDriver);
                    driver = new ChromeDriver();
                    break;
                case (BrowserTypes.GOOGLECHROME_HEADLESS):
                case (BrowserTypes.GOOGLECHROME_MOBILE):
                    System.setProperty("webdriver.chrome.driver", pathChromeDriver);
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case (BrowserTypes.FIREFOX):
                    System.setProperty("webdriver.gecko.driver", pathFirefox);
                    driver = new FirefoxDriver();
                    break;
                case (BrowserTypes.FIREFOX_HEADLESS):
                    System.setProperty("webdriver.gecko.driver", pathFirefox);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
            }
        }
        return driver;
    }

    private String getBrowserType() {
        String browser;
        if(System.getProperty("selenium.browser") != null) {
            browser = System.getProperty("selenium.browser");
        } else {
            browser = BrowserTypes.GOOGLECHROME;
            log.info("didn't get browser type, started default browser googlechrome");
        }
        return browser;
    }
}


