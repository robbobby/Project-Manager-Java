package loginwindow;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginWindow {

    // ############################## FXML FIELDS ############################## //
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

    // ########################### Registration form ########################### //
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

    // ###################### Warnings on registration form #################### //
    public Label userIDWarning_Label;
    public Label passwordWarning_Label;
    public Label passwordMatchWarning_Label;
    public HBox passwordMatchWarning_HBox;
    public HBox userIDWarning_HBox;
    public HBox passwordWarning_HBox;
    public ImageView firstName_Warning;
    public ImageView email_Warning;
    public ImageView lastName_Warning;
    public static Stage THIS_WINDOW = null;

    public void Register_Button_Clicked() {
    }

    public void SignIn_Button_Clicked() {
    }

    public void Exit_Button_Clicked() {
    }

    public void RegisterNext_Button_Clicked() {
    }

    public void UserID_TextField_TypedIn() {
    }

    public void Password_TextField_TypedIn() {
    }

    public void Password2_TextField_Changed() {
    }

    public void RegisterBack_button_Clicked() {
    }

    public void CompleteRegistration_Clicked() {
    }
}