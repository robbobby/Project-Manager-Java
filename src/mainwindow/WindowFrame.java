package mainwindow;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import loginwindow.LoginWindow;

import java.util.HashMap;

public class WindowFrame {

    public ComboBox companySelection_ComboBox;
    public ComboBox projectSelection_ComboBox;
    public VBox menuContainer_VBox;
    public ToggleButton menuToggle_Button;
    public ListView menu_ListView;

            // ##### Left Menu Items ##### //
    public VBox settingsMenuContainer_VBox;
    public HBox home_ListViewItem;
    public HBox calendar_ListViewItem;
    public HBox message_ListViewItem;
    public Label home_Label;
    public Label calendar_Label;
    public Label message_Label;


    public Button settings_Button;
    public Button logOut_Button;
    public Label settings_Label;
    public Label logOut_Label;
    public VBox BottomLeftMenuContainer;
    public BorderPane mainPane;
    public Button refresh;

    HashMap<String, String> MENU_ITEMS = new HashMap<String, String>();

    // ################ For checking against JavaFX ################### //
    // Add new menu windows to this list //
    public WindowFrame() {
        MENU_ITEMS.put("Home", "HBox[id=home_ListViewItem]");
        MENU_ITEMS.put("Calendar", "HBox[id=calendar_ListViewItem]");
        MENU_ITEMS.put("Messages", "HBox[id=message_ListViewItem]");

    }


    @FXML
    public void onMenuItems_Selected() {
        if (MENU_ITEMS.get("Home").equals(menu_ListView.getSelectionModel().getSelectedItem().toString())) {
            System.out.println("Home Selected");

            PageLoader sceneToLoad = new PageLoader();
            Pane view = sceneToLoad.getScene(sceneToLoad.setURLString("Home"));
            mainPane.setCenter(view);
            System.out.println(mainPane.getChildren());
        }
        if (MENU_ITEMS.get("Calendar").equals(menu_ListView.getSelectionModel().getSelectedItem().toString())) {
            System.out.println("Calendar Selected");

            PageLoader sceneToLoad = new PageLoader();
            Pane view = sceneToLoad.getScene(sceneToLoad.setURLString("Calendar"));
            mainPane.setCenter(view);
            System.out.println(mainPane.centerProperty().get());
        }
        if (MENU_ITEMS.get("Messages").equals(menu_ListView.getSelectionModel().getSelectedItem().toString())) {
            System.out.println("Message Selected");

            PageLoader sceneToLoad = new PageLoader();
            Pane view = sceneToLoad.getScene(sceneToLoad.setURLString("Message"));
            mainPane.setCenter(view);
        }
    }

    public void settings_Button_Clicked() {
    }

        /* Closes this window and the login screen
        *** TODO: Make EXIT button open option to log off, lock or close application
        */

    public void logOut_Button_Clicked() {
        Stage loginScreen = LoginWindow.getWindow();
        Stage thisWindow = (Stage) settings_Label.getScene().getWindow();
        loginScreen.close();
        thisWindow.close();
    }

            // ##### Make the Left Menu grow, shrink and hide Labels ##### //
    public void menuToggle_Clicked() {
        int minSize = 50;
        int maxSize = 150;
        home_Label.setVisible(!home_Label.isVisible());
        calendar_Label.setVisible(!calendar_Label.isVisible());
        message_Label.setVisible(!message_Label.isVisible());
        logOut_Label.setVisible(!logOut_Label.isVisible());
        settings_Label.setVisible(!settings_Label.isVisible());
        if (!home_Label.isVisible()) {
            menuContainer_VBox.setMaxWidth(minSize);
            settingsMenuContainer_VBox.maxWidth(minSize);
        } else
            menuContainer_VBox.setMaxWidth(maxSize);
        settingsMenuContainer_VBox.prefWidth(maxSize);
    }
}