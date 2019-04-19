package pages.IFramePage;

import pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class IFramePage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/tinymce";

    private By iFrame = By.tagName("iframe");
    private By iFrameTextField = By.xpath("//body[@id='tinymce']");

    private String defaultText = "Your content goes here.";

    public String getUrl() {
        return url;
    }

    public String getExpectedDefaultText() {
        return defaultText;
    }

    @Step("swished to iFrame window")
    public void swishToIFrame() {
        switchToFrame(iFrame);
        log.info("swished to iFrame window");
    }

    @Step("got current default text")
    public String getCurrentText() {
        String text = getText(iFrameTextField);
        log.info("got iFrame text: " + text);
        return text;
    }

    @Step("cleared editor window")
    public void clearEditor() {
        clear(iFrameTextField);
        log.info("cleared editor window");
    }

    @Step("typed in text editor")
    public void typeInEditor(String InputText) {
        type(iFrameTextField, InputText);
        log.info("in text editor typed: " + InputText);
    }
}
