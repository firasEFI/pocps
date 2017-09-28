package dk.rim.is.ic.inttests.nets.dmiism;

import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.nets.NetsTestBase;
import dk.rim.is.ic.inttests.util.PathManager;
import org.junit.Test;

import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus.RETRIEVED_BY_INTEGRATION;
import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName.NETS_601;
import static dk.rim.is.ic.inttests.util.TestNTimes.executeTestNTimes;


public class SendFileTestE2E extends NetsTestBase {
    @Test
    public void uplaod_file_then_download_as_a_new_test() throws Exception {
        //given
        String fileUrl = createFileAndStoreFileOnFtp(NETS_601,  CommonURLs.NETS_ENDPOINT.INDBTLS);

        //then
        downloadFromFtp(fileUrl, CommonURLs.NETS_ENDPOINT.INDBTLS);

        executeTestNTimes(() -> fileStatusCheck(RETRIEVED_BY_INTEGRATION));
    }

    @Override
    protected String targetUrl() {
        return PathManager.getIcExternalUriSoapIncoming(Property.IC_NETS_INDISM_PATH.load());
    }
}
