package utils;

import org.testng.SkipException;

public class SkippedTestCaseException extends SkipException {

    public SkippedTestCaseException(String skipMessage) {
        super(skipMessage);
    }
    
    public synchronized Throwable fillInStackTrace() { 
        return this; 
    }
}
