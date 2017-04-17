/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml;

import coursesfxml.model.Group;
import coursesfxml.model.Load;
import coursesfxml.model.Teacher;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static int loadCode = 0;

    /**
     * Конструктор
     */
    public Data(){
        
        try {
            DataBase.Conn();
            DataBase.CreateDB();
            DataBase.ReadDB();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void setTeacherCode(int teacherCode) {
        Data.teacherCode = teacherCode;
    }

    public static void setGroupCode(int groupCode) {
        Data.groupCode = groupCode;
    }

    public static void setLoadCode(int loadCode) {
        Data.loadCode = loadCode;
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
