package dk.rim.is.ic.inttests;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Lists all system properties available in various tests in the integration tests project.
 * It also is a utility enum that allows loading properties from system using defaults specified in this enum.
 */
public enum Property {

    //values are defined in apache-deltaspike.properties

    BATCH_DB_HOST("batch.db.host"),
    BATCH_DB_PORT("batch.db.port"),
    BATCH_DB_SID("batch.db.sid"),
    BATCH_DB_USER("batch.db.user"),
    BATCH_DB_PASSWORD("batch.db.password"),
    BATCH_REST_PORT("batch.rest.port"),
    BATCH_REST_HOST("batch.rest.host"),
    BATCH_ENDPOINT("batch.endpoint.path"),
    BATCH_REST_CONTROL("batch.control.path"),

    FTP_HOST("ftp.host"),
    FTP_PORT("ftp.port"),
    FTP_SAMPLE_FILE("ftp.samplefile"),

    IC_HOST("ic.host"),
    IC_INTERNAL_HOST("ic.internal.host"),
    IC_EXTERNAL_HOST("ic.external.host"),
    IC_EXTERNAL_PORT("ic.external.port"),
    IC_REST_EXTERNAL_PORT("ic.rest.external.port"),
    IC_REST_INTERNAL_PORT("ic.rest.internal.port"),
    IC_SOAP_INTERNAL_PROTOCOL("ic.soap.internal.protocol"),
    IC_SOAP_EXTERNAL_PROTOCOL("ic.soap.external.protocol"),
    IC_SOAP_INTERNAL_PORT("ic.soap.internal.port"),
    IC_SOAP_EXTERNAL_PORT("ic.soap.external.port"),
    IC_SOAP_EXTERNAL_INTERNALPORT("ic.soap.external.internalport"),
    
    IC_CSRP_DELETEFILE_PATH("ic.csrp.deleteFile.path"),
    IC_CSRP_DOWNLOADFILE_PATH("ic.csrp.downloadFile.path"),
    IC_CSRP_PERSONFILEREADY_PATH("ic.csrp.personFileReady.path"),
    IC_CSRP_PSMH_PATH("ic.csrp.psmh.path"),

    IC_AOGD_MEDDELELSEMULTISEND_PATH("ic.aogd.meddelelseMultiSend.path"),
    IC_AOGD_MEDDELELSEMULTISENDEKSPRES_PATH("ic.aogd.meddelelseMultiSendEkspres.path"),
    IC_AOGD_MEDDELELSESTATUSMULTIHENT_KLADDE_PATH("ic.aogd.meddelelseStatusMultiHentKladde.path"),
    IC_AOGD_FORMATERETMEDDELELSEINDHOLDMULTIHENT_PATH("ic.aogd.formateretMeddelelseIndholdMultiHent.path"),
    IC_AOGD_FORMATERETMEDDELELSEINDHOLDMULTIHENT_KLADDE_PATH("ic.aogd.formateretMeddelelseIndholdMultiHentKladde.path"),
    IC_AOGD_REQUESTFORMATTEDMESSAGEDOWNLOAD_PATH("ic.aogd.requestFormattedMessageDownload.path"),

    IC_EINDKOMST_ESH_PATH("ic.eindkomst-i.esh.servicePath"),
    IC_EINDKOMST_IOSH_PATH("ic.eindkomst.iosh.servicePath"),
    IC_EINDKOMST_IOSAB_PATH("ic.eindkomst.iosab.servicePath"),
    IC_EINDKOMST_LIA_PATH("ic.eindkomst-i.lia.servicePath"),

    IC_PSRM_RETRIEVEALLCPR_PATH("ic.psrm.retrieveAllCpr.path"),
    IC_PSRM_STATUSUPDATE_PATH("ic.psrm.statusUpdate.path"),
    IC_PSRM_DOCUMENTINFO_PATH("ic.psrm.documentInfo.path"),
    IC_PSRM_DOCUMENTUPDATE_PATH("ic.psrm.documentUpdate.path"),
    IC_PSRM_RECEIVECLAIM_PATH("ic.psrm.receiveClaim.path"),
    IC_PSRM_RECEIVEHOERING_PATH("ic.psrm.receiveHoering.path"),
    IC_PSRM_NOTIFYINFO_PATH("ic.psrm.underretSamling.path"),

