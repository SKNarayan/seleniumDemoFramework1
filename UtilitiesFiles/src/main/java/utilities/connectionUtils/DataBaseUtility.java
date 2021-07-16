package utilities.connectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * class to perform database operations
 *
 * @author Shashank2.Singh
 */
public class DataBaseUtility {

    public static Connection dbConnection = null;

    /**
     * Method to create the database connection
     *
     * @param host     hostname in string
     * @param userName database username in string
     * @param password database password in string
     */
    public static Connection createOracleDatabaseConnection(String host, String userName, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            dbConnection = DriverManager.getConnection(host, userName, password);
            System.out.println("Database connection successful.");

            return dbConnection;
        } catch (Exception e) {
            throw new RuntimeException("Error while creating the Oracle DB connection", e);
        }
    }

    public static void createOracleDatabaseConnection(String host, String port, String sid, String userName, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + sid + "", "" + userName + "", "" + password + "");
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            throw new RuntimeException("Error while creating the Oracle DB connection", e);
        }
    }

    /**
     * Method to create the MySql database connection
     *
     * @param host     - Host Name of the Database
     * @param port     - Port Number of the Database
     * @param userName - Username of the Database
     * @param password - Password of the Database
     */
    //@Step("MySQL Database Connection")
    public static void createMySqlDatabaseConnection(String host, String port, String userName, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port, userName, password);

        } catch (Exception e) {
            throw new RuntimeException("error while creating the SQL DB connection" + e.getMessage());
        }
    }

    /**
     * Method to execute non select queries
     *
     * @param query sql non select query in string
     */
    public static void executeNonSelectStatement(String query) {
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate("Commit");

            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException("Error while executing the non select query.");
        }
    }

    /**
     * Method to execute non select queries
     *
     * @param connection Database connection
     * @param query      sql non select query in string
     */
    public static void executeNonSelectStatement(Connection connection, String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.executeUpdate("Commit");

            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException("Error while executing the non select query.");
        }
    }

    /**
     * Method to execute select queries. Result set will be return as a output
     *
     * @param query sql select query
     * @return resultSet
     */
    public static ResultSet executeSelectStatement(String query) {
        ResultSet resultSet;
        try {
            Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);

            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException("Error while executing the select query.");
        }
        return resultSet;
    }


    /**
     * Method to execute select queries. Result set will be return as a output
     *
     * @param connection database connection
     * @param query      sql select query
     * @return resultSet
     */
    public static ResultSet executeSelectStatement(Connection connection, String query) {
        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);

            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException("Error while executing the select query.");
        }
        return resultSet;
    }

    /**
     * to get the records from the database
     * database column name is key for the values, using index you can get the values
     * for example :
     * Map<String, ArrayList<String> dbMap = get_database_records("Select * from employee");
     * String empName = dbMap.get("ColumnName").get("RecordIndex");
     *
     * @param query select query in string
     * @return HashMap<String, ArrayList < String>>
     */
    public static HashMap<String, ArrayList<String>> getDatabaseRecords(String query) {
        ResultSet resultSet = executeSelectStatement(query);
        return getRecordsFormDatabase(resultSet);
    }

    /**
     * @param connection DB connection
     * @param query      select query in string
     * @return HashMap<String, ArrayList < String>>
     */
    public static HashMap<String, ArrayList<String>> getDatabaseRecords(Connection connection, String query) {
        ResultSet resultSet = executeSelectStatement(connection, query);
        return getRecordsFormDatabase(resultSet);
    }

    private static HashMap<String, ArrayList<String>> getRecordsFormDatabase(ResultSet resultSet) {
        HashMap<String, ArrayList<String>> mapDatabase = new HashMap<>();

        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int colCount = 1; colCount <= resultSetMetaData.getColumnCount(); colCount++) {

                ArrayList<String> cellValues = new ArrayList<>();
                while (resultSet.next()) {
                    cellValues.add(resultSet.getString(colCount));
                }
                mapDatabase.put(resultSetMetaData.getColumnName(colCount), cellValues);
                resultSet.first();
                resultSet.beforeFirst();
            }
            resultSet.close();

        } catch (Exception e) {
            throw new RuntimeException("Error while getting the data from database.");
        }
        return mapDatabase;
    }

    /**
     * get the number of rows after querying the select
     *
     * @param resultSet database result set
     * @return number of rows
     */
    public static int getRowCount(ResultSet resultSet) {
        int rowCount = 0;
        try {
            resultSet.last();
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (Exception e) {
            throw new RuntimeException("Error while getting the row count.");
        }
        return rowCount;
    }

    /**
     * to get the data from database
     *
     * @param query sql query
     * @return Object[][]
     */
    public static Object[][] getDatabaseDataProvider(String query) {
        Object[][] obj;
        try {
            ResultSet resultSet = executeSelectStatement(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int rowCount = getRowCount(resultSet);

            obj = new Object[rowCount][1];
            int arrIndex = 0;

            while (resultSet.next()) {
                Map<Object, Object> mapData = new HashMap<>();
                for (int colCount = 1; colCount <= resultSetMetaData.getColumnCount(); colCount++) {
                    mapData.put(resultSetMetaData.getColumnName(colCount), resultSet.getString(colCount));
                }

                if (arrIndex < rowCount) {
                    // adding the test data in array
                    obj[arrIndex][0] = mapData;
                    arrIndex++;
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while executing the select query.");
        }
        return obj;
    }

    public static Object[][] getDatabaseDataProvider(Connection connection, String query) {
        Object[][] obj;
        try {
            ResultSet resultSet = executeSelectStatement(connection, query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int rowCount = getRowCount(resultSet);

            obj = new Object[rowCount][1];
            int arrIndex = 0;

            while (resultSet.next()) {
                Map<Object, Object> mapData = new HashMap<>();
                for (int colCount = 1; colCount <= resultSetMetaData.getColumnCount(); colCount++) {
                    mapData.put(resultSetMetaData.getColumnName(colCount), resultSet.getString(colCount));
                }

                if (arrIndex < rowCount) {
                    // adding the test data in array
                    obj[arrIndex][0] = mapData;
                    arrIndex++;
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while executing the select query.");
        }
        return obj;
    }
}

