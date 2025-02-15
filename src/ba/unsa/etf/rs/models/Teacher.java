package ba.unsa.etf.rs.models;

import ba.unsa.etf.rs.enums.Gender;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Teacher extends Person {
    private final SimpleStringProperty email, telephone;

    public Teacher() {
        super();
        this.email = new SimpleStringProperty("");
        this.telephone = new SimpleStringProperty("");
    }

    public Teacher(int id, String firstName, String lastName, String address, String jmbg, String placeOfBirth, LocalDate dateOfBirth, Gender gender, String email, String telephone) {
        super(id, firstName, lastName, address, jmbg, placeOfBirth, dateOfBirth, gender);
        this.email = new SimpleStringProperty(email);
        this.telephone = new SimpleStringProperty(telephone);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
