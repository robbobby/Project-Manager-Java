package dbconnection;

import java.sql.*;
import java.util.Arrays;
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

        Connection connection = getConnection(userID, password);
        createDatabase(connection, databaseName);
        connection = getTableCreationConnection(userID, password, databaseName);
        createTables(connection);

    }


    private static void createDatabase(Connection connection, String databaseName ) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE " + databaseName);
    }

    private static void createTables(Connection connection) throws SQLException {
        createDbTables(connection);

    }

//    private static void createDbTables(Connection connection) throws SQLException {
//        Statement statement = connection.createStatement();
//
//        String[] createTable ={"CREATE TABLE team_members(\n" +
//                              "`admin_access_level` INT,\n" +
//                              "`user_unique_id` INT NOT NULL PRIMARY KEY UNIQUE,\n" +
//                              "`first_name` VARCHAR(20),\n" +
//                              "`last_name` VARCHAR(25),\n" +
//                              "`email_address` VARCHAR(40) UNIQUE, \n" +
//                              "`mobile_number` VARCHAR(15),\n" +
//                              "`data_added` DATETIME);\n",
//
//                              "CREATE TABLE projects (\n" +
//                              "`project_id` INT UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
//                              "`project_name` VARCHAR(50) NOT NULL UNIQUE,\n" +
//                              "`project_admin_uid` INT NOT NULL,\n" +
//                              "`project_was_made` DATETIME,\n" +
//                              "`description` VARCHAR(255));",};
//
////                              "CREATE TABLE project_members (\n" +
////                              "`project_id` INT NOT NULL,\n" +
////                              "`user_unique_id` INT NOT NULL,\n" +
////                              "PRIMARY KEY(project_id, user_unique_id));"};
//
//        for (String i : createTable) {
//            statement.executeUpdate(Arrays.toString(createTable));
//        }
//    }

    private static void createDbTables(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTeamMembersTable = "CREATE TABLE team_members(\n" +
                "\t`admin_access_level` INT,\n" +
                "\t`user_unique_id` INT NOT NULL PRIMARY KEY UNIQUE,\n" +
                "\t`first_name` VARCHAR(20),\n" +
                "\t`last_name` VARCHAR(25),\n" +
                "\t`email_address` VARCHAR(40) UNIQUE, \n" +
                "\t`mobile_number` VARCHAR(15),\n" +
                "\t`data_added` DATETIME);\n";

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

    private static Connection getConnection(String userID, String password) {
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

    private static Connection getTableCreationConnection(String userID, String password, String databaseName) {
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