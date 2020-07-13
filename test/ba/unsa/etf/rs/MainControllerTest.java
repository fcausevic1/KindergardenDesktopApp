package ba.unsa.etf.rs;

import ba.unsa.etf.rs.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class MainControllerTest {
    Stage theStage;
    Controller controller;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = loader.load();
        stage.setTitle("DayCare Manager");
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
        theStage = stage;
    }

    @Test
    public void testGradeTableView(FxRobot robot) {
        TableView gradesTableView = robot.lookup("#gradesTableView").queryAs(TableView.class);
        assertEquals(3, gradesTableView.getItems().size());
    }

    @Test
    public void testChildrenTableView(FxRobot robot) {
        robot.clickOn("#childrenTab");
        ListView childrenList = robot.lookup("#childrenListView").queryAs(ListView.class);
        assertEquals(6, childrenList.getItems().size());
    }

    @Test
    public void testAddAChild(FxRobot robot) {
        robot.clickOn("#childrenTab");
        robot.clickOn("#addChildBtn");
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

        ListView childrenList = robot.lookup("#childrenListView").queryAs(ListView.class);
        assertEquals("Test Testic", childrenList.getItems().get(childrenList.getItems().size() - 1).toString());

    }

}