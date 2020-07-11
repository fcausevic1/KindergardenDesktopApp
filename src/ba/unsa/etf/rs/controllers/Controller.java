package ba.unsa.etf.rs.controllers;

import ba.unsa.etf.rs.enums.Gender;
import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Grade;
import ba.unsa.etf.rs.models.Parent;
import ba.unsa.etf.rs.models.Teacher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Observables
    private final ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.values());
    private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private final ObservableList<Child> children = FXCollections.observableArrayList();
    private final ObservableList<Grade> grades = FXCollections.observableArrayList();
    public TableView<Grade> gradesTableView;
    public TextField teacherFirstNameField;
    public TextField teacherLastNameField;
    public TextField teacherTelephoneField;
    public TextField teacherBirthplaceField;
    public TextField teacherAddressField;
    public TextField teacherJMBGField;
    public DatePicker teacherDateField;
    public TableColumn<Grade, String> gradeColumn;
    public TableColumn<Grade, Teacher> teacherColumn;
    public TextField childFirstNameField;
    public TextField childLastNameField;
    public TextField childParentNameField;
    public TextField childParentTelephoneField;
    public DatePicker childDateField;
    public TextField childAddressField;
    public TextField childBirthplaceField;
    public TableColumn<Grade, String> childrenColumn;
    public TextField childJMBGField;
    public ListView<Teacher> teachersListView;
    public ChoiceBox<Gender> teacherGenderChoiceBox;
    public ListView<Child> childrenListView;
    public ChoiceBox<Gender> childGenderChoiceBox;
    public TextField teacherEmailField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ChoiceBox initialization
        teacherGenderChoiceBox.setItems(genders);
        childGenderChoiceBox.setItems(genders);

        //ListView initalization
        teachersListView.setItems(teachers);
        childrenListView.setItems(children);

        //Table initialization
        gradesTableView.setItems(grades);
        gradeColumn.setCellValueFactory(grade -> grade.getValue().nameProperty());
        teacherColumn.setCellValueFactory(grade -> grade.getValue().teacherProperty());
        childrenColumn.setCellValueFactory(grade -> new SimpleStringProperty(String.valueOf(grade.getValue().getChildren().size())));

        //Parent fields
        childParentNameField.setEditable(false);
        childParentTelephoneField.setEditable(false);

        //Test items
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Teacher");
        teacher1.setLastName("One");
        teachers.add(teacher1);
        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Teacher");
        teacher2.setLastName("Two");
        teachers.add(teacher2);
        Parent parent1 = new Parent();
        parent1.setFirstName("Parent");
        parent1.setLastName("One");
        parent1.setTelephone("000/000-000");
        Child child1 = new Child();
        child1.setFirstName("Child");
        child1.setLastName("One");
        child1.setFirstParent(parent1);
        child1.setGender(Gender.FEMALE);
        children.add(child1);
        Grade grade1 = new Grade();
        grade1.setName("Grade One");
        grade1.setTeacher(teacher1);
        grade1.getChildren().add(child1);
        grades.add(grade1);

        //Bindings
        childrenListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                childFirstNameField.textProperty().unbindBidirectional(oldValue.firstNameProperty());
                childLastNameField.textProperty().unbindBidirectional(oldValue.lastNameProperty());
                childDateField.valueProperty().unbindBidirectional(oldValue.dateOfBirthProperty());
                childBirthplaceField.textProperty().unbindBidirectional(oldValue.placeOfBirthProperty());
                childJMBGField.textProperty().unbindBidirectional(oldValue.jmbgProperty());
                childAddressField.textProperty().unbindBidirectional(oldValue.addressProperty());
                childGenderChoiceBox.valueProperty().unbindBidirectional(oldValue.genderProperty());
                childParentTelephoneField.setText("");
                childParentNameField.setText("");
            }
            if (newValue == null) {
                childFirstNameField.setText("");
                childLastNameField.setText("");
                childDateField.setValue(LocalDate.now());
                childParentTelephoneField.setText("");
                childBirthplaceField.setText("");
                childJMBGField.setText("");
                childAddressField.setText("");
                childParentNameField.setText("");
                childGenderChoiceBox.getSelectionModel().selectFirst();
            } else {
                childFirstNameField.textProperty().bindBidirectional(newValue.firstNameProperty());
                childLastNameField.textProperty().bindBidirectional(newValue.lastNameProperty());
                childDateField.valueProperty().bindBidirectional(newValue.dateOfBirthProperty());
                childBirthplaceField.textProperty().bindBidirectional(newValue.placeOfBirthProperty());
                childJMBGField.textProperty().bindBidirectional(newValue.jmbgProperty());
                childAddressField.textProperty().bindBidirectional(newValue.addressProperty());
                childGenderChoiceBox.valueProperty().bindBidirectional(newValue.genderProperty());
                if (newValue.getFirstParent() != null) {
                    childParentTelephoneField.setText(newValue.getFirstParent().getTelephone());
                    childParentNameField.setText(newValue.getFirstParent().toString());
                }
            }
        });

        teachersListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                teacherFirstNameField.textProperty().unbindBidirectional(oldValue.firstNameProperty());
                teacherLastNameField.textProperty().unbindBidirectional(oldValue.lastNameProperty());
                teacherDateField.valueProperty().unbindBidirectional(oldValue.dateOfBirthProperty());
                teacherTelephoneField.textProperty().unbindBidirectional(oldValue.telephoneProperty());
                teacherBirthplaceField.textProperty().unbindBidirectional(oldValue.placeOfBirthProperty());
                teacherJMBGField.textProperty().unbindBidirectional(oldValue.jmbgProperty());
                teacherAddressField.textProperty().unbindBidirectional(oldValue.addressProperty());
                teacherEmailField.textProperty().unbindBidirectional(oldValue.emailProperty());
                teacherGenderChoiceBox.valueProperty().unbindBidirectional(oldValue.genderProperty());
            }
            if (newValue == null) {
                teacherFirstNameField.setText("");
                teacherLastNameField.setText("");
                teacherDateField.setValue(LocalDate.now());
                teacherTelephoneField.setText("");
                teacherBirthplaceField.setText("");
                teacherJMBGField.setText("");
                teacherAddressField.setText("");
                teacherEmailField.setText("");
                teacherGenderChoiceBox.getSelectionModel().selectFirst();
            } else {
                teacherFirstNameField.textProperty().bindBidirectional(newValue.firstNameProperty());
                teacherLastNameField.textProperty().bindBidirectional(newValue.lastNameProperty());
                teacherDateField.valueProperty().bindBidirectional(newValue.dateOfBirthProperty());
                teacherTelephoneField.textProperty().bindBidirectional(newValue.telephoneProperty());
                teacherBirthplaceField.textProperty().bindBidirectional(newValue.placeOfBirthProperty());
                teacherJMBGField.textProperty().bindBidirectional(newValue.jmbgProperty());
                teacherAddressField.textProperty().bindBidirectional(newValue.addressProperty());
                teacherEmailField.textProperty().bindBidirectional(newValue.emailProperty());
                teacherGenderChoiceBox.valueProperty().bindBidirectional(newValue.genderProperty());
            }
        });

        teacherFirstNameField.textProperty().addListener((observableValue, oldValue, newValue) -> teachersListView.refresh());
        teacherLastNameField.textProperty().addListener((observableValue, oldValue, newValue) -> teachersListView.refresh());


    }

    public void onAddGrade(ActionEvent actionEvent) {
    }

    public void onEditGrade(ActionEvent actionEvent) {
    }

    public void onDeleteGrade(ActionEvent actionEvent) {
        grades.removeAll(gradesTableView.getSelectionModel().getSelectedItem());
    }

    public void onAddTeacher(ActionEvent actionEvent) {
        teachers.add(new Teacher());
        teachersListView.getSelectionModel().selectLast();
    }

    public void onDeleteTeacher(ActionEvent actionEvent) {
        teachers.removeAll(teachersListView.getSelectionModel().getSelectedItem());
    }

    public void onAddChild(ActionEvent actionEvent) {
        children.add(new Child());
        childrenListView.getSelectionModel().selectLast();
    }

    public void onDeleteChild(ActionEvent actionEvent) {
        children.removeAll(childrenListView.getSelectionModel().getSelectedItems());
    }

    public void onParentDetails(ActionEvent actionEvent) {
    }

    public void onActivity(ActionEvent actionEvent) {
    }
}
