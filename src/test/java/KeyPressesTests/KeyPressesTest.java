package KeyPressesTests;

import BaseTests.BaseTest;
import pages.KeyPressesPage.KeyPressesPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class KeyPressesTest extends BaseTest {

    @Parameters({"key"})
    @Test
    @Step("keyPressesTest started")
    @Description(value = "test checks keyboard emulation with keys")
    public void keyPressesTest(@Optional("Keys.ENTER") Keys key) {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        KeyPressesPage keyPressesPage = welcomePage.clickOnKeyPressesPageLink();
        checkURL(keyPressesPage.getUrl());
        keyPressesPage.sentKey(key);
        assertKeyPressesByText(keyPressesPage.getResultMessage(), keyPressesPage.getExpectedMessage(key), "didn't get expected  message");
    }

    @Parameters({"key"})
    @Test
    @Step("keyPressesTestWithAction started")
    @Description(value = "test checks keyboard emulation with action")
    public void keyPressesTestWithAction(@Optional("Keys.ENTER") Keys key) {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        KeyPressesPage keyPressesPage = welcomePage.clickOnKeyPressesPageLink();
        checkURL(keyPressesPage.getUrl());
        keyPressesPage.sendKeyWithAction(key);
        assertKeyPressesByText(keyPressesPage.getResultMessage(), keyPressesPage.getExpectedMessage(key), "didn't get expected  message");
    }

    @Step("verification keyboard emulation")
    private void assertKeyPressesByText(String resultMessage, String expectedMessage, String s) {
        Assert.assertEquals(resultMessage, expectedMessage, s);
    }
}
