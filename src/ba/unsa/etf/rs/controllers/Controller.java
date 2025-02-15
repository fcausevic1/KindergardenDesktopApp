package ba.unsa.etf.rs.controllers;

import ba.unsa.etf.rs.DaycareDAO;
import ba.unsa.etf.rs.DaycareDAOBase;
import ba.unsa.etf.rs.enums.Gender;
import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Grade;
import ba.unsa.etf.rs.models.Teacher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Observables
    private final ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.values());
    private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    private final ObservableList<Child> children = FXCollections.observableArrayList();
    private final ObservableList<Grade> grades = FXCollections.observableArrayList();
    public TableView<Grade> gradesTableView;
    public TextField teacherFirstNameField;
    public TextField teacherLastNameField;
    public TextField teacherTelephoneField;
    public TextField teacherBirthplaceField;
    public TextField teacherAddressField;
    public TextField teacherJMBGField;
    public DatePicker teacherDateField;
    public TableColumn<Grade, String> gradeColumn;
    public TableColumn<Grade, Teacher> teacherColumn;
    public TextField childFirstNameField;
    public TextField childLastNameField;
    public TextField childParentNameField;
    public TextField childParentTelephoneField;
    public DatePicker childDateField;
    public TextField childAddressField;
    public TextField childBirthplaceField;
    public TableColumn<Grade, String> childrenColumn;
    public TextField childJMBGField;
    public ListView<Teacher> teachersListView;
    public ChoiceBox<Gender> teacherGenderChoiceBox;
    public ListView<Child> childrenListView;
    public ChoiceBox<Gender> childGenderChoiceBox;
    public TextField teacherEmailField;
    private final DaycareDAO daycareDAO = DaycareDAOBase.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grades.setAll(daycareDAO.getGrades());
        teachers.setAll(daycareDAO.getTeachers());
        children.setAll(daycareDAO.getChildren());

        //ChoiceBox initialization
        teacherGenderChoiceBox.setItems(genders);
        childGenderChoiceBox.setItems(genders);

        //ListView initalization
        teachersListView.setItems(teachers);
        childrenListView.setItems(children);

        //Table initialization
        gradesTableView.setItems(grades);
        gradeColumn.setCellValueFactory(grade -> grade.getValue().nameProperty());
        teacherColumn.setCellValueFactory(grade -> grade.getValue().teacherProperty());
        childrenColumn.setCellValueFactory(grade -> new SimpleStringProperty(String.valueOf(grade.getValue().getChildren().size())));

        //Parent fields
        childParentNameField.setEditable(false);
        childParentTelephoneField.setEditable(false);

        //Bindings
        childrenListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                childFirstNameField.textProperty().unbindBidirectional(oldValue.firstNameProperty());
                childLastNameField.textProperty().unbindBidirectional(oldValue.lastNameProperty());
                childDateField.valueProperty().unbindBidirectional(oldValue.dateOfBirthProperty());
                childBirthplaceField.textProperty().unbindBidirectional(oldValue.placeOfBirthProperty());
                childJMBGField.textProperty().unbindBidirectional(oldValue.jmbgProperty());
                childAddressField.textProperty().unbindBidirectional(oldValue.addressProperty());
                childGenderChoiceBox.valueProperty().unbindBidirectional(oldValue.genderProperty());
                childParentTelephoneField.setText("");
                childParentNameField.setText("");
            }
            if (newValue == null) {
                childFirstNameField.setText("");
                childLastNameField.setText("");
                childDateField.setValue(LocalDate.now());
                childParentTelephoneField.setText("");
                childBirthplaceField.setText("");
                childJMBGField.setText("");
                childAddressField.setText("");
                childParentNameField.setText("");
                childGenderChoiceBox.getSelectionModel().selectFirst();
            } else {
                childFirstNameField.textProperty().bindBidirectional(newValue.firstNameProperty());
                childLastNameField.textProperty().bindBidirectional(newValue.lastNameProperty());
                childDateField.valueProperty().bindBidirectional(newValue.dateOfBirthProperty());
                childBirthplaceField.textProperty().bindBidirectional(newValue.placeOfBirthProperty());
                childJMBGField.textProperty().bindBidirectional(newValue.jmbgProperty());
                childAddressField.textProperty().bindBidirectional(newValue.addressProperty());
                childGenderChoiceBox.valueProperty().bindBidirectional(newValue.genderProperty());
                if (newValue.getFirstParent() != null) {
                    childParentTelephoneField.setText(newValue.getFirstParent().getTelephone());
                    childParentNameField.setText(newValue.getFirstParent().toString());
                }
            }
        });

        childFirstNameField.textProperty().addListener((observableValue, oldValue, newValue) -> childrenListView.refresh());
        childLastNameField.textProperty().addListener((observableValue, oldValue, newValue) -> childrenListView.refresh());

        teachersListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                teacherFirstNameField.textProperty().unbindBidirectional(oldValue.firstNameProperty());
                teacherLastNameField.textProperty().unbindBidirectional(oldValue.lastNameProperty());
                teacherDateField.valueProperty().unbindBidirectional(oldValue.dateOfBirthProperty());
                teacherTelephoneField.textProperty().unbindBidirectional(oldValue.telephoneProperty());
                teacherBirthplaceField.textProperty().unbindBidirectional(oldValue.placeOfBirthProperty());
                teacherJMBGField.textProperty().unbindBidirectional(oldValue.jmbgProperty());
                teacherAddressField.textProperty().unbindBidirectional(oldValue.addressProperty());
                teacherEmailField.textProperty().unbindBidirectional(oldValue.emailProperty());
                teacherGenderChoiceBox.valueProperty().unbindBidirectional(oldValue.genderProperty());
            }
            if (newValue == null) {
                teacherFirstNameField.setText("");
                teacherLastNameField.setText("");
                teacherDateField.setValue(LocalDate.now());
                teacherTelephoneField.setText("");
                teacherBirthplaceField.setText("");
                teacherJMBGField.setText("");
                teacherAddressField.setText("");
                teacherEmailField.setText("");
                teacherGenderChoiceBox.getSelectionModel().selectFirst();
            } else {
                teacherFirstNameField.textProperty().bindBidirectional(newValue.firstNameProperty());
                teacherLastNameField.textProperty().bindBidirectional(newValue.lastNameProperty());
                teacherDateField.valueProperty().bindBidirectional(newValue.dateOfBirthProperty());
                teacherTelephoneField.textProperty().bindBidirectional(newValue.telephoneProperty());
                teacherBirthplaceField.textProperty().bindBidirectional(newValue.placeOfBirthProperty());
                teacherJMBGField.textProperty().bindBidirectional(newValue.jmbgProperty());
                teacherAddressField.textProperty().bindBidirectional(newValue.addressProperty());
                teacherEmailField.textProperty().bindBidirectional(newValue.emailProperty());
                teacherGenderChoiceBox.valueProperty().bindBidirectional(newValue.genderProperty());
            }
        });

        teacherFirstNameField.textProperty().addListener((observableValue, oldValue, newValue) -> teachersListView.refresh());
        teacherLastNameField.textProperty().addListener((observableValue, oldValue, newValue) -> teachersListView.refresh());
    }

    public void onAddGrade(ActionEvent actionEvent) {
        Stage stage = new Stage();
        javafx.scene.Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grade.fxml"));
            GradeController gradeController = new GradeController(null, getChildrenWithoutClass(), teachers);
            loader.setController(gradeController);
            root = loader.load();
            stage.setTitle("Grade");
            stage.setScene(new Scene(root));
            stage.setMinWidth(800);
            stage.setMaxWidth(800);
            stage.setMinHeight(500);
            stage.setMaxHeight(500);
            stage.initOwner(childrenListView.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
            stage.setOnHiding(event -> {
                Grade grade = gradeController.getGrade();
                if (grade != null) {
                    grades.add(grade);
                    daycareDAO.addGrade(grade);
                    daycareDAO.updateGradeChildren(grade, gradeController.getGradeChildren());
                    daycareDAO.removeGradeChildren(gradeController.getChildren());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditGrade(ActionEvent actionEvent) {
        if (gradesTableView.getSelectionModel().getSelectedItem() == null) return;
        Grade selectedGrade = gradesTableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        javafx.scene.Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grade.fxml"));
            GradeController gradeController = new GradeController(selectedGrade, getChildrenWithoutClass(), teachers);
            loader.setController(gradeController);
            root = loader.load();
            stage.setTitle("Grade");
            stage.setScene(new Scene(root));
            stage.setMinWidth(800);
            stage.setMaxWidth(800);
            stage.setMinHeight(500);
            stage.setMaxHeight(500);
            stage.initOwner(childrenListView.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
            stage.setOnHiding(event -> {
                Grade grade = gradeController.getGrade();
                if (grade != null) {
                    gradesTableView.getSelectionModel().getSelectedItem().setChildren(grade.getChildren());
                    gradesTableView.getSelectionModel().getSelectedItem().setName(grade.getName());
                    gradesTableView.getSelectionModel().getSelectedItem().setTeacher(grade.getTeacher());
                    gradesTableView.refresh();
                    daycareDAO.updateGrade(grade);
                    daycareDAO.updateGradeChildren(grade, gradeController.getGradeChildren());
                    daycareDAO.removeGradeChildren(gradeController.getChildren());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeleteGrade(ActionEvent actionEvent) {
        daycareDAO.deleteGrade(gradesTableView.getSelectionModel().getSelectedItem());
        daycareDAO.removeGradeChildren(gradesTableView.getSelectionModel().getSelectedItem().getChildren());
        grades.removeAll(gradesTableView.getSelectionModel().getSelectedItem());
    }

    public void onAddTeacher(ActionEvent actionEvent) {
        teachers.add(new Teacher());
        teachersListView.getSelectionModel().selectLast();
        daycareDAO.addTeacher(teachersListView.getSelectionModel().getSelectedItem());
    }

    public void onDeleteTeacher(ActionEvent actionEvent) {
        daycareDAO.deleteTeacher(teachersListView.getSelectionModel().getSelectedItem());
        teachers.removeAll(teachersListView.getSelectionModel().getSelectedItem());
    }

    public void onAddChild(ActionEvent actionEvent) {
        children.add(new Child());
        childrenListView.getSelectionModel().selectLast();
        daycareDAO.addChild(childrenListView.getSelectionModel().getSelectedItem());
    }

    public void onDeleteChild(ActionEvent actionEvent) {
        daycareDAO.deleteChild(childrenListView.getSelectionModel().getSelectedItem());
        children.removeAll(childrenListView.getSelectionModel().getSelectedItem());
    }

    public void onParentDetails(ActionEvent actionEvent) {
        if (childrenListView.getSelectionModel().getSelectedItem() == null) return;
        Child selectedChild = childrenListView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        javafx.scene.Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/parent.fxml"));
            ParentController parentController = new ParentController(selectedChild);
            loader.setController(parentController);
            root = loader.load();
            stage.setTitle("Parents");
            stage.setScene(new Scene(root));
            stage.setMinWidth(355);
            stage.setMaxWidth(355);
            stage.setMinHeight(500);
            stage.setMaxHeight(500);
            stage.initOwner(childrenListView.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
            stage.setOnHiding(event -> {
                if (selectedChild.getFirstParent() != null) {
                    childParentTelephoneField.setText(selectedChild.getFirstParent().getTelephone());
                    childParentNameField.setText(selectedChild.getFirstParent().toString());
                    daycareDAO.updateParent(selectedChild.getFirstParent());
                    daycareDAO.updateParent(selectedChild.getSecondParent());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActivity(ActionEvent actionEvent) {
        if (childrenListView.getSelectionModel().getSelectedItem() == null) return;
        Child selectedChild = childrenListView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        javafx.scene.Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activity.fxml"));
            ActivityController activityController = new ActivityController(selectedChild, daycareDAO);
            loader.setController(activityController);
            root = loader.load();
            stage.setTitle("Activities");
            stage.setScene(new Scene(root));
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.initOwner(childrenListView.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
            stage.setOnHiding(event -> {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObservableList<Child> getChildrenWithoutClass() {
        System.out.println(daycareDAO.getChildrenWithoutGrade().size());
        return FXCollections.observableArrayList(daycareDAO.getChildrenWithoutGrade());
    }
}
