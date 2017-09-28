package dk.rim.is.ic.inttests.aogd;

import dk.rim.is.common.entity.brev.BrevEntity;
import dk.rim.is.ic.inttests.util.GenericDao;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static dk.rim.is.ic.inttests.ContentTypes.APPLICATION_XML_UTF_8;
import static dk.rim.is.ic.inttests.Property.IC_AOGD_MEDDELELSEMULTISEND_PATH;
import static dk.rim.is.ic.inttests.Property.IC_INTERNAL_HOST;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.SpringContext.buildDao;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Only for MLO testing
 */
public class MeddelelseMultiSendTest_MLO {

    private static final String REQUESTS_PATH = "build/resources/test/aogd/meddelelseMultiSend/";

    private static final String VALID_PATH = REQUESTS_PATH + "ValidRequest.xml";

    private static final String BREV_ID = "98";

    private static String meddelelseMultiSendUrl;

    GenericDao<BrevEntity> brevDao = buildDao(BrevEntity.class);

    @BeforeClass
    public static void loadProperties() {
        meddelelseMultiSendUrl = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_INTERNAL_HOST, IC_SOAP_INTERNAL_PORT, IC_AOGD_MEDDELELSEMULTISEND_PATH);
    }

    @Test
    //@Ignore //TODO on MLO requirement: verify why it is so slow
    public void meddelelseMultiSend_whenValid_thenStatusCode200() throws Exception {
        clearBrev();

        given().log().all()
                .body(new File(VALID_PATH))
                .when().contentType(APPLICATION_XML_UTF_8).post(meddelelseMultiSendUrl)
                .then().log().all()
                .statusCode(200);

        assertThat(brevDao.getBy(p -> p.getBrevId().equals(BREV_ID)).size(), is(1));
        clearBrev();
    }

    private void clearBrev() {
        brevDao.delete(p -> p.getBrevId().equals(BREV_ID));
    }
}
