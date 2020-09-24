package homewindow;

import data.User;
import data.UserProfile;
import javafx.scene.control.ComboBox;
import utilitywindows.AddNewTeamWindow;

public class HomeWindow {
    public ComboBox projectSelect_ComboBox;
    public ComboBox teamSelection_ComboBox;

    public void addNewTeamProfile() {
        AddNewTeamWindow profileWindow = new AddNewTeamWindow();
        profileWindow.init();


        User user = new User();

    }

}


