package JsAlertsTests;

import BaseTests.BaseTest;
import Pages.JsAlertsPage.JavaScriptAlertsPage;
import Pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsAlertsDismissTests extends BaseTest {

    @Test
    @Step("jsConfirmDismissTest started")
    @Description(value = "test checks dismiss action in JS alert")
    public void jsConfirmDismissTest() {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        JavaScriptAlertsPage jsAlerts = welcomePage.clickOnAlertsPageLink();
        checkURL(jsAlerts.getUrl());
        jsAlerts.clickOnConfirmButton();
        jsAlerts.dismiss();
        assertConfirmDismissByExpectedMessage(jsAlerts);
    }

    @Step("verification alert dismiss by expected message")
    private void assertConfirmDismissByExpectedMessage(JavaScriptAlertsPage jsAlerts) {
        Assert.assertEquals(jsAlerts.getResultMessage(), jsAlerts.getExpectedConfirmDismissMessage(),"didn't get expected  message");
    }

    @Test
    @Step("jsPromptDismissTest started")
    @Description(value = "test checks dismiss action in JS alert")
    public void jsPromptDismissTest() {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        JavaScriptAlertsPage jsAlert = welcomePage.clickOnAlertsPageLink();
        checkURL(jsAlert.getUrl());
        jsAlert.clickOnPromptButton();
        jsAlert.dismiss();
        assertPromptDismissByExpectedMessage(jsAlert);
    }

    @Step("verification alert dismiss by expected message")
    private void assertPromptDismissByExpectedMessage(JavaScriptAlertsPage jsAlert) {
        Assert.assertEquals(jsAlert.getResultMessage(), jsAlert.getExpectedPromptDismissMessage(), "didn't get expected  message");
    }
}
