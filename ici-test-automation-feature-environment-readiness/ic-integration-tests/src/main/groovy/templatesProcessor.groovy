import groovy.io.FileType
import org.w3c.dom.Element

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory
import java.util.regex.Pattern

Logger.info("****** Temporary template processor sciprt for IT purposes only! ****\n")
Logger.info("Started...")

// e.g.:  "groovy templateProcessor.groovy '/software/integration-component/integration-component/lib' 'apache-deltaspike.properties.ic-external.stmpl' 'C:\SKMIS\Settings\LOCAL'
// there are three parameters:
// 1: path to the directory containing templates (example: '/software/integration-component/integration-component/lib') - also subdirectories can contain templates
String templatesDirectoryPath = args[0]
Logger.info("args[0]=$templatesDirectoryPath (templatesDirectoryPath)")

// 2: template name (example: 'apache-deltaspike.properties.ic-external.stmpl')
String templateName = args[1]
Logger.info("args[1]=$templateName (templateName)")

// 3: environment directory path (example: 'C:\SKMIS\Settings\LOCAL')
String environmentDirectoryPath = args[2]
Logger.info("args[2]=$environmentDirectoryPath (environmentDirectoryPath)")

File templatesDirectory = new File(templatesDirectoryPath)
File environmentDirectory = new File(environmentDirectoryPath)
File commonDirectory = new File(environmentDirectory.parent, "COMMON")

String extractOutputFleNameFromTemplateName(String templateName) {
    String[] tokens = templateName.split("\\.")
    String[] tokensToUse = tokens.take(tokens.size() - 2)
    // template identificator (e.g.: 'ic-external') and 'stmpl' extension
    return tokensToUse.join(".")
}

boolean templateProcessed = false;

templatesDirectory.eachFileRecurse(FileType.FILES) { file ->
    if (templateName == file.getName()) {
        String fileName = extractOutputFleNameFromTemplateName(templateName)
        File newFile = new File(file.getParent(), fileName)
        Logger.info("Creating new file '$newFile' from the template '$templateName'")
        templateProcessed = true;
        newFile.newWriter().withWriter { writer ->
            TemplateProcessor templateProcessor = new TemplateProcessorImpl(new ExpressionEvaluatorImpl(environmentDirectory, commonDirectory))
            writer << templateProcessor.process(file.getText())
        }
    }
}

if (!templateProcessed) {
    throw new RuntimeException("Template $templateName not found in the path: $templatesDirectoryPath.")
}

Logger.info("Finished with SUCCESS")

//////////////////////////////////////////////////////////////////////////////////////////////
// INTERNAL CLASSES
//////////////////////////////////////////////////////////////////////////////////////////////

class Logger {
    public static void info(String message) {
        log(message, "INFO")
    }

    private static void log(String message, String level) {
        println "[templatesProcessor.groovy $level]: $message"
    }
}


interface ExpressionEvaluator {
    String evaluate(Expression expression)
}

class ExpressionEvaluatorImpl implements ExpressionEvaluator {

    static final String XML_EXTENSION = ".xml"
    final File environmentDirectory
    final File commonDirectory

    ExpressionEvaluatorImpl(File environmentDirectory, File commonDirectory) {
        this.environmentDirectory = environmentDirectory
        this.commonDirectory = commonDirectory
    }

    @Override
    String evaluate(Expression expression) {
        String evaluated = evaluateInternal(expression, configurationFile(expression))
        if (evaluated) {
            return evaluated
        }
        evaluated = evaluateInternal(expression, commonConfigurationFile(expression))
        if (evaluated) {
            return evaluated
        }
        // TODO: uncommenting turns on validation
//        throw new RuntimeException("No entry for expression: '${expression}' in both: " +
//                "'${environmentDirectory.getName()}' and '${commonDirectory.getName()}' settings directories.")
        return ""
    }

    String evaluateInternal(Expression expression, File configurationFile) {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        Element element = builder.parse(configurationFile).getDocumentElement()
        XPath xpath = XPathFactory.newInstance().newXPath()
        String extractedString = xpath.evaluate(expression.xPath, element, XPathConstants.STRING)
        return extractedString
    }

