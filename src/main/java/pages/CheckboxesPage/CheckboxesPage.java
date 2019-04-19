package pages.CheckboxesPage;

import pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/checkboxes";

    private By checkboxesAll = By.xpath("//form[@id='checkboxes']//input");
    private By checkbox1 = By.xpath("//form[@id='checkboxes']//input[1]");
    private By checkbox2 = By.xpath("//form[@id='checkboxes']//input[2]");

    public By getCheckboxesAll() { return checkboxesAll; }

    public By getCheckbox1() {
        return checkbox1;
    }

    public By getCheckbox2() {
        return checkbox2;
    }

    public String getUrl() {
        return url;
    }

    @Step("selected checkbox")
    public void selectCheckbox(By locator) {
        WebElement checkbox = findElement(locator);
        if(!checkbox.isSelected()){
            checkbox.click();
        }
        log.info(checkbox.getText()+ " checkbox selected");
    }

    @Step("unselected checkbox")
    public void unselectCheckbox(By locator) {
        WebElement checkbox = findElement(locator);
        if(checkbox.isSelected()){
            checkbox.click();
        }
        log.info(checkbox.getText()+ " checkbox unselected");
    }

    @Step("checkboxes group selected")
    public void selectAllCheckboxes() {
        List<WebElement> checkboxes = findAllElements(checkboxesAll);
        for(WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        log.info("all checkboxes selected");
    }

    @Step("checkboxes group unselected")
    public void unselectAllCheckboxes() {
        List<WebElement> checkboxes = findAllElements(checkboxesAll);
        for(WebElement checkbox : checkboxes) {
            if(checkbox.isSelected()) {
                checkbox.click();
            }
        }
        log.info("all checkboxes unselected");
    }

    @Step("checked is checkbox selected")
    public boolean isCheckboxSelected(By locator) {
        WebElement checkbox = findElement(locator);
        if(!checkbox.isSelected()) {
            return false;
        }
        return true;
    }

    @Step("checked is checkboxes group selected")
    public boolean isAllCheckboxesSelected() {
        List<WebElement> checkboxes = findAllElements(checkboxesAll);
        for(WebElement checkbox : checkboxes) {
            if(!checkbox.isSelected()) {
                return false;
            }
        }
        return true;
    }
}
