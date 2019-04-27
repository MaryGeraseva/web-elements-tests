package BaseTest;

import common.Driver;
import common.LogInstance;
import common.TestListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners({TestListener.class})
public class BaseTest {

    public WebDriver driver;
    public Logger log;

    @BeforeMethod
    public void setUp(Method method, ITestContext context, Object[] testData) {
        log = LogInstance.setContext(context, method);
        driver = Driver.getDriver();
        maximizeWindow();
        log.info("setUp " + method.getName());
    }

    @AfterMethod
    public void tearDown(Method method, ITestContext context) {
        log.info("tearDown " + method.getName());
        attachLog(getLogPath(context));
        driver.quit();
        Driver.resetDriver();
        LogInstance.resetLog();
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void attachLog(String path) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            Allure.addAttachment("Logs", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLogPath(ITestContext context) {
        return  String.format("%s\\target\\logs\\methods\\%s\\%d#%s.log",
                System.getProperty("user.dir"), context.getCurrentXmlTest().getName(), LogInstance.getTestCaseId(context), Thread.currentThread().getName());
    }

    @Step("verification of page by current url")
    public void checkURL(String url) {
        Assert.assertEquals(driver.getCurrentUrl(), url, "expected page didn't open");
    }
}
