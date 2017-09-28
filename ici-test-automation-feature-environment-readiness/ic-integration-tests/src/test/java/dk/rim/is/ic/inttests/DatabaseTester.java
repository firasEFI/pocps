package dk.rim.is.ic.inttests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Database tester.
 */
public class DatabaseTester {

    private Connection conn;

    public DatabaseTester(String dbUrl, String dbUser, String dbPassword) {
        conn = getConnection(dbUrl, dbUser, dbPassword);
    }

    public boolean tableExists(String tableName) {
        boolean exists;
        try (
                Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName)) {
            exists = true;
        } catch (SQLException e) {
            exists = false;
        }
        return exists;
    }

    public int countEntities(String tableName) {
        return countEntities(tableName, "1=1");
    }

    public int countEntities(String tableName, String whereStatement) {
        int count = 0;
        try (
                Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName + " WHERE " + whereStatement)) {
            while (rset.next()) {
                count = rset.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int deleteAllEntities(String tableName) {
        return deleteEntities(tableName, "1=1");
    }

    public int deleteEntities(String tableName, String whereStatement) {
        int count = 0;
        try (Statement stmt = conn.createStatement()) {
            count = stmt.executeUpdate("DELETE " + tableName + " WHERE " + whereStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public List<Map<String, Object>> select(String tableName, String columnName, String whereClause) {
        List<Map<String, Object>> result = new LinkedList<>();

        String sqlQuery = String.format("SELECT %s FROM %s WHERE %s", columnName, tableName, whereClause);
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sqlQuery);

            while (resultSet.next()) {
                result.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private HashMap<String, Object> mapResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        HashMap<String, Object> valueMap = new HashMap<>();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            String rsetColumnName = rsmd.getColumnName(i);
            Object columnValue = resultSet.getObject(i);
            valueMap.put(rsetColumnName, columnValue);
        }
        return valueMap;
    }

    @Deprecated
    public int bulkInsertEntities(String tableName, List<Map<String, String>> insetMapList) {
        return bulkInsertEntities(tableName, insetMapList, true);
    }

    @Deprecated
    public int bulkInsertEntities(String tableName, List<Map<String, String>> insetMapList, boolean shouldAddId) {
        int count = 0;
        try (
                Statement stmt = conn.createStatement()) {

            for (Map<String, String> insetMap : insetMapList) {
                String columns = "";
                String values = "";
                for (Map.Entry<String, String> entry : insetMap.entrySet()) {
                    if (columns != "")
                        columns += ", ";
                    columns += entry.getKey();

                    if (values != "")
                        values += ", ";
                    values += entry.getValue();
                }

                if (shouldAddId) {
                    columns += ", ID";
                    values += ", PK_SEQ.nextval";
                }

                stmt.addBatch("INSERT INTO " + tableName + "(" + columns + ") VALUES (" + values + ")");
            }

            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void execute(String stmt) {
        try (Statement statement = conn.createStatement()) {

            statement.execute(stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName(oracle.jdbc.OracleDriver.class.getName());
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
