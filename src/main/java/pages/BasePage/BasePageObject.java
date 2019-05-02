package pages.BasePage;

import common.drivers.Driver;
import common.logger.LogInstance;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BasePageObject {

    protected Logger log = LogInstance.getLogger();
    protected WebDriver driver = Driver.getDriver();

    public void openPage(String url) {
        try {
            driver.get(url);
            log.info("opened page: " + url);
        } catch (Exception e) {
            log.info("url parameter didn't find, page didn't open");
            log.error(e.getMessage());
        }
    }

    public String getText(By locator) {
        waitForVisibilityOf(locator,5);
        return driver.findElement(locator).getText();
    }

    @Step("got page content")
    public String getContent() {
        return driver.getPageSource();
    }

    @Step("got window header")
    public String getWindowHeader() {
        return driver.getWindowHandle();
    }

    public WebElement findElement(By locator) {
        waitForVisibilityOf(locator, 5);
        return driver.findElement(locator);
    }

    public List<WebElement> findAllElements(By locator) {
        waitForVisibilityOf(locator, 5);
        return driver.findElements(locator);
    }

    public void click(By locator) {
        waitForVisibilityOf(locator, 5);
        findElement(locator).click();
        log.info("webElement clicked " + locator.toString());
    }

    public void type(By locator, String text) {
        waitForVisibilityOf(locator, 5);
        findElement(locator).sendKeys(text);
        log.info("in field typed: " + text);
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds :30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    public void waitForVisibilityOf(By locator, Integer ... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
                log.error("element is stale " + e.getMessage());
            }
            attempts++;
        }
    }

    public boolean isVisible(By locator) {return findElement(locator).isDisplayed();
    }

    public Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        log.info("switching to alert");
        return driver.switchTo().alert();
    }

    public void switchToWindow(String firstWindowHeader) {
        for(String windowHandle : driver.getWindowHandles()) {
            if(!windowHandle.equals(firstWindowHeader)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    public void switchToFrame(By locator) {
        driver.switchTo().frame(findElement(locator));
        log.info("switched to iFrame");
    }

    public void clear(By locator) {
        findElement(locator).clear();
        log.info("field was cleaned");
    }

    public void sendKey(By locator, Keys key) {
        findElement(locator).sendKeys(key);
    }

    @Step("sent key with action")
    public void sendKeyWithAction(Keys key) {
         Actions action = new Actions (driver);
         action.sendKeys(key).build().perform();
         log.info("sent key with action");
    }

    public void scrollToBottom() {
        log.info("scrolling to the bottom of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        log.info("scrolled to the bottom");
    }

    public void scrollToTop() {
        log.info("scrolling to the top of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        log.info("scrolled to the top");
    }

    public void performDragAndDropWithJS(By from, By to) {
        log.info("performing drag and drop with JS");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                        + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
                        + "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                        + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
                        + "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                        + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                        + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                        + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
                        + "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                        + "var dragStartEvent =createEvent('dragstart');\n"
                        + "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
                        + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                        + "var dragEndEvent = createEvent('dragend');\n"
                        + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                        + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                        + "simulateHTML5DragAndDrop(source,destination);",
                findElement(from), findElement(to));
        log.info("drag and drop performed");
    }

    public void performDragAndDropWithAction(By from, By to) {
        log.info("performing drag and drop with Action");
        Actions action = new Actions(driver);
        action.dragAndDrop(findElement(from), findElement(to)).build().perform();
        log.info("drag and drop performed");
    }

    public void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        log.info("hovered over webElement");
    }


    public void setSliderWithOffset(By locator, String value) {
        log.info("moving slider to " + value);
        WebElement slider = findElement(locator);
        int width = slider.getSize().getWidth();
        log.info("got width: " + width);
        String maxRange = slider.getAttribute("max");
        log.info("got max range: " + maxRange);
        double percent = Double.parseDouble(value) / Double.parseDouble(maxRange);
        log.info("calculated percent: " + percent);
        int xOffset = (int) (width * percent);
        log.info("got offset: " + xOffset);
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider, xOffset, 0).build().perform();
        log.info("slider moved");
    }

    public void setSliderByKeyboard(By locator, String value) {
        log.info("moving slider to " + value);
        WebElement slider = findElement(locator);
        String step = slider.getAttribute("step");
        log.info("got step value: " + step);
        int steps = (int) (Double.parseDouble(value)/Double.parseDouble(step));
        log.info("got steps quantity: " + steps);
        sendKey(locator, Keys.ENTER);
        for(int i = 0; i < steps; i++ ) {
            sendKey(locator, Keys.ARROW_RIGHT);
        }
        log.info("slider moved");
    }

    public void setCookie(Cookie ck) {
        log.info("adding cookie: " + ck.getName());
        driver.manage().addCookie(ck);
        log.info("cookie added");
    }

    public String getCookie(String name) {
        log.info("getting value of cookie: " + name);
        return driver.manage().getCookieNamed(name).getValue();
    }
}


