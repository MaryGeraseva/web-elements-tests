package pages.JsAlertsPage;

import pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class JavaScriptAlertsPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/javascript_alerts";

    private By jsAlert = By.xpath("//button[@onclick='jsAlert()']");
    private By jsConfirm = By.xpath("//button[@onclick='jsConfirm()']");
    private By jsPrompt = By.xpath("//button[@onclick='jsPrompt()']");
    private By result = By.id("result");

    private String expectedAlertMessage = "You successfuly clicked an alert";
    private String expectedConfirmAcceptMessage = "You clicked: Ok";
    private String expectedConfirmDismissMessage = "You clicked: Cancel";
    private String expectedPromptAcceptMessage = "You entered: ";
    private String expectedPromptDismissMessage = "You entered: null";

    public String getUrl() {
        return url;
    }

    public String getExpectedAlertMessage() {
        return expectedAlertMessage;
    }

    public String getExpectedConfirmAcceptMessage() {
        return expectedConfirmAcceptMessage;
    }

    public String getExpectedConfirmDismissMessage() {
        return expectedConfirmDismissMessage;
    }

    public String getExpectedPromptDismissMessage() {
        return expectedPromptDismissMessage;
    }

    @Step("alert accepted")
    public void accept() {
        Alert alert = switchToAlert();
        log.info("alert is accepting");
        alert.accept();
        log.info("alert accepted");
    }

    @Step("alert dismissed")
    public void dismiss() {
        Alert alert = switchToAlert();
        log.info("alert is dismissing");
        alert.dismiss();
        log.info("alert dismissed");
    }

    @Step("input text in alert field")
    public String getExpectedPromptAcceptMessage(String text) {
        log.info("got text: " + text);
        return expectedPromptAcceptMessage + text;
    }

    @Step("typed in alert field")
    public void typeToAlertField(String text) {
        Alert alert = switchToAlert();
        alert.sendKeys(text);
        log.info("typed in alert field");
    }

    @Step("clicked on JS alert button")
    public void clickOnAlertButton() {
        click(jsAlert);
        log.info("clicked on JS alert button");
    }

    @Step("clicked on JS confirm button")
    public void clickOnConfirmButton() {
        click(jsConfirm);
        log.info("clicked on JS confirm button");
    }

    @Step("clicked on JS prompt button")
    public void clickOnPromptButton() {
        click(jsPrompt);
        log.info("clicked on JS prompt button");
    }

    @Step("got result message")
    public String getResultMessage() {
        String message = getText(result);
        log.info("got result message: " + message);
        return message;
    }
}
