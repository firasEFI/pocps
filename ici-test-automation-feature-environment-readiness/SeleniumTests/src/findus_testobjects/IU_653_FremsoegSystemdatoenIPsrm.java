package findus_testobjects;

import findus_pageobjects.BasePsrmPage;
import findus_pageobjects.batchjobindsendelse.BatchjobindsendelsePage;

public class IU_653_FremsoegSystemdatoenIPsrm {

    private BasePsrmPage page;

    public IU_653_FremsoegSystemdatoenIPsrm(BasePsrmPage page) {
        this.page = page;
    }

    /**
     * Funktion går til batchjobindsendelsessiden
     * 
     * Afvigelse fra IU: 
     * Dette testobjekt henter ikke systemdatoen ud af den fremkomne side. Til at gøre dette anvendes 
     * metoden "getPsrmDate" på Batchjobindsendelse_PrimaerSubPage via
     * [fremkommen side].activatePrimaer.getPsrmDate();
     * 
     * @return BatchjobindsendelsePage
     */
    public BatchjobindsendelsePage execute() {

        page
                .menu()
                .batch()
                .batchJobIndsendelse().tilfoej();
                
        return new BatchjobindsendelsePage();
    }
}