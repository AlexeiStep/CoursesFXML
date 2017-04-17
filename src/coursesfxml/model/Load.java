/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.model;

import coursesfxml.Data;
import java.util.Iterator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;

/**
 *
 * @author MrHuySGory
 */
public class Load {

    private IntegerProperty code;
    private SimpleObjectProperty teacher;
    private SimpleObjectProperty group;
    private IntegerProperty numberOfHourses;
    private StringProperty subject;
    private StringProperty typeOfEmployment;
    private IntegerProperty payment;
    //   private int teacherCode;

    private void initialize() {
        code.set(0);
        numberOfHourses.set(0);
        payment.set(0);
    }

    public String getInfo() {
        return (getTeacher().getValue().getFullName()
                + ' ' + getGroup().getValue().getNumber()
                + ' ' + getNumberOfHourses()
                + ' ' + getSubject()
                + ' ' + getTypeOfEmployment()
                + " $" + getPayment());
    }

    public int getCode() {
        return code.get();
    }

    public IntegerProperty codeProperty() {
        return code;
    }

    public void setCode(Integer code) {
        this.code.set(code);
    }

    public SimpleObjectProperty<Teacher> getTeacher() {
        return teacher;
    }

    public Property<Teacher> teacherProperty() {
        return (Property<Teacher>) teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher.set(teacher);
    }

    public void setTeacher(String teacherFullName) {
        for (Teacher t : Data.getTeachersData()) {
            if (t.getFullName().equalsIgnoreCase(teacherFullName) == true) {
                this.teacher = new SimpleObjectProperty(t.getSomeObject());
                return;
            }
        }
    }

    public void setTeacher(int teacherCode) {
        for (Teacher t : Data.getTeachersData()) {
            if (t.getCode() == teacherCode) {
                this.teacher = new SimpleObjectProperty(t.getSomeObject());
                return;
            }
        }
    }

    public SimpleObjectProperty<Group> getGroup() {
        return group;
    }

    public Property<Group> groupProperty() {
        return (Property<Group>) group;
    }

    public void setGroup(Group group) {
        this.group.set(group);
    }

    public void setGroup(Integer groupNumber) {
        for (Group g : Data.getGroupsData()) {
            if (groupNumber.equals(g.getNumber()) == true) {
                this.group = new SimpleObjectProperty(g.getSomeObject());
                return;
            }
        }

    }

    public int getNumberOfHourses() {
        return numberOfHourses.get();
    }

    public IntegerProperty numberOfHoursesProperty() {
        return numberOfHourses;
    }

    public void setNumberOfHourses(int numberOfHourses) {
        this.numberOfHourses.set(numberOfHourses);
    }

    public String getSubject() {
        return subject.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getTypeOfEmployment() {
        return typeOfEmployment.get();
    }

    public StringProperty typeOfEmploymentProperty() {
        return typeOfEmployment;
    }

    public void setTypeOfEmployment(String typeOfEmployment) {
        this.typeOfEmployment.set(typeOfEmployment);
    }

    public int getPayment() {
        return payment.get();
    }

    public IntegerProperty paymentProperty() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment.set(payment);
    }

    public int getTeacherCode() {
        return getTeacher().getValue().getCode();
    }

    public int getGroupCode() {
        return getGroup().getValue().getCode();
    }

    public static Builder newBuilder() {
        return new Load().new Builder();
    }

    public class Builder {

        private Integer code;
        private Teacher teacher;
        private Group group;
        private Integer numberOfHourses;
        private String subject;
        private String typeOfEmployment;
        private Integer payment;

        public Builder setCode(int code) {
            this.code = code;

            return this;
        }

        public Builder setTeacher(Teacher teacher) {
            this.teacher = teacher;

            return this;
        }

        public Builder setTeacher(int teacherCode) {
            for (Teacher t : Data.getTeachersData()) {
                if (t.getCode() == teacherCode) {
                    this.teacher = t;
                    return this;
                }
            }
            return this;
        }

        public Builder setGroup(Group group) {
            this.group = group;

            return this;
        }

        public Builder setGroup(Integer groupCode) {
            for (Group g : Data.getGroupsData()) {
                if (groupCode.equals(g.getCode()) == true) {
                    this.group = g;
                    return this;
                }
            }
            return this;
        }

        public Builder setNumberOfHourses(int numberOfHourses) {
            this.numberOfHourses = numberOfHourses;

            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;

            return this;
        }

        public Builder setTypeOfEmployment(String typeOfEmployment) {
            this.typeOfEmployment = typeOfEmployment;

            return this;
        }

        public Builder setPayment(int payment) {
            this.payment = payment;

            return this;
        }

        public Load build() {

            Load load = new Load();

            load.code = new SimpleIntegerProperty(this.code);
            load.teacher = new SimpleObjectProperty(this.teacher);
            load.group = new SimpleObjectProperty(this.group);
            load.numberOfHourses = new SimpleIntegerProperty(this.numberOfHourses);
            load.subject = new SimpleStringProperty(this.subject);
            load.typeOfEmployment = new SimpleStringProperty(this.typeOfEmployment);
            load.payment = new SimpleIntegerProperty(this.payment);

            return load;
        }

    }

}
