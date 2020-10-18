package dbconnection;

import data.UserProfile;
import dbconnection.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLogin {

    //########################### USER PROFILE ITEMS ########################### //

    int userUniqueID;
    String firstName = "";
    String lastName = "";
    String emailAddress = "";
    String userLogin = "";
    String mobileNumber = "";

    // ##### Setters ##### //
    public void setUserUniqueID(int a_UserUniqueID) {
        userUniqueID = a_UserUniqueID;
    }

    ;

    public void setUserLogin(String a_UserLogin) {
        userLogin = a_UserLogin;
    }
    public void setFirstName(String a_FirstName) {
        firstName = a_FirstName;
    }
    public void setPhoneNumber(String a_PhoneNumber) {
        mobileNumber = a_PhoneNumber;
    }
    public void setLastName(String lastName) {
        lastName = lastName;
    }
    public void setEmail(String a_Email) {
        emailAddress = a_Email;
    }

    // ##### Getters #####
    public int get_UserUniqueId() {
        return userUniqueID;
    }

    public String getUserLogin() {
        return userLogin;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return emailAddress;
    }
    public String getPhoneNumber() {
        return mobileNumber;
    }


    public void signIn(String user_name, String user_password) throws SQLException {
        // MySQL stuff //
        Connection connection = new DbConnection().getConnection();
        ResultSet loginResult = null;

    }

    public static boolean login(String userID, String password) throws SQLException {
        String loginQuery = "SELECT user_id, user_login, first_name, last_name, email, mobile_number " +
                "FROM users " +
                "WHERE user_login = '" + userID + "' AND user_password = '" + password + "';";

        Connection connection = new DbConnection().getConnection();
        Statement loginStatement = connection.createStatement();
        ResultSet loginResult = loginStatement.executeQuery(loginQuery);
        System.out.println(loginQuery);

        // Make sure MySQL returned with an account //
        try {
            if (loginResult.isBeforeFirst()) {
                while (loginResult.next()) {
                    System.out.println("Found a user account with the correct details.");
                    System.out.println("Settings UserProfile Details");
                    System.out.println("Check 1");

                    UserProfile userProfile = UserProfile.getUserProfile();

                    userProfile.set_UserUniqueId(loginResult.getInt("user_id"));    // UNIQUE ID
                    userProfile.setFirstName(loginResult.getString("first_name"));  // FIRST NAME
                    userProfile.setLastName(loginResult.getString("last_name"));    // LAST NAME
                    userProfile.setEmail(loginResult.getString("email"));           // EMAIL ADDRESS
                    userProfile.setUserLogin(loginResult.getString("user_login")); // USER_LOGIN
                    userProfile.setPhoneNumber(loginResult.getString("mobile_number")); // USER_LOGIN


                    System.out.println("Found user login details");
                }
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}
