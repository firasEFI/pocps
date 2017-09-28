package dk.rim.is.ic.inttests;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

/**
 * Builder for running SoapUI tests.
 */
public class SoapUIBuilder {

  private String targetUrl;
  private String projectFile;

  public static SoapUIBuilder given(String soapUIProjectFile) {
    return new SoapUIBuilder(soapUIProjectFile);
  }

  private SoapUIBuilder(String projectFile) {
    this.projectFile = projectFile;
  }

  public void runAgainst(String targetUrl) throws Exception {
    this.withTarget(targetUrl);
    this.run();
  }

  public SoapUIBuilder withTarget(String targetUrl) {
    this.targetUrl = targetUrl;
    return this;
  }

  /** Executes the test prepared in the builder. */
  public void run() throws Exception {
    if (targetUrl == null) throw new IllegalArgumentException("Target URL must not be null.");
    if (projectFile == null) throw new IllegalArgumentException("Project file must not be null.");
    SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
    runner.setEndpoint(targetUrl);
    runner.setProjectFile(projectFile);
    runner.setPrintReport(true);
    runner.setPrintAlertSiteReport(true);
    runner.run();
  }
}
