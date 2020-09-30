package dbconnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** CLass for connection to the project databased */

public class TeamDbConnection {


    public static void makeDatabase(String userID, String password, String databaseName) throws SQLException {
        Connection connection = getConnection(userID, password);
        createDatabase(connection, databaseName);

    }

    public static void makeDatabase(String databaseName) throws SQLException {
        String userID = "root";
        String password = "default";
        String dbName = databaseName.replace(" ", "_");
        System.out.println(dbName);

        Connection connection = getConnection(userID, password);
        createDatabase(connection, dbName);
        connection = getTableCreationConnection(userID, password, dbName);
        createTables(connection);

    }


    private static void createDatabase(Connection connection, String databaseName ) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE " + databaseName);
    }

    private static void createTables(Connection connection) throws SQLException {
        createDbTables(connection);

    }



            // After a database is made for a team we call this function to create the tables in that database //
    private static void createDbTables(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTeamMembersTable = "CREATE TABLE team_members(\n" +
                "\t`admin_access_level` INT,\n" +
                "\t`user_unique_id` INT NOT NULL PRIMARY KEY UNIQUE,\n" +
                "\t`first_name` VARCHAR(20),\n" +
                "\t`last_name` VARCHAR(25),\n" +
                "\t`email_address` VARCHAR(40) UNIQUE, \n" +
                "\t`mobile_number` VARCHAR(15),\n" +
                "\t`date_added` DATETIME);\n";

        String createProjectTable = "CREATE TABLE projects (\n" +
                "\t`project_id` INT UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "\t`project_name` VARCHAR(50) NOT NULL UNIQUE,\n" +
                "\t`project_admin_uid` INT NOT NULL,\n" +
                "\t`project_was_made` DATETIME,\n" +
                "\t`description` VARCHAR(255));";

        String createProjectMembersTable = "CREATE TABLE project_members (\n" +
                "`project_id` INT NOT NULL,\n" +
                "`user_unique_id` INT NOT NULL,\n" +
                "PRIMARY KEY(project_id, user_unique_id));";

        statement.executeUpdate(createTeamMembersTable);
        statement.executeUpdate(createProjectTable);
        statement.executeUpdate(createProjectMembersTable);
    }
















                    // ###### Get Connections to Database ###### //

                            // Connection to make the Database //

    public static Connection getConnection(String userID, String password) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Successfully loaded MySQL J Connector for team database");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC", userID, password);
        } catch (ClassNotFoundException | SQLException errorMessage) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.ALL, null, errorMessage);
        }
        return connection;
    }


                            // Connection to make tables after database is created //

    public static Connection getTableCreationConnection(String userID, String password, String databaseName) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Successfully loaded MySQL J Connector for team database");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +databaseName + "?serverTimezone=UTC", userID, password);
            System.out.println("Successfully got into new created database");
        } catch (ClassNotFoundException | SQLException errorMessage) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.ALL, null, errorMessage);
        }
        return connection;
    }
}