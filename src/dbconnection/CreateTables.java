package dbconnection;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Call this function to check and create SQL tables if they do not already exist */

public class CreateTables {
                // ###### MAIN DATABASE ##### //
    //# MAKE THIS STATIC, WE DON'T NEED AN OBJECT #//

    // Check if Table exists
    // If not create the table
        // Table 1) - User Table
        // Table 2) - Team Table

    public static void createMainDbTables() throws SQLException {
        String checkAndCreateTables = "CREATE TABLE IF NOT EXISTS users(\n" +
                "\tuser_id INT NOT NULL  AUTO_INCREMENT PRIMARY KEY,\n" +
                "\temail VARCHAR(40) NOT NULL,\n" +
                "\tfirst_name VARCHAR(40),\n" +
                "\tlast_name VARCHAR(40),\n" +
                "\tmobile_number VARCHAR(16),\n" +
                "\tmobile_number_country_code VARCHAR(8),\n" +
                "\taddress_l1 VARCHAR(40),\n" +
                "\taddress_l2 VARCHAR(40),\n" +
                "\taddress_city VARCHAR(40),\n" +
                "\taddress_postcode VARCHAR(8),\n" +
                "\taddress_country VARCHAR(40),\n" +
                "\tuser_login VARCHAR(20),\n" +
                "\tuser_password VARCHAR(30),\n" +
                "\tregistration_date DATETIME,\n" +
                "\tis_activated BOOL)";
        System.out.println(checkAndCreateTables);

        Connection connection = new DbConnection().getConnection();
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(checkAndCreateTables);
            System.out.println("Created users database");
        } catch(SQLException exception) {
            System.out.println("users database is already made OR failed to make the database");
        }

    }
                // ##### TEAM DATABASE ##### //
    // This already exists in the Project, MOVE IT TO HERE
}
