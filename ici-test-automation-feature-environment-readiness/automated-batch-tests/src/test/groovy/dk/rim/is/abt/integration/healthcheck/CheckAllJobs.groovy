package dk.rim.is.abt.integration.healthcheck

import org.junit.runner.JUnitCore
import org.junit.runner.Result

/**
 * Created by micw on 16-05-2017.
 * Run all job from BAC job LIST
 * without prepare INPUT
 */
class CheckAllJobs {

    static void main(String[] args) {
        Result result = JUnitCore.runClasses(CheckAllJobsTest.class)
        result.getFailures().each {
            println it.toString()
        }
        println result.wasSuccessful()
    }

}
