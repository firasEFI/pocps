package modules;

import java.text.ParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.Keys;

import icisel.navigation.menu.fluent.MenuNavigator;
import icisel.pageobjects.elements.Input;
import icisel.testng.TestContext;
import icisel.utils.driver.patient.PatientWebDriver;
import pageobjects.generic.PO_Opret_Opgave;
import utils.tools.TO_Tools;

public class MO_OpretOpgave {

    /**
     * Funktion opretter opgave manuelt gennem topmenuen. Argumenterne styrer alle valg undervejs i opgaveoprettelsen. 
     * Denne funktion overskriver ikke de prædefinerede felter: Prioritet, beskrivelse og tidligste slutdato
     * @param testContext = testContext
     * @param opgavekategori = Skriv teksten som den muligheden fremgår af PSRM
     * @param opgavetype = Skriv teksten som muligheden fremgår af PSRM
     * @param fristDageFraNu = Indsæt antal dage som fristen skal sættes til
     * @param inkluderetID = ID kan være følgende: Part: SkyldnerID, Fordring: Internt fordringsID, Sag: SagsID
     * @param sendTil = Enten "Bruger" eller "Rolle"
     * @param modtager = For Bruger: Brugerens initialer, For Rolle: Rolles initialer, fx DK-CASWGEN for Sagsbehandler generel 
     * @return = opgave ID som string
     */
    public static String opretOpgaveManuelt(TestContext testContext, String opgavekategori, String opgavetype, String fristDageFraNu, String inkluderetID, String sendTil, String modtager) {
        //Gå til opret opgave manuelt og vælg ønsket opgavekategori- og type
        opretOpgaveManuelt_Start(testContext, opgavekategori, opgavetype);

        //Indtast evt. besked, frist, inkluderet ID og vælg hvem opgaven skal sendes til før der trykkes gem og lukkes
        String opgaveID = opretOpgaveManuelt_Slut(testContext, opgavekategori, opgavetype, fristDageFraNu, inkluderetID, sendTil, modtager);
        
        return opgaveID;
    }

    /**
     * Funktion opretter opgave manuelt gennem topmenuen. Argumenterne styrer alle valg undervejs i opgaveoprettelsen. 
     * Denne funktion overskriver ikke de prædefinerede felter: Prioritet, beskrivelse og tidligste slutdato
     * @param testContext = testContext
     * @param opgavekategori = Skriv teksten som den muligheden fremgår af PSRM
     * @param opgavetype = Skriv teksten som muligheden fremgår af PSRM
     * @param fristDageFraNu = Indsæt antal dage som fristen skal sættes til
     * @param inkluderetID = ID kan være følgende: Part: SkyldnerID, Fordring: Internt fordringsID, Sag: SagsID
     * @param sendTil = Enten "Bruger" eller "Rolle"
     * @param modtager = For Bruger: Brugerens initialer, For Rolle: Rolles initialer, fx DK-CASWGEN for Sagsbehandler generel 
     * @param prioritet = Skriv teksten som den muligheden fremgår af PSRM. OBS: Skriv "null" hvis ikke denne skal overskrives
     * @param beskrivelse = Indtast tekst som ønskes skrevet i ny beskrivelse. OBS: Skriv "null" hvis ikke denne skal overskrives
     * @param tidligsteSlutdatoDageFraNu = indsæt int med antallet af dage fra systemets tid, som datoen ønskes sat til
     * @param psrmDato = Indsæt systemets dato, som datoen tager udgangspunkt i. OBS: Skriv "null" hvis ikke tidligste slutdato skal overskrives.
     * @return = opgave ID som string
     * @throws ParseException 
     */
    public static String opretOpgaveManuelt_OverskrivFelter(TestContext testContext, String opgavekategori, String opgavetype, String fristDageFraNu, String inkluderetID, String sendTil, String modtager, String prioritet, String beskrivelse, int tidligsteSlutdatoDageFraNu, String psrmDato) throws ParseException {
        //Gå til opret opgave manuelt og vælg ønsket opgavekategori- og type
        opretOpgaveManuelt_Start(testContext, opgavekategori, opgavetype);

        //Overskriv oprindelig prioritet hvis ikke denne er sat til null
        if (!prioritet.equalsIgnoreCase(null))
            pickPrioritet(testContext, prioritet);
        
        //Overskriv oprindelig beskrivelse
        if (!beskrivelse.equalsIgnoreCase(null))
            indtastBeskrivelse(beskrivelse);
        
        //Overskriv tidligste slutdato
        if (!psrmDato.equalsIgnoreCase(null))
            indtastTidligsteSlutdato(tidligsteSlutdatoDageFraNu, psrmDato);

        //Indtast evt. besked, frist, inkluderet ID og vælg hvem opgaven skal sendes til før der trykkes gem og lukkes
        String opgaveID = opretOpgaveManuelt_Slut(testContext, opgavekategori, opgavetype, fristDageFraNu, inkluderetID, sendTil, modtager);
   
        return opgaveID;
    }
    
