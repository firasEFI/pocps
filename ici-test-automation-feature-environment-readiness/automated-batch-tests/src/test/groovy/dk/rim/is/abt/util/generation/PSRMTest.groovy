package dk.rim.is.abt.util.generation

/**
 * Created by wos on 12.05.2017.
 */
class PSRMTest extends Test {
    public static String RUN_PSRM_JOB = "runPsrmJob"
    PSRMTest(String type, String name, String description) {
        this.name = name
        this.type = type
        this.description = description
        fillMethod()
    }
    void fillMethod() {
        methods +=
                "\t\tdef jobReturnCode = BatchController." + RUN_PSRM_JOB + "(JOB_DESCRIPTION)\n" +
                        "\t\tassert jobReturnCode.executionStatus == \"SUCCESS\";\n" +
                        "\t}\n"
    }
}
