package dk.rim.is.abt.util.configuration

import org.apache.deltaspike.core.api.config.ConfigResolver

/**
 * Created by mlo on 30-05-2017.
 */
class ConfigurationProvider {
    private static properties = readProperties()

    static def readProperties() {
        def  properties = new Properties();
        BufferedInputStream propFile = ConfigurationProvider.getClass().getResourceAsStream("/META-INF/apache-deltaspike.properties")

        properties.load(propFile)

        properties
    }

    static def getTestUtilUrl(){
        "http://" + ConfigResolver.getPropertyValue("testutil.host") + ":" + ConfigResolver.getPropertyValue("testutil.port") + "/test-util-service/";
    }

    static def getTestUtilService(){
        getTestUtilUrl() + "FtpService"
    }

    static FtpConnectionInfo getFtpgwInfo() {
        FtpConnectionInfo result = new FtpConnectionInfo()
        result.server =  properties['ftpgw.ftp.host']
        result.port =  properties['ftpgw.ftp.port']
        result.username =  properties['ftpgw.ftp.user']
        result.password =  properties['ftpgw.ftp.password']
        result.dictionary =  properties['ftpgw.ftp.dictionary']

        result
    }

    static String getNemKonto(def value) {
        properties['nemKonto.' + value]
    }

    static FtpConnectionInfo getCsrpInfo() {
        FtpConnectionInfo result = new FtpConnectionInfo()
        result.server =  properties['csrp.ftp.host']
        result.port =  properties['csrp.ftp.port']
        result.username =  properties['csrp.ftp.user']
        result.password =  properties['csrp.ftp.password']
        result.dictionary =  properties['csrp.ftp.dictionary']

        result
    }

    static FtpConnectionInfo getDataBankInfo() {
        FtpConnectionInfo result = new FtpConnectionInfo()
        result.server =  properties['databank.ftp.host']
        result.port =  properties['databank.ftp.port']
        result.username =  properties['databank.ftp.username']
        result.password =  properties['databank.ftp.password']
        result.dictionary = '/' + properties['databank.ftp.folder.in'] + "/"

        result
    }
}
