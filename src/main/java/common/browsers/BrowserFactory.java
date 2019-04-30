package common.browsers;

import common.LogInstance;
import org.apache.log4j.Logger;

public class BrowserFactory {

    private Logger log = LogInstance.getLogger();
    private Browser browser;

    public BrowserFactory(){
        String propertyBrowser = System.getProperty("selenium.browser");
        if (propertyBrowser == null) {
            Browser.browser = BrowserTypes.CHROME;
            browser = new Chrome();
            log.info("didn't get browser type, started default browser chrome");
        } else if (propertyBrowser.contains("firefox")) {
            browser = new Firefox();
        } else if (propertyBrowser.contains("chrome")) {
            browser = new Chrome();
        }
        log.info("created webDriver: " + Browser.browser);
    }

    public Browser getBrowser() {
        return browser;
    }
}
