import ba.unsa.etf.rs.models.Child;
import ba.unsa.etf.rs.models.Grade;
import ba.unsa.etf.rs.models.Teacher;

import java.util.ArrayList;

public interface DaycareDAO {
    void addChild(Child child);

    void updateChild(Child child);

    void deleteChild(Child child);

    Child getChild(int id);

    ArrayList<Child> getChildren();

    int getNextChildID();

    void addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    Teacher getTeacher(int id);

    ArrayList<Teacher> getTeachers();

    int getNextTeacherID();

    void addGrade(Grade grade);

    void updateGrade(Grade grade);

    void deleteGrade(Grade grade);

    Grade getGrade(int id);

    ArrayList<Grade> getGrades();

    int getNextGradeID();

    ArrayList<Child> getChildren(Grade grade);
}
