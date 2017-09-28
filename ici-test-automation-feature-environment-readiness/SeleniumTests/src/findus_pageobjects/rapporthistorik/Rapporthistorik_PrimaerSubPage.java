package findus_pageobjects.rapporthistorik;

import findus_datamodels.RapportSearchModel;
import findus_pageobjects.SubPage;
import icisel.pageobjects.elements.Button;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class Rapporthistorik_PrimaerSubPage extends SubPage<RapporthistorikPage> {
    public Rapporthistorik_PrimaerSubPage(RapporthistorikPage parentPage) {
        super(parentPage);
    }
    private Dropdown rapportType = new Dropdown(Frames.zoneMapFrame_1, By.id("reportSelector"));
    private Input bogfoeringsDatoFraField = new Input(Frames.zoneMapFrame_1, By.id("accountingDateFrom"));
    private Input bogfoeringsDatoTilField = new Input(Frames.zoneMapFrame_1, By.id("accountingDateTo"));
    private Input kontoNumre = new Input(Frames.zoneMapFrame_1, By.id("glAccount"));
    private Input eksternKilde = new Input(Frames.zoneMapFrame_1, By.id("rt5AccountSource"));
    private Button genererRapportBtn = new Button(Frames.zoneMapFrame_1, By.id("SAVE_BTN_MP"));


    public Rapporthistorik_PrimaerSubPage chooseBalanceofStatementOfAccount() {
        rapportType.pick("RT01");
        return this;
    }

    public Rapporthistorik_PrimaerSubPage chooseCremul_Finsta() {
        rapportType.pick("RT05");
        return this;
    }

    public Rapporthistorik_PrimaerSubPage chooseDebmul_Finsta() {
        rapportType.pick("RT06");
        return this;
    }

    public void udfyldOgVisRapport(RapportSearchModel rapportSearchModel) {
        if(rapportSearchModel.kontoNummerIsNotEmptyOrNull()) {
            kontoNumre.sendKeys(rapportSearchModel.getKontoNummer());
        }

        if(rapportSearchModel.eksternKildeIDIsNotEmptyOrNull()) {
            eksternKilde.sendKeys(rapportSearchModel.getEksternKildeID());
        }

        if(rapportSearchModel.bogfoeringsDatoFraIsNotNull()) {
            bogfoeringsDatoFraField.sendKeys(rapportSearchModel.getBogfoeringsDatoFraFormatted());
        }

        if(rapportSearchModel.bogfoeringsDatoTilIsNotNull()) {
            bogfoeringsDatoTilField.sendKeys(rapportSearchModel.getBogfoeringsDatoTilFormatted());
        }
        //genererRapportBtn.click(); TODO enable and read file when we have person that fulfils this.
    }
}
