package HorizontalSliderTests;

import BaseTests.BaseTest;
import pages.HorizontalSliderPage.HorizontalSliderPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HorizontalSliderTest extends BaseTest {

    @Parameters({"value"})
    @Test
    @Step("horizontalSliderTest started")
    @Description(value = "test checks interaction with horizontal slider")
    public void horizontalSliderTest(@Optional("2.5") String value) {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        HorizontalSliderPage horizontalSliderPage = welcomePage.clickOnHorizontalSliderPageLink();
        checkURL(horizontalSliderPage.getUrl());
        horizontalSliderPage.moveSliderByKeyboard(value);
        assertSliderOffset(value, horizontalSliderPage);
    }

    @Step("verification slider offset")
    private void assertSliderOffset(@Optional("2.5") String value, HorizontalSliderPage horizontalSliderPage) {
        Assert.assertEquals(value, horizontalSliderPage.getRange());
    }
}
