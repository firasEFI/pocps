package findus_pageobjects.synchronization;

import com.google.common.base.Predicate;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import icisel.utils.driver.patient.PatientWebDriver;
import org.openqa.selenium.By;

public class SynchronizeByOraPageTitle extends PageSynchronizer {

    private String oraPageTitle;

    public SynchronizeByOraPageTitle(String oraPageTitle)
    {
        super(10);

        this.oraPageTitle = oraPageTitle;
    }

    @Override
    protected Predicate<PatientWebDriver> getSynchronization(PatientWebDriver driver) {
        return new Predicate<PatientWebDriver>() {
            @Override
            public boolean apply(PatientWebDriver input) {
                PageElement oraPageTitle = new PageElement(Frames.uiMap, By.className("oraPageTitle"));

                return oraPageTitle.getText().equals(SynchronizeByOraPageTitle.this.oraPageTitle);
            }
        };
    }
}
