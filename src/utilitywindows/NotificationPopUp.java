package utilitywindows;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NotificationPopUp {
/** Pop up window with simple OK Button and message
 *** Is TopMost always and stops user from interacting with parent window*/
public static void display(String message) {
        Stage window = new Stage();
//        window.initStyle(StageStyle.UNDECORATED);
            // Make Window TopMost and stop user interacting with parent window //
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(200);
        window.setMinHeight(170);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());

        VBox container = new VBox(10);
        container.getChildren().addAll (label, closeButton);
        container.setAlignment(Pos.CENTER);

        Parent root;
        Scene scene = new Scene(container);
        scene.getStylesheets().add("utilitywindows/UtilityStyle.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
