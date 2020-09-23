package homewindow;

import javafx.scene.control.ComboBox;
import utilitywindows.AddNewTeamWindow;

public class HomeWindow {
    public ComboBox projectSelect_ComboBox;

    public void addNewProfile() {
        AddNewTeamWindow profileWindow = new AddNewTeamWindow();
        profileWindow.init();


    }

}


