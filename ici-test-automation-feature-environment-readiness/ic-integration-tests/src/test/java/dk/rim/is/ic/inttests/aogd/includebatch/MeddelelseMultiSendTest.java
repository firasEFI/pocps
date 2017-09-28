package dk.rim.is.ic.inttests.aogd.includebatch;

import dk.rim.is.common.entity.brev.BrevEntity;
import dk.rim.is.common.entity.brev.BrevSendStatus;
import dk.rim.is.common.entity.brev.BrevStatusProduktion;
import dk.rim.is.ic.inttests.ContentTypes;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.util.GenericDao;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static dk.rim.is.ic.inttests.SpringContext.buildDao;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static dk.rim.is.ic.inttests.util.TestNTimes.executeTestNTimes;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.DateUtil.now;

public class MeddelelseMultiSendTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(MeddelelseMultiSendTest.class);

    private static final String REQUESTS_PATH = "build/resources/test/aogd/meddelelseMultiSend/";

    private static final String VALID_MEDDELELSE = REQUESTS_PATH + "Request_meddelelse_kunde.xml";
    private static final String jobName = "AogDRequestStatus";

    private static String batchControlUrl;

    private final String BREV_ID = "00000000000000000000000000000999999";

    private GenericDao<BrevEntity> brevDao = buildDao(BrevEntity.class);

    private static void loadProperties() {
        String host = Property.BATCH_REST_HOST.load();
        String endpoint = Property.BATCH_ENDPOINT.load();
        String control = Property.BATCH_REST_CONTROL.load();
        String batchPort = Property.BATCH_REST_PORT.load();

        batchControlUrl = toUrl("http", host, batchPort, endpoint + control);
    }

    @Before
    public void setup() {
        loadProperties();

        cleanUpDb();
    }

    @Test
    public void meddelelseMultiSend_sample_10_messages() throws Exception {
        final int meddelelseMessagesCount = 10;
        saveTestBrevs(meddelelseMessagesCount);

        runIBJob();

        long count = brevDao.getBy(p -> p.getBrevId().equals(BREV_ID)
                && p.getStatusSend() == BrevSendStatus.SENT_TO_AOGD).size();

        assertThat(count).isEqualTo(meddelelseMessagesCount);
    }

    private void runIBJob() throws Exception {
        given().log().all()
                .body(jobName)
                .when().contentType(ContentTypes.APPLICATION_JSON_UTF_8)
                .post(batchControlUrl)
                .then().log().all()
                .statusCode(200);

        executeTestNTimes(this::statusChangedToSentToAogd);
    }

    private void saveTestBrevs(int meddelelseMessagesCount) throws IOException {
        List<BrevEntity> entities = new ArrayList<>();
        for (int i = 0; i < meddelelseMessagesCount; i++) {
            String template = IOUtils.toString(new FileInputStream(new File(VALID_MEDDELELSE)), "UTF-8");
            template = template.replace("{iterator}", "" + i).replace("{randomInt}", "" + new Random().nextInt())
                    .replace("{randomString}", java.util.UUID.randomUUID().toString());

            BrevEntity entity = new BrevEntity();
            entity.setStatusSend(BrevSendStatus.TO_SEND);
            entity.setStatusProduktion(BrevStatusProduktion.Created);
            entity.setBatchId(0L);
            entity.setBrevId(BREV_ID);
            entity.setDatoOprettelse(now());
            entity.setXml(template);

            entities.add(entity);
        }

        brevDao.save(entities);
    }

    private void cleanUpDb() {
        brevDao.delete(p -> p.getBrevId().equals(BREV_ID) && p.getStatusSend() == BrevSendStatus.SENT_TO_AOGD);
    }

    private void statusChangedToSentToAogd() {
        long count = brevDao.getBy(p -> p.getBrevId().equals(BREV_ID) && p.getStatusSend() == BrevSendStatus.SENT_TO_AOGD).size();
        assertThat(count).isGreaterThan(0);
    }
}
