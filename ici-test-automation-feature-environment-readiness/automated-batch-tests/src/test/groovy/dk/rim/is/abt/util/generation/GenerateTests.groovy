package dk.rim.is.abt.util.generation

import dk.rim.is.abt.util.BatchController
import groovy.io.FileType
import groovy.json.JsonSlurper

/**
 * Created by wos on 12.05.2017.
 */
class GenerateTests {

    private static final String ROOT_TEST = System.getProperty("user.dir") + "\\src\\test";
    private static final String ROOT_PACKAGE_NAME_FOR_TEST = ROOT_TEST + "\\groovy\\dk\\rim\\is\\abt\\";

    static void main(String[] args) {

        if (args.size() > 0 && "checkForMissingJobs" == args[0]) {
            checkForMissingBatchTests()
        } else {
            generateTests()
        }
    }

    static void generateTests() {
        def data = BatchController.getBatchList()
//        def data = generateJSON()

        Object list = getAllLocalFiles()

        data.each {
            String name = removeForbiddenCharacters(it.name)
            def ret = checkIfTestExists(name, list)
            if (ret)
                println it.name + ", " + it.batchType +  ", test already exists"
            else {
                println "\nCreating test for " + it.name + ", " + it.batchType + "\n"
                def body = TestFactory.getTest(it.batchType, name, it.description).toString()
                saveToFile(body, ROOT_PACKAGE_NAME_FOR_TEST + it.batchType.toLowerCase() + "\\" + name + "Test.groovy")
            }
        }
    }

    public static void checkForMissingBatchTests() {
        def data = BatchController.getBatchList()
//        def data = generateJSON()

        def missingTests = []

        Object list = getAllLocalFiles()
        data.each {
            String name = removeForbiddenCharacters(it.name)
            def ret = checkIfTestExists(name, list)
            if (ret)
                println it.name + ", " + it.batchType +  ", test already exists"
            else {
                missingTests << it.name
            }
        }
        if (!missingTests.isEmpty()) {
            throw new Exception("Batch jobs found without tests:\n\t" + missingTests +
                    "\n\tCreate missing test for this batch jobs.")
        }

    }

    private static Object getAllLocalFiles() {
        def dir = new File(ROOT_TEST)
        dir.listFiles()
        println(dir)
        def list = []
        dir.eachFileRecurse(FileType.FILES) { file ->
            list << file
        }
        list
    }

    private static boolean saveToFile(String body, String path) {
        File file = new File(path)
        file.getParentFile().mkdirs()
        file.write(body)
    }

    private static String removeForbiddenCharacters(String name) {
        name.replaceAll("[^A-Za-z0-9]","")
    }

    private static boolean checkIfTestExists(String name, List<File> list) {

        def ret = false
        list.each {
            if (it.path.contains(name)) {
                ret = true
                return
            }
        }
        ret
    }

