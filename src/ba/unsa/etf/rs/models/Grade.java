package ba.unsa.etf.rs.models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Grade {
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<Teacher> teacher;
    private ArrayList<Child> children;

    public Grade() {
        this.name = new SimpleStringProperty("");
        this.teacher = new SimpleObjectProperty<>();
        this.children = new ArrayList<>();
    }

    public Grade(String name, Teacher teacher, ArrayList<Child> children) {
        this.name = new SimpleStringProperty(name);
        this.teacher = new SimpleObjectProperty<>(teacher);
        this.children = children;
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

    public Person getTeacher() {
        return teacher.get();
    }

    public void setTeacher(Teacher teacher) {
        this.teacher.set(teacher);
    }

    public SimpleObjectProperty<Teacher> teacherProperty() {
        return teacher;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return getName();
    }
}
