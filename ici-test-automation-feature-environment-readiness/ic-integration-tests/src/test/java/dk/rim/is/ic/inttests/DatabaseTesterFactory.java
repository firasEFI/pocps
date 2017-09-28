package dk.rim.is.ic.inttests;

/**
 * Created by tomb on 01-12-2016.
 */
public class DatabaseTesterFactory {

    public static DatabaseTester createDbTester() {
        String batchDbUrl = Property.BATCH_DB_HOST.load()+":"+Property.BATCH_DB_PORT.load()+":"+Property.BATCH_DB_SID.load();
        String batchDbUser = Property.BATCH_DB_USER.load();
        String batchDbPassword = Property.BATCH_DB_PASSWORD.load();

        String batchOracleDbUrl = String.format("jdbc:oracle:thin:@%s", batchDbUrl);
        return new DatabaseTester(batchOracleDbUrl, batchDbUser, batchDbPassword);
    }
}
