package dk.rim.is.ic.inttests.ftpgw;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
import dk.rim.is.ic.inttests.nets.NetsTestBase;
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


public class FileHandleTestE2E extends NetsTestBase {
    @Test
    public void uplad_test() throws Exception {
        //given
        createFileAndStoreFileOnFtp();

    }

    @Test
    public void first_uplad_then_download_as_a_new_file_test() throws Exception {
        //given
        String fileUrl = createFileAndStoreFileOnFtp();

        //then
        downloadFromFtp(fileUrl, CommonURLs.NETS_ENDPOINT.INDBTLS);

        executeTestNTimes(this::fileStatusChangedToSuccess);
    }

    private String createFileAndStoreFileOnFtp() throws IOException {
        fileInfo = netsUtil.insertTestIntegrationFile(IOUtils.toString(new FileInputStream(new File(SAMPLE_DATA)), "UTF-8"), NETS_601);

        return uploadFileToFtp(fileInfo, CommonURLs.NETS_ENDPOINT.INDBTLS);
    }

    private void fileStatusChangedToSuccess() {
        List<IntegrationFileEntity> files = fileDao.getBy(entity -> fileInfo.getFileName().equals(entity.getFileName()) &&
                entity.getStatus() == RETRIEVED_BY_INTEGRATION);

        assertThat(files).hasSize(1);
    }

    @Override
    protected String targetUrl() {
        return null;
    }

}