    IC_CAPTIA_DOKUMENTOPDATER_PATH("ic.captia.dokumentopdater.servicePath"),
    IC_CAPTIA_DOKUMENTHENT_PATH("ic.captia.dokumenthent.servicePath"),
    IC_CAPTIA_DOKUMENTMULTIOPRET_PATH("ic.captia.dokumentmultiopret.servicePath"),
    IC_CAPTIA_DOKUMENTMULTIOPRET_BESKEDHVISVIRUS_PATH("ic.captia.dokumentmultiopretbeskedhvisvirus.servicePath"),
    IC_CAPTIA_SAGOPDATER_PATH("ic.captia.sagopdater.servicePath"),
    IC_CAPTIA_SAGOPRET_PATH("ic.captia.sagopret.servicePath"),
    IC_CAPTIA_DPDOKUMENTOPRET_PATH("ic.captia.dpdokumentopret.servicePath"),

    IC_DCS_GEI_PATH("ic.dcs.gei.servicePath"),
    IC_DCS_TNPRGSH_PATH("ic.dcs.tnprgsh.servicePath"),
    IC_DCS_SSOSH_PATH("ic.dcs.ssosh.servicePath"),
    IC_DCS_SKATGUID_PATH("ic.dcs.skatGuid"),

    IC_ES_DMIFOH_PATH("ic.es.dmifoh.servicePath"),
    IC_ES_DMIFOH_USERNAME("ic.es.dmifoh.username"),
    IC_ES_DMIFOH_PASSWORD("ic.es.dmifoh.password"),

    IC_ES_VSOSH_PATH("ic.es.vsosh.servicePath"),
    IC_ES_VBFKH_PATH("ic.es.vbfkh.servicePath"),
    IC_ES_VKOSH_PATH("ic.es.vkosh.servicePath"),
    IC_ES_VS_PATH("ic.es.vs.servicePath"),
    IC_ES_EVRH_PATH("ic.es.evrh.servicePath"),
    IC_ES_CNSNRH_PATH("ic.es.cnsnrh.servicePath"),
    IC_ES_VAELRSH_PATH("ic.es.vaelrsh.servicePath"),

    IC_NETS_INDBTLS_PATH("ic.nets.indbtls.servicePath"),
    IC_NETS_INDBTLM_PATH("ic.nets.indbtlm.servicePath"),
    IC_NETS_INDISM_PATH("ic.nets.indism.servicePath"),

    IC_SKB_INDKOLM_PATH("ic.skb.indkolm.local.servicePath"),
    IC_SKB_INDIOLM_PATH("ic.skb.indiolm.local.servicePath"),
    IC_SKB_INDUOLM_PATH("ic.skb.induolm.local.servicePath"),

    NEMKONTO_HOST("nemKonto.host"),
    NEMKONTO_PORT("nemKonto.port"),
    NEMKONTO_CHANNEL_NAME("nemKonto.chanelName"),
    NEMKONTO_QUEUE_MANAGER_NAME("nemKonto.queueManagerName"),
    NEMKONTO_REQUEST_QUEUE_NAME("nemKonto.request.queueName"),
    NEMKONTO_RESPONSE_QUEUE_NAME("nemKonto.response.queueName"),

    IC_SAP38_FINANSKONTO_PATH("ic.sap38.finansKonto.servicePath"),

    IC_SLUT_PERSONAARSOPGOERELSEHENT_PATH("ic.slut.personaarsopgoerelsehent.path"),

    LETTER_GENERATION_PORT_PATH("lettergeneration.port"),
    LETTER_GENERATION_HOST_PATH("lettergeneration.host"),
    LETTER_GENERATION_SERVICE_PATH("lettergeneration.servicePath");

    private static final Logger LOG = LoggerFactory.getLogger(Property.class);

    static {
        LOG.info("Test properties:");
        Arrays.asList(values()).forEach(e -> LOG.info("\t" + e.key + "=" + e.load()));
    }

    private String key;

    public String getKey() {
        return key;
    }

    Property(String key) {
        this.key = key;
    }

    public String load() {
        return ConfigResolver.getPropertyValue(getKey());
    }


}
