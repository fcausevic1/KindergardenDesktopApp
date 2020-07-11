package ba.unsa.etf.rs;

import java.time.LocalDate;
import java.util.ArrayList;

public class Child extends User {
    private User firstParent, secondParent;
    private ArrayList<Activity> activities;

    public Child() {
        super();
        this.activities = new ArrayList<>();
    }

    public Child(int id, String firstName, String lastName, String address, String jmbg, String placeOfBirth, LocalDate dateOfBirth, Gender gender, User firstParent, User secondParent, ArrayList<Activity> activities) {
        super(id, firstName, lastName, address, jmbg, placeOfBirth, dateOfBirth, gender);
        this.firstParent = firstParent;
        this.secondParent = secondParent;
        this.activities = activities;
    }

    public User getFirstParent() {
        return firstParent;
    }

    public void setFirstParent(User firstParent) {
        this.firstParent = firstParent;
    }

    public User getSecondParent() {
        return secondParent;
    }

    public void setSecondParent(User secondParent) {
        this.secondParent = secondParent;
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
