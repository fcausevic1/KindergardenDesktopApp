package ba.unsa.etf.rs.models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Grade {
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<Teacher> teacher;
    private ObservableList<Child> children;

    public Grade() {
        this.name = new SimpleStringProperty("");
        this.teacher = new SimpleObjectProperty<>();
        this.children = FXCollections.observableArrayList();
    }

    public Grade(String name, Teacher teacher, ArrayList<Child> children) {
        this.name = new SimpleStringProperty(name);
        this.teacher = new SimpleObjectProperty<>(teacher);
        this.children = FXCollections.observableArrayList(children);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher.get();
    }

    public void setTeacher(Teacher teacher) {
        this.teacher.set(teacher);
    }

    public SimpleObjectProperty<Teacher> teacherProperty() {
        return teacher;
    }

    public ObservableList<Child> getChildren() {
        return children;
    }

    public void setChildren(ObservableList<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return getName();
    }
}
