package fxml_oracle;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OpenScene {
    public Pane getPane(String fxmlFile) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    public static void loadFXML(String fxml, Stage window) {
        try {
            Parent root = FXMLLoader.load(OpenScene.class.getResource(fxml));
            window.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}