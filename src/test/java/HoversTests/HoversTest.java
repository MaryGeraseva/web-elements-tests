package HoversTests;

import BaseTest.BaseTest;
import pages.HoversPage.HoversPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HoversTest extends BaseTest {

    @DataProvider(name = "users")
    private static Object[][] users(){
        return new Object[][]{
                {"1", "user#3", "3"},
                {"2", "user#2", "2"},
                {"3", "user#1", "1"},
        };
    }

    @Test(dataProvider = "users")
    @Step("hoversTest started")
    @Description(value = "test checks interaction with hover over")
    public void hoversTest(String testId, String userName, int userNumber) {
        log.info("test #" + testId + " with " + userName);
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        HoversPage hoversPage = welcomePage.clickOnHoversPageLink();
        checkURL(hoversPage.getUrl());
        hoversPage.openUserProfile(userNumber);
        assertHoverByUserPage(userNumber);
    }

    @Step("verification hover over action by opened page url")
    private void assertHoverByUserPage(int userNumber) {
        Assert.assertTrue(driver.getCurrentUrl().contains("/users/" + userNumber), "unexpected url");
    }
}
