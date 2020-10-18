package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Each method or extension of this class should open and close the connection
 *  after it is done
 *  This will probably end up as a */

public class DbConnection {
    Connection connection = null;

    private void initConnection() {

        /* Login details for MySQL
         *** TODO Make it so you have to enter userID and password into MySQL
         */
        String sqlUserID = "u21pzxyrruuj7oji";
        String sqlPassword = "fgHsImrphK0OMZZZtmZr";
        
        try {
            // Load driver to connect to MySQL //
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully loaded MySQL J Connector Driver");

            // Attempt to establish a connection to the Database //
            connection = DriverManager.getConnection(
                    "jdbc:mysql://u21pzxyrruuj7oji:fgHsImrphK0OMZZZtmZr@b1bbeunpiubpwgg8mg8v-mysql.services.clever-cloud.com:3306/b1bbeunpiubpwgg8mg8v");

            System.out.println("Successfully connected to the database");
        } catch (ClassNotFoundException | SQLException errorMessage) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.WARNING, null, errorMessage);
            System.out.println("Failed to connect to Remote Server");
        }
    }

    /** Initiates and gets the connection */
    public Connection getConnection() {
        initConnection();
        return connection;
    }
}