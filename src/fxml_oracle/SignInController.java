package fxml_oracle;

import java.io.File;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignInController implements Initializable {
    @FXML
    private TextField username1;

    @FXML
    private PasswordField password1;

    @FXML
    private BorderPane mainPane;

    @FXML
    private void login1(ActionEvent event) {
        try {
            if (username1.getText().isEmpty() || password1.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Login Gagal", "Error", "Username atau Password Tidak Boleh Kosong");
                return;
            }

            XStream xStream = new XStream(new StaxDriver());
            xStream.allowTypes(new Class[] { User.class });

            User loadedUser = null;
            String fileName = "dataMember.xml";

            try {
                File file = new File(fileName);
                if (file.exists()) {
                    loadedUser = (User) xStream.fromXML(file);
                    if (loadedUser != null && username1.getText().equals(loadedUser.getUsername())
                            && password1.getText().equals(loadedUser.getPassword())) {
                        showAlert(AlertType.INFORMATION, null, "Login Berhasil", "Login Berhasil!!");
                        loadDashboard(event);
                        return;
                    }
                } else {
                    System.out.println("File not found: " + fileName);
                }
            } catch (Exception e) {
                System.err.println("Error reading file " + fileName + ": " + e.getMessage());
            }
            showAlert(AlertType.ERROR, "Login Gagal", "Error", "Username atau Password Salah");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Terjadi Kesalahan", "Detail: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadDashboard(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("1menu.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Error", "Gagal Membuka Halaman", "Tidak dapat membuka dashboard.fxml");
            e.printStackTrace();
        }
    }
    
    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void signup1(ActionEvent event) {
        OpenScene object1 = new OpenScene();
        Pane view = object1.getPane("2SignUp1.fxml");
        mainPane.setCenter(view);
    }

    @FXML
    private void forgotpassword(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
