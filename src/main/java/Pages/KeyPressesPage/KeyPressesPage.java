package Pages.KeyPressesPage;

import Pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class KeyPressesPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/key_presses";

    private By currentTextLocator = By.id("result");
    private By bodyLocator = By.tagName("body");

    private String defaultText = "You entered: ";

    public String getUrl() {
        return url;
    }

    @Step("sent key")
    public void sentKey(Keys key) {
        sendKey(bodyLocator, key);
        log.info("sent key: " + key.name());
    }

    @Step("got result message")
    public String getResultMessage() {
        String resultMessage =getText(currentTextLocator);
        log.info("got result message: " +resultMessage);
        return resultMessage;
    }

    public String getExpectedMessage(Keys key) {
        String expectedMessage = defaultText + key.name();
        log.info("expected message is: " + expectedMessage);
        return expectedMessage;
    }
}
