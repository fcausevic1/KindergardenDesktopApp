package ba.unsa.etf.rs;

import ba.unsa.etf.rs.models.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface DaycareDAO {
    void addChild(Child child);

    void updateChild(Child child);

    void deleteChild(Child child);

    Child getChild(int id);

    ArrayList<Child> getChildren();

    void addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);

    Teacher getTeacher(int id);

    void addParent(Parent parent);

    void updateParent(Parent parent);

    void deleteParent(Parent parent);

    Parent getParent(int id);

    ArrayList<Teacher> getTeachers();

    void addGrade(Grade grade);

    void updateGrade(Grade grade);

    void deleteGrade(Grade grade);

    Grade getGrade(int id);

    ArrayList<Grade> getGrades();

    int getNextGradeID();

    ArrayList<Child> getChildren(int id);

    ArrayList<Child> getChildrenWithoutGrade();

    void updateGradeChildren(Grade grade, ObservableList<Child> children);

    void removeGradeChildren(ObservableList<Child> children);

    void addActivity(Child child, Activity activity);

    void updateActivity(Activity activity);

    void deleteActivity(Activity activity);

    Activity getActivity(int id);

    ArrayList<Activity> getActivities(int id);

    int getNextActivityID();
}
