package dk.rim.is.ic.inttests.nets.dmibtls;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.nets.NetsTestBase;
import dk.rim.is.ic.inttests.util.PathManager;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus.RETRIEVED_BY_INTEGRATION;
import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName.NETS_601;
import static dk.rim.is.ic.inttests.util.TestNTimes.executeTestNTimes;
import static org.assertj.core.api.Assertions.assertThat;


public class IncomingModtagAnmodTestE2E extends NetsTestBase {
    @Test
    public void incoming_modtaganmod() throws Exception {
        //given
        createFileAndStoreFileOnFtp();

        //when
        simulateFtpgwModtagAnmodCall("INDBetalingsoplysningerTraekListeModtagService");

        //then
        executeTestNTimes(this::fileStatusChangedToSuccess);
    }

    private void createFileAndStoreFileOnFtp() throws IOException {
        fileInfo = netsUtil.insertTestIntegrationFile(IOUtils.toString(new FileInputStream(new File(SAMPLE_DATA)), "UTF-8"), NETS_601);

        uploadFileToFtp(fileInfo, CommonURLs.NETS_ENDPOINT.INDBTLM);
    }

    private void fileStatusChangedToSuccess() {
        List<IntegrationFileEntity> files = fileDao.getBy(entity -> fileInfo.getFileName().equals(entity.getFileName()) &&
                                                          entity.getStatus() == RETRIEVED_BY_INTEGRATION);
        assertThat(files).hasSize(1);
    }

    @Override
    protected String targetUrl() {

        return PathManager.getIcExternalUriSoapIncoming(Property.IC_NETS_INDBTLS_PATH.load());
    }
}
