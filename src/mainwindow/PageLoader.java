package mainwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class PageLoader {
    URL url;
    private Pane view;

    public Pane getScene(String sceneURL) {
        try {
            url = PageLoader.class.getResource(sceneURL);
            if (url == null) {
                throw new java.io.FileNotFoundException("Cannot find path to file");
            }

            new FXMLLoader();
            view = FXMLLoader.load(url);

        } catch (Exception e) {
            System.err.println(e);
        }
        return view;
    }

    public String setURLString(String urlString) {
        return "/" + urlString + "Window/" + urlString + "Window.fxml";
    }
}