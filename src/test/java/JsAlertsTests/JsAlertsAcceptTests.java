package JsAlertsTests;

import BaseTests.BaseTest;
import Pages.JsAlertsPage.JavaScriptAlertsPage;
import Pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JsAlertsAcceptTests extends BaseTest {

    @Test
    @Step("jsAlertTest started")
    @Description(value = "test checks interaction with JS alert")
    public void jsAlertTest() {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        JavaScriptAlertsPage jsAlerts = welcomePage.clickOnAlertsPageLink();
        checkURL(jsAlerts.getUrl());
        jsAlerts.clickOnAlertButton();
        jsAlerts.accept();
        assertAlertByExpectedMessage(jsAlerts);
    }

    @Step("verification alert accepting by expected message")
    private void assertAlertByExpectedMessage(JavaScriptAlertsPage jsAlerts) {
        Assert.assertEquals(jsAlerts.getResultMessage(), jsAlerts.getExpectedAlertMessage(), "didn't get expected  message");
    }

    @Test
    @Step("jsConfirmAcceptTest started")
    @Description(value = "test checks accept action in JS alert")
    public void jsConfirmAcceptTest() {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        JavaScriptAlertsPage jsAlerts = welcomePage.clickOnAlertsPageLink();
        checkURL(jsAlerts.getUrl());
        jsAlerts.clickOnConfirmButton();
        jsAlerts.accept();
        assertConfirmAcceptByExpectedMessage(jsAlerts);
    }

    @Step("verification alert accepting by expected message")
    private void assertConfirmAcceptByExpectedMessage(JavaScriptAlertsPage jsAlerts) {
        Assert.assertEquals(jsAlerts.getResultMessage(), jsAlerts.getExpectedConfirmAcceptMessage(), "didn't get expected  message");
    }

    @Parameters({"text"})
    @Test
    @Step("jsPromptAcceptTest started")
    @Description(value = "test checks accept action in JS alert")
    public void jsPromptAcceptTest(@Optional("test") String text) {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        JavaScriptAlertsPage jsAlerts = welcomePage.clickOnAlertsPageLink();
        checkURL(jsAlerts.getUrl());
        jsAlerts.clickOnPromptButton();
        jsAlerts.typeToAlertField(text);
        jsAlerts.accept();
        assertPromptAcceptByTypedText(text, jsAlerts);
    }

    @Step("verification alert accepting by expected message")
    private void assertPromptAcceptByTypedText(@Optional("test") String text, JavaScriptAlertsPage jsAlerts) {
        Assert.assertEquals(jsAlerts.getResultMessage(), jsAlerts.getExpectedPromptAcceptMessage(text));
    }
}
