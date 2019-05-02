package common.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class LogInstance {

    private static ThreadLocal<Logger> log = new ThreadLocal<>();
    private static ConcurrentHashMap<String, Integer> testCaseIdList = new ConcurrentHashMap<>();
    private static volatile int loggerId = 0;

    private LogInstance() {}

    public static synchronized Logger getLogger() {
        if (log.get() == null) {
            LogManager.getLogger("Logger");
        }
        return log.get();
    }

    public static synchronized Logger setContext(ITestContext context, Method method) {
        Thread.currentThread().setName(method.getName());
        Logger logger = LogManager.getLogger(String.format("- [%d] - %s", loggerId++, context.getCurrentXmlTest().getName()));
        logger.addAppender(LogAppender.fileAppenderConfig(context, testCaseIdList));
        logger.addAppender(LogAppender.consoleAppenderConfig());
        log.set(logger);
        return log.get();
    }

    public static void resetLog() {
        log.set(null);
    }

    public static Integer getTestCaseId(ITestContext context) {
        return testCaseIdList.get(context.getCurrentXmlTest().getName());
    }
}
