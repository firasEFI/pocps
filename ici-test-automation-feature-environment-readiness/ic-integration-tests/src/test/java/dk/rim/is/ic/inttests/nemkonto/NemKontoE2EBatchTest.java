package dk.rim.is.ic.inttests.nemkonto;

import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
import dk.rim.is.ic.inttests.util.GenericDao;
import dk.rim.is.ic.inttests.util.jms.JmsConnector;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.io.IOException;
import java.util.List;

import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus.RETRIEVED_BY_INTEGRATION;
import static dk.rim.is.ic.inttests.Property.NEMKONTO_CHANNEL_NAME;
import static dk.rim.is.ic.inttests.Property.NEMKONTO_HOST;
import static dk.rim.is.ic.inttests.Property.NEMKONTO_PORT;
import static dk.rim.is.ic.inttests.Property.NEMKONTO_QUEUE_MANAGER_NAME;
import static dk.rim.is.ic.inttests.Property.NEMKONTO_RESPONSE_QUEUE_NAME;
import static dk.rim.is.ic.inttests.SpringContext.buildDao;
import static dk.rim.is.ic.inttests.util.BatchControl.initiateBatchProcess;
import static dk.rim.is.ic.inttests.util.TestNTimes.executeTestNTimes;
import static dk.rim.is.ic.inttests.util.file.FileReader.readFile;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 31.01.2017
 */
public class NemKontoE2EBatchTest {
    private static final Logger LOG = LoggerFactory.getLogger(NemKontoE2EBatchTest.class);

    private static final String SAMPLES_PATH = "build/resources/test/nemkonto/";
    private static final String VALID_REQUEST_ENVELOPE = SAMPLES_PATH + "sampleNKSPayment.xml";

    private JmsConnector jmsConnector;
    private GenericDao<IntegrationFileEntity> dao = buildDao(IntegrationFileEntity.class);

    private String msgId;

    @Before
    public void setUp() throws Exception {
        jmsConnector = new JmsConnector(NEMKONTO_RESPONSE_QUEUE_NAME.load(), NEMKONTO_HOST.load(),
                NEMKONTO_PORT.load(), NEMKONTO_CHANNEL_NAME.load(), NEMKONTO_QUEUE_MANAGER_NAME.load());
    }

    @Test
    public void shouldReadFileFromQueue_and_SaveItToDatabase() throws Exception {
        //given
        msgId = sendFileToQueue();

        //when
        initiateBatchProcess("NemKontoReceiveReceipts");

        //then
        executeTestNTimes(this::checkThatFileIsSavedInDatabase);
    }

    private void checkThatFileIsSavedInDatabase() {
        List<IntegrationFileEntity> files = dao.getBy(file -> (msgId + ".xml").equals(file.getFileName()));

        assertThat(files).hasSize(1);
        assertThat(files.get(0).getStatus()).isEqualTo(RETRIEVED_BY_INTEGRATION);
    }

    private String sendFileToQueue() throws IOException, JMSException {
        return jmsConnector.sendMessage(readFile(VALID_REQUEST_ENVELOPE));
    }


}
