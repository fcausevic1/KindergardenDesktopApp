package ba.unsa.etf.rs.controllers;

import ba.unsa.etf.rs.models.Activity;
import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ActivityController implements Initializable {
    private final ObservableList<Activity> activities = FXCollections.observableArrayList();
    public ListView<Activity> activityListView;
    public TextField assignmentField;
    public TextArea descriptionField;
    public TextArea reviewField;
    public ChoiceBox<Teacher> teacherChoiceBox;

    ActivityController(Child child, ArrayList<Teacher> teachers) {
        activities.setAll(child.getActivities());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activityListView.setItems(activities);
    }

    public void onAddActivity(ActionEvent actionEvent) {
        activities.add(new Activity());
        activityListView.getSelectionModel().selectLast();
    }

    public void onDeleteActivity(ActionEvent actionEvent) {
        activities.removeAll(activityListView.getSelectionModel().getSelectedItems());
    }

    public void onClose(ActionEvent actionEvent) {
    }
}
