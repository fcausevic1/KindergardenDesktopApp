package ba.unsa.etf.rs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Observables
    private final ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.values());
    private final ObservableList<Person> teachers = FXCollections.observableArrayList();
    private final ObservableList<Child> children = FXCollections.observableArrayList();
    private final ObservableList<Grade> grades = FXCollections.observableArrayList();
    public TableView<Grade> gradesTableView;
    public TextField teacherFirstNameField;
    public TextField teacherLastNameField;
    public TextField teacherTelephoneField;
    public TextField teacherEmailField;
    public TextField teacherBirthplaceField;
    public TextField teacherAddressField;
    public TextField teacherJMBGField;
    public DatePicker teacherDateField;
    public TableColumn<Grade, String> gradeColumn;
    public TableColumn<Grade, Person> teacherColumn;
    public TextField childFirstNameField;
    public TextField childLastNameField;
    public TextField childParentNameField;
    public TextField childParentTelephoneField;
    public DatePicker childDateField;
    public TextField childAddressField;
    public TextField childBirthplaceField;
    public TableColumn<Grade, String> childrenColumn;
    public TextField childJMBGField;
    public ListView<Person> teachersListView;
    public ChoiceBox<Gender> teacherGenderChoiceBox;
    public ListView<Child> childrenListView;
    public ChoiceBox<Gender> childGenderChoiceBox;

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

        //Test items
        Person teacher1 = new Person();
        teacher1.setFirstName("Teacher");
        teacher1.setLastName("One");
        teachers.add(teacher1);
        Child child1 = new Child();
        child1.setFirstName("Child");
        child1.setLastName("One");
        children.add(child1);
        Grade grade1 = new Grade();
        grade1.setName("Grade One");
        grade1.setTeacher(teacher1);
        grade1.getChildren().add(child1);
        grades.add(grade1);

    }

    public void onAddGrade(ActionEvent actionEvent) {
    }

    public void onEditGrade(ActionEvent actionEvent) {
    }

    public void onDeleteGrade(ActionEvent actionEvent) {
    }

    public void onAddTeacher(ActionEvent actionEvent) {
    }

    public void onDeleteTeacher(ActionEvent actionEvent) {
    }

    public void onAddChild(ActionEvent actionEvent) {
    }

    public void onDeleteChild(ActionEvent actionEvent) {
    }

    public void onParentDetails(ActionEvent actionEvent) {
    }

    public void onActivity(ActionEvent actionEvent) {
    }
}