    public static void opretOpgaveManuelt_Start(TestContext testContext, String opgavekategori, String opgavetype){
        //Gå igennem topmenuen til opgave, opret opgave manuelt
        goToOpretOpgaveManuelt(testContext);

        //Vælg opgavekategori
        pickOpgavekategori(testContext, opgavekategori);

        // Vælg opgavetype
        pickOpgavetype(testContext, opgavetype);
    }
    
    public static String opretOpgaveManuelt_Slut(TestContext testContext, String opgavekategori, String opgavetype, String fristDageFraNu, String inkluderetID, String sendTil, String modtager){
     // Udfyld besked - Boks forekommer kun hvis opgavekategori = "Udvælge skyldnere" eller hvis opgavetype indeholder "Manuel opgave - "
        if (opgavekategori.equalsIgnoreCase("Udvælge Skyldnere") || opgavetype.contains("Manuel opgave - "))
            indtastBesked();
        
        // indtast frist
        indtastFrist(fristDageFraNu);

        //Indtast ID (fordring, part, sag mm.)
        indtastInkluderetID(testContext, inkluderetID);       

        // Udfyld "send til"
        pickSendTil(sendTil);

        //Vælg hvem der er modtageren 
        if (sendTil.equalsIgnoreCase("Bruger"))
            indtastTildeltSagsbehandler(testContext, modtager);
        else if (sendTil.equalsIgnoreCase("Rolle"))
            indtastTildeltGruppe(testContext, modtager);

        //Klik Gem
        klikGem(testContext);

        // Luk vindue
        String opgaveID = klikLukVindue(testContext);
        
        return opgaveID;
    }
    
    public static void goToOpretOpgaveManuelt(TestContext testContext) {
        //Gå via topmenuen til opgave, opret opgave manuelt
        MenuNavigator.menu().opgave().opretOpgaveManuelt();

        //Vent på at siden er loaded
        testContext.waitForPageToBeLoaded();        
    }

    public static void pickOpgavekategori(TestContext testContext, String valgtOpgavekategori) {

        // Map alle mulighder
        HashMap<String, String> opgavekategori = new HashMap<String, String>();
        opgavekategori.put("Administrere anden aktør", "HandleAgent");
        opgavekategori.put("Administrere skyldner", "HandleDebtor");
        opgavekategori.put("Afdragsordning", "Payplan");
        opgavekategori.put("Afregne", "Settlement");
        opgavekategori.put("Afskrive", "Writeoff");
        opgavekategori.put("Behandle klager", "Complaints");
        opgavekategori.put("Bistandsanmodning til og fra udlandet", "Foreigncase");
        opgavekategori.put("Henstand", "Deferral");
        opgavekategori.put("Håndtere dokumenter og oplysninger", "Documents");
        opgavekategori.put("Håndtere forældelse", "Limitation");
        opgavekategori.put("Håndtere sager", "HandleCases");
        opgavekategori.put("Håndtere udbetalinger", "Payouts");
        opgavekategori.put("Modtag og håndter betalinger", "Payments");
        opgavekategori.put("Modtage og håndtere fordringer", "Claims");
        opgavekategori.put("Nedskrivning", "Writedowns");
        opgavekategori.put("Oprette og håndtere inddrivelsesrenter", "RecoveryInterests");
        opgavekategori.put("Påkrav", "FirstDemand");
        opgavekategori.put("Udlæg", "Garnishment");
        opgavekategori.put("Udvælge Skyldnere", "ChooseDebtors");

        PO_Opret_Opgave.drp_Opgavekategori.pick(opgavekategori.get(valgtOpgavekategori));
        
        testContext.waitForPageToBeLoaded();
    }


    public static void pickOpgavetype(TestContext testContext, String opgavetype) {
        //Vælg opgavetype udfra synlig tekst
        PO_Opret_Opgave.drp_Opgavetype.pickByVisibleText(opgavetype);

        //vent på at side er loaded
        testContext.waitForPageToBeLoaded();
    }
    
