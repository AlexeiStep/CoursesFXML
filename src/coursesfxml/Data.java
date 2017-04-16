/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml;

import coursesfxml.model.Group;
import coursesfxml.model.Load;
import coursesfxml.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MrHuySGory
 */
public class Data {

    /**
     * Данные, в виде наблюдаемого списка преподавателей.
     */
    private static final ObservableList<Teacher> teachersData = FXCollections.observableArrayList();
    private static int teacherCode = 0;

    private static final ObservableList<Group> groupsData = FXCollections.observableArrayList();
    private static int groupCode = 0;

    private static final ObservableList<Load> loadsData = FXCollections.observableArrayList();
    private static Integer loadCode = 0;

    /**
     * Конструктор
     */
    public Data() {
        // В качестве образца добавляем некоторые данные
        try {
            groupsData.add(Group.newBuilder()
                    .setCode(this.groupCode++)
                    .setNumber(123)
                    .setSpeciality("PI")
                    .setOffice("MexMaT")
                    .setNumberOfStudents(23)
                    .build());
            groupsData.add(Group.newBuilder()
                    .setCode(this.groupCode++)
                    .setNumber(444)
                    .setSpeciality("PfssgsI")
                    .setOffice("sfgsfg")
                    .setNumberOfStudents(23)
                    .build());

            teachersData.add(Teacher.newBuilder()
                    .setCode(this.teacherCode++)
                    .setLastName("qwe")
                    .setFirstName("rty")
                    .setPatronymic("sdf")
                    .setPhone("89170251015")
                    .setExperience(3)
                    .setNumberOfLoads(1)
                    .build());
            teachersData.add(Teacher.newBuilder()
                    .setCode(this.teacherCode++)
                    .setLastName("ttttt")
                    .setFirstName("trrrrrrrr")
                    .setPatronymic("eeeeeeee")
                    .setPhone("89170299999")
                    .setExperience(3)
                    .setNumberOfLoads(0)
                    .build());
            loadsData.add(Load.newBuilder()
                    .setCode(this.loadCode++)
                    .setTeacher(teachersData.get(0))
                    .setGroup(groupsData.get(0))
                    .setNumberOfHourses(4)
                    .setSubject("English")
                    .setTypeOfEmployment("Lecture")
                    .setPayment(4)
                    .build());
        } catch (NullPointerException e) {
        }

    }

    /**
     * Возвращает данные в виде наблюдаемого списка преподавателей.
     *
     * @return
     */
    public static ObservableList<Teacher> getTeachersData() {
        return teachersData;
    }

    public static ObservableList<Group> getGroupsData() {
        return groupsData;
    }

    public static ObservableList<Load> getLoadsData() {
        return loadsData;
    }

    public static int getTeacherCode() {
        return teacherCode++;
    }

    public static int getGroupCode() {
        return groupCode++;
    }

    public static Integer getLoadCode() {
        return loadCode++;
    }

    public static void removeLoad(int Code) {

        String methodName = "";
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 3) {
            StackTraceElement element = stackTraceElements[2];
            methodName = element.getMethodName();
        }

        if (methodName.equalsIgnoreCase("handleDeleteTeacher")) {
            for (int i = 0; i < getLoadsData().size(); i++) {
                if (getLoadsData().get(i).getTeacherCode() == Code) {
                    getLoadsData().remove(i);
                }
            }
        } else if (methodName.equalsIgnoreCase("handleDeleteGroup")) {
            for (int i = 0; i < getLoadsData().size(); i++) {
                if (getLoadsData().get(i).getGroupCode() == Code) {
                    getLoadsData().remove(i);
                }
            }
        }

    }

}
