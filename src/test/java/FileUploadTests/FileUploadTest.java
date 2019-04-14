package FileUploadTests;

import BaseTests.BaseTest;
import Pages.FileUploadPage.FileUploadPage;
import Pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {

    @DataProvider(name = "files")
    private static Object[][] files(){
        return new Object[][]{
                {"1", "index.html"},
                {"2", "logo.png"},
                {"3", "text.txt"}
        };
    }

    @Test(dataProvider = "files")
    @Step("fileUploadTest started")
    @Description(value = "test checks file upload service with different type of file from dataProvider")
    public void fileUploadTest(String testID, String fileName) {
        log.info("test #" + testID + " with " + fileName);
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openWelcomePage();
        FileUploadPage fileUploadPage = welcomePage.clickOnFileUploadPageLink();
        checkURL(fileUploadPage.getUrl());
        fileUploadPage.selectFile(fileName);
        fileUploadPage.clickOnUploadButton();
        assertUpload(fileName, fileUploadPage.getFileName());
    }

    @Step("verification of uploaded file by name")
    private void assertUpload(String fileName, String currentFileName) {
        Assert.assertTrue(currentFileName.contains(fileName), "expected file didn't upload");
    }
}


