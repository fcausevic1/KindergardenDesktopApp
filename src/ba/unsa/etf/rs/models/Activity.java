package ba.unsa.etf.rs.models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Activity {
    private final SimpleStringProperty description, assignment, teacherReview;
    private final SimpleObjectProperty<LocalDate> dateOfLecture;
    int id;

    public Activity() {
        this.id = 1;
        this.description = new SimpleStringProperty("");
        this.assignment = new SimpleStringProperty("");
        this.teacherReview = new SimpleStringProperty("");
        this.dateOfLecture = new SimpleObjectProperty<>(LocalDate.now());
    }

    public Activity(int id, String description, String assignment, String teacherReview, LocalDate dateOfLecture) {
        this.id = id;
        this.description = new SimpleStringProperty(description);
        this.assignment = new SimpleStringProperty(assignment);
        this.teacherReview = new SimpleStringProperty(teacherReview);
        this.dateOfLecture = new SimpleObjectProperty<>(dateOfLecture);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getAssignment() {
        return assignment.get();
    }

    public void setAssignment(String assignment) {
        this.assignment.set(assignment);
    }

    public SimpleStringProperty assignmentProperty() {
        return assignment;
    }

    public String getTeacherReview() {
        return teacherReview.get();
    }

    public void setTeacherReview(String teacherReview) {
        this.teacherReview.set(teacherReview);
    }

    public SimpleStringProperty teacherReviewProperty() {
        return teacherReview;
    }

    public LocalDate getDateOfLecture() {
        return dateOfLecture.get();
    }

    public void setDateOfLecture(LocalDate dateOfLecture) {
        this.dateOfLecture.set(dateOfLecture);
    }

    public SimpleObjectProperty<LocalDate> dateOfLectureProperty() {
        return dateOfLecture;
    }

    @Override
    public String toString() {
        return getAssignment();
    }
}
