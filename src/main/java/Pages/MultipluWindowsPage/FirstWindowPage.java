package Pages.MultipluWindowsPage;

import Pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class FirstWindowPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/windows";

    private String FirstWindowExpectedText = "Opening a new window";

    private By newWindowLink = By.xpath("//a[contains(text(),'Click Here')]");

    public String getUrl() {
        return url;
    }

    public String getFirstWindowExpectedText() {
        return FirstWindowExpectedText;
    }

    @Step("clicked on new page link")
    public void clickOnNewPageLink() {
        click(newWindowLink);
        log.info("clicked on new page link");
    }

    @Step("switched to the new window")
    public SecondWindowPage switchToNewWindow(String firstWindowHeader) {
        switchToWindow(firstWindowHeader);
        log.info("switched to the new window");
        return new SecondWindowPage();
    }

    @Step("switched to the previous window")
    public FirstWindowPage returnToPreviousWindow(String previousPageTitle) {
        switchToWindow(previousPageTitle);
        log.info("switched to the previous window");
        return this;
    }
}
