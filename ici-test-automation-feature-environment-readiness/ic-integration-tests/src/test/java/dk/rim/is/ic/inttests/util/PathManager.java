package dk.rim.is.ic.inttests.util;

import dk.rim.is.ic.inttests.Property;

import static dk.rim.is.ic.inttests.UrlUtils.toUrl;

/**
 * Created by mlo on 24-02-2017.
 */
public class PathManager {
    private static final String icProtocol = Property.IC_SOAP_EXTERNAL_PROTOCOL.load();
    private static final String hostInternal = Property.IC_SOAP_INTERNAL_PROTOCOL.load();
    private static final String hostExternal = Property.IC_EXTERNAL_HOST.load();
    private static final String portInternal = Property.IC_SOAP_INTERNAL_PORT.load();
    private static final String soapPortExternal = Property.IC_SOAP_EXTERNAL_PORT.load();
    private static final String soapPortExternalInternal = Property.IC_SOAP_EXTERNAL_INTERNALPORT.load();
    private static final String restPortExternal = Property.IC_REST_EXTERNAL_PORT.load();
    private static final String hostBatch = Property.BATCH_REST_HOST.load();
    private static final String portBatch = Property.BATCH_REST_PORT.load();

    private static String getIcInternalUri(){
        String soapProtocol = Property.IC_SOAP_INTERNAL_PROTOCOL.load();
        String host = Property.IC_INTERNAL_HOST.load();
        return soapProtocol + "://" + host;
    }
    public static String getIcExternalUriSoapIncoming(String endpoint){
        return toUrl(icProtocol, hostExternal, soapPortExternal, endpoint);
    }

    public static String getIcExternalUriSoap(String endpoint){
        return toUrl(icProtocol, hostExternal, portInternal, endpoint);
    }

    public static String getIcExternalUriRest(String endpoint){
        return toUrl(icProtocol, hostExternal, restPortExternal, endpoint);
    }

    public static String getIcInternalUriSoap(){
        String port = Property.IC_SOAP_INTERNAL_PORT.load();

        return getIcInternalUri() + ":" + port + "/";
    }

    public static String getIcInternalUriRest(String endpoint){
        String port = "7017"; //just workaround

        return toUrl(icProtocol, hostExternal, soapPortExternal, endpoint);
    }

    public static String getIcExternalUriSoapExternal(String endpoint){
        return toUrl(icProtocol, hostExternal, soapPortExternal, endpoint);
    }

    public static String getIcExternalUriSoapInternal(String endpoint){
        return toUrl(icProtocol, hostExternal, soapPortExternalInternal, endpoint);
    }

    public static String getIbUri(String endpoint){
       return toUrl("http", hostBatch, portBatch, endpoint);
    }
}
