package ba.unsa.etf.rs.controllers;

import ba.unsa.etf.rs.DaycareDAO;
import ba.unsa.etf.rs.models.Activity;
import ba.unsa.etf.rs.models.Child;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityController implements Initializable {
    private final ObservableList<Activity> activities = FXCollections.observableArrayList();
    public ListView<Activity> activityListView;
    public TextField assignmentField;
    public TextArea descriptionField;
    public TextArea reviewField;
    public DatePicker assignmentDate;
    private final Child child;
    private final DaycareDAO daycareDAO;

    ActivityController(Child child, DaycareDAO daycareDAO) {
        this.daycareDAO = daycareDAO;
        this.child = child;
        activities.setAll(child.getActivities());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activityListView.setItems(activities);

        activityListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                assignmentField.textProperty().unbindBidirectional(oldValue.assignmentProperty());
                descriptionField.textProperty().unbindBidirectional(oldValue.descriptionProperty());
                reviewField.textProperty().unbindBidirectional(oldValue.teacherReviewProperty());
                assignmentDate.valueProperty().unbindBidirectional(oldValue.dateOfLectureProperty());
            }
            if (newValue == null) {
                assignmentField.setText("");
                descriptionField.setText("");
                reviewField.setText("");
            } else {
                assignmentField.textProperty().bindBidirectional(newValue.assignmentProperty());
                descriptionField.textProperty().bindBidirectional(newValue.descriptionProperty());
                reviewField.textProperty().bindBidirectional(newValue.teacherReviewProperty());
                assignmentDate.valueProperty().bindBidirectional(newValue.dateOfLectureProperty());
            }
        });

        assignmentField.textProperty().addListener((observableValue, oldValue, newValue) -> activityListView.refresh());
    }

    public void onAddActivity(ActionEvent actionEvent) {
        activities.add(new Activity());
        activityListView.getSelectionModel().selectLast();
        daycareDAO.addActivity(child, activityListView.getSelectionModel().getSelectedItem());
    }

    public void onDeleteActivity(ActionEvent actionEvent) {
        daycareDAO.deleteActivity(activityListView.getSelectionModel().getSelectedItem());
        activities.removeAll(activityListView.getSelectionModel().getSelectedItem());
    }

    public void onSaveChanges(ActionEvent actionEvent) {
        for (Activity activity : activityListView.getItems()) {
            System.out.println(activity.getId() + activity.getAssignment());
            daycareDAO.updateActivity(activity);
        }
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
