package dk.rim.is.ic.inttests.util.file;

import dk.rim.is.ic.inttests.DatabaseTester;
import dk.rim.is.ic.inttests.DatabaseTesterFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 03.02.2017
 */
public class IntegrationFileDbUtil {
    private static final String INTEGRATION_FILE_TABLE = "CISADM.INTEGRATION_FILE";
    private static final String INTEGRATION_FILE_TYPE_TABLE = "CISADM.INTEGRATION_FILE_TYPE";
    private static final String HOVEDOPLYSIGNERMAP_TABLE = "BATCH.HOVEDOPLYSIGNERMAP";

    private static final DatabaseTester DB_TESTER = DatabaseTesterFactory.createDbTester();

    private IntegrationFileDbUtil() {
    }

    public static void checkThatTablesExist() {
        assertThat(DB_TESTER.tableExists(INTEGRATION_FILE_TABLE)).isTrue();
        assertThat(DB_TESTER.tableExists(INTEGRATION_FILE_TYPE_TABLE)).isTrue();
        assertThat(DB_TESTER.tableExists(HOVEDOPLYSIGNERMAP_TABLE)).isTrue();
    }

    public static void clearAllRows() {
        DB_TESTER.deleteAllEntities(INTEGRATION_FILE_TABLE);
    }
}
