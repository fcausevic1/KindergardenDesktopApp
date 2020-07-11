package ba.unsa.etf.rs.models;

import ba.unsa.etf.rs.enums.Gender;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Person {
    int id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty jmbg;
    private final SimpleStringProperty placeOfBirth;
    private final SimpleObjectProperty<LocalDate> dateOfBirth;
    private final SimpleObjectProperty<Gender> gender;

    public Person() {
        this.id = -1;
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.jmbg = new SimpleStringProperty("");
        this.placeOfBirth = new SimpleStringProperty("");
        this.dateOfBirth = new SimpleObjectProperty<>(LocalDate.now());
        this.gender = new SimpleObjectProperty<>(Gender.MALE);
    }

    public Person(int id, String firstName, String lastName, String address, String jmbg, String placeOfBirth, LocalDate dateOfBirth, Gender gender) {
        this.id = id;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address = new SimpleStringProperty(address);
        this.jmbg = new SimpleStringProperty(jmbg);
        this.placeOfBirth = new SimpleStringProperty(placeOfBirth);
        this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
        this.gender = new SimpleObjectProperty<>(gender);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getJmbg() {
        return jmbg.get();
    }

    public SimpleStringProperty jmbgProperty() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg.set(jmbg);
    }

    public String getPlaceOfBirth() {
        return placeOfBirth.get();
    }

    public SimpleStringProperty placeOfBirthProperty() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth.set(placeOfBirth);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public Gender getGender() {
        return gender.get();
    }

    public SimpleObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
