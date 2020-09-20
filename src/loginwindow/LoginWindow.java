package loginwindow;

import data.AddToUserDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginWindow {

    // ###################### FXML FIELDS ###################### //
    public VBox loginForm;
    public GridPane registryForm;
    public GridPane registryForm_Page1;
    public GridPane registryForm_Page2;
    public GridPane registerComplete;
    public TextField loginUserName_TextField;
    public TextField loginPassword_TextField;
    public CheckBox staySignedIn_CheckBox;
    public CheckBox rememberUserName_CheckBox;
    public Button signInWithFacebook_Button;
    public Button signInWithTwitter_Button;
    public Button sighInWithGoogle_Button;

    // ###################### Registration form ###################### //
    public TextField firstName_TextField;
    public TextField lastName_TextField;
    public TextField emailAddress_TextField;
    public TextField mobileNumberCountryCode_TextField;
    public TextField mobileNumber_TextField;
    public TextField addressLine1_TextField;
    public TextField addressLine2_TextField;
    public TextField addressCity_TextField;
    public TextField addressPostCode_TextField;
    public TextField addressCountry_TextField;
    public TextField userID_TextField;
    public TextField passwordRegister_PasswordField;
    public TextField passwordRegister1_PasswordField;

    // ###################### Warnings on registration form ###################### //
    public Label userIDWarning_Label;
    public Label passwordWarning_Label;
    public Label passwordMatchWarning_Label;
    public HBox passwordMatchWarning_HBox;
    public HBox userIDWarning_HBox;
    public HBox passwordWarning_HBox;
    public ImageView firstName_Warning;
    public ImageView email_Warning;
    public ImageView lastName_Warning;
    public static Stage thisWindow = null;
    public HBox finalWarning;


    public void SignIn_Button_Clicked() {
        thisWindow = (Stage) loginForm.getScene().getWindow();
    }

    // ##### Register Buttons #####//
    public void Register_Button_Clicked() {
        registryForm_Page1.setVisible(!registryForm_Page1.isVisible());
        registryForm_Page2.setVisible(false);

        if (registryForm_Page1.isVisible()) {
            registryForm.setOpacity(0.8);
            loginForm.setOpacity(0.2); // not working for some reason
        } else
            registryForm.setOpacity(0.2);
        loginForm.setOpacity(1);
    }


    public void Exit_Button_Clicked() {
        thisWindow = (Stage) loginForm.getScene().getWindow();
        thisWindow.close();
    }


    //###################### Registration form Button Events ###################### //
    public void RegisterNext_Button_Clicked() {
        registryForm_Page1.setVisible(false);
        registryForm_Page2.setVisible(true);
    }

    public void RegisterBack_button_Clicked() {
        registryForm_Page1.setVisible(true);
        registryForm_Page2.setVisible(false);
    }

    // ##### Try New Registration Event ##### //
    public void CompleteRegistration_Clicked() throws SQLException {

        // ##### Make array from the TextFields ##### //
        String[] userDetails = {emailAddress_TextField.getText(),
                firstName_TextField.getText(),
                lastName_TextField.getText(),
                mobileNumber_TextField.getText(),
                mobileNumberCountryCode_TextField.getText(),
                addressLine1_TextField.getText(),
                addressLine2_TextField.getText(),
                addressCity_TextField.getText(),
                addressPostCode_TextField.getText(),
                addressCountry_TextField.getText(),
                userID_TextField.getText(),
                passwordRegister_PasswordField.getText(),
        };
        ResultSet resultSet = null;
        if (CheckUserID(userID_TextField.getText()) && CheckPassword(passwordRegister_PasswordField.getText()) && CheckPasswordMatch()) {
            System.out.println(Arrays.toString(userDetails));
            System.out.println(userDetails.length);
            resultSet = AddToUserDB.addUser(userDetails);
            finalWarning.setVisible(false);
            registryForm_Page2.setVisible(false);
            registerComplete.setVisible(true);
        }
        else
            finalWarning.setVisible(true);


        email_Warning.setVisible(!CheckEmailAddress(emailAddress_TextField.getText()));



        if (resultSet == null) {
            // Registration success //
        }
        //emailAddress, firstName, lastName, mobileNumber, mobileCC, addressLine1, addressLine2,
        //                addressCity, addressPostCode, addressCountry, userLogin, password);
    }

    public void UserID_TextField_TypedIn() {
        String idLengthWarning = "";
        String idSpaceWarning = "";

        if (userID_TextField.getText().length() < 5) {
            userIDWarning_HBox.setVisible(true);
            idLengthWarning = "User ID must be at least 5 characters long.\n";
            userIDWarning_Label.setText(idLengthWarning);
        }

        if (userID_TextField.getText().contains(" ")) {
            userIDWarning_HBox.setVisible(true);
            idSpaceWarning = "UserID cannot contain spaces";
        }

        if (idLengthWarning.length() + idSpaceWarning.length() < 5) {
            userIDWarning_HBox.setVisible(false);
            userIDWarning_Label.setText(idLengthWarning + idSpaceWarning);
        }
    }


    public void Password_TextField_TypedIn() {
        String passwordCapitalWarning = "";
        String passwordNumberWarning = "";
        String passwordSpaceWarning = "";
        String passwordLengthWarning = "";

        String password = passwordRegister_PasswordField.getText();
        // Check length of password
        if (passwordRegister_PasswordField.getText().length() < 6 || passwordRegister_PasswordField.getText().length() > 20) {
            passwordWarning_Label.setVisible(true);
            passwordLengthWarning = "The password must be within 6 and 20 characters long. \n";
        }
        // Check to make sure password contains 1 upper and lower case letter
        if (password.equals(password.toLowerCase()) || password.equals(password.toUpperCase())) {
            passwordWarning_HBox.setVisible(true);
            passwordCapitalWarning = "Password must contain both 1 lower and uppercase letter.\n";
        }
        /* Check to make sure password contains a number //
         *  Not a very nice way and should probably think about extending the java string class
         *  However it is marked as final so would have to make a wrapper of the string class, sounds a lot of work*/
        passwordWarning_HBox.setVisible(true);
        if (password.replaceAll("[*0-9]", "").equals(password)) {
            passwordNumberWarning = "Password must contain a number";
        }
        //Check to make sure the password does not contain spaces //

        if (password.contains(" ")) {
            passwordWarning_HBox.setVisible(true);
            passwordSpaceWarning = "Password cannot contain a space";
        }

        passwordWarning_Label.setText(passwordCapitalWarning + passwordLengthWarning +
                passwordNumberWarning + passwordSpaceWarning);
        if (passwordWarning_Label.getText().length() < 5) {
            passwordWarning_HBox.setVisible(false);
        }
    }





            // ##### INTERNAL CLASS USE CHECKS ##### //
    /** Check email contains no spaces, is 5+ characters and contains: '@' '.' */
    private boolean CheckEmailAddress(String email) {
        return !email.contains(" ") && email.contains("@") && email.contains(".") && email.length() > 5;
    }

    /** Check to see if User ID is 5 characters long and contains no spaces.*/
    private boolean CheckUserID(String id) {
        return !id.contains(" ") && id.length() > 4;
    }

    /** Check to make sure that both passwords entered are the same */
    private boolean CheckPasswordMatch() {
        return passwordRegister_PasswordField.getText().equals(passwordRegister1_PasswordField.getText());
    }

    public void Password2_TextField_Changed() {
        if (passwordRegister_PasswordField.getText().length() > 6 && passwordWarning_Label.getText().length() < 5) {
            if (!passwordRegister_PasswordField.getText().equals(passwordRegister1_PasswordField.getText())){
                passwordMatchWarning_HBox.setVisible(true);
                passwordMatchWarning_Label.setText("Passwords do not match");
            }
            else
                passwordMatchWarning_HBox.setVisible(false);
        }
    }
    private boolean CheckPassword(String pass) {
        return  !pass.contains(" ") &&
                !pass.replaceAll("[*0-9]", "").equals(pass) &&
                !pass.equals(pass.toLowerCase()) &&
                !pass.equals(pass.toUpperCase()) &&
                pass.length() > 5;
    }
}

