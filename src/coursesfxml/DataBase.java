/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml;

import coursesfxml.model.Group;
import coursesfxml.model.Load;
import coursesfxml.model.Teacher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ListIterator;

/**
 *
 * @author MrHuySGory
 */
public class DataBase {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSetTeachers;
    public static ResultSet resSetGroups;
    public static ResultSet resSetLoads;

    // --------CONNECTING TO DATA BASE--------
    public static void Conn() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src/coursesfxml/data/data.s3db");

        System.out.println("Base connected!");
    }

    // --------CREATING TABLES--------
    public static void CreateDB() throws ClassNotFoundException, SQLException {
        statmt = (Statement) conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'Teachers' ('code' INT, 'lastName' text, 'firstName' text, 'patronymic' text, 'phone' text, 'experience' INT, 'numberOfLoads' INT);");
        statmt.execute("CREATE TABLE if not exists 'Groups' ('code' INT, 'number' INT, 'speciality' text, 'office' text, 'numberOfStudents' INT);");
        statmt.execute("CREATE TABLE if not exists 'Loads' ('code' INT, 'teacherCode' INT, 'groupCode' INT, 'numberOfHourses' INT, 'subject' text, 'typeOfEmployment' text, 'payment' INT);");
        System.out.println("Tables created or already exists!");
    }

    // --------FILLING OUT TABLES--------
    public static void WriteDB() throws SQLException {

        statmt.execute("DELETE FROM Teachers");
        statmt.execute("DELETE FROM Groups");
        statmt.execute("DELETE FROM Loads");

        ListIterator<Teacher> itrT = Data.getTeachersData().listIterator();
        int l = 0;
        while (itrT.hasNext()) {
            statmt.execute("INSERT INTO 'Teachers' ('code', 'lastName', 'firstName', 'patronymic', 'phone', 'experience', 'numberOfLoads')"
                    + "VALUES (" + Data.getTeachersData().get(l).getCode()
                    + ", '" + Data.getTeachersData().get(l).getLastName()
                    + "', '" + Data.getTeachersData().get(l).getFirstName()
                    + "', '" + Data.getTeachersData().get(l).getPatronymic()
                    + "', '" + Data.getTeachersData().get(l).getPhone()
                    + "', " + Data.getTeachersData().get(l).getExperience()
                    + ", " + Data.getTeachersData().get(l).getNumberOfLoads() + "); ");
            l++;
            itrT.next();
        }

        ListIterator<Group> itrG = Data.getGroupsData().listIterator();
        l = 0;
        while (itrG.hasNext()) {
            statmt.execute("INSERT INTO 'Groups' ('code', 'number', 'speciality', 'office', 'numberOfStudents')"
                    + "VALUES (" + Data.getGroupsData().get(l).getCode()
                    + "," + Data.getGroupsData().get(l).getNumber()
                    + ", '" + Data.getGroupsData().get(l).getSpeciality()
                    + "', '" + Data.getGroupsData().get(l).getOffice()
                    + "'," + Data.getGroupsData().get(l).getNumberOfStudents() + "); ");
            l++;
            itrG.next();
        }

        ListIterator<Load> itrL = Data.getLoadsData().listIterator();
        l = 0;
        while (itrL.hasNext()) {
            statmt.execute("INSERT INTO 'Loads' ('code', 'teacherCode', 'groupCode', 'numberOfHourses', 'subject', 'typeOfEmployment', 'payment')"
                    + "VALUES (" + Data.getLoadsData().get(l).getCode()
                    + "," + Data.getLoadsData().get(l).getTeacherCode()
                    + "," + Data.getLoadsData().get(l).getGroupCode()
                    + "," + Data.getLoadsData().get(l).getNumberOfHourses()
                    + ", '" + Data.getLoadsData().get(l).getSubject()
                    + "', '" + Data.getLoadsData().get(l).getTypeOfEmployment()
                    + "'," + Data.getLoadsData().get(l).getPayment() + "); ");
            l++;
            itrL.next();
        }

        System.out.println("Table is filled!");
    }

    // --------UPLOADING TABLES--------
    public static void ReadDB() throws ClassNotFoundException, SQLException {

        resSetTeachers = statmt.executeQuery("SELECT * FROM Teachers");

        while (resSetTeachers.next()) {

            Data.getTeachersData().add(Teacher.newBuilder()
                    .setCode(resSetTeachers.getInt("code"))
                    .setLastName(resSetTeachers.getString("lastName"))
                    .setFirstName(resSetTeachers.getString("firstName"))
                    .setPatronymic(resSetTeachers.getString("patronymic"))
                    .setPhone(resSetTeachers.getString("phone"))
                    .setExperience(resSetTeachers.getInt("experience"))
                    .setNumberOfLoads(resSetTeachers.getInt("numberOfLoads"))
                    .build());

            Data.setTeacherCode(resSetTeachers.getInt("code")+1);
        }

        resSetGroups = statmt.executeQuery("SELECT * FROM Groups");

        while (resSetGroups.next()) {

            Data.getGroupsData().add(Group.newBuilder()
                    .setCode(resSetGroups.getInt("code"))
                    .setNumber(resSetGroups.getInt("number"))
                    .setSpeciality(resSetGroups.getString("speciality"))
                    .setOffice(resSetGroups.getString("office"))
                    .setNumberOfStudents(resSetGroups.getInt("numberOfStudents"))
                    .build());

            Data.setGroupCode(resSetGroups.getInt("code")+1);
        }

        resSetLoads = statmt.executeQuery("SELECT * FROM Loads");

        while (resSetLoads.next()) {

            Data.getLoadsData().add(Load.newBuilder()
                    .setCode(resSetLoads.getInt("code"))
                    .setTeacher(resSetLoads.getInt("teacherCode"))
                    .setGroup(resSetLoads.getInt("groupCode"))
                    .setNumberOfHourses(resSetLoads.getInt("numberOfHourses"))
                    .setSubject(resSetLoads.getString("subject"))
                    .setTypeOfEmployment(resSetLoads.getString("typeOfEmployment"))
                    .setPayment(resSetLoads.getInt("payment"))
                    .build());

            Data.setLoadCode(resSetLoads.getInt("code")+1);
        }

        System.out.println("Tables is uploaded!");

    }
    
    // --------CLOSING CONNECTIONS--------
    public static void CloseDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSetTeachers.close();

        System.out.println("Connections is closed!");
    }

}
