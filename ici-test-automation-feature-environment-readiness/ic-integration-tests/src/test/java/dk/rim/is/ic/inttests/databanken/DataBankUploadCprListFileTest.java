package dk.rim.is.ic.inttests.databanken;

import dk.rim.is.clock.TimeTravelDateProvider;
import dk.rim.is.common.CommonURLs.DATABANK;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_JSON;;
import static dk.rim.is.ic.inttests.Property.IC_HOST;
import static dk.rim.is.ic.inttests.Property.IC_REST_EXTERNAL_PORT;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

/**
 *
 * Created by maho on 21.09.2017.
 */
public class DataBankUploadCprListFileTest {
    
    private static String formatedDate = DateFormatUtils.format(TimeTravelDateProvider.getInstance().getDate(), "yyyyMMdd");
    private static final String CHECKSUM_FILE_NAME = "ici_skyldnereOK.D" + formatedDate;

    private static String dataBankUploadCprsListUrl = toUrl("http", IC_HOST, IC_REST_EXTERNAL_PORT, DATABANK.ROOT + DATABANK.UPLOAD_CPR_LIST_FILE);

    @Test
    public void uploadFile_whenValid_thenStatusCode200() throws Exception {
        given().log().all()
              .urlEncodingEnabled(true)
              .body("{}")
              .when().contentType(APPLICATION_JSON).post(dataBankUploadCprsListUrl)
              .then().log().all()
              .statusCode(SC_OK)
              .body(containsString(CHECKSUM_FILE_NAME));
    }
}