    private File commonConfigurationFile(Expression expression) {
        return new File(commonDirectory, expression.fileName + XML_EXTENSION)
    }

    private File configurationFile(Expression expression) {
        return new File(environmentDirectory, expression.fileName + XML_EXTENSION)
    }
}

class Expression {

    static final String FILE_NAME_REGEX = ".+"
    static final String SEPARATOR = ":"
    static final String XPATH_REGEX = ".+"
    static final Pattern PATTERN = Pattern.compile(FILE_NAME_REGEX + SEPARATOR + XPATH_REGEX)

    String xPath
    String fileName

    Expression(String expression) {
        validateExpression(expression)
        this.xPath = extractXPath(expression)
        this.fileName = extractFileName(expression)
    }

    private void validateExpression(String expression) {
        if (!PATTERN.matcher(expression).matches()) {
            throw new RuntimeException("Incorrect expression syntax: " + expression);
        }
    }

    private String extractFileName(String expression) {
        return expression.substring(0, expression.indexOf(SEPARATOR))
    }

    private String extractXPath(String expression) {
        return expression.substring(expression.indexOf(SEPARATOR) + 1)
    }


    @Override
    public String toString() {
        return "{${fileName}:${xPath}}"
    }
}

interface TemplateProcessor {
    String process(String input)
}

class TemplateProcessorImpl implements TemplateProcessor {

    enum State {
        OPENING_BRACKET,
        OTHER_CHAR,
        CLOSING_BRACKET
    }

    ExpressionEvaluator expressionEvaluator;
    State currentState = State.OTHER_CHAR
    Stack<StringBuilder> expressions = new Stack<>();
    StringBuilder output = new StringBuilder();

    TemplateProcessorImpl(ExpressionEvaluator expressionEvaluator) {
        this.expressionEvaluator = expressionEvaluator
    }

    @Override
    String process(String input) {
        for (char currentChar in input.getChars()) {
            if (isOpeningBracket(currentChar)) {
                processIfOpeningBracket()
            } else if (isClosingBracket(currentChar)) {
                processIfClosingBracket()
            } else {
                processIfOtherCharacter(currentChar)
            }
        }
        if (!expressions.isEmpty()) {
            throw new RuntimeException("Template parser in an inconsistent state - incorrect template.")
        }
        return output.toString()
    }

    private void processIfOpeningBracket() {
        switch (currentState) {
            case State.OPENING_BRACKET:
            case State.OTHER_CHAR:
            case State.CLOSING_BRACKET:
                expressions.push(new StringBuilder()) // new expression
                break;
        }
        currentState = State.OPENING_BRACKET
    }

    private void processIfOtherCharacter(char currentChar) {
        switch (currentState) {
            case State.OPENING_BRACKET:
                expressions.peek().append(currentChar)
                break;
            case State.OTHER_CHAR:
            case State.CLOSING_BRACKET:
                if (expressions.isEmpty()) { // we are outside of template
                    output.append(currentChar)
                } else {
                    expressions.peek().append(currentChar)
                }
                break;
        }
        currentState = State.OTHER_CHAR
    }

    private void processIfClosingBracket() {
        switch (currentState) {
            case State.OPENING_BRACKET:
                throw new RuntimeException("Empty template expression: {}")
            case State.OTHER_CHAR:
            case State.CLOSING_BRACKET:
                if (expressions.isEmpty()) {
                    throw new RuntimeException("Closing bracket in an unexpected place.")
                }
                String expression = expressions.pop()
                String evaluatedExpression = evaluateExpression(expression)
                if (expressions.isEmpty()) {
                    output.append(evaluatedExpression)
                } else {
                    expressions.peek().append(evaluatedExpression)
                }
                break;
        }
        currentState = State.CLOSING_BRACKET
    }

    private boolean isOpeningBracket(char character) {
        return '{' == character
    }

    private boolean isClosingBracket(char character) {
        return '}' == character
    }

    private String evaluateExpression(String expression) {
        return expressionEvaluator.evaluate(new Expression(expression))
    }
}