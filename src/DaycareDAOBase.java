import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Grade;
import ba.unsa.etf.rs.models.Teacher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DaycareDAOBase implements DaycareDAO {
    private static DaycareDAOBase instance = null;
    private Connection connection;
    private PreparedStatement addChildStatement;
    private PreparedStatement updateChildStatement;
    private PreparedStatement deleteChildStatement;
    private PreparedStatement getChildrenStatement;
    private PreparedStatement getNextChildIDStatement;
    private PreparedStatement addTeacherStatement;
    private PreparedStatement updateTeacherStatement;
    private PreparedStatement deleteTeacherStatement;
    private PreparedStatement getTeachersStatement;
    private PreparedStatement getNextTeacherIDStatement;
    private PreparedStatement addGradeStatement;
    private PreparedStatement updateGradeStatement;
    private PreparedStatement deleteGradeStatement;
    private PreparedStatement getGradesStatement;
    private PreparedStatement getNextGradeIDStatement;

    private DaycareDAOBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:daycare.db");
            try {
                addChildStatement = connection.prepareStatement("INSERT INTO children VALUES (?,?)");
            } catch (SQLException e) {
                initializeDatabase();
                try {
                    addChildStatement = connection.prepareStatement("INSERT INTO children VALUES (?)");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DaycareDAOBase getInstance() {
        if (instance == null) instance = new DaycareDAOBase();
        return instance;
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void initializeDatabase() {
        Scanner input;
        try {
            input = new Scanner(new FileInputStream("initialize.sql"));
            StringBuilder sqlStatement = new StringBuilder();
            while (input.hasNext()) {
                sqlStatement.append(input.nextLine());
                if (sqlStatement.charAt(sqlStatement.length() - 1) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlStatement.toString());
                        sqlStatement = new StringBuilder();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addChild(Child child) {

    }

    @Override
    public void updateChild(Child child) {

    }

    @Override
    public void deleteChild(Child child) {

    }

    @Override
    public Child getChild(int id) {
        return null;
    }

    @Override
    public ArrayList<Child> getChildren() {
        return null;
    }

    @Override
    public int getNextChildID() {
        return 0;
    }

    @Override
    public void addTeacher(Teacher teacher) {

    }

    @Override
    public void updateTeacher(Teacher teacher) {

    }

    @Override
    public void deleteTeacher(Teacher teacher) {

    }

    @Override
    public Teacher getTeacher(int id) {
        return null;
    }

    @Override
    public ArrayList<Teacher> getTeachers() {
        return null;
    }

    @Override
    public int getNextTeacherID() {
        return 0;
    }

    @Override
    public void addGrade(Grade grade) {

    }

    @Override
    public void updateGrade(Grade grade) {

    }

    @Override
    public void deleteGrade(Grade grade) {

    }

    @Override
    public Grade getGrade(int id) {
        return null;
    }

    @Override
    public ArrayList<Grade> getGrades() {
        return null;
    }

    @Override
    public int getNextGradeID() {
        return 0;
    }

    @Override
    public ArrayList<Child> getChildren(Grade grade) {
        return null;
    }
}
