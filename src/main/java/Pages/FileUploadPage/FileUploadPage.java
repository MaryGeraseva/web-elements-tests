package Pages.FileUploadPage;

import Pages.BasePage.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class FileUploadPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/upload";

    private By locatorAddFileButton = By.id("file-upload");
    private By locatorUploadButton = By.id("file-submit");
    private By locatorUploadedFileName = By.id("uploaded-files");

    public String getUrl() {
        return url;
    }

    @Step("selected file by name")
    public void selectFile(String fileName) {
        log.info("selecting file " + fileName + " from file folder" );
        String filePath = String.format("%s\\src\\main\\resources\\files\\%s", System.getProperty("user.dir"), fileName);
        type(locatorAddFileButton, filePath);
        log.info(fileName + " selected");
    }

    @Step("clicked on button")
    public void clickOnUploadButton() {
        findElement(locatorUploadButton).click();
        log.info("clicked on button with locator" + locatorUploadButton);
    }

    @Step("got file name")
    public String getFileName() {
        String fileName = getText(locatorUploadedFileName);
        log.info("got file name: " + fileName);
        return fileName;
    }
}
