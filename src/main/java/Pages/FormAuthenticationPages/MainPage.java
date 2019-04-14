package Pages.FormAuthenticationPages;

import Pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class MainPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/login";

    private By heading = By.xpath("//h2[contains(text(),'Login Page')]");
    private By error = By.id("flash");
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//i[@class='fa fa-2x fa-sign-in']");

    private String expectedHeading = "Login Page";

    public String getUrl() {
        return url;
    }

    public String getExpectedHeading() {
        return expectedHeading;
    }

    @Step("typed user name")
    public MainPage typeUserName(String username) {
        type(usernameField, username);
        log.info("input login " + username);
        return this;
    }

    @Step("typed password")
    public MainPage typePassword(String password) {
        type(passwordField, password);
        log.info("input password " + password);
        return this;
    }

    public LoginPage clickLoginButton() {
        click(loginButton);
        log.info("clicked login button");
        return new LoginPage();
    }

    @Step("valid registration is completed")
    public LoginPage registration(String username, String password) {
        typeUserName(username);
        typePassword(password);
        clickLoginButton();
        log.info("valid registration is completed");
        return new LoginPage();
    }

    @Step("invalid registration is completed")
    public MainPage registrationInvalid(String username, String password) {
        typeUserName(username);
        typePassword(password);
        clickLoginButton();
        log.info("invalid registration is completed");
        return this;
    }

    @Step("got page heading")
    public String getHeading() {
        String headingText = getText(heading);
        log.info("got heading: " + headingText);
        return headingText;
    }

    @Step("got error text")
    public String getError() {
        String errorText = getText(error);
        log.info("got error text: " + errorText.split("!")[0]);
        return errorText;
    }
}
