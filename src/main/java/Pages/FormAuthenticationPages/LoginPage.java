package Pages.FormAuthenticationPages;

import Pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/secure";

    private By heading = By.xpath("//div[@class='example']//h2");
    private By logoutButton = By.xpath("//i[@class='icon-2x icon-signout']");

    private String expectedHeading = "Secure Area";

    public String getUrl() {
        return url;
    }

    public String getExpectedHeading() {
        return expectedHeading;
    }

    @Step("got page heading")
    public String getHeading() {
        String headingText = getText(heading);
        log.info("got heading: " + headingText);
        return headingText;
    }

    @Step("clicked on logout button")
    public MainPage clickLogoutButton() {
        click(logoutButton);
        log.info("clicked on logout button");
        return new MainPage();
    }
}
