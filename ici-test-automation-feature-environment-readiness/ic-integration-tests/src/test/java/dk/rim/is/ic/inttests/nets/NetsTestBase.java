package dk.rim.is.ic.inttests.nets;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import dk.rim.is.common.CommonURLs;
import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus;
import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName;
import dk.rim.is.ic.inttests.util.GenericDao;
import dk.rim.is.ic.inttests.util.PathManager;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.SpringContext.buildDao;
import static dk.rim.is.ic.inttests.SpringContext.resolve;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by mlo on 22-02-2017.
 */
public abstract class NetsTestBase {
    private static final String STYRETFILOVERFORSEL_PATH = "styretfiloverforsel/";
    protected static final String MODTAG_ANMOD = STYRETFILOVERFORSEL_PATH + "StyretFiloverfoerselModtagAnmod_I_Request.xml";

    private static String IC_NETS_UPLOAD_URL = PathManager.getIcExternalUriRest(CommonURLs.FTPGW.ROOT + CommonURLs.FTPGW.UPLOAD_FILE);
    private static String IC_NETS_DOWNLOAD_URL = PathManager.getIcExternalUriRest(CommonURLs.FTPGW.ROOT + CommonURLs.FTPGW.DOWNLOAD_FILE);

    private static final String RESOURCES_PATH = "build/resources/test/nets/";
    protected static final String SAMPLE_DATA = RESOURCES_PATH + "sample/sample.dat";

    protected NetsUtil netsUtil = resolve(NetsUtil.class);
    protected IntegrationFileEntity fileInfo;

    protected GenericDao<IntegrationFileEntity> fileDao = buildDao(IntegrationFileEntity.class);

    abstract protected String targetUrl();

    protected void simulateFtpgwModtagAnmodCall(String serviceQName) throws IOException {
        URL messageBodyUrl = Resources.getResource(MODTAG_ANMOD);
        String messageBody = Resources.toString(messageBodyUrl, Charsets.UTF_8);

        messageBody = messageBody.replace("{serviceName}", serviceQName)
                .replace("{fileLocation}", "/out/" + fileInfo.getFileName());

        given().log().all()
                .body(messageBody)
                .when().contentType(APPLICATION_XML_UTF_8)
                .post(targetUrl())
                .then().log().all()
                .statusCode(200)
                .body(containsString("StyretFiloverførselModtagAnmod_O"));
    }

    protected String uploadFileToFtp(IntegrationFileEntity file, String targetSystem) {
        return given()
                .log().all()
                .header("TransaktionsID", "someTransaktionsID")
                .body("{\"fileId\":"+file.getId()+", \"targetSystem\":\""+targetSystem+"\"}")
                .when()
                .post(IC_NETS_UPLOAD_URL)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("fileUrl", containsString(file.getFileName()))
                .extract().path("fileUrl");
    }

    protected String createFileAndStoreFileOnFtp(IntegrationFileTypeName type, String endpoint) throws IOException {
        fileInfo = netsUtil.insertTestIntegrationFile(IOUtils.toString(new FileInputStream(new File(SAMPLE_DATA)), "UTF-8"), type);

        return uploadFileToFtp(fileInfo, endpoint);
    }


    protected int downloadFromFtp(String filePath, String targetSystem) {
        return given()
                .log().all()
                .header("TransaktionsID", "someTransaktionsID")
                .body("{\"fileUrl\":\""+filePath+"\", \"targetSystem\":\""+targetSystem+"\"}")
                .when()
                .post(IC_NETS_DOWNLOAD_URL)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("fileUrl", containsString(filePath))
                .extract().path("fileId");

    }

    protected void fileStatusCheck(IntegrationFileStatus statusToCheck) {
        List<IntegrationFileEntity> files = fileDao.getBy(entity -> fileInfo.getFileName().equals(entity.getFileName()) &&
                entity.getStatus() == statusToCheck);
        assertThat(files).hasSize(1);
    }
}
