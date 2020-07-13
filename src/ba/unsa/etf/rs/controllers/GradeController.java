package ba.unsa.etf.rs.controllers;

import ba.unsa.etf.rs.MultiSelectList;
import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Grade;
import ba.unsa.etf.rs.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GradeController implements Initializable {
    private final ObservableList<Child> children = FXCollections.observableArrayList();
    private final ObservableList<Child> gradeChildren = FXCollections.observableArrayList();
    private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    public MultiSelectList<Child> childrenList;
    public MultiSelectList<Child> gradeList;
    public ChoiceBox<Teacher> teacherChoiceBox;
    public TextField nameField;
    private Grade grade;

    GradeController(Grade grade, ObservableList<Child> allChildren, ObservableList<Teacher> teachers) {
        this.children.setAll(allChildren);
        this.teachers.setAll(teachers);
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public ObservableList<Child> getChildren() {
        return children;
    }

    public ObservableList<Child> getGradeChildren() {
        return gradeChildren;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherChoiceBox.setItems(teachers);
        childrenList.setItems(children);
        gradeList.setItems(gradeChildren);
        if (grade != null) {
            gradeChildren.setAll(grade.getChildren());
            nameField.setText(grade.getName());
            teacherChoiceBox.getSelectionModel().select(grade.getTeacher());
        } else {
            teacherChoiceBox.getSelectionModel().selectFirst();
        }
    }

    public void onAddToGrade(ActionEvent actionEvent) {
        gradeChildren.addAll(childrenList.getSelectionModel().getSelectedItems());
        children.removeAll(childrenList.getSelectionModel().getSelectedItems());
    }

    public void onRemoveFromGrade(ActionEvent actionEvent) {
        children.addAll(gradeList.getSelectionModel().getSelectedItems());
        gradeChildren.removeAll(gradeList.getSelectionModel().getSelectedItems());
    }

    public void onSave(ActionEvent actionEvent) {
        if (grade == null) {
            grade = new Grade();
        }
        grade.setTeacher(teacherChoiceBox.getValue());
        grade.setName(nameField.getText());
        grade.setChildren(gradeList.getItems());
        onClose();
    }

    public void onCancel(ActionEvent actionEvent) {
        grade = null;
        onClose();
    }

    public void onClose() {
        Stage stage = (Stage) gradeList.getScene().getWindow();
        stage.close();
    }
}
