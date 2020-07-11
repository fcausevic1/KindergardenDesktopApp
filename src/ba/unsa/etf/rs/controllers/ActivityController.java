package ba.unsa.etf.rs.controllers;

import ba.unsa.etf.rs.models.Activity;
import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityController implements Initializable {
    private final ObservableList<Activity> activities = FXCollections.observableArrayList();
    public ListView<Activity> activityListView;
    public TextField assignmentField;
    public TextArea descriptionField;
    public TextArea reviewField;
    public ChoiceBox<Teacher> teacherChoiceBox;
    public DatePicker assignmentDate;
    private final Child child;
    private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();

    ActivityController(Child child, ObservableList<Teacher> teachers) {
        this.child = child;
        activities.setAll(child.getActivities());
        this.teachers.setAll(teachers);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherChoiceBox.setItems(teachers);
        activityListView.setItems(activities);

        activityListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                assignmentField.textProperty().unbindBidirectional(oldValue.assignmentProperty());
                descriptionField.textProperty().unbindBidirectional(oldValue.descriptionProperty());
                teacherChoiceBox.valueProperty().unbindBidirectional(oldValue.teacherProperty());
                reviewField.textProperty().unbindBidirectional(oldValue.teacherReviewProperty());
                assignmentDate.valueProperty().unbindBidirectional(oldValue.dateOfLectureProperty());
            }
            if (newValue == null) {
                assignmentField.setText("");
                descriptionField.setText("");
                reviewField.setText("");
                teacherChoiceBox.getSelectionModel().selectFirst();
            } else {
                assignmentField.textProperty().bindBidirectional(newValue.assignmentProperty());
                descriptionField.textProperty().bindBidirectional(newValue.descriptionProperty());
                teacherChoiceBox.valueProperty().bindBidirectional(newValue.teacherProperty());
                reviewField.textProperty().bindBidirectional(newValue.teacherReviewProperty());
                assignmentDate.valueProperty().bindBidirectional(newValue.dateOfLectureProperty());
            }
        });

        assignmentField.textProperty().addListener((observableValue, oldValue, newValue) -> activityListView.refresh());
    }

    public void onAddActivity(ActionEvent actionEvent) {
        activities.add(new Activity());
        activityListView.getSelectionModel().selectLast();
    }

    public void onDeleteActivity(ActionEvent actionEvent) {
        activities.removeAll(activityListView.getSelectionModel().getSelectedItems());
    }

    public void onSaveChanges(ActionEvent actionEvent) {
        child.setActivities(activityListView.getItems());
        onClose();
    }

    public void onCancel(ActionEvent actionEvent) {
        onClose();
    }

    public void onClose() {
        Stage stage = (Stage) activityListView.getScene().getWindow();
        stage.close();
    }
}
