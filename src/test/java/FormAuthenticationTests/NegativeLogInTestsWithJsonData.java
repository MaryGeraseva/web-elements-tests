package FormAuthenticationTests;

import BaseTests.BaseTest;
import common.JsonDataProvider.JsonDataProvider;
import pages.FormAuthenticationPages.MainPage;
import pages.Welcomepage.WelcomePage;
import common.JsonDataProvider.UserLogInData;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.*;


public class NegativeLogInTestsWithJsonData extends BaseTest {

    @Test(dataProvider = "jsonReader", dataProviderClass = JsonDataProvider.class)
    @Step("negativeLogInTestWithJsonData started")
    @Description(value = "test checks logIn with invalid user data from json file")
    public void negativeLogInTestWithJsonData(UserLogInData userLogInData){
        log.info("test #" + userLogInData.testId+ " with " + userLogInData.description);
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        MainPage mainPage = welcomePage.clickOnMainPageLink();
        checkURL(mainPage.getUrl());
        mainPage.registrationInvalid(userLogInData.username, userLogInData.password);
        assertInvalidRegistration(userLogInData, mainPage);
    }

    @Step("verification invalid registration case by expected error message")
    private void assertInvalidRegistration(UserLogInData userLogInData, MainPage mainPage) {
        Assert.assertTrue(mainPage.getError().contains(userLogInData.expectedError), "expected error message didn't get");
    }
}
