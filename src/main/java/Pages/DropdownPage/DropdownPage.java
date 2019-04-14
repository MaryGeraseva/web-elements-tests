package Pages.DropdownPage;

import Pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DropdownPage extends BasePageObject {

    private static String url = "http://the-internet.herokuapp.com/dropdown";

    private By dropdownLocator = By.id("dropdown");
    private By optionDefault = By.xpath("//select[@id='dropdown']//option[1]");
    private By option1 = By.xpath("//select[@id='dropdown']//option[2]");
    private By option2 = By.xpath("//select[@id='dropdown']//option[3]");

    private String option1Text = "Option 1";
    private String option2Text = "Option 2";

    public String getUrl() {
        return url;
    }

    public By getDropdownLocator() {
        return dropdownLocator;
    }

    public By getOptionDefault() {
        return optionDefault;
    }

    public By getOption1() {
        return option1;
    }

    public By getOption2() {
        return option2;
    }

    public String getOption1Text() {
        return option1Text;
    }

    public String getOption2Text() {
        return option2Text;
    }

    @Step("selected drop down option by text")
    public void selectOptionByText(String expectedText) {
        WebElement dropdownElement = findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(expectedText);
        log.info(expectedText + " selected");
    }

    @Step("selected drop down option by index")
    public void selectOptionByIndex(int index) {
        WebElement dropdownElement = findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
        log.info(dropdown.getFirstSelectedOption().getText() + " selected");
    }

    @Step("selected drop down option by locator")
    public void selectByLocator(By optionLocator) {
        WebElement dropdown = findElement(dropdownLocator);
        dropdown.click();
        WebElement option = findElement(optionLocator);
        option.click();
        log.info(option.getText() + " selected");
    }

    @Step("checked drop down option selection")
    public boolean checkSelectedOption(String expectedText) {
        WebElement dropdownElement = findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        if(!dropdown.getFirstSelectedOption().getText().equals(expectedText)) {
            return false;
        }
        return true;
    }

}
