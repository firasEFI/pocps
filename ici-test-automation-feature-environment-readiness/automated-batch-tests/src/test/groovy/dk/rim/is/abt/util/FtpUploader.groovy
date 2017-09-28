package dk.rim.is.abt.util

import dk.rim.is.abt.util.configuration.ConfigurationProvider
import dk.rim.is.abt.util.configuration.FtpConnectionInfo
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class FtpUploader {
    static ftpgwUpload(InputStream resourceStream, String filename) {
        FtpConnectionInfo ftpConnectionInfo = ConfigurationProvider.getFtpgwInfo()
        ftpConnectionInfo.dictionary = "/dwcsc/"
        upload(resourceStream, filename, ftpConnectionInfo)
    }

    static csrpUpload(InputStream resourceStream, String filename) {
        upload(resourceStream, filename, ConfigurationProvider.getCsrpInfo())
    }

    static private upload(InputStream resourceStream, String filename, FtpConnectionInfo ftpConnectionInfo){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[1000];

        while ((nRead = resourceStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        String bytes = Base64.getEncoder().encodeToString(buffer.toByteArray());
        //we have a String of our encoded bytes, close reader/writer streams
        buffer.close()
        resourceStream.close()

        //create client and connect

        Map<String, String> jsonBody = new HashMap<String, String>();
        jsonBody.put("server", ftpConnectionInfo.server)
        jsonBody.put("port", ftpConnectionInfo.port)
        jsonBody.put("username", ftpConnectionInfo.username);
        jsonBody.put("password", ftpConnectionInfo.password);
        jsonBody.put("dictionary", ftpConnectionInfo.dictionary)
        jsonBody.put("bytes", bytes)
        jsonBody.put("filename", filename)

        def ftpServer
        ftpServer = new HTTPBuilder(ConfigurationProvider.getTestUtilService() + '/save')

        // POST
        ftpServer.request(Method.POST, ContentType.JSON) {
            uri.query = [format: 'json']
            body = jsonBody

            response.success = { resp, json ->
                assert resp.status == 200
            }

            response.failure = { resp, json ->
                throw new Exception("Stopping at item POST: uri: " + uri + "\n" +
                        "   Unknown error trying to create item: ${resp.status}, not creating Item." +
                        "\njson = ${json}")
            }
        }
    }
}
