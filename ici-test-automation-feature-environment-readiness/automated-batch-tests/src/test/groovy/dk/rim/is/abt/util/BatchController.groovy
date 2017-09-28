package dk.rim.is.abt.util

import groovyx.net.http.RESTClient
import org.apache.deltaspike.core.api.config.ConfigResolver

import static dk.rim.is.baa.services.BacServices.SERVICE_EXECUTE_INTEGRATION_M_PATH
import static dk.rim.is.baa.services.BacServices.SERVICE_EXECUTE_NYMF_BJ_M_PATH
import static dk.rim.is.baa.services.BacServices.SERVICE_EXECUTE_PSRM_BJ_M_PATH
import static dk.rim.is.baa.services.BacServices.SERVICE_GET_BATCH_JOBS_PATH
import static dk.rim.is.baa.services.BacServices.SERVICE_PARAMETER_ID_OR_DESCRIPTION

class BatchController {


    static runAndReportIntegrationJob(String jobDescription) {
        def jobStatus = prepareRest(SERVICE_EXECUTE_INTEGRATION_M_PATH, jobDescription)
        println String.format("Batch ID is %s and return status is %s", jobStatus.batchJobExecutionId, jobStatus.executionStatus)
        if (jobStatus.executionStatus != "SUCCESS") {
            println String.format("Error message is %s and cause is %s", jobStatus.errorMessage, jobStatus.errorsDuringExecutionOfBatchJob)
        }
        return jobStatus
    }
    static runIntegrationJob(String jobDescription) {
        prepareRest(SERVICE_EXECUTE_INTEGRATION_M_PATH, jobDescription)
    }
    static runNyMFJob(String jobDescription) {
        prepareRest(SERVICE_EXECUTE_NYMF_BJ_M_PATH, jobDescription)
    }
    static runPsrmJob(String jobDescription) {
        prepareRest(SERVICE_EXECUTE_PSRM_BJ_M_PATH, jobDescription)
    }

    private static prepareRest(String component, String jobDescription) {
        RESTClient client = createRestClient(component)

        println "Calling $component with ID: $jobDescription"

        def param = [:]
        if (jobDescription != null)
            param.put(SERVICE_PARAMETER_ID_OR_DESCRIPTION, jobDescription)
        def resp = client.get([query: param])

        assert resp.status == 200

        resp.data
    }

    private static RESTClient createRestClient(String component) {
        def bac = ConfigResolver.getPropertyValue("bac.host")
        def batchUrl = "http://" + bac + "/" + component;
        def client = new RESTClient(batchUrl)
        client.getClient().params.setParameter('http.connection.timeout', new Integer(120000));
        client.getClient().params.setParameter('http.socket.timeout', new Integer(120000));
        client
    }

    static getBatchList() {
        prepareRest(SERVICE_GET_BATCH_JOBS_PATH, null).jobsSorted
    }

}
