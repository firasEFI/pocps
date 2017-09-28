package dk.rim.is.abt.util

import dk.rim.is.abt.util.configuration.ConfigurationProvider
import dk.rim.is.abt.util.configuration.FtpConnectionInfo
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

class FtpDownloader {
    static List<String> ftpgwListFiles(){
        FtpConnectionInfo ftpConnectionInfo = ConfigurationProvider.getFtpgwInfo()
        ftpConnectionInfo.dictionary = "/dwcsc/"
        listFiles(ftpConnectionInfo)
    }

    static List<String> listFiles(FtpConnectionInfo ftpConnectionInfo) {
        Map<String, String> jsonBody = new HashMap<String, String>()
        jsonBody.put("server", "127.0.0.1") //TODO MLO Fix
        jsonBody.put("port", ftpConnectionInfo.port)
        jsonBody.put("username", ftpConnectionInfo.username)
        jsonBody.put("password", ftpConnectionInfo.password)
        jsonBody.put("dictionary", ftpConnectionInfo.dictionary)

        String bodyString = groovy.json.JsonOutput.toJson(jsonBody)
        def client = new RESTClient(ConfigurationProvider.getTestUtilService()+ "/getAll")
        def response = client.post(body: bodyString, requestContentType : JSON,  headers: [Accept: 'application/json'])

        assert response.status == 200

        def result = []
        response.responseData.each{
            result.add(it.name)
        }

        result
    }
}
