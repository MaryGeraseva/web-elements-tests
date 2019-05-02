package common.drivers;

import common.browsers.BrowserFactory;
import common.logger.LogInstance;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;


public class DriverFactory {

    private static WebDriver driver;
    private Logger log = LogInstance.getLogger();

    private String server = System.getProperty("selenium.server");
    private String serverURL = System.getProperty("server.url");

    public WebDriver createDriver() {
        if (Objects.equals(server,"grid")) {
            try {
                driver = new RemoteWebDriver(new URL(serverURL), new BrowserFactory().getBrowser().getCapabilities());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            log.info("started webDriver on selenium grid server");
            log.info("server URL: " + serverURL);
        } else {
            driver = new BrowserFactory().getBrowser().getDriver();
        }
        return driver;
    }
}
