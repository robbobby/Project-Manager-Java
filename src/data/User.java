package data;

import dbconnection.DbConnection;
import dbconnection.TeamDbConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

        // This class will contain all use profile related functions //

public class User {

    public static ResultSet addUser(String[] userDetails) throws SQLException {
        // 1 - Create the string for the Statement... In its own function to de-clutter the process
        // 2 - Get the connection to MySQL - TODO maybe dbconnection should be static, will have to make an instance of this a lot otherwise
        // 3 - Send query to MySQL and get back data from database
        // Check if exists already
        // If exists already tell the user
        // Register user if does not exist

        // 1 -  Create the strings for the Statements... In its own function to de-clutter the process
        String addUserString = getAddUserString(userDetails);
        String checkUserString = getCheckUserString(userDetails[10], userDetails[0]);


        System.out.println(checkUserString); // TODO - This should only show if the program is in debug -- add program states
        System.out.println(addUserString);
        // Get the connection to MySql and make statements and result set ready
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();

        ResultSet resultSet = null;
        Statement statement = connection.createStatement();

        // 3 - Send query to MySQL and get back data from database
        resultSet = statement.executeQuery(checkUserString);

        // 4 - Check if unique fields already exist
        if (!resultSet.isBeforeFirst()) { // TODO - change this to true if its not working
            System.out.println("No user with this details");
            resultSet = null;
            statement.clearBatch();
            statement.executeUpdate(addUserString);
        } else {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_login") + " " + resultSet.getString("email"));
            }
            System.out.println("The user is already registered with the email or userID");
        }
        // Print out user details that came back from MySQL //
        connection.close();
        System.out.println("Connection Closed");
        return resultSet;
    }


    // LOGGING IN - Makes statement for checking the user login details

    private static String getCheckUserString(String userLogin, String emailAddress) {
        String statementQuery = "SELECT email, user_login " +
                "FROM users " +
                "WHERE user_login = '" + userLogin + "' OR email = '" + emailAddress + "'";
        return statementQuery;
    }


    //##### ADDING USER TO USER DATABASE - Make the string ##### //
    private static String getAddUserString(String[] userDetails) {
        StringBuilder string = new StringBuilder("INSERT INTO users (email, first_name, last_name, mobile_number, " +
                "mobile_number_country_code, address_l1 ,address_l2, address_city, address_postcode, " +
                "address_country, user_login, user_password) " +
                "VALUES (");
        // For loop to add values to the
        for (int i = 0; i < userDetails.length; i++) {
            string.append("'" + userDetails[i]);
            if ((i < userDetails.length - 1)) {
                string.append("', ");
            }
        }
        string.append("');");
        return string.toString();
    }

    /**
     * Add user to team_members database
     */
    public static void addUserToTeam(String projectName, int uniqueID, int adminLevel) throws SQLException {

        // Connect to the new project database that we made //
        Connection connectionToTeamDB = TeamDbConnection.getTableCreationConnection("root", "default", projectName);
        Connection connectionToMainDB = new DbConnection().getConnection();
        ResultSet resultSet = null;
        String getUser = "SELECT user_id, first_name, last_name, email, mobile_number\n" +
                "FROM users\n" +
                "\n" +
                "WHERE user_id = " +uniqueID + ";";

        System.out.println(getUser);

        Statement statement = connectionToMainDB.createStatement();
        resultSet = statement.executeQuery(getUser);

        StringBuilder setUser = new StringBuilder( "INSERT INTO team_members" +
                " (admin_access_level, user_unique_id, first_name, last_name, email_address, mobile_number, date_added) " +
                " VALUES ('" + adminLevel);
        while (resultSet.next()) {
            for (int i = 1; i < 6; i++) {
                setUser.append("', '" + resultSet.getString(i));
                if (i == 5)
                    setUser.append("', NOW());");
            }
        }

        System.out.println(setUser);


        statement = connectionToTeamDB.createStatement();
        statement.executeUpdate(String.valueOf(setUser));



    }

}
