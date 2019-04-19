package DragAndDropTests;

import BaseTests.BaseTest;
import pages.DragAndDropPage.DragAndDropPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseTest {

    @Test
    @Step("dragAndDropTest started")
    @Description("test checks interaction with drag and drop")
    public void dragAndDropTest() {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        DragAndDropPage dragAndDropPage = welcomePage.clickOnDragAndDropPageLink();
        checkURL(dragAndDropPage.getUrl());
        dragAndDropPage.dragAToB();
        assertDragAToB(dragAndDropPage.getColumnBText());
        dragAndDropPage.dragBToA();
        assertDragBToA(dragAndDropPage.getColumnAText());
    }

    @Step("verification drag and drop A box to B box")
    private void assertDragAToB(String bText) {
        Assert.assertEquals(bText, "A", "drag and drop action didn't confirmation");
    }

    @Step("verification drag and drop B box to A box")
    private void assertDragBToA(String aText) {
        Assert.assertEquals(aText, "A", "drag and drop action didn't confirmation");
    }

}
