package Pages.HoversPage;

import Pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HoversPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/hovers";

    private By locatorAvatar= By.xpath("//div[@class='figure']");
    private By locatorViewProfileLink = By.xpath(".//a[contains(text(),'View profile')]");

    public String getUrl() {
        return url;
    }

    @Step("clicked on user profile link")
    public void openUserProfile(int i) {
        log.info("hovering over avatar user number " + i);
        List<WebElement> avatars = findAllElements(locatorAvatar);
        WebElement specifiedAvatars = avatars.get(i - 1);
        hoverOverElement(specifiedAvatars);
        specifiedAvatars.findElement(locatorViewProfileLink).click();
        log.info("clicked on user profile link");
    }
}
