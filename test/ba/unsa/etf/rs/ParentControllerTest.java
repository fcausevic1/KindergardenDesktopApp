package ba.unsa.etf.rs;

import ba.unsa.etf.rs.controllers.Controller;
import ba.unsa.etf.rs.models.Child;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class ParentControllerTest {
    Stage theStage;
    Controller controller;
    Child child;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        javafx.scene.Parent root = loader.load();
        stage.setTitle("DayCare Manager");
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setScene(new Scene(root));
        stage.show();
        theStage = stage;
    }

    @Test
    public void testAddParentDetails(FxRobot robot) {
        robot.clickOn("#childrenTab");
        robot.clickOn("#childFirstNameField");
        robot.write("Test");
        robot.clickOn("#childLastNameField");
        robot.write("Testic");
        robot.clickOn("#childAddressField");
        robot.write("Address1");
        robot.clickOn("#childBirthplaceField");
        robot.write("Place1");
        robot.clickOn("#childJMBGField");
        robot.write("1234567891234");
        robot.clickOn("#addChildBtn");
        robot.clickOn("#parentDetailsBtn");
        robot.clickOn("#firstNameField");
        robot.write("PTest");
        robot.clickOn("#lastNameField");
        robot.write("PTestic");
        robot.clickOn("#telephoneField");
        robot.write("38761234567");
        robot.clickOn("#emailField");
        robot.write("Parent.test@etf.unsa.ba");
        robot.clickOn("#saveChangesBtn");

    }

    @Test
    public void addTeacherTest(FxRobot robot) {
        robot.clickOn("#teachersTab");
        robot.clickOn("#addTeacherBtn");
        robot.clickOn("#teacherFirstNameField");
        robot.write("TTest");
        robot.clickOn("#teacherLastNameField");
        robot.write("TTestic");
        robot.clickOn("#teacherTelephoneField");
        robot.write("38761234567");
        robot.clickOn("#teacherEmailField");
        robot.write("Parent.test@etf.unsa.ba");

        ListView lv = robot.lookup("#teachersListView").queryAs(ListView.class);
        assertEquals(5, lv.getItems().size());
    }


}