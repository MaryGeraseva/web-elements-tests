package common.logger;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.testng.ITestContext;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class LogAppender {

    public static FileAppender fileAppenderConfig(ITestContext context, ConcurrentHashMap<String, Integer> testCaseIdList) {

        String path = getFilePath(context, testCaseIdList);

        new File(path);

        FileAppender appender = new FileAppender();
        appender.setFile(path);
        appender.setLayout(new EnhancedPatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"));
        appender.setThreshold(Level.TRACE);
        appender.setAppend(false);
        appender.activateOptions();
        return appender;
    }

    private static String getFilePath(ITestContext context, ConcurrentHashMap<String, Integer> testCaseIdList) {
        Integer testCaseId;

        if (!testCaseIdList.containsKey(context.getCurrentXmlTest().getName())) {
            testCaseId = 1;
            testCaseIdList.put(context.getCurrentXmlTest().getName(), testCaseId);
        } else {
            testCaseId = testCaseIdList.get(context.getCurrentXmlTest().getName());
            ++testCaseId;
            testCaseIdList.put(context.getCurrentXmlTest().getName(), testCaseId);
        }

        return String.format("%s\\target\\logs\\methods\\%s\\%d#%s.log",
                System.getProperty("user.dir"), context.getCurrentXmlTest().getName(), testCaseId, Thread.currentThread().getName());
    }

    public static ConsoleAppender consoleAppenderConfig(){
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(new EnhancedPatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"));
        consoleAppender.setThreshold(Level.TRACE);
        consoleAppender.activateOptions();
        return consoleAppender;
    }
}
