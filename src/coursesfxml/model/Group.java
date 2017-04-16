/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MrHuySGory
 */
public class Group {

    private IntegerProperty code;
    private IntegerProperty number;   //изменить
    private StringProperty speciality;
    private StringProperty office;
    private IntegerProperty numberOfStudents;

    private void initialize() {
        code.set(0);
        number.set(0);
        numberOfStudents.set(0);
    }

    public int getCode() {
        return code.get();
    }

    public IntegerProperty codeProperty() {
        return code;
    }
    
    public void setCode(int code){
        this.code.set(code);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getSpeciality() {
        return speciality.get();
    }

    public StringProperty specialityProperty() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality.set(speciality);
    }

    public String getOffice() {
        return office.get();
    }

    public StringProperty officeProperty() {
        return office;
    }

    public void setOffice(String office) {
        this.office.set(office);
    }

    public int getNumberOfStudents() {
        return numberOfStudents.get();
    }

    public IntegerProperty numberOfStudentsProperty() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents.set(numberOfStudents);
    }

    // Геттер для записи в файл через linkedlist
    public String getInfo() {
        return ("(" + this.code.get() + ")"
                + " " + this.number.get() + ": "
                + " " + this.speciality.get()
                + " " + this.office.get()
                + " " + this.numberOfStudents.get());
    }
    
    public Group getSomeObject(){
        return this;
    }

    public static Builder newBuilder() {
        return new Group().new Builder();
    }

    public class Builder {

        private Integer code = 0;
        private Integer number;
        private String speciality;
        private String office;
        private Integer numberOfStudents;

        private Builder() {

        }

        public Builder setCode(int code) {
            this.code = code;

            return this;
        }

        public Builder setNumber(int number) {
            this.number = number;

            return this;
        }

        public Builder setSpeciality(String speciality) {
            this.speciality = speciality;

            return this;
        }

        public Builder setOffice(String office) {
            this.office = office;

            return this;
        }

        public Builder setNumberOfStudents(int numberOfStudents) {
            this.numberOfStudents = numberOfStudents;

            return this;
        }

        public Group build() {

            Group group = new Group();
            group.code = new SimpleIntegerProperty(this.code);
            group.number = new SimpleIntegerProperty(this.number);
            group.speciality = new SimpleStringProperty(this.speciality);
            group.office = new SimpleStringProperty(this.office);
            group.numberOfStudents = new SimpleIntegerProperty(this.numberOfStudents);

            return group;
        }

    }

}
