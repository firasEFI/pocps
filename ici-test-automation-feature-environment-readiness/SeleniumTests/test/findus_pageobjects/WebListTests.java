package findus_pageobjects;

import findus_datamodelWarehouses.PsrmUsersWarehouse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import findus.BaseTest;
import findus_datamodelWarehouses.RoleMapWarehouse;
import findus_pageobjects._360_graders_soegning.SkyldnereOgFordringsHavereSearchArgs;
import findus_pageobjects._360_graders_soegning._360GradersSoegningPage;
import icisel.pageobjects.frames.Frames;

public class WebListTests extends BaseTest {

    private WebList _360GradersSøgningResultater;

    @BeforeClass()
    public void ClassSetup() {
        SkyldnereOgFordringsHavereSearchArgs søgeArg = new SkyldnereOgFordringsHavereSearchArgs();
        søgeArg.setFornavn("%");

        // .login(RoleMapWarehouse.SAGSBEHANDLER_GENEREL)
        _360GradersSoegningPage _360GradersSoegningPageWithResults = super.applicationController.startAtLoginNormal(true)
                .LoginMedValidBruger(PsrmUsersWarehouse.getGenerelSagsbehandler())
                .menu().a360GradersSoegning()
                .selectSearchForSkyldnereOgFordringshavere()
                .fillForm(søgeArg)
                .activateSoeg();

        _360GradersSøgningResultater = new WebList(Frames.tabPage, "dataExplorerTable1");
    }

    @Test
    public void Test360GradersSøgningResultater_NavnKollonne() {
        String actualNavn = _360GradersSøgningResultater.getCellValue("Navn", 0);

        Assert.assertEquals(actualNavn, "Asger Rasmussen");
    }

    @Test
    public void Test360GradersSøgningResultater_IdTypeKollonne() {
        String actualIdType = _360GradersSøgningResultater.getCellValue("ID type", 0);

        Assert.assertEquals(actualIdType, "CPR-nummer");
    }

    @Test
    public void Test360GradersSøgningResultater_IdKollonne() {
        String actualId = _360GradersSøgningResultater.getCellValue("ID værdi", 0);

        Assert.assertEquals(actualId, "241182-xxxx");
    }

    @Test
    public void Test360GradersSøgningResultater_KontoKollonne() {
        String actualKonto = _360GradersSøgningResultater.getCellValue("Konto", 0);

        Assert.assertEquals(actualKonto, "0100000005");
    }

    @Test
    public void Test360GradersSøgningResultater_InterntIdKollonne() {
        String actualInterntId = _360GradersSøgningResultater.getCellValue("Internt ID", 0);

        Assert.assertEquals(actualInterntId, "0100000005");
    }

    @Test
    public void Test360GradersSøgningResultater_LookupIdFraNavn() {
        String actualIdFromName = _360GradersSøgningResultater.lookupCellValue("Navn", "Caja Jensen", "ID værdi");

        Assert.assertEquals(actualIdFromName, "050575-xxxx");
    }
}
