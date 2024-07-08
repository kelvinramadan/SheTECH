package fxml_oracle;

import javax.xml.stream.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class XMLHandler {

    public void saveToXML(ObservableList<UserData> userDataList, String fileName) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(fileName));

            writer.writeStartDocument();
            writer.writeStartElement("users");

            for (UserData userData : userDataList) {
                writer.writeStartElement("user");

                writer.writeStartElement("name");
                writer.writeCharacters(userData.getName());
                writer.writeEndElement();

                writer.writeStartElement("address");
                writer.writeCharacters(userData.getAddress());
                writer.writeEndElement();

                writer.writeStartElement("email");
                writer.writeCharacters(userData.getEmail());
                writer.writeEndElement();

                writer.writeStartElement("phoneNumber");
                writer.writeCharacters(userData.getPhoneNumber());
                writer.writeEndElement();

                writer.writeStartElement("interest");
                writer.writeCharacters(userData.getInterest());
                writer.writeEndElement();

                writer.writeStartElement("bio");
                writer.writeCharacters(userData.getBio());
                writer.writeEndElement();

                writer.writeEndElement(); // end user
            }

            writer.writeEndElement(); // end users
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<UserData> loadFromXML(String fileName) {
        ObservableList<UserData> userDataList = FXCollections.observableArrayList();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(fileName));

            String currentElement = null;
            UserData userData = null;

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        if ("user".equals(currentElement)) {
                            userData = new UserData();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (text.isEmpty()) {
                            break;
                        }
                        switch (currentElement) {
                            case "name":
                                userData.setName(text);
                                break;
                            case "address":
                                userData.setAddress(text);
                                break;
                            case "email":
                                userData.setEmail(text);
                                break;
                            case "phoneNumber":
                                userData.setPhoneNumber(text);
                                break;
                            case "interest":
                                userData.setInterest(text);
                                break;
                            case "bio":
                                userData.setBio(text);
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if ("user".equals(reader.getLocalName())) {
                            userDataList.add(userData);
                        }
                        break;
                }
            }

            reader.close();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return userDataList;
    }
}
