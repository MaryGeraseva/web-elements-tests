package DropDownTests;

import BaseTests.BaseTest;
import Pages.DropdownPage.DropdownPage;
import Pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownTest extends BaseTest {

    @Test
    @Step("dropdownTest started")
    @Description(value = "test checks interaction with drop down element by text, index and locator")
    public void dropdownTest(){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        DropdownPage dropdownPage = welcomePage.clickOnDropdownPageLink();
        checkURL(dropdownPage.getUrl());
        dropdownPage.selectOptionByText(dropdownPage.getOption2Text());
        assertDropDownAction(dropdownPage);
        dropdownPage.selectOptionByIndex(2);
        assertDropDownAction(dropdownPage);
        dropdownPage.selectByLocator(dropdownPage.getOption2());
        assertDropDownAction(dropdownPage);
    }

    @Step("verification drop down action")
    private void assertDropDownAction(DropdownPage dropdownPage) {
        Assert.assertTrue(dropdownPage.checkSelectedOption(dropdownPage.getOption2Text()), "option didn't selected");
    }
}
