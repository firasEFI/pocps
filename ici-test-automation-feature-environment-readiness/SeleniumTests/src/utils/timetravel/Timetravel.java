package utils.timetravel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxProfile;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import icisel.testng.PropertyProvider;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientFirefoxDriver;
import utils.tools.TO_Tools;

public class Timetravel {
    PropertyProvider propertyProvider;

    public Timetravel(PropertyProvider propertyProvider) {
        super();
        this.propertyProvider = propertyProvider;
    }

    /**
     * Opens a new {@link PatientFirefoxDriver} with a custom profile that opens
     * the port specified by {@link PropertyProvider#getUrl()}.
     * 
     * @param date
     *            the date to time travel to
     */
    public void to(Date date) {
        // Allowing firefox to visit the test-portal that is otherwise blocked
        FirefoxProfile prof = new FirefoxProfile();
        String port = getPortOfUrl(getTestPortalUrl());
        prof.setPreference("network.security.ports.banned.override", port);
        Engine.setDriver(new PatientFirefoxDriver(prof));

        Engine.getDriver().get(getTestPortalUrl());
        Engine.getDriver().manage().window().maximize();

        Button timeSimuNavBtn = new Button(Frames.defaultContent,
                By.xpath("//md-toolbar-row//a[text()='Time Simulation']"));
        timeSimuNavBtn.click();

        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
        String formattedDate = formatter.format(date);

        // Date field
        Input dateField = new Input(Frames.defaultContent, By.xpath(".//*[@id='md-input-0']"));
        dateField.sendKeys(formattedDate);

        // Submit
        Button submitBtn = new Button(Frames.defaultContent,
                By.xpath("//button[@ng-reflect-message='Set new date in all components']"));
        // Button submitBtn = new Button(Frames.defaultContent,
        // By.xpath(".//[@id='mat-button-wrapper']//span[text()='Set time']"));
        submitBtn.click();

        Engine.closeDriver();

    }

    private String getTestPortalUrl() {
        String psrmUrl = propertyProvider.getUrl();

        String testPortalUrlEnd = "/test-portal/";

        String testPortalUrl = TO_Tools.parseDomain(psrmUrl) + testPortalUrlEnd;

        return testPortalUrl;
    }

    private String getPortOfUrl(String url) {
        int i;
        int numColon = 0;
        int numForwardSlash = 0;
        int startString = 0;
        int endString = 0;

        for (i = 0; i < url.length(); i++) {
            if (url.charAt(i) == ':') {
                numColon++;
                if (numColon == 2) {
                    startString = i;
                }
            }
            if (url.charAt(i) == '/') {
                numForwardSlash++;
                if (numForwardSlash == 3) {
                    endString = i;
                }
            }

        }
        return url.substring(startString + 1, endString);
    }
}
