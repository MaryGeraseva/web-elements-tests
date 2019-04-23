package FileUploadTests;

import BaseTest.BaseTest;
import pages.FileUploadPage.FileUploadPage;
import pages.Welcomepage.WelcomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FileUploadTestError extends BaseTest {

    @DataProvider(name = "files")
    private static Object[][] files(){
        return new Object[][]{
                {1, "index.html"},
                {2, "logo.png"},
                {3, "text.txt"}
        };
    }

    @Test(dataProvider = "files")
    @Step("fileUploadTest started")
    @Description(value = "test checks error processing")
    public void fileUploadTestError(int testID, String fileName) {
        log.info("test #" + testID + " for " + fileName);
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.openPage(welcomePage.getUrl());
        FileUploadPage fileUploadPage = welcomePage.clickOnFileUploadPageLink();
        checkURL(fileUploadPage.getUrl());
        fileUploadPage.selectFile(fileName);
        fileUploadPage.clickOnUploadButton();
        checkFileName(fileUploadPage.getFileName());
    }

    @Step("verification of uploaded file by name")
    private void checkFileName(String currentFileName) {
        Assert.assertTrue(currentFileName.contains("error example"), "error !!!!!!!");
    }
}