    private static Object generateJSON() {
        String text = "{\n" +
                "  \"jobs\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"GLASSIGN\",\n" +
                "      \"description\": \"GLASSIGN, assigning the GL account\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"DKCRVCHR\",\n" +
                "      \"description\": \"DKCRVCHR - Create Vouchers\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"DKTRVCHR\",\n" +
                "      \"description\": \"DKTRVCHR - Transfer Vouchers\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"C1-PEPL1\",\n" +
                "      \"description\": \"Process C1-PEPL1 - Upload Payments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 10,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"C1-PEPL2\",\n" +
                "      \"description\": \"Process C1-PEPL2 - Upload Payments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 11,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 6,\n" +
                "      \"name\": \"C1-PEPL3\",\n" +
                "      \"description\": \"Process C1-PEPL3 - Upload Payments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 12,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"name\": \"AogDRequestStatus\",\n" +
                "      \"description\": \"AogDRequestStatus\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 1200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"name\": \"AogDSendLetters\",\n" +
                "      \"description\": \"AogDSendLetters\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"CreateToDoIf-RecommendationExpired\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 13,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"CreateInstallment, MarkInstallmentAsPaid  ClosePaymentPlanCase\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 14,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"CloseDemandCase\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 15,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 12,\n" +
                "      \"name\": \"CsrpUpdatePersonDispatcher\",\n" +
                "      \"description\": \"CsrpUpdatePerson\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 1200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 13,\n" +
                "      \"name\": \"Nets601FileUpload\",\n" +
                "      \"description\": \"Nets601FileUpload\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 2,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 14,\n" +
                "      \"name\": \"DKNB\",\n" +
                "      \"description\": \"NetsBills\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 18000000,\n" +
                "      \"priority\": 1,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 15,\n" +
                "      \"name\": \"DKNBR\",\n" +
                "      \"description\": \"NetsBillsResponse\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 7,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 16,\n" +
                "      \"name\": \"DKNP\",\n" +
                "      \"description\": \"NetsPayments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 8,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 17,\n" +
                "      \"name\": \"DKSKBP\",\n" +
                "      \"description\": \"SKBPayments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 9,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 18,\n" +
                "      \"name\": \"DKSKBO\",\n" +
                "      \"description\": \"SKBPayouts\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 5,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 19,\n" +
                "      \"name\": \"DKSKBT\",\n" +
                "      \"description\": \"SKBTransactions\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 6,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 20,\n" +
                "      \"name\": \"DKDBOVP\",\n" +
                "      \"description\": \"NKSDebtorPayouts\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 3,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 21,\n" +
                "      \"name\": \"DKCLOVP\",\n" +
                "      \"description\": \"NKSClaimantPayouts\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 4,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 22,\n" +
                "      \"name\": \"DKCLRSP\",\n" +
                "      \"description\": \"NKSPayoutResponse\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 23,\n" +
                "      \"name\": \"SaveSentLetters\",\n" +
                "      \"description\": \"SaveSentLetters\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 24,\n" +
                "      \"name\": \"DKDACP\",\n" +
                "      \"description\": \"DeactivateDebtor\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 16,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 25,\n" +
                "      \"name\": \"DKDROCTD\",\n" +
                "      \"description\": \"ObligationsReachingLimitionDate\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 17,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 26,\n" +
                "      \"name\": \"DKDGOCTD\",\n" +
                "      \"description\": \"ObligationsReached LimitionDate\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 18,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 27,\n" +
                "      \"name\": \"NemKontoExportPayments\",\n" +
                "      \"description\": \"NemKontoExportPayments\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 300000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 28,\n" +
                "      \"name\": \"NemKontoReceiveReceipts\",\n" +
                "      \"description\": \"NemKontoReceiveReceipts\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 300000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 29,\n" +
                "      \"name\": \"DKTDCSV\",\n" +
                "      \"description\": \"CreateToDoFromCSVFile\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 30,\n" +
                "      \"name\": \"DKRSCFN\",\n" +
                "      \"description\": \"JournalizeNotesToWorkZone\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 31,\n" +
                "      \"name\": \"DKRSCFN\",\n" +
                "      \"description\": \"CreatePersonCasesInWorkZone\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 32,\n" +
                "      \"name\": \"C1-TXFRM\",\n" +
                "      \"description\": \"Tax Form Deferred Monitor\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 33,\n" +
                "      \"name\": \"DK_CALPI\",\n" +
                "      \"description\": \"Calculate Interest - Monthly\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 34,\n" +
                "      \"name\": \"DK_CALPI\",\n" +
                "      \"description\": \"Calculate Interest - Yearly\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 35,\n" +
                "      \"name\": \"SkyldnerValidation\",\n" +
                "      \"description\": \"SkylderValidation\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 36,\n" +
                "      \"name\": \"Retskraftsvurdering\",\n" +
                "      \"description\": \"Retskraftsvurdering\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 37,\n" +
                "      \"name\": \"Routing\",\n" +
                "      \"description\": \"Routing\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 38,\n" +
                "      \"name\": \"SendToPSRM\",\n" +
                "      \"description\": \"SendToPSRM\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 39,\n" +
                "      \"name\": \"SendToExMF\",\n" +
                "      \"description\": \"SendToExMF\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 40,\n" +
                "      \"name\": \"C1-SUPPM\",\n" +
                "      \"description\": \"Interest Suppression Monitor\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 41,\n" +
                "      \"name\": \"DKCLNOTI\",\n" +
                "      \"description\": \"Generate claimants notifications\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 42,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Cancel Settlement Stop and Create a to do\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 43,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Foreign Country Case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 44,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Repossession Case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 45,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Estate case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 46,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Suppression case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 47,\n" +
                "      \"name\": \"SendToHearing\",\n" +
                "      \"description\": \"SendToHearing\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"jobsSorted\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"GLASSIGN\",\n" +
                "      \"description\": \"GLASSIGN, assigning the GL account\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"DKCRVCHR\",\n" +
                "      \"description\": \"DKCRVCHR - Create Vouchers\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"DKTRVCHR\",\n" +
                "      \"description\": \"DKTRVCHR - Transfer Vouchers\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"C1-PEPL1\",\n" +
                "      \"description\": \"Process C1-PEPL1 - Upload Payments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 10,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"C1-PEPL2\",\n" +
                "      \"description\": \"Process C1-PEPL2 - Upload Payments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 11,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 6,\n" +
                "      \"name\": \"C1-PEPL3\",\n" +
                "      \"description\": \"Process C1-PEPL3 - Upload Payments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 12,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"CreateToDoIf-RecommendationExpired\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 13,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"CreateInstallment, MarkInstallmentAsPaid  ClosePaymentPlanCase\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 14,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"CloseDemandCase\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 15,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 14,\n" +
                "      \"name\": \"DKNB\",\n" +
                "      \"description\": \"NetsBills\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 18000000,\n" +
                "      \"priority\": 1,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 15,\n" +
                "      \"name\": \"DKNBR\",\n" +
                "      \"description\": \"NetsBillsResponse\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 7,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 16,\n" +
                "      \"name\": \"DKNP\",\n" +
                "      \"description\": \"NetsPayments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 8,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 17,\n" +
                "      \"name\": \"DKSKBP\",\n" +
                "      \"description\": \"SKBPayments\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 9,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 18,\n" +
                "      \"name\": \"DKSKBO\",\n" +
                "      \"description\": \"SKBPayouts\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 5,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 19,\n" +
                "      \"name\": \"DKSKBT\",\n" +
                "      \"description\": \"SKBTransactions\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 6,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 20,\n" +
                "      \"name\": \"DKDBOVP\",\n" +
                "      \"description\": \"NKSDebtorPayouts\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 3,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 21,\n" +
                "      \"name\": \"DKCLOVP\",\n" +
                "      \"description\": \"NKSClaimantPayouts\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 4,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 22,\n" +
                "      \"name\": \"DKCLRSP\",\n" +
                "      \"description\": \"NKSPayoutResponse\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 24,\n" +
                "      \"name\": \"DKDACP\",\n" +
                "      \"description\": \"DeactivateDebtor\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 16,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 25,\n" +
                "      \"name\": \"DKDROCTD\",\n" +
                "      \"description\": \"ObligationsReachingLimitionDate\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 17,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 26,\n" +
                "      \"name\": \"DKDGOCTD\",\n" +
                "      \"description\": \"ObligationsReached LimitionDate\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 18,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 29,\n" +
                "      \"name\": \"DKTDCSV\",\n" +
                "      \"description\": \"CreateToDoFromCSVFile\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 30,\n" +
                "      \"name\": \"DKRSCFN\",\n" +
                "      \"description\": \"JournalizeNotesToWorkZone\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 31,\n" +
                "      \"name\": \"DKRSCFN\",\n" +
                "      \"description\": \"CreatePersonCasesInWorkZone\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 32,\n" +
                "      \"name\": \"C1-TXFRM\",\n" +
                "      \"description\": \"Tax Form Deferred Monitor\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 33,\n" +
                "      \"name\": \"DK_CALPI\",\n" +
                "      \"description\": \"Calculate Interest - Monthly\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 34,\n" +
                "      \"name\": \"DK_CALPI\",\n" +
                "      \"description\": \"Calculate Interest - Yearly\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 40,\n" +
                "      \"name\": \"C1-SUPPM\",\n" +
                "      \"description\": \"Interest Suppression Monitor\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 41,\n" +
                "      \"name\": \"DKCLNOTI\",\n" +
                "      \"description\": \"Generate claimants notifications\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 42,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Cancel Settlement Stop and Create a to do\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 43,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Foreign Country Case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 44,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Repossession Case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 45,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Estate case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 46,\n" +
                "      \"name\": \"C1-PFLM\",\n" +
                "      \"description\": \"Create to do when balance is zero - Suppression case\",\n" +
                "      \"batchType\": \"PSRM\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"name\": \"AogDRequestStatus\",\n" +
                "      \"description\": \"AogDRequestStatus\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 1200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"name\": \"AogDSendLetters\",\n" +
                "      \"description\": \"AogDSendLetters\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 12,\n" +
                "      \"name\": \"CsrpUpdatePersonDispatcher\",\n" +
                "      \"description\": \"CsrpUpdatePerson\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 1200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 13,\n" +
                "      \"name\": \"Nets601FileUpload\",\n" +
                "      \"description\": \"Nets601FileUpload\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 3600000,\n" +
                "      \"priority\": 2,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 23,\n" +
                "      \"name\": \"SaveSentLetters\",\n" +
                "      \"description\": \"SaveSentLetters\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 27,\n" +
                "      \"name\": \"NemKontoExportPayments\",\n" +
                "      \"description\": \"NemKontoExportPayments\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 300000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 28,\n" +
                "      \"name\": \"NemKontoReceiveReceipts\",\n" +
                "      \"description\": \"NemKontoReceiveReceipts\",\n" +
                "      \"batchType\": \"INTEGRATION\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 300000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 35,\n" +
                "      \"name\": \"SkyldnerValidation\",\n" +
                "      \"description\": \"SkylderValidation\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 36,\n" +
                "      \"name\": \"Retskraftsvurdering\",\n" +
                "      \"description\": \"Retskraftsvurdering\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 37,\n" +
                "      \"name\": \"Routing\",\n" +
                "      \"description\": \"Routing\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 38,\n" +
                "      \"name\": \"SendToPSRM\",\n" +
                "      \"description\": \"SendToPSRM\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 39,\n" +
                "      \"name\": \"SendToExMF\",\n" +
                "      \"description\": \"SendToExMF\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 47,\n" +
                "      \"name\": \"SendToHearing\",\n" +
                "      \"description\": \"SendToHearing\",\n" +
                "      \"batchType\": \"NYMF\",\n" +
                "      \"published\": false,\n" +
                "      \"timeout\": 7200000,\n" +
                "      \"priority\": 99,\n" +
                "      \"threadCount\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}"
        JsonSlurper surp = new JsonSlurper();
        surp.parseText(text).jobsSorted
    }
}
