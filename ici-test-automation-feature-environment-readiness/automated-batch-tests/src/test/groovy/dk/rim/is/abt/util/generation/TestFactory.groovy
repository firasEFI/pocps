package dk.rim.is.abt.util.generation

/**
 * Created by wos on 12.05.2017.
 */
class TestFactory {
    static Test getTest(String type, String name, String description) {
        switch (type) {
            case "NYMF":
                return new NyMFTest(type, name, description)
                break
            case "INTEGRATION":
                return new IntegrationTest(type, name, description)
                break
            case "PSRM":
                return new PSRMTest(type, name, description)
                break
        }
        return new IntegrationTest("","","")
    }
}
