package FormAuthenticationTests;

import BaseTests.BaseTest;
import pages.FormAuthenticationPages.MainPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeLogInTestsWithDataProvider extends BaseTest {

    @DataProvider(name = "invalidLogInData")
    private static Object[][] invalidLogInData(){
        return new Object[][]{
                {"1", "test", "SuperSecretPassword!", "Your username is invalid!", "invalid username test"},
                {"2", "tomsmith", "test", "Your password is invalid!", "invalid password test"},
                {"3", "", "SuperSecretPassword!", "Your username is invalid!", "empty username test"},
                {"4", "tomsmith", "", "Your password is invalid!", "empty password test"}
        };
    }

    @Test(dataProvider = "invalidLogInData")
    @Step("NegativeLogInTestsWithDataProvider started")
    @Description(value = "test checks logIn with invalid user data from TestNG DataProvider")
    public void negativeLogInTest(String testId, String username, String password, String expectedError, String description){
        log.info("test #" + testId+ " with " + description);
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        MainPage mainPage = welcomePage.clickOnMainPageLink();
        checkURL(mainPage.getUrl());
        mainPage.registrationInvalid(username, password);
        assertInvalidRegistration(expectedError, mainPage);
    }

    @Step("verification invalid registration case by expected error message")
    private void assertInvalidRegistration(String expectedError, MainPage mainPage) {
        Assert.assertTrue(mainPage.getError().contains(expectedError), "expected error message didn't get");
    }
}
