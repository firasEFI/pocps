package dk.rim.is.ic.inttests.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 24.01.2017
 */
public class TestNTimes {
    private static final Logger LOG = LoggerFactory.getLogger(TestNTimes.class);

    private static final int DEFAULT_SECONDS_BETWEEN_RUNS = 2;
    private static final int DEFAULT_MAX_NUMBER_OF_EXECUTIONS = 8;

    private final Integer maxNumberOfExecutions;
    private final Action action;
    private final int secondsBetweenRuns;
    private String successMessage = "";

    private TestNTimes(Action action, Integer maxNumberOfExecutions, int secondsBetweenRuns) {
        this.maxNumberOfExecutions = maxNumberOfExecutions;
        this.action = action;
        this.secondsBetweenRuns = secondsBetweenRuns;
    }

    public static void executeTestNTimes(Action action) throws Exception {
        testNTimes(action).execute();
    }

    public static void executeTestNTimes(Action action, int maxNumberOfExecutions) throws Exception {
        testNTimes(action, maxNumberOfExecutions).execute();
    }

    public static void executeTestNTimes(Action action, int maxNumberOfExecutions, int secondsBetweenRuns) throws Exception {
        testNTimes(action, maxNumberOfExecutions, secondsBetweenRuns).execute();
    }

    public static TestNTimes testNTimes(Action action) {
        return testNTimes(action, DEFAULT_MAX_NUMBER_OF_EXECUTIONS);
    }

    public static TestNTimes testNTimes(Action action, int maxNumberOfExecutions) {
        return testNTimes(action, maxNumberOfExecutions, DEFAULT_SECONDS_BETWEEN_RUNS);
    }

    public static TestNTimes testNTimes(Action action, int maxNumberOfExecutions, int secondsBetweenRuns) {
        return new TestNTimes(action, maxNumberOfExecutions, secondsBetweenRuns);
    }

    public TestNTimes successMessage(String successMessage) {
        if (null != successMessage) {
            this.successMessage = successMessage;
        }

        return this;
    }

    public void execute() throws Exception {
        for (int currentCounter = 0; currentCounter < maxNumberOfExecutions; currentCounter++) {
            if (isNotLastExecution(currentCounter)) {
                //if something goes wrong here - we will catch the exception and try again until we reach the limit
                try {
                    executeTestAction();
                    break;
                } catch (AssertionError e) {
                    LOG.warn("Test action failed on " + currentCounter + " execution, waiting for another " + secondsBetweenRuns + " seconds");
                    TimeUnit.SECONDS.sleep(secondsBetweenRuns);
                }
            } else {
                //if something goes wrong here - test will fail
                executeTestAction();
            }
        }
    }

    private void executeTestAction() throws Exception {
        action.execute();
        LOG.info("Test passed! " + successMessage);
    }

    private boolean isNotLastExecution(int currentCounter) {
        return currentCounter < maxNumberOfExecutions - 1;
    }
}
