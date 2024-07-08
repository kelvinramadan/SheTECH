package fxml_oracle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class register2Controller {
    @FXML
    private TextField searchbox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField expertField;

    @FXML
    private TextField expertiseField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonback1;

    @FXML
    private TableView<Expert> expertTable;
    @FXML
    private TableColumn<Expert, String> nameColumn;
    @FXML
    private TableColumn<Expert, String> expertColumn;
    @FXML
    private TableColumn<Expert, String> expertiseColumn;

    private ObservableList<Expert> expertData = FXCollections.observableArrayList();

    public register2Controller() {
        // Initialize some data
        expertData.add(new Expert("Rahmawati S.T M.Cs", "Software Engineer", "Java"));
        expertData.add(new Expert("Maharani S.T ", "Data Scientist", "Machine Learning"));
        expertData.add(new Expert("Yuniar Mayanti S.T M.Cs", "Software Engineer", "C++"));
        expertData.add(new Expert("Ayu Januari S.T M.Cs", "FrontEnd", "Java"));
        expertData.add(new Expert("Megawati S.T M.Cs", "Software Engineer", "Python"));
        expertData.add(new Expert("Naura Maya S.T M.Cs", "Data Scientist", "Machine Learning"));
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        expertColumn.setCellValueFactory(cellData -> cellData.getValue().expertProperty());
        expertiseColumn.setCellValueFactory(cellData -> cellData.getValue().expertiseProperty());

        expertTable.setItems(expertData);
    }

    private boolean showExpertDetailsDialog(Expert expert) {
        TextInputDialog dialog = new TextInputDialog(expert != null ? expert.getName() : "");
        dialog.setTitle(expert == null ? "Add Expert" : "Edit Expert");
        dialog.setHeaderText(expert == null ? "Enter expert details" : "Edit expert details");

        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String name = result.get();
            dialog.setContentText("Expert:");
            result = dialog.showAndWait();
            if (result.isPresent()) {
                String expertName = result.get();
                dialog.setContentText("Expertise:");
                result = dialog.showAndWait();
                if (result.isPresent()) {
                    String expertise = result.get();
                    if (expert == null) {
                        expertData.add(new Expert(name, expertName, expertise));
                    } else {
                        expert.setName(name);
                        expert.setExpert(expertName);
                        expert.setExpertise(expertise);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleTableMouseClicked(MouseEvent event) throws IOException {
        // Mendapatkan Stage dari AnchorPane tempat TableView berada
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Memuat FXML 3register3.fxml
        Parent scene2 = FXMLLoader.load(getClass().getResource("3register3.fxml"));

        // Menampilkan scene2 di Stage
        stage.setScene(new Scene(scene2));
    }

    @FXML
    private void back1(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene2 = FXMLLoader.load(getClass().getResource("1menu.fxml"));
        stage.setScene(new Scene(scene2));
    }
}
