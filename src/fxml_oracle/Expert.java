package fxml_oracle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Expert {
    private StringProperty name;
    private StringProperty expert;
    private StringProperty expertise;

    public Expert(String name, String expert, String expertise) {
        this.name = new SimpleStringProperty(name);
        this.expert = new SimpleStringProperty(expert);
        this.expertise = new SimpleStringProperty(expertise);
    }

    // Getter and Setter methods
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getExpert() {
        return expert.get();
    }

    public void setExpert(String expert) {
        this.expert.set(expert);
    }

    public StringProperty expertProperty() {
        return expert;
    }

    public String getExpertise() {
        return expertise.get();
    }

    public void setExpertise(String expertise) {
        this.expertise.set(expertise);
    }

    public StringProperty expertiseProperty() {
        return expertise;
    }
}
