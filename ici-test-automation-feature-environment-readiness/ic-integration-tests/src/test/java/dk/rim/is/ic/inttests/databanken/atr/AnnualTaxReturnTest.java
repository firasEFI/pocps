package dk.rim.is.ic.inttests.databanken.atr;


import dk.rim.is.common.CommonURLs;
import dk.rim.is.ic.inttests.Property;
import dk.rim.is.ic.inttests.UrlUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * Created by niar on 18-09-2017.
 */
public class AnnualTaxReturnTest {
    private static final Logger LOG = LoggerFactory.getLogger(AnnualTaxReturnTest.class);

    private static String annualTaxReturnUrl;

    private static final String GET_WITH_DUMMY_CPR = "/0000000000";
    private static final String GET_WITH_FAULTY_DUMMY_CPR = "/0000";

    private static void loadProperties(){
        String protocol = "http";
        String host = Property.IC_INTERNAL_HOST.load();
        String restPort = Property.IC_REST_INTERNAL_PORT.load();

        String endpointPath = CommonURLs.DATABANK.ROOT + CommonURLs.DATABANK.GET_TAXRETURN;

        annualTaxReturnUrl = UrlUtils.toUrl(protocol,host,restPort, endpointPath);

        LOG.info("Using AnnualTaxReturnUrl: " + annualTaxReturnUrl);
    }

    @BeforeClass
    public static void setup() {
        loadProperties();
    }

    @Test
    public void annualTaxReturn_reachEndpoint_correctCprLength() throws Exception {
        given().log().all()
                .when().get(annualTaxReturnUrl + GET_WITH_DUMMY_CPR)
                .then().statusCode(200);
    }

    @Test
    public void annualTaxReturn_reachEndpoint_wrongFormat() throws Exception {
        given().log().all()
                .when().get(annualTaxReturnUrl + GET_WITH_FAULTY_DUMMY_CPR)
                .then().statusCode(200);
    }

    @Test
    public void annualTaxReturn_failToReachEndpoint_noCPR() throws Exception {
        given().log().all()
                .when().get(annualTaxReturnUrl)
                .then().statusCode(404);
    }
}
