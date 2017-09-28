package dk.rim.is.ic.inttests.psrm.receiveclaim;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

import static dk.rim.is.ic.inttests.Property.IC_INTERNAL_HOST;
import static dk.rim.is.ic.inttests.Property.IC_PSRM_RECEIVECLAIM_PATH;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PORT;
import static dk.rim.is.ic.inttests.Property.IC_SOAP_INTERNAL_PROTOCOL;
import static dk.rim.is.ic.inttests.UrlUtils.toUrl;
import static dk.rim.is.ic.inttests.util.file.FileReader.getResourceFileAsString;
import static java.lang.ClassLoader.getSystemClassLoader;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReceiveClaimTestHelper {
    private static final String AKTION_ID_PLACEHOLDER = "CHANGE_MFAktionID";
    static final String ACTION_CODE = "Envelope.Body.MFFordringIndberet_O.FordringInfo.FordringAktionStatusSamling.MFAktionStruktur.MFAktionKode";
    static final String ACTION_STATUS_CODE = "Envelope.Body.MFFordringIndberet_O.FordringInfo.FordringAktionStatusSamling.MFAktionStruktur.MFAktionStatusKode";
    static final String AFVIST_CODE = "Envelope.Body.MFFordringIndberet_O.FordringInfo.FordringAktionStatusSamling.MFAktionStruktur.AfvistÅrsagSamling.MFAktionAfvistStruktur.MFAktionAfvistNummer";
    static final String AFVIST_MESSAGE = "Envelope.Body.MFFordringIndberet_O.FordringInfo.FordringAktionStatusSamling.MFAktionStruktur.AfvistÅrsagSamling.MFAktionAfvistStruktur.MFAktionAfvistTekst";
    static final String SAMPLES_PATH = "psrm/receiveClaim/";
    static final String RECEIVE_CLAIM_URL = toUrl(IC_SOAP_INTERNAL_PROTOCOL, IC_INTERNAL_HOST, IC_SOAP_INTERNAL_PORT, IC_PSRM_RECEIVECLAIM_PATH);

    private ReceiveClaimTestHelper() {
    }

    public static String readFileAndReplaceAktionId(String requestFilePath, String uniqueActionId) throws IOException {
        return getResourceFileAsString(requestFilePath).replace(AKTION_ID_PLACEHOLDER, uniqueActionId);
    }
}
