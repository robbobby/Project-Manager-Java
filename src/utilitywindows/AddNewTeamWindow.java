package utilitywindows;

import data.UserProfile;
import dbconnection.TeamDbConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import data.AddCompanyProfile;

import java.sql.SQLException;

public class AddNewTeamWindow {

    HBox warning;


    public void init() {
        // Setup new window and stage //
        Stage window = new Stage(); // Window

        // Setup items to be added to window //
        VBox comp = new VBox(); // Holder for items
        Insets inset = new Insets(10);
        Label profileName = new Label("Team Profile Name");
        TextField profileName_TextField = new TextField("Name");
        HBox buttonContainer = new HBox();
        HBox buttonContainer1 = new HBox();
        Button exit = new Button("Exit");
        Button finish = new Button("Finish");
        Image warningIm = new Image("utilitywindows/Assets/Warning.png", 20, 20, false, true);
        ImageView warningImage = new ImageView(warningIm);
        Label warningLabel = new Label();
        System.out.println(warningIm.getUrl());

            // Set member properties //
        warningLabel.setText("Team name already exists");
        warningLabel.setStyle("-fx-font-size: 8pt;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-opacity: 0.8;\n" +
                "    -fx-font-style: italic;\n" +
                "    -fx-wrap-text: true;");
        buttonContainer1.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainer1.setMinWidth(120);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        warning = new HBox();
        comp.setPadding(inset);
        comp.setId("vbox_style");
        warning.setVisible(false);
        warning.setSpacing(10);
        warning.setAlignment(Pos.TOP_LEFT);
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.initStyle(StageStyle.UNDECORATED);



                // ########## Setup page layout ########## //
        comp.getChildren().addAll(profileName, profileName_TextField, warning);
        warning.getChildren().addAll(warningImage, warningLabel);
        comp.getChildren().add(buttonContainer);
        buttonContainer.getChildren().addAll(buttonContainer1, finish);
        buttonContainer1.getChildren().add(exit);

        UserProfile userProfile = UserProfile.getUserProfile();


        // Start Scene
        Scene stageScene = new Scene(comp, 200, 170);
        stageScene.getStylesheets().add("utilitywindows/UtilityStyle.css");


        window.setScene(stageScene);
        window.show();
        // Exit Button Clicked //
        exit.setOnAction(event -> window.close());

        // Finish Button Clicked //
        finish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println(userProfile.get_UserUniqueId());
                    System.out.println(userProfile.getEmail());
                    if (AddCompanyProfile.addProject(profileName_TextField.getText(), userProfile.get_UserUniqueId(), window))
                        warning.setVisible(true);
                    else {
                        warning.setVisible(false);
                    window.close();
                        TeamDbConnection.makeDatabase(profileName_TextField.getText());
                        NotificationPopUp.display("Project Created Successfully");
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public HBox getWarning () { return warning; }
}

