package ba.unsa.etf.rs.controllers;

import ba.unsa.etf.rs.enums.Gender;
import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ParentController implements Initializable {
    private final ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.values());
    private final ObservableList<Parent> parents = FXCollections.observableArrayList();
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField telephoneField;
    public TextField emailField;
    public DatePicker dateField;
    public TextField addressField;
    public TextField birthplaceField;
    public ChoiceBox<Gender> genderChoiceBox;
    public TextField JMBGField;
    public ChoiceBox<Parent> parentChoiceBox;
    private final Child child;

    ParentController(Child child) {
        this.child = child;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderChoiceBox.setItems(genders);
        parentChoiceBox.setItems(parents);
        if (child.getFirstParent() == null) {
            parents.add(new Parent());
        } else {
            parents.add(child.getFirstParent());
        }
        if (child.getSecondParent() == null) {
            parents.add(new Parent());
        } else {
            parents.add(child.getSecondParent());
        }

        parentChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                firstNameField.textProperty().unbindBidirectional(oldValue.firstNameProperty());
                lastNameField.textProperty().unbindBidirectional(oldValue.lastNameProperty());
                dateField.valueProperty().unbindBidirectional(oldValue.dateOfBirthProperty());
                telephoneField.textProperty().unbindBidirectional(oldValue.telephoneProperty());
                birthplaceField.textProperty().unbindBidirectional(oldValue.placeOfBirthProperty());
                JMBGField.textProperty().unbindBidirectional(oldValue.jmbgProperty());
                addressField.textProperty().unbindBidirectional(oldValue.addressProperty());
                emailField.textProperty().unbindBidirectional(oldValue.emailProperty());
                genderChoiceBox.valueProperty().unbindBidirectional(oldValue.genderProperty());
            }
            if (newValue == null) {
                firstNameField.setText("");
                lastNameField.setText("");
                dateField.setValue(LocalDate.now());
                telephoneField.setText("");
                birthplaceField.setText("");
                JMBGField.setText("");
                addressField.setText("");
                emailField.setText("");
                genderChoiceBox.getSelectionModel().selectFirst();
            } else {
                firstNameField.textProperty().bindBidirectional(newValue.firstNameProperty());
                lastNameField.textProperty().bindBidirectional(newValue.lastNameProperty());
                dateField.valueProperty().bindBidirectional(newValue.dateOfBirthProperty());
                telephoneField.textProperty().bindBidirectional(newValue.telephoneProperty());
                birthplaceField.textProperty().bindBidirectional(newValue.placeOfBirthProperty());
                JMBGField.textProperty().bindBidirectional(newValue.jmbgProperty());
                addressField.textProperty().bindBidirectional(newValue.addressProperty());
                emailField.textProperty().bindBidirectional(newValue.emailProperty());
                genderChoiceBox.valueProperty().bindBidirectional(newValue.genderProperty());
            }
        });

        parentChoiceBox.getSelectionModel().selectFirst();
    }

    public void onSaveChanges(ActionEvent actionEvent) {
        child.setFirstParent(parentChoiceBox.getItems().get(0));
        child.setSecondParent(parentChoiceBox.getItems().get(1));
        onClose();
    }

    public void onClose() {
        Stage stage = (Stage) parentChoiceBox.getScene().getWindow();
        stage.close();
    }
}
