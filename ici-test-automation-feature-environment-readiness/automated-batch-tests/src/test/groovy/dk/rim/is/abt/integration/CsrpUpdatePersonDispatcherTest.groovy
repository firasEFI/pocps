package dk.rim.is.abt.integration

import dk.rim.is.abt.dao.cisadm.C1AddressEntity
import dk.rim.is.abt.dao.cisadm.CiPerAddrEntity
import dk.rim.is.abt.dao.cisadm.CiPerIdEntity
import dk.rim.is.abt.dao.cisadm.IntegrationFileEntity
import dk.rim.is.abt.util.BatchController
import dk.rim.is.abt.util.FtpUploader
import dk.rim.is.abt.util.GenericDao
import dk.rim.is.abt.util.SpringContext
import dk.rim.is.common.entity.DateStoreEntity
import org.assertj.core.util.Strings
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.sql.SQLException
import java.text.DecimalFormat
import java.time.LocalDate

import static dk.rim.is.abt.util.SpringContextCisadm.buildDao
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

class CsrpUpdatePersonDispatcherTest {

    private static final String JOB_NAME = "CsrpUpdatePersonDispatcher"

    private GenericDao<IntegrationFileEntity> fileDao = buildDao(IntegrationFileEntity.class)
    private GenericDao<DateStoreEntity> dsDao = SpringContext.buildDao(DateStoreEntity.class)
    private GenericDao<CiPerIdEntity> ciPerIdDao = buildDao(CiPerIdEntity.class)
    private GenericDao<CiPerAddrEntity> ciPerAddrDao = buildDao(CiPerAddrEntity.class)
    private GenericDao<C1AddressEntity> ciAddressDao = buildDao(C1AddressEntity.class)

    //@Test
    void testBatchReturnCode() {
        def jobReturnCode = BatchController.runAndReportIntegrationJob(JOB_NAME)
        assert jobReturnCode.executionStatus == "SUCCESS"
    }

    /**
     * STEP 1: make a change to a known user, changing the address in the DB
     * STEP 2: Make a change file and ftpgwUpload it to the ftp (mock or otherwise)
     * STEP 3: call batch job
     * STEP 4: check that batch changed the user's address
     * STEP 5: success
     */
    @Test
    void runTest() {
        //the DB tests work correctly on DEV for the given CPR
        String cprNumber = "1808930706"
        String filename = "D170601A.xml"
        C1AddressEntity c1Address = getAddressIdForCPRNumber(cprNumber)
        assertNotNull("Address_Id cannot be null", c1Address.getAddressId())

        modifyAddressInDB(c1Address)
        FtpUploader.csrpUpload(getClass().getResourceAsStream("/file/csrp/" + filename), getDatedFilename())

        testBatchReturnCode()
        assertTrue("Found some address details that seemed to be dummy/test values. Check them before you consider the test failed", verifyPersonUpdateInPSRMDB(cprNumber))
    }

    @Before
    void prepareDB() throws SQLException {
        dsDao.delete({ p -> p.getName().equals("CSRP_UpdatePerson_last_processed") })
    }

    @After
    void cleanUp() throws SQLException {
        fileDao.delete({ p -> p.getFilename() == getDatedFilename() })
    }

    private C1AddressEntity getAddressIdForCPRNumber(String cprNumber) {
        List<CiPerIdEntity> perIds = ciPerIdDao.getBy({ p -> p.getPerIdNbr().equals(cprNumber) })
        perIds.each {
            println "Number " + it.getPerIdNbr()
        }

        if (perIds.size() == 0) {
            throw new Exception("No CSRP number in db found for " + cprNumber)
        }

        List<CiPerAddrEntity> perAdd = ciPerAddrDao.getBy({ p -> p.getPerId().equals(perIds[0].getPerId()) })

        for (elem in perAdd) {
            List<C1AddressEntity> address = ciAddressDao.getBy({ p -> p.getAddressId().equals(elem.getAddressId()) })
            if (address.size() > 0) {
                return address[0]
            }
        }
        throw new Exception("No PerAddr number in db found for " + cprNumber)
    }

    private boolean modifyAddressInDB(C1AddressEntity c1address) {
        c1address.setNum1("")
        c1address.setNum2("")
        c1address.setAddress1("Do not use this address")
        c1address.setAddress2("it is a dummy")
        c1address.setAddress3("please no")
        c1address.setAddress4("")
        c1address.setCountry("Not")
        c1address.setCity("NotKobenhavn")
        c1address.setState("")
        c1address.setPostal("3003")

        ciAddressDao.update(c1address)
    }

    /**
     * We want to check that the address has been reverted.
     * @param cprNumber the cpr number of the person we want to look-up
     */
    boolean verifyPersonUpdateInPSRMDB(String cprNumber) {
        C1AddressEntity address = getAddressIdForCPRNumber(cprNumber)
        boolean found = false;
        String city = address.getCity()
        String country = address.getCountry()
        if (!Strings.isNullOrEmpty(city) && !Strings.isNullOrEmpty(country)) {
            if (city.contains('Not') && country.contains('Not')) {
                //we don't want to end up here
                found = true
            }
        }
        return !found
    }

    private static String getDatedFilename() {
        //we need to change the filename
        DecimalFormat mFormat = new DecimalFormat("00")
        StringBuilder sb = new StringBuilder()
        sb.append("D")
        LocalDate ld = LocalDate.now()
        sb.append(ld.getYear() - 2000)
        sb.append(mFormat.format(Integer.valueOf(ld.getMonth().getValue())))
        sb.append(mFormat.format(Integer.valueOf(ld.getDayOfMonth())))
        sb.append("A")
        sb.append(".xml")
        return sb.toString()
    }
}