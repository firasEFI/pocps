package dk.rim.is.abt.nymf.healthcheck

import org.junit.runner.JUnitCore
import org.junit.runner.Result
/**
 * Created by micw on 16-05-2017.
 */
class CheckAllJobs {

    static void main(String[] args) {
        Result result = JUnitCore.runClasses(NymfHelfCheckTest.class)
        result.getFailures().each {
            println it.toString()
        }
        println result.wasSuccessful()
    }

}
