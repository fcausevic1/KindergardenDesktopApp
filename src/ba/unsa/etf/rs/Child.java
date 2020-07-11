package ba.unsa.etf.rs;

import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class Child extends User {
    private final SimpleObjectProperty<User> firstParent, secondParent;
    private ArrayList<Activity> activities;

    public Child() {
        super();
        this.activities = new ArrayList<>();
        this.firstParent = new SimpleObjectProperty<>();
        this.secondParent = new SimpleObjectProperty<>();
    }

    public Child(int id, String firstName, String lastName, String address, String jmbg, String placeOfBirth, LocalDate dateOfBirth, Gender gender, User firstParent, User secondParent, ArrayList<Activity> activities) {
        super(id, firstName, lastName, address, jmbg, placeOfBirth, dateOfBirth, gender);
        this.firstParent = new SimpleObjectProperty<>(firstParent);
        this.secondParent = new SimpleObjectProperty<>(secondParent);
        this.activities = activities;
    }

    public User getFirstParent() {
        return firstParent.get();
    }

    public SimpleObjectProperty<User> firstParentProperty() {
        return firstParent;
    }

    public void setFirstParent(User firstParent) {
        this.firstParent.set(firstParent);
    }

    public User getSecondParent() {
        return secondParent.get();
    }

    public SimpleObjectProperty<User> secondParentProperty() {
        return secondParent;
    }

    public void setSecondParent(User secondParent) {
        this.secondParent.set(secondParent);
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
