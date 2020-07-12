package ba.unsa.etf.rs;

import ba.unsa.etf.rs.enums.Gender;
import ba.unsa.etf.rs.models.*;
import javafx.collections.FXCollections;

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
    private PreparedStatement updateChildGradeStatement;
    private PreparedStatement deleteChildStatement;
    private PreparedStatement getChildrenStatement;
    private PreparedStatement getChildStatement;
    private PreparedStatement getNextChildIDStatement;
    private PreparedStatement addTeacherStatement;
    private PreparedStatement updateTeacherStatement;
    private PreparedStatement deleteTeacherStatement;
    private PreparedStatement getTeachersStatement;
    private PreparedStatement getTeacherStatement;
    private PreparedStatement getNextTeacherIDStatement;
    private PreparedStatement addParentStatement;
    private PreparedStatement updateParentStatement;
    private PreparedStatement deleteParentStatement;
    private PreparedStatement getParentsStatement;
    private PreparedStatement getParentStatement;
    private PreparedStatement getNextParentIDStatement;
    private PreparedStatement addGradeStatement;
    private PreparedStatement updateGradeStatement;
    private PreparedStatement deleteGradeStatement;
    private PreparedStatement getGradesStatement;
    private PreparedStatement getGradeStatement;
    private PreparedStatement getNextGradeIDStatement;
    private PreparedStatement getChildrenByGradeStatement;
    private PreparedStatement addPersonStatement;
    private PreparedStatement updatePersonStatement;
    private PreparedStatement deletePersonStatement;
    private PreparedStatement getNextPersonIDStatement;
    private PreparedStatement addActivityStatement;
    private PreparedStatement updateActivityStatement;
    private PreparedStatement deleteActivityStatement;
    private PreparedStatement getNextActivityIDStatement;

    private DaycareDAOBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:daycare.db");
            try {
                addChildStatement = connection.prepareStatement("INSERT INTO children VALUES (?, ?, ?, ?, null)");
            } catch (SQLException e) {
                initializeDatabase();
                try {
                    addChildStatement = connection.prepareStatement("INSERT INTO children VALUES (?, ?, ?, ?, null)");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                updateChildStatement = connection.prepareStatement("UPDATE children SET first_parent_id=?, second_parent_id=? WHERE person_id=?");
                updateChildGradeStatement = connection.prepareStatement("UPDATE children SET grade_id=? WHERE person_id=?");
                deleteChildStatement = connection.prepareStatement("DELETE FROM children WHERE person_id=?");
                getChildrenStatement = connection.prepareStatement("SELECT persons.id, first_name, last_name, address, jmbg, place_of_birth, date_of_birth, gender, first_parent_id, second_parent_id FROM persons, children WHERE children.person_id = persons.id");
                getChildStatement = connection.prepareStatement("SELECT persons.id, first_name, last_name, address, jmbg, place_of_birth, date_of_birth, gender, first_parent_id, second_parent_id FROM persons, children WHERE children.person_id = persons.id AND persons.id=?");
                getNextChildIDStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM children");

                addTeacherStatement = connection.prepareStatement("INSERT INTO teachers VALUES (?, ?, ?, ?)");
                updateTeacherStatement = connection.prepareStatement("UPDATE teachers SET telephone=?, email=? WHERE person_id=?");
                deleteTeacherStatement = connection.prepareStatement("DELETE FROM teachers WHERE person_id=?");
                getTeachersStatement = connection.prepareStatement("SELECT persons.id, first_name, last_name, address, jmbg, place_of_birth, date_of_birth, gender, telephone, email FROM persons, teachers WHERE teachers.person_id = persons.id");
                getTeacherStatement = connection.prepareStatement("SELECT persons.id, first_name, last_name, address, jmbg, place_of_birth, date_of_birth, gender, telephone, email FROM persons, teachers WHERE teachers.person_id = persons.id AND persons.id = ?");
                getNextTeacherIDStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM teachers");

                addParentStatement = connection.prepareStatement("INSERT INTO parents VALUES (?, ?, ?, ?)");
                updateParentStatement = connection.prepareStatement("UPDATE parents SET telephone=?, email=? WHERE person_id=?");
                deleteParentStatement = connection.prepareStatement("DELETE FROM parents WHERE person_id=?");
                getParentsStatement = connection.prepareStatement("SELECT persons.id, first_name, last_name, address, jmbg, place_of_birth, date_of_birth, gender, telephone, email FROM persons, parents WHERE parents.person_id = persons.id");
                getParentStatement = connection.prepareStatement("SELECT persons.id, first_name, last_name, address, jmbg, place_of_birth, date_of_birth, gender, telephone, email FROM persons, parents WHERE parents.person_id = persons.id AND persons.id=?");
                getNextParentIDStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM parents");

                addGradeStatement = connection.prepareStatement("INSERT INTO grades VALUES (?, ?, ?)");
                updateGradeStatement = connection.prepareStatement("UPDATE grades SET name=?, teacher_id=? WHERE id=?");
                deleteGradeStatement = connection.prepareStatement("DELETE FROM grades WHERE id=?");
                getGradesStatement = connection.prepareStatement("SELECT * FROM grades");
                getGradeStatement = connection.prepareStatement("SELECT * FROM grades WHERE id=?");
                getNextGradeIDStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM grades");
                getChildrenByGradeStatement = connection.prepareStatement("SELECT persons.id, first_name, last_name, address, jmbg, place_of_birth, date_of_birth, gender, first_parent_id, second_parent_id FROM persons,children WHERE children.person_id = persons.id AND children.grade_id=?");

                addPersonStatement = connection.prepareStatement("INSERT INTO persons VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                updatePersonStatement = connection.prepareStatement("UPDATE persons SET first_name=?, last_name=?, address=?, jmbg=?, place_of_birth=?, date_of_birth=?, gender=? WHERE id=?");
                deletePersonStatement = connection.prepareStatement("DELETE FROM persons WHERE id=?");
                getNextPersonIDStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM persons");

                addActivityStatement = connection.prepareStatement("INSERT INTO activities VALUES (?, ?, ?, ?, ?, ?, ?)");
                updateActivityStatement = connection.prepareStatement("UPDATE activities SET assignment=?, description=?, review=?, lecture_date=?, teacher_id=? WHERE id=?");
                deleteActivityStatement = connection.prepareStatement("DELETE FROM activities WHERE id=?");
                getNextActivityIDStatement = connection.prepareStatement("SELECT MAX(id)+1 FROM activities");
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

    private Teacher getTeacherFromResultSet(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt(1));
        teacher.setFirstName(rs.getString(2));
        teacher.setLastName(rs.getString(3));
        teacher.setAddress(rs.getString(4));
        teacher.setJmbg(rs.getString(5));
        teacher.setPlaceOfBirth(rs.getString(6));
        teacher.setDateOfBirth(rs.getDate(7).toLocalDate());
        teacher.setGender(rs.getInt(8) == 0 ? Gender.MALE : Gender.FEMALE);
        teacher.setTelephone(rs.getString(9));
        teacher.setEmail(rs.getString(10));
        return teacher;
    }

    private Parent getParentFromResultSet(ResultSet rs) throws SQLException {
        Parent parent = new Parent();
        parent.setId(rs.getInt(1));
        parent.setFirstName(rs.getString(2));
        parent.setLastName(rs.getString(3));
        parent.setAddress(rs.getString(4));
        parent.setJmbg(rs.getString(5));
        parent.setPlaceOfBirth(rs.getString(6));
        parent.setDateOfBirth(rs.getDate(7).toLocalDate());
        parent.setGender(rs.getInt(8) == 0 ? Gender.MALE : Gender.FEMALE);
        parent.setTelephone(rs.getString(9));
        parent.setEmail(rs.getString(10));
        return parent;
    }

    private Grade getGradeFromResultSet(ResultSet rs, Teacher teacher, ArrayList<Child> children) throws SQLException {
        return new Grade(rs.getInt(1), rs.getString(2), teacher, children);
    }

    private Activity getActivityFromResultSet(ResultSet rs, Teacher teacher) throws SQLException {
        return new Activity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), teacher);
    }

    private Child getChildFromResultSet(ResultSet rs, Parent firstParent, Parent secondParent, ArrayList<Activity> activities) throws SQLException {
        Child child = new Child();
        child.setId(rs.getInt(1));
        child.setFirstName(rs.getString(2));
        child.setLastName(rs.getString(3));
        child.setAddress(rs.getString(4));
        child.setJmbg(rs.getString(5));
        child.setPlaceOfBirth(rs.getString(6));
        child.setDateOfBirth(rs.getDate(7).toLocalDate());
        child.setGender(rs.getInt(8) == 0 ? Gender.MALE : Gender.FEMALE);
        child.setFirstParent(firstParent);
        child.setSecondParent(secondParent);
        child.setActivities(FXCollections.observableArrayList()); // TODO: ACT
        return child;
    }

    public int getNextPersonID() {
        ResultSet rs;
        int id = -1;
        try {
            rs = getNextPersonIDStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void addChild(Child child) {
        try {
            ResultSet rs = getNextChildIDStatement.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            int personID = addPerson(child);
            addChildStatement.setInt(1, id);
            addChildStatement.setInt(2, personID);
            addChildStatement.setInt(3, child.getFirstParent().getId());
            addChildStatement.setInt(4, child.getSecondParent().getId());
            addChildStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateChild(Child child) {
        try {
            updatePerson(child);
            updateTeacherStatement.setInt(1, child.getFirstParent().getId());
            updateTeacherStatement.setInt(2, child.getSecondParent().getId());
            updateTeacherStatement.setInt(3, child.getId());
            updateTeacherStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteChild(Child child) {
        try {
            deleteChildStatement.setInt(1, child.getId());
            deleteChildStatement.executeUpdate();
            deletePerson(child);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Child getChild(int id) {
        try {
            getChildStatement.setInt(1, id);
            ResultSet rs = getChildStatement.executeQuery();
            if (!rs.next()) return null;
            Parent firstParent = getParent(rs.getInt(9));
            Parent secondParent = getParent(rs.getInt(10));
            return getChildFromResultSet(rs, firstParent, secondParent, null); // TODO: Child Activity
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Child> getChildren() {
        ArrayList<Child> children = new ArrayList<>();
        try {
            ResultSet rs = getChildrenStatement.executeQuery();
            while (rs.next()) {
                Parent firstParent = getParent(rs.getInt(9));
                Parent secondParent = getParent(rs.getInt(10));
                Child child = getChildFromResultSet(rs, firstParent, secondParent, null); // TODO: Child Activity
                children.add(child);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return children;
    }

    @Override
    public int getNextChildID() {
        ResultSet rs;
        int id = -1;
        try {
            rs = getNextChildIDStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        try {
            ResultSet rs = getNextTeacherIDStatement.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            int personID = addPerson(teacher);
            addTeacherStatement.setInt(1, id);
            addTeacherStatement.setInt(2, personID);
            addTeacherStatement.setString(3, teacher.getTelephone());
            addTeacherStatement.setString(4, teacher.getEmail());
            addTeacherStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        try {
            updatePerson(teacher);
            updateTeacherStatement.setString(1, teacher.getTelephone());
            updateTeacherStatement.setString(2, teacher.getEmail());
            updateTeacherStatement.setInt(3, teacher.getId());
            updateTeacherStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        try {
            deleteTeacherStatement.setInt(1, teacher.getId());
            deleteTeacherStatement.executeUpdate();
            deletePerson(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacher(int id) {
        try {
            getTeacherStatement.setInt(1, id);
            ResultSet rs = getTeacherStatement.executeQuery();
            if (!rs.next()) return null;
            return getTeacherFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            ResultSet rs = getTeachersStatement.executeQuery();
            while (rs.next()) {
                Teacher teacher = getTeacherFromResultSet(rs);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public int getNextTeacherID() {
        ResultSet rs;
        int id = -1;
        try {
            rs = getNextTeacherIDStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void addParent(Parent parent) {
        try {
            ResultSet rs = getNextParentIDStatement.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            int personID = addPerson(parent);
            addParentStatement.setInt(1, id);
            addParentStatement.setInt(2, personID);
            addParentStatement.setString(3, parent.getTelephone());
            addParentStatement.setString(4, parent.getEmail());
            addParentStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateParent(Parent parent) {
        try {
            updatePerson(parent);
            updateParentStatement.setString(1, parent.getTelephone());
            updateParentStatement.setString(2, parent.getEmail());
            updateParentStatement.setInt(3, parent.getId());
            updateParentStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteParent(Parent parent) {
        try {
            deleteParentStatement.setInt(1, parent.getId());
            deleteParentStatement.executeUpdate();
            deletePerson(parent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNextParentID() {
        ResultSet rs;
        int id = -1;
        try {
            rs = getNextParentIDStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Parent getParent(int id) {
        try {
            getParentStatement.setInt(1, id);
            ResultSet rs = getParentStatement.executeQuery();
            if (!rs.next()) return null;
            return getParentFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addGrade(Grade grade) {
        try {
            ResultSet rs = getNextGradeIDStatement.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addGradeStatement.setInt(1, id);
            addGradeStatement.setString(2, grade.getName());
            addGradeStatement.setInt(3, grade.getTeacher().getId());
            addGradeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGrade(Grade grade) {
        try {
            updateGradeStatement.setString(1, grade.getName());
            updateGradeStatement.setInt(2, grade.getTeacher().getId());
            updateGradeStatement.setInt(3, grade.getId());
            updateGradeStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteGrade(Grade grade) {
        try {
            deleteGradeStatement.setInt(1, grade.getId());
            deleteGradeStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Grade getGrade(int id) {
        try {
            getGradeStatement.setInt(1, id);
            ResultSet rs = getGradeStatement.executeQuery();
            if (!rs.next()) return null;
            Teacher teacher = getTeacher(rs.getInt(3));
            return getGradeFromResultSet(rs, teacher, getChildren(id));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Grade> getGrades() {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            ResultSet rs = getGradesStatement.executeQuery();
            while (rs.next()) {
                Grade grade = getGrade(rs.getInt(1));
                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    @Override
    public int getNextGradeID() {
        ResultSet rs;
        int id = -1;
        try {
            rs = getNextGradeIDStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int addPerson(Person person) {
        int id = 1;
        try {
            ResultSet rs = getNextPersonIDStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            addPersonStatement.setInt(1, id);
            addPersonStatement.setString(2, person.getFirstName());
            addPersonStatement.setString(3, person.getLastName());
            addPersonStatement.setString(4, person.getAddress());
            addPersonStatement.setString(5, person.getJmbg());
            addPersonStatement.setString(6, person.getPlaceOfBirth());
            addPersonStatement.setDate(7, Date.valueOf(person.getDateOfBirth()));
            addPersonStatement.setInt(8, person.getGender() == Gender.MALE ? 0 : 1);
            addPersonStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void updatePerson(Person person) throws SQLException {
        updatePersonStatement.setString(1, person.getFirstName());
        updatePersonStatement.setString(2, person.getLastName());
        updatePersonStatement.setString(3, person.getAddress());
        updatePersonStatement.setString(4, person.getJmbg());
        updatePersonStatement.setString(5, person.getPlaceOfBirth());
        updatePersonStatement.setDate(6, Date.valueOf(person.getDateOfBirth()));
        updatePersonStatement.setInt(7, person.getGender() == Gender.MALE ? 0 : 1);
        updatePersonStatement.setInt(8, person.getId());
        updatePersonStatement.executeUpdate();
    }

    public void deletePerson(Person person) {
        try {
            deletePersonStatement.setInt(1, person.getId());
            deletePersonStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Child> getChildren(int id) {
        ArrayList<Child> children = new ArrayList<>();
        try {
            getChildrenByGradeStatement.setInt(1, id);
            ResultSet rs = getChildrenByGradeStatement.executeQuery();
            while (rs.next()) {
                Parent firstParent = getParent(rs.getInt(9));
                Parent secondParent = getParent(rs.getInt(10));
                Child child = getChildFromResultSet(rs, firstParent, secondParent, null); // TODO: Child Activity
                children.add(child);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return children;
    }


}
