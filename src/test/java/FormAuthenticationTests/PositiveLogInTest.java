package FormAuthenticationTests;

import BaseTests.BaseTest;
import pages.FormAuthenticationPages.LoginPage;
import pages.FormAuthenticationPages.MainPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.*;


public class PositiveLogInTest extends BaseTest {

    @Parameters({"username", "password"})
    @Test
    @Step("positiveLogInTest started")
    @Description(value = "test checks logIn with correct user data")
    public void positiveLogInTest(String username, String password) {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        MainPage mainPage = welcomePage.clickOnMainPageLink();
        checkURL(mainPage.getUrl());
        LoginPage loginPage = mainPage.registration(username, password);
        checkURL(loginPage.getUrl());
        assertLogInByPageHeading(loginPage.getExpectedHeading(), loginPage.getHeading());
        MainPage newMainPage = loginPage.clickLogoutButton();
        assertLogOutByPageHeading(mainPage.getExpectedHeading(), newMainPage.getHeading());
    }

    @Step("verification logIn by expected page heading")
    private void assertLogInByPageHeading(String expectedHeading, String heading) {
        Assert.assertTrue(expectedHeading.contains(heading));
    }

    @Step("verification logOut by expected page heading")
    private void assertLogOutByPageHeading(String expectedHeading, String heading) {
        Assert.assertTrue(expectedHeading.contains(heading));
    }
}

