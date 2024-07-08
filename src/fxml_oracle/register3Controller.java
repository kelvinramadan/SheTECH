package fxml_oracle;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class register3Controller {

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private TextField phonenumber;

    @FXML
    private TextField interest;

    @FXML
    private TextField bio;

    @FXML
    private Button submitregist;

    @FXML
    private Button buttonback2;

    @FXML
    private TableView<UserData> tableView;

    @FXML
    private TableColumn<UserData, String> namebox;

    @FXML
    private TableColumn<UserData, String> addressbox;

    @FXML
    private TableColumn<UserData, String> emailbox;

    @FXML
    private TableColumn<UserData, String> phonebox;

    @FXML
    private TableColumn<UserData, String> interestbox;

    @FXML
    private TableColumn<UserData, String> biobox;

    private ObservableList<UserData> userDataList;
    private XMLHandler xmlHandler;

    @FXML
    public void initialize() {
        userDataList = FXCollections.observableArrayList();
        xmlHandler = new XMLHandler();

        // Bind columns with UserData properties
        namebox.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressbox.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailbox.setCellValueFactory(new PropertyValueFactory<>("email"));
        phonebox.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        interestbox.setCellValueFactory(new PropertyValueFactory<>("interest"));
        biobox.setCellValueFactory(new PropertyValueFactory<>("bio"));

        // Load data from XML
        userDataList.addAll(xmlHandler.loadFromXML("userdata.xml"));
        tableView.setItems(userDataList);
    }

    @FXML
    void btnSubmit(ActionEvent event) {
        String newName = name.getText();
        String newAddress = address.getText();
        String newEmail = email.getText();
        String newPhoneNumber = phonenumber.getText();
        String newInterest = interest.getText();
        String newBio = bio.getText();

        UserData newUser = new UserData(newName, newAddress, newEmail, newPhoneNumber, newInterest, newBio);
        userDataList.add(newUser);

        // Save to XML
        xmlHandler.saveToXML(userDataList, "userdata.xml");

        // Clear input fields
        name.clear();
        address.clear();
        email.clear();
        phonenumber.clear();
        interest.clear();
        bio.clear();
    }

    @FXML
    private void back2(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Memuat FXML 3register3.fxml
        Parent scene2 = FXMLLoader.load(getClass().getResource("1menu.fxml"));

        // Menampilkan scene2 di Stage
        stage.setScene(new Scene(scene2));
    }
}
