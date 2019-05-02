package FormAuthenticationTests;

import BaseTest.BaseTest;
import pages.FormAuthenticationPages.MainPage;
import pages.Welcomepage.WelcomePage;
import common.dataProviders.CsvDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class NegativeLogInTestsWithCSVData extends BaseTest {

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProvider.class)
    @Step("negativeLogInTestWithCSVData started")
    @Description(value = "test checks logIn with invalid user data from csv file")
    public void negativeLogInTestWithCSVData(Map<String, String> testData){
        String testId = testData.get("testId");
        String description = testData.get("description");
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedError = testData.get("expectedError");
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
