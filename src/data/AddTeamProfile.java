package data;

import dbconnection.DbConnection;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddTeamProfile {

    public static boolean addProject(String teamName, int adminUser, Stage parentWindow) throws SQLException {

        Connection connection = new DbConnection().getConnection();
        Statement addProjectStatement = connection.createStatement();
        ResultSet loginResult = null;

        String checkIfProfileExists = "SELECT team_name from teams WHERE team_name='" + teamName + "'";

        String createProfile = "INSERT INTO `teams` (`team_name`, `admin_user`, `Created_on`)\n" +
                "VALUES('" + teamName.replace(" ", "_") + "', '" + adminUser + "', NOW());\n";
        System.out.println(createProfile);

        System.out.println(checkIfProfileExists);


        loginResult = addProjectStatement.executeQuery(checkIfProfileExists);
        if (!loginResult.isBeforeFirst()) {
            addProjectStatement.executeUpdate(createProfile);
        return false;
        }
        else {
            return true;
        }
    }
}