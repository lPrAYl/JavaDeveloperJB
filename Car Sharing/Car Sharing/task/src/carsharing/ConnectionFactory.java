package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    // JDBC driver name and database URL
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:." + FILE_SEPARATOR + "src" +
            FILE_SEPARATOR + "carsharing" + FILE_SEPARATOR + "db" + FILE_SEPARATOR;

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    private String databaseFileName;

    public ConnectionFactory(String databaseFileName) {
        this.databaseFileName = databaseFileName;
    }

    public Connection getConnection() {
        Connection connection;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
//            connection = DriverManager.getConnection(DB_URL + this.databaseFileName, USER, PASS);
            connection = DriverManager.getConnection(DB_URL + this.databaseFileName);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException | ClassNotFoundException se) {
            throw new RuntimeException(se);
        }
    }
}
