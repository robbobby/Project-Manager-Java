package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage loginWindow) throws Exception {
        Parent loginWindowScene = FXMLLoader.load(getClass().getResource("../loginwindow/LoginWindow.fxml"));
        loginWindow.setTitle("Buggy Manager");
        loginWindow.setResizable(false);
        loginWindow.initStyle(StageStyle.UNDECORATED);
        loginWindow.initStyle(StageStyle.TRANSPARENT);
        loginWindow.setScene(new Scene(loginWindowScene, 605, 480));

        Scene scene = loginWindow.getScene();
        scene.setFill(Color.TRANSPARENT);

        loginWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}