    public static void pickPrioritet(TestContext testContext, String prioritet) {
        //Vælg opgavetype udfra synlig tekst
        PO_Opret_Opgave.drp_Prioritet.pickByVisibleText(prioritet);

        //vent på at side er loaded
        testContext.waitForPageToBeLoaded();
    }

    public static void indtastBeskrivelse(String beskrivelse) {
        //Clear felt
        PO_Opret_Opgave.input_Beskrivelse.clear();
        
        //Indsæt ny beskrivelse
        PO_Opret_Opgave.input_Beskrivelse.sendKeys(beskrivelse);
    }
    
    public static void indtastBesked(){
        //Indtast beskednote
        PO_Opret_Opgave.input_Besked.sendKeys("Test af beskedfelt");
    }
    
    public static void indtastTidligsteSlutdato(int tidligsteSlutdatoDageFraNu, String psrmDato) throws ParseException {
        //Clear felt
        PO_Opret_Opgave.input_TidligsteSlutdato.clear();
        
        //Indsæt tidligste slutdato
        PO_Opret_Opgave.input_TidligsteSlutdato.sendKeys(TO_Tools.getPsrmDatoPlus_DDMMYYYY(psrmDato, tidligsteSlutdatoDageFraNu));
    }
    
    public static void indtastFrist(String fristDageFraNu) {
        //Clear felt
        PO_Opret_Opgave.input_Frist.clear();
        
        //Indsæt frist (antal dage fra nu)
        PO_Opret_Opgave.input_Frist.sendKeys(fristDageFraNu);
    }


    public static void indtastInkluderetID(TestContext testContext, String inkluderetID) {
        PatientWebDriver driver = testContext.getDriver();

        System.out.println("Text: "+PO_Opret_Opgave.txt_InkluderetIdType.getText());
        
        //Indtast inkluderet ID
        getEnabledOpgaveSearchInput().sendKeys(inkluderetID);
            
        //Tryk "TAB" for at komme ud af tekstfeltet
        TO_Tools.trykKnap(Keys.TAB);
    }

    public static Input getEnabledOpgaveSearchInput() {
        Input[] inputs = {PO_Opret_Opgave.input_PartID, PO_Opret_Opgave.input_FordringsID, PO_Opret_Opgave.input_SagsID};
        for (Input input: inputs) {
            if (input.isDisplayed()) {
                return input;
            } 
        }
        throw new NoSuchElementException("Ingen af inputs Part, Fordring, eller Sag er enabled.");
    }
    
    public static void pickSendTil(String modtagergruppe) {

        // Map alle mulighder
        HashMap<String, String> sendTil = new HashMap<String, String>();
        sendTil.put("Bruger", "SNDU");
        sendTil.put("Rolle", "SNDR");

        PO_Opret_Opgave.drp_SendTil.pick(sendTil.get(modtagergruppe));
    }

    public static void indtastTildeltSagsbehandler(TestContext testContext, String modtager){
        PatientWebDriver driver = testContext.getDriver();

        //Indtast sagsbehandlerens identifikator (initialer)
        PO_Opret_Opgave.input_TildeltSagsbehandler.sendKeys(modtager);

        //Tryk "TAB" for at komme ud af tekstfeltet
        TO_Tools.trykKnap(Keys.TAB);
    }
    
    public static void indtastTildeltGruppe(TestContext testContext, String modtager){
        PatientWebDriver driver = testContext.getDriver();

        //Indtast gruppens identifikator, fx DK-CASWGEN for Sagsbehandler generel
        PO_Opret_Opgave.input_TildeltGruppe.sendKeys(modtager);

        //Tryk "TAB" for at komme ud af tekstfeltet
        TO_Tools.trykKnap(Keys.TAB);
    }

    public static void klikGem(TestContext testContext){
        //Klik på Gem
        PO_Opret_Opgave.btn_Gem.click();
        
        //Vent til at siden er loaded
        testContext.waitForPageToBeLoaded();
        
        //Vent til siden indeholder den rigtige tekst i topbaren
        MO_Utilities.ventTilFeltIndeholderTekst(PO_Opret_Opgave.txt_TopbarText, "Visning af oprettet opgave");
        
        //Screenshot
        testContext.doScreenshot();
    }
    
    public static String klikLukVindue(TestContext testContext){
        //Hent opgaveID'et
        String opgaveID = PO_Opret_Opgave.txt_OpgaveID.getText();
        
        //Klik på krydset i højre hjørne for at lukke vinduet
        PO_Opret_Opgave.btn_luk.click();
        
        testContext.waitForPageToBeLoaded();
        
        return opgaveID;
    }
    
}
