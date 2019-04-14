package common;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private String testMethodName;
    private Logger log;
    private TestUtilities utilities = new TestUtilities();

    String screenshotPath= String.format("%s\\target\\test-output\\screenshots\\%s\\%s\\%s.png",
            System.getProperty("user.dir"), utilities.getTodayDate(), testMethodName, utilities.getSystemTime());

    @Override
    public void onTestStart(ITestResult iTestResult) {
        this.testMethodName = iTestResult.getMethod().getMethodName();
        this.log = LogInstance.getLogger();
        log.info("[starting " + testMethodName + "]");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("[test " + testMethodName + " finished successful]");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        utilities.getScreenshotFile(screenshotPath);
        saveScreenshot(utilities.getScreenshotByte());
        log.info("[test " + testMethodName + " failed]");
        getErrorLog(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        utilities.getScreenshotFile(screenshotPath);
        log.info("[test " + testMethodName + " failed but with in success percentage]");
        getErrorLog(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {}

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.removeAllAppenders();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        this.testMethodName = iTestResult.getMethod().getMethodName();
        log.info("[test " + testMethodName + " skipped]");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    private void getErrorLog(ITestResult iTestResult) {
        log.error(iTestResult.getThrowable().toString());
        String stackTrace = "\n";
        for (StackTraceElement element : iTestResult.getThrowable().getStackTrace()) {
            stackTrace += element.toString() + "\n";
        }
        log.error(stackTrace);
    }
}
