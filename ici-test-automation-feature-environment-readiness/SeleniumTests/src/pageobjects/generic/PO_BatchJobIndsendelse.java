package pageobjects.generic;

import org.openqa.selenium.By;

import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;

public class PO_BatchJobIndsendelse {

    public static final Input today = new Input(Frames.tabPage, By.id("BATCH_START_DTTM_FWDTTM_P1"));

    public static final Input timeOfDay = new Input(Frames.tabPage, By.id("BATCH_START_DTTM_FWDTTM_P2"));
}
