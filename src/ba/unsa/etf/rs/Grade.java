package ba.unsa.etf.rs;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Grade {
    private final SimpleStringProperty name;
    private final SimpleObjectProperty<Person> teacher;
    private ArrayList<Child> children;

    public Grade() {
        this.name = new SimpleStringProperty("");
        this.teacher = new SimpleObjectProperty<>();
        this.children = new ArrayList<>();
    }

    public Grade(String name, Person teacher, ArrayList<Child> children) {
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

    public void setTeacher(Person teacher) {
        this.teacher.set(teacher);
    }

    public SimpleObjectProperty<Person> teacherProperty() {
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
