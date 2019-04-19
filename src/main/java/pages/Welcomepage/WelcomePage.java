package pages.Welcomepage;

import pages.BasePage.BasePageObject;
import pages.CheckboxesPage.CheckboxesPage;
import pages.DragAndDropPage.DragAndDropPage;
import pages.DropdownPage.DropdownPage;
import pages.FileUploadPage.FileUploadPage;
import pages.FormAuthenticationPages.MainPage;
import pages.HorizontalSliderPage.HorizontalSliderPage;
import pages.HoversPage.HoversPage;
import pages.IFramePage.IFramePage;
import pages.JSErrorPage.JSErrorPage;
import pages.JsAlertsPage.JavaScriptAlertsPage;
import pages.KeyPressesPage.KeyPressesPage;
import pages.MultipluWindowsPage.FirstWindowPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;



public class WelcomePage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/";

    private By pageLinkMain = By.xpath("//a[contains(text(),'Form Authentication')]");
    private By pageLinkCheckboxes = By.xpath("//a[contains(text(),'Checkboxes')]");
    private By pageLinkDropdown = By.xpath("//a[contains(text(),'Dropdown')]");
    private By pageLinkAlerts = By.xpath("//a[contains(text(), 'JavaScript Alerts')]");
    private By pageLinkMultipleWindow = By.xpath("//a[contains(text(),'Multiple Windows')]");
    private By pageLinkIFrame = By.xpath("//a[contains(text(),'WYSIWYG Editor')]");
    private By pageLinkKeyPresses = By.xpath("//a[contains(text(), 'Key Presses')]");
    private By pageLinkFileUpload = By.xpath("//a[contains(text(), 'File Upload')]");
    private By pageLinkDragAndDrop = By.xpath("//a[contains(text(),'Drag and Drop')]");
    private By pageLinkHoversPage = By.xpath("//a[contains(text(),'Hovers')]");
    private By pageLinkHorizontalSlider = By.xpath("//a[contains(text(),'Horizontal Slider')]");
    private By pageLinkJSError = By.xpath("//a[contains(text(),'JavaScript onload event error')]");

    public String getUrl() {
        return url;
    }

    public By getPageLinkMain() {
        return pageLinkMain;
    }

    public By getPageLinkCheckboxes() {
        return pageLinkCheckboxes;
    }

    public By getPageLinkDropdown() {
        return pageLinkDropdown;
    }

    public By getPageLinkAlerts() {
        return pageLinkAlerts;
    }

    public void openWelcomePage() {
        openPage(url);
    }

    @Step("opened expected page")
    public MainPage clickOnMainPageLink() {
        click(pageLinkMain);
        return new MainPage();
    }

    @Step("opened expected page")
    public CheckboxesPage clickOnCheckboxesPageLink() {
        click(pageLinkCheckboxes);
        return new CheckboxesPage();
    }

    @Step("opened expected page")
    public DropdownPage clickOnDropdownPageLink() {
        click(pageLinkDropdown);
        return new DropdownPage();
    }

    @Step("opened expected page")
    public JavaScriptAlertsPage clickOnAlertsPageLink() {
        click(pageLinkAlerts);
        return new JavaScriptAlertsPage();
    }

    @Step("opened expected page")
    public FirstWindowPage clickOnMultiWindowPageLink() {
        click(pageLinkMultipleWindow);
        return new FirstWindowPage();
    }

    @Step("opened expected page")
    public IFramePage clickOnIFramePageLink() {
        click(pageLinkIFrame);
        return new IFramePage();
    }

    @Step("opened expected page")
    public KeyPressesPage clickOnKeyPressesPageLink() {
        click(pageLinkKeyPresses);
        return new KeyPressesPage();
    }

    @Step("opened expected page")
    public FileUploadPage clickOnFileUploadPageLink() {
        click(pageLinkFileUpload);
        return new FileUploadPage();
    }

    @Step("opened expected page")
    public DragAndDropPage clickOnDragAndDropPageLink() {
        click(pageLinkDragAndDrop);
        return new DragAndDropPage();
    }

    @Step("opened expected page")
    public HoversPage clickOnHoversPageLink() {
        click(pageLinkHoversPage);
        return new HoversPage();
    }

    @Step("opened expected page")
    public HorizontalSliderPage clickOnHorizontalSliderPageLink() {
        click(pageLinkHorizontalSlider);
        return new HorizontalSliderPage();
    }

    @Step("opened expected page")
    public JSErrorPage clickOnJSErrorPageLink() {
        click(pageLinkJSError);
        return new JSErrorPage();
    }
}
