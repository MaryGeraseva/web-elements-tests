package IFrameTests;

import BaseTests.BaseTest;
import pages.IFramePage.IFramePage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IFrameTest extends BaseTest {

    @Test
    @Step("iFrameDefaultTest started")
    @Description("test checks interaction with text editor which contains in iFrame")
    public void iFrameDefaultTest() {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        welcomePage.scrollToBottom();
        IFramePage iFramePage = welcomePage.clickOnIFramePageLink();
        checkURL(iFramePage.getUrl());
        iFramePage.swishToIFrame();
        assertSwishingIFrame(iFramePage);
    }

    @Step("verification swishing to iFrame by editor default text")
    private void assertSwishingIFrame(IFramePage iFramePage) {
        Assert.assertEquals(iFramePage.getExpectedDefaultText(), iFramePage.getCurrentText(), "didn't get expected text");
    }

    @Parameters({"inputText"})
    @Test
    @Step("iFrameInputTest started")
    @Description("test checks interaction with inputText editor which contains in iFrame")
    public void iFrameInputTest(@Optional("test") String inputText) {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        IFramePage iFramePage = welcomePage.clickOnIFramePageLink();
        checkURL(iFramePage.getUrl());
        iFramePage.swishToIFrame();
        iFramePage.clearEditor();
        iFramePage.typeInEditor(inputText);
        assertTypeInEditor(inputText, iFramePage);
    }

    @Step("verification interaction with text editor in iFrame window by typed text")
    private void assertTypeInEditor(String inputText, IFramePage iFramePage) {
        Assert.assertEquals(inputText, iFramePage.getCurrentText(), "didn't get expected text");
    }
}
