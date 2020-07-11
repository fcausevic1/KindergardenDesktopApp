package ba.unsa.etf.rs.models;

import ba.unsa.etf.rs.enums.Gender;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Child extends Person {
    private final SimpleObjectProperty<Parent> firstParent, secondParent;
    private ObservableList<Activity> activities;

    public Child() {
        super();
        this.activities = FXCollections.observableArrayList();
        this.firstParent = new SimpleObjectProperty<>();
        this.secondParent = new SimpleObjectProperty<>();
    }

    public Child(int id, String firstName, String lastName, String telephone, String address, String jmbg, String placeOfBirth, LocalDate dateOfBirth, Gender gender, Parent firstParent, Parent secondParent, ArrayList<Activity> activities) {
        super(id, firstName, lastName, address, jmbg, placeOfBirth, dateOfBirth, gender);
        this.firstParent = new SimpleObjectProperty<>(firstParent);
        this.secondParent = new SimpleObjectProperty<>(secondParent);
        this.activities = FXCollections.observableArrayList(activities);
    }

    public Parent getFirstParent() {
        return firstParent.get();
    }

    public SimpleObjectProperty<Parent> firstParentProperty() {
        return firstParent;
    }

    public void setFirstParent(Parent firstParent) {
        this.firstParent.set(firstParent);
    }

    public Parent getSecondParent() {
        return secondParent.get();
    }

    public SimpleObjectProperty<Parent> secondParentProperty() {
        return secondParent;
    }

    public void setSecondParent(Parent secondParent) {
        this.secondParent.set(secondParent);
    }

    public ObservableList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ObservableList<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
