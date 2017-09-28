package findus_pageobjects.justering_forespoergsel;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.synchronization.SynchronizeByPageTitle;
import findus_pageobjects.synchronization.Synchronizer;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class JusteringForespoergselPage extends BasePsrmPage {

    // region "Auto generated"
    private static final Synchronizer synchronizer = new SynchronizeByPageTitle("Justering forespørgsel", 5);

    public JusteringForespoergselPage(){
        super(synchronizer);
    }
    final PageElement primaerTab = new PageElement(Frames.tabMenu, By.xpath(createTabLocator("Primær")));

    final Dropdown multiQueryZoneFilters1 = new Dropdown(Frames.tabPage, By.id("multiQueryZoneFilters1"));


    public JusteringForespoergsel_PrimaerSubPage activatePrimaer()
    {
        primaerTab.click();
        return new JusteringForespoergsel_PrimaerSubPage(this);
    }

    public JusteringForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage selectSearchForNavnPaaPartenOgIdTypePaaPartenVaerdi() {
        multiQueryZoneFilters1.pick("Navn på parten og ID type på parten / Værdi");

        return new JusteringForespoergsel_NavnPaaPartenOgIdTypePaaPartenVaerdiSubPage(this);
    }

    public JusteringForespoergsel_KontoIdSubPage selectSearchForKontoId() {
        multiQueryZoneFilters1.pick("Konto ID");

        return new JusteringForespoergsel_KontoIdSubPage(this);
    }

    public JusteringForespoergsel_FordringsIdSubPage selectSearchForFordringsId() {
        multiQueryZoneFilters1.pick("Fordrings ID");

        return new JusteringForespoergsel_FordringsIdSubPage(this);
    }

    public JusteringForespoergsel_FordringsformularIdSubPage selectSearchForFordringsformularId() {
        multiQueryZoneFilters1.pick("Fordringsformular ID");

        return new JusteringForespoergsel_FordringsformularIdSubPage(this);
    }

    public JusteringForespoergsel_JusteringsIdSubPage selectSearchForJusteringsId() {
        multiQueryZoneFilters1.pick("Justerings ID");

        return new JusteringForespoergsel_JusteringsIdSubPage(this);
    }


    // endregion

}

