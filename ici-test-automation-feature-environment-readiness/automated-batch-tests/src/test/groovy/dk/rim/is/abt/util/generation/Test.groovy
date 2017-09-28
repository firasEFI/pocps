package dk.rim.is.abt.util.generation

/**
 * Created by wos on 12.05.2017.
 */
abstract class Test {
    String packageName
    def imports = []
    String name
    String description
    String type
    public static String BATCH_CONTROLLER = "BatchController"
    String methods  = "\t@Test\n" +
    "\tvoid testBatchReturnCode() {\n"
    Test(){}
    void fillFields() {
        imports << ("dk.rim.is.abt.util." + BATCH_CONTROLLER)
        imports << "org.junit.Test"
        this.packageName = "dk.rim.is.abt." + type.toLowerCase()
    }

    String toString() {
        fillFields()
        StringBuilder sb = new StringBuilder();
        def endLine = ";\n"
        def newLine = "\n"
        sb.append("package ").append(packageName).append(endLine).append(newLine)
        imports.each {
            sb.append("import ").append(it).append(endLine)
        }
        sb.append(newLine)
        sb.append("class ").append(name).append("Test {").append(newLine)
        sb.append("\tprivate static final String JOB_DESCRIPTION = \"").append(description).append("\"")
                .append(endLine).append(newLine)
        sb.append("\tprivate static final String JOB_NAME = \"").append(name).append("\"")
                .append(endLine).append(newLine)
        sb.append(methods)
        sb.append("}")
        sb.toString()
    }
}
