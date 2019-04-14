package CheckboxesTests;

import BaseTests.BaseTest;
import Pages.CheckboxesPage.CheckboxesPage;
import Pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxesTest extends BaseTest {

    @Test
    @Step("checkboxesTest started")
    @Description(value = "test checks interaction with checkboxes")
    public void checkboxesTest(){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        CheckboxesPage checkboxesPage = welcomePage.clickOnCheckboxesPageLink();
        checkURL(checkboxesPage.getUrl());
        checkboxesPage.selectCheckbox(checkboxesPage.getCheckbox1());
        assertIsAllCheckboxesSelected(checkboxesPage);
        checkboxesPage.unselectAllCheckboxes();
        assertIsAllCheckboxUnselected(checkboxesPage);
    }

    @Step("verification is checkboxes group selected")
    private void assertIsAllCheckboxesSelected(CheckboxesPage checkboxesPage) {
        Assert.assertTrue(checkboxesPage.isAllCheckboxesSelected(), "all checkboxes didn't select");
    }

    @Step("verification is checkboxes group unselected")
    private void assertIsAllCheckboxUnselected(CheckboxesPage checkboxesPage) {
        Assert.assertFalse(checkboxesPage.isAllCheckboxesSelected(), "all checkboxes didn't unselect");
    }
}
