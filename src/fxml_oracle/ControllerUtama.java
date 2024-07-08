package fxml_oracle;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControllerUtama implements Initializable {
    @FXML
    private BorderPane borderpane;

    @FXML
    private Button profile, dashboard, register, guidance, resource, forum, confirmation, feedback;

    @FXML
    private TextField name, bio, interest, birth, phonenumber, email, address, searchbox;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void science(ActionEvent event) throws IOException {
        navigateToScene(event, "3register2.fxml");
    }

    @FXML
    private void technology(ActionEvent event) throws IOException {
        navigateToScene(event, "3register2.fxml");
    }

    @FXML
    private void engineering(ActionEvent event) throws IOException {
        navigateToScene(event, "3register2.fxml");
    }

    @FXML
    private void mathematics(ActionEvent event) throws IOException {
        navigateToScene(event, "3register2.fxml");
    }

    private void navigateToScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnProfile(ActionEvent event) throws IOException {
        // Handle Profile button action
    }

    @FXML
    private void btnDashboard(ActionEvent event) throws IOException {
        loadView("2dashboard1.fxml");
    }

    @FXML
    private void btnRegister(ActionEvent event) throws IOException {
        loadView("3register.fxml");
    }

    @FXML
    private void btnGuidance(ActionEvent event) throws IOException {
        loadView("4guidance.fxml");
    }

    @FXML
    private void btnResource(ActionEvent event) throws IOException {
        loadView("5resource.fxml");
    }

    @FXML
    private void btnForum(ActionEvent event) throws IOException {
        loadView("6forums.fxml");
    }

    @FXML
    private void btnConfirmation(ActionEvent event) throws IOException {
        loadView("7confirmation.fxml");
    }

    @FXML
    private void btnFeedback(ActionEvent event) throws IOException {
        loadView("8feedback.fxml");
    }

    private void loadView(String fxmlFile) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource(fxmlFile));
        borderpane.setCenter(view);
    }

    @FXML
    private void btnSubmit(ActionEvent event) {
        // Handle submit button action
    }

    @FXML
    private void btnSubmitFeedback(ActionEvent event) {
        // Handle submit feedback button action
    }

    //resources
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private void nextpage1(ActionEvent event) throws IOException {
        navigateToScene(event, "5resource2.fxml");
    }

    @FXML
    private void nextpage2(ActionEvent event) throws IOException {
        navigateToScene(event, "5resource3.fxml");
    }

    @FXML
    private TextField feedbackTextField;

    @FXML
    private Button submitButton;

    private List<String> feedbackList = new ArrayList<>();

    @FXML
    private void btnFeedback2(ActionEvent event) {
        String feedback = feedbackTextField.getText();
        if (!feedback.isEmpty()) {
            feedbackList.add(feedback);
            saveFeedbackToXML(feedbackList);
            feedbackTextField.clear();
        }
    }

    private void saveFeedbackToXML(List<String> feedbackList) {
        XStream xStream = new XStream(new StaxDriver());
        xStream.alias("feedbackList", List.class);
        xStream.alias("feedback", String.class);
        
        try (FileWriter writer = new FileWriter("feedback.xml")) {
            xStream.toXML(feedbackList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code here
    }
}
