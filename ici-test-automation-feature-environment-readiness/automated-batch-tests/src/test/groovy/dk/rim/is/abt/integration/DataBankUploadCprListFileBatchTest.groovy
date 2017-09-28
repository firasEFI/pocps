package dk.rim.is.abt.integration

import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.FtpDownloader
import dk.rim.is.abt.util.configuration.ConfigurationProvider
import dk.rim.is.clock.TimeTravelDateProvider
import org.apache.commons.lang.time.DateFormatUtils
import org.junit.Test


/**
 *
 * Created by maho on 21.09.2017.
 */
class DataBankUploadCprListFileBatchTest {

    private static String formatedDate = DateFormatUtils.format(TimeTravelDateProvider.getInstance().getDate(), "yyyyMMdd");

    private static final String FILE_NAME =  "ici_skyldnere.D" + formatedDate;
    private static final String CHECKSUM_FILE_NAME = "ici_skyldnereOK.D" + formatedDate;

    @Test
    void shouldExecuteBatchJob_and_uploadFtpFiles() throws Exception {

        def jobStatus = BatchController.runAndReportIntegrationJob ("DataBankUploadCprListFile")
        assert jobStatus.executionStatus == "SUCCESS"

        def ftpFiles = FtpDownloader.listFiles(ConfigurationProvider.getDataBankInfo())
        assert ftpFiles.contains(FILE_NAME)
        assert ftpFiles.contains(CHECKSUM_FILE_NAME)
    }
}
