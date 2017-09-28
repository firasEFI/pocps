package dk.rim.is.ic.inttests.managedfiletransfer.skb;

import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.managedfiletransfer.FileDownloadReadyE2ETestBase;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 06.02.2017
 */
public class SkbCremulDownload_E2ETest extends FileDownloadReadyE2ETestBase {
    private static final String TEST_REQUESTS_FOLDER_PATH = "build/resources/test/skb/";
    private static final String TEST_FILE_NAME = "FileDownloadReady_ValidRequest_envelope.xml";

    @Override
    protected String pathToRequestFile() {
        return TEST_REQUESTS_FOLDER_PATH + TEST_FILE_NAME;
    }

    @Override
    protected String icRequestPath() {
        return Property.IC_SKB_INDIOLM_PATH.load();
    }

    @Override
    protected IntegrationFileTypeName testedFileType() {
        return IntegrationFileTypeName.SKB_CREMUL;
    }
}
