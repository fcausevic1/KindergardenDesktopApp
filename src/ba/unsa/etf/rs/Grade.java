package ba.unsa.etf.rs;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Grade {
    private final SimpleStringProperty name;
    private User teacher;
    private ArrayList<Child> children;

    public Grade() {
        this.name = new SimpleStringProperty("");
        this.children = new ArrayList<>();
    }

    public Grade(String name, User teacher, ArrayList<Child> children) {
        this.name = new SimpleStringProperty(name);
        this.teacher = teacher;
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
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
