package fxml_oracle;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class resourceController {

    @FXML
    private Button backButton;

    @FXML
    private void back1(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene2 = FXMLLoader.load(getClass().getResource("1menu.fxml"));
        stage.setScene(new Scene(scene2));
    }
}
