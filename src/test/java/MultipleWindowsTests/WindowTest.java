package MultipleWindowsTests;

import BaseTests.BaseTest;
import pages.MultipluWindowsPage.FirstWindowPage;
import pages.MultipluWindowsPage.SecondWindowPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowTest extends BaseTest {

    @Test
    @Step("multipleWindowsTest started")
    @Description(value = "test checks interaction with multiple windows")
    public void multipleWindowsTest() {
    WelcomePage welcomePage = new WelcomePage();
    welcomePage.openWelcomePage();
    FirstWindowPage firstWindow = welcomePage.clickOnMultiWindowPageLink();
    checkURL(firstWindow.getUrl());
    String firstWindowHeader = firstWindow.getWindowHeader();
    log.info("got first window header: " + firstWindowHeader);
    String firstWindowContent = firstWindow.getContent();
    log.info("got first window content");
    firstWindow.clickOnNewPageLink();
    SecondWindowPage secondWindow = firstWindow.switchToNewWindow(firstWindowHeader);
    String secondWindowContent = secondWindow.getContent();
    assertWindowByExpectedText(secondWindowContent, secondWindow.getSecondWindowExpectedText());
    firstWindow.returnToPreviousWindow(firstWindowHeader);
    assertWindowByExpectedText(firstWindowContent, firstWindow.getFirstWindowExpectedText());
}

    @Step("verification new window by expected text")
    private void assertWindowByExpectedText(String windowContent, String windowExpectedText) {
        Assert.assertTrue(windowContent.contains(windowExpectedText), "didn't get expected  message");
    }
}
