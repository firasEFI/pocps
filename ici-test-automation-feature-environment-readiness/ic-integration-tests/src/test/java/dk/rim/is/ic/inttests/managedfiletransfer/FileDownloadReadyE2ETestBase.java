package dk.rim.is.ic.inttests.managedfiletransfer;

import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileTypeEntity;
import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.util.GenericDao;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus.RETRIEVED_BY_INTEGRATION;
import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.SpringContext.buildDao;
import static dk.rim.is.ic.inttests.util.TestNTimes.executeTestNTimes;
import static dk.rim.is.ic.inttests.util.file.IntegrationFileDbUtil.checkThatTablesExist;
import static dk.rim.is.ic.inttests.util.file.IntegrationFileDbUtil.clearAllRows;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration test for DMIBTLM - receiving information from Nets that file is ready to be downloaded.
 */
public abstract class FileDownloadReadyE2ETestBase {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadReadyE2ETestBase.class);

    private GenericDao<IntegrationFileEntity> fileDao = buildDao(IntegrationFileEntity.class);
    private GenericDao<IntegrationFileTypeEntity> fileTypeDao = buildDao(IntegrationFileTypeEntity.class);

    @Test
    public void sendFileDownloadReady_thenItShouldBeStoredInDb() throws Exception {
        //when
        Response response = sendFileReadyToDownloadRequest();

        //then
        checkThatResponseIsCorrect(response);

        //and then
        executeTestNTimes(this::fileStatusChangedToRetrieved);
    }

    private Response sendFileReadyToDownloadRequest() {
        //@formatter:off
        return given()
                        .log().all()
                        .urlEncodingEnabled(true)
                        .body(new File(pathToRequestFile()))
                .when()
                        .contentType(APPLICATION_XML_UTF_8)
                        .post(icUrl());
        //@formatter:on
    }

    protected abstract String pathToRequestFile();

    private String icUrl() {
        String protocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
        String host = Property.IC_HOST.load();
        String port = Property.IC_SOAP_EXTERNAL_PORT.load();
        String path = icRequestPath();

        String url = protocol + "://" + host + ":" + port + "/" + path;

        LOG.info("Using Integration-Component URL: " + url);

        return url;
    }

    protected abstract String icRequestPath();

    private void checkThatResponseIsCorrect(Response response) {
        //@formatter:off
        response.then()
                .log().all()
                .assertThat()
                        .statusCode(200)
                        .body(containsString("StyretFiloverførselModtagAnmod_O"));
        //@formatter:on
    }

    private void fileStatusChangedToRetrieved() {
        List<IntegrationFileEntity> files = fileDao.getAll();
        assertThat(files).hasSize(1);
        assertThat(files.get(0).getStatus()).isEqualTo(RETRIEVED_BY_INTEGRATION);
        assertThat(files.get(0).getStep()).isEqualTo(" ");
        assertThat(files.get(0).getIntegrationFileType()).isEqualTo(fileType());
    }

    private IntegrationFileTypeEntity fileType() {
        return fileTypeDao.getBy(entity -> testedFileType().equals(entity.getFileTypeName())).get(0);
    }

    protected abstract IntegrationFileTypeName testedFileType();

    @BeforeClass
    public static void prepareDatabase() {
        checkThatTablesExist();
        clearAllRows();
    }
}
