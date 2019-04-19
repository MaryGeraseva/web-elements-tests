package pages.DragAndDropPage;

import pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DragAndDropPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/drag_and_drop";

    private By locatorA = By.id("column-a");
    private By locatorB = By.id("column-b");

    public String getUrl() {
        return url;
    }

    @Step("performed drag and drop A box to B box")
    public void dragAToB() {
        log.info("drag and drop A box to B box");
        performDragAndDropWithJS(locatorA, locatorB);
    }

    @Step("performed drag and drop B box to A box")
    public void dragBToA() {
        log.info("drag and drop B box to A box");
        performDragAndDropWithJS(locatorB, locatorA);
    }

    @Step("got column A text")
    public String getColumnAText() {
        String text = getText(locatorA);
        log.info("column A text is " + text);
        return text;
    }

    @Step("got column B text")
    public String getColumnBText() {
        String text = getText(locatorB);
        log.info("column B text is " + text);
        return text;
    }
}
