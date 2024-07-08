package fxml_oracle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
    @FXML
    private TextField addressbox;

    @FXML
    private DatePicker birthbox;

    @FXML
    private Button createaccount;

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField namebox;

    @FXML
    private TextField password2;

    @FXML
    private TextField phonebox;

    @FXML
    private Hyperlink signin;

    @FXML
    private Button signin1;

    @FXML
    private TextField username2;

    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void createaccount(ActionEvent event) {
        try {
            if (username2.getText().isEmpty() || password2.getText().isEmpty() ||
                    namebox.getText().isEmpty() || phonebox.getText().isEmpty() ||
                    addressbox.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Registrasi Gagal", "Error", "Semua Data Harus di Isi");
                return;
            }

            User newUser = new User(username2.getText(), password2.getText(), namebox.getText(), addressbox.getText(),
                    phonebox.getText());
            XStream xstream = new XStream(new StaxDriver());
            xstream.allowTypes(new Class[] { User.class });

            String xml = xstream.toXML(newUser);

            try (FileOutputStream output = new FileOutputStream("dataMember.xml")) {
                output.write(xml.getBytes());
                showAlert(AlertType.INFORMATION, "Registrasi Berhasil", null, "Registrasi berhasil. Silakan login.");

                // Load the SignIn page after showing the success message
                Parent signInRoot = FXMLLoader.load(getClass().getResource("1SignIn.fxml"));
                Scene signInScene = new Scene(signInRoot);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(signInScene);
                stage.show();

            } catch (IOException e) {
                showAlert(AlertType.ERROR, "Error", "Gagal Menyimpan Data", "Detail: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Terjadi Kesalahan", "Detail: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void signin(ActionEvent event) {
        OpenScene.loadFXML("1SignIn.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void signin1(ActionEvent event) {
        OpenScene.loadFXML("1SignIn.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
