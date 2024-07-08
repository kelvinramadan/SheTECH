package fxml_oracle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class feedback2Controller {
    @FXML
    private TableView<String> feedbackTableView;
    @FXML
    private TableColumn<String, String> feedbackColumn;

    private ObservableList<String> feedbackData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        feedbackColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        feedbackTableView.setItems(feedbackData);
        loadFeedbackFromXML();
    }

    private void loadFeedbackFromXML() {
        XStream xStream = new XStream(new StaxDriver());
        xStream.alias("feedbackList", List.class);
        xStream.alias("feedback", String.class);

        try (FileReader reader = new FileReader("feedback.xml")) {
            List<String> feedbackList = (List<String>) xStream.fromXML(reader);
            setFeedbackList(feedbackList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFeedbackList(List<String> feedbackList) {
        feedbackData.addAll(feedbackList);
    }
}
