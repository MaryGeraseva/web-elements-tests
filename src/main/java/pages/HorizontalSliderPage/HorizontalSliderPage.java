package pages.HorizontalSliderPage;

import pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HorizontalSliderPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/horizontal_slider";

    private By locatorSlider = By.xpath("//div[@class='sliderContainer']//input");
    private By locatorRange = By.id("range");

    public String getUrl() {
        return url;
    }

    @Step("got range value")
    public String getRange() {
        String range = getText(locatorRange);
        log.info("got range value: " + range);
        return range;
    }

    @Step("moved slider with offset")
    public void moveSliderWithOffset(String value) {
        setSliderWithOffset(locatorSlider, value);
        log.info("moved slider with offset");
    }

    @Step("moved slider by keyboard")
    public void moveSliderByKeyboard(String value) {
        setSliderByKeyboard(locatorSlider, value);
        log.info("moved slider by keyboard");
    }
}
