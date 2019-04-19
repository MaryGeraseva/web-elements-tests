package JsErrorTests;

import BaseTests.BaseTest;
import pages.JSErrorPage.JSErrorPage;
import pages.Welcomepage.WelcomePage;
import common.TestUtilities;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class JsErrorTest extends BaseTest {

    private TestUtilities utilities = new TestUtilities();

    @Test
    @Step("jsErrorTest started")
    @Description(value = "test checks JS errors on the page")
    public void jsErrorTest() {
        SoftAssert softAssert = new SoftAssert();
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        JSErrorPage jsErrorPage = welcomePage.clickOnJSErrorPageLink();
        checkURL(jsErrorPage.getUrl());
        checkJsError(softAssert);
    }

    @Step("check JS errors on the page")
    private void checkJsError(SoftAssert softAssert) {
        List<LogEntry> logs = utilities.getBrowserLogs();
        for (LogEntry logEntry : logs) {
            if(logEntry.getLevel().toString().equals("SEVERE")) {
                softAssert.fail("severe error: " + logEntry.getMessage());
            }
        }
        softAssert.assertAll();
    }
}
