package utils.integrationfiles;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.testng.PropertyProvider;
import icisel.utils.driver.Engine;
import icisel.utils.driver.patient.PatientWebDriver;
import utils.tools.TO_Tools;

public class IntegrationFileSender {
    PropertyProvider propertyProvider;

    public IntegrationFileSender(PropertyProvider propertyProvider) {
        super();
        this.propertyProvider = propertyProvider;
    }

    /**
     * 
     * @param fileTypeTextForInput the text to type in the input field "File type"
     */
    public void indsendFil(File file, String fileTypeTextForInput) {
        // Gem oprindelig driver
        PatientWebDriver psrm = Engine.getDriver();
        // Åben nyt vindue
        Engine.setDriver(Engine.newDriver());

        // Go to Indbetaling webpage
        Engine.getDriver().get(integrationFileUrl());


        // =========== Define Upload field ============
        PageElement fileInput = new PageElement(Frames.defaultContent, By.id("file"));

        // Define file to upload in field
        fileInput.sendKeys(file.getAbsolutePath());

        // Submit upload
        Button uploadFile = new Button(Frames.defaultContent,
                By.xpath("//input[@type='submit' and @value='Upload file']"));
        // Click Submit upload
        uploadFile.click();

        // =========== Define filetype in integration_file ============

        // Define dropdown for payment file type
        Dropdown fileType = new Dropdown(Frames.defaultContent, By.name("fileType"));

        // Select filetype from dropdown e.g SKB_Cremul
        fileType.pickByVisibleText(fileTypeTextForInput);

        // Define who has uploaded the file
        Input createdby = new Input(Frames.defaultContent, By.name("createdBy"));

        // Clear and enter text for who has uploaded file
        createdby.clear();
        createdby.sendKeys("ici_automated_testing_machine");

        // Save
        Engine.getDriver().findElement(By.xpath("//input[@type='submit' and @value='Save']")).click();
        // Button Save = new Button(Frames.defaultContent,
        // By.xpath("//input[@type='submit' and @value='Save']"));
        // Save.clickElementUntilNotPresent();

        // Luk vindue
        Engine.closeDriver();
        // Skift tilbage til oprindelig driver
        Engine.setDriver(psrm);
    }

    private String integrationFileUrl() {
        String psrmUrl = propertyProvider.getUrl();

        // String psrmUrl = "https://5.44.137.168:476";
        return TO_Tools.parseDomain(psrmUrl) + "/test/batchfiles/newFile.jsp";
    }

}
