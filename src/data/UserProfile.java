package data;

import java.sql.ResultSet;
import java.util.ArrayList;

/** Contains all details of company project and user details required in the program */
public class UserProfile {
    private
    int userUniqueId;
    private String UserLogin;
    private String FirstName;
    private String LastName;
    private String Email;
    private String PhoneNumber;

    ArrayList profileArray = new ArrayList();

    ResultSet companyProfile;


    // ### Setters ### ///
    public void set_UserUniqueId(int m_UserUniqueId) { this.userUniqueId = m_UserUniqueId; }
    public void setUserLogin(String userLogin) { UserLogin = userLogin; }
    public void setFirstName(String firstName) { FirstName = firstName; }
    public void setPhoneNumber(String phoneNumber) { PhoneNumber = phoneNumber; }
    public void setLastName(String lastName) { LastName = lastName; }
    public void setEmail(String email) { Email = email; }

    // ### Getters ### //
    public int get_UserUniqueId() { return userUniqueId; }
    public String getUserLogin() { return UserLogin; }
    public String getFirstName() { return FirstName; }
    public String getLastName() { return LastName; }
    public String getEmail() { return Email; }
    public String getPhoneNumber() { return PhoneNumber; }



    private static boolean isInit = false;

    private static volatile UserProfile userProfile;
    public UserProfile() {
    }

    public static UserProfile getUserProfile() {
        if (!isInit) {
            userProfile = new UserProfile();
        isInit = true;
        }
        return userProfile;
    }
    /** Updates Profile, call when each item of the profile is updated */
    public void updateProfile() {
    }

    public boolean getIsInit() { return isInit; }
}