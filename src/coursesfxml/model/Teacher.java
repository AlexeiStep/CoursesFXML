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
 * Класс-модель для преподавателя (Teacher).
 *
 */
public class Teacher {

    private IntegerProperty code;
    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty patronymic;
    private StringProperty phone;
    private IntegerProperty experience;
    private IntegerProperty numberOfLoads;

    private void initialize() {
        code.set(0);
        experience.set(0);
        numberOfLoads.set(0);

    }

    // не знаю зачем эти методы
    public int getCode() {
        return code.get();
    }

    //Эти методы для заполнения таблицы

    public IntegerProperty codeProperty() {
        return code;
    }
    public void setCode(int code){
        this.code.set(code);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }
    
    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public int getExperience() {
        return experience.get();
    }

    public IntegerProperty experienceProperty() {
        return experience;
    }
    
    public void setExperience(int experience) {
        this.experience.set(experience);
    }

    // Геттер для записи в файл через linkedlist
    public String getInfo() {
        return ("(" + this.code.get() + ")"
                + " " + this.lastName.get()
                + " " + this.firstName.get()
                + " " + this.patronymic.get()
                + " " + this.phone.get()
                + " " + this.experience.get());
    }

    public String getFullName() {
        return (lastName.get() + " " + firstName.get() + " " + patronymic.get());
    }

    public void increaseNumberOfLoads() {
        numberOfLoads.set(numberOfLoads.get() + 1);
    }

    public void decreaseNumberOfLoads() {
        numberOfLoads.set(numberOfLoads.get() - 1);
    }

    public int getNumberOfLoads() {
        return numberOfLoads.get();
    }

    public IntegerProperty numberOfLoadsProperty() {
        return numberOfLoads;
    }
    
    public Teacher getSomeObject(){
        return this;
    }

    public static Builder newBuilder() {
        return new Teacher().new Builder();
    }
    
    
    public class Builder {

        private Integer code;
        private String lastName;
        private String firstName;
        private String patronymic;
        private String phone;
        private Integer experience;
        private Integer numberOfLoads;

        private Builder() {

        }

        public Builder setCode(int code) {
            this.code = code;

            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;

            return this;
        }

        public Builder setPatronymic(String patronymic) {
            this.patronymic = patronymic;

            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;

            return this;
        }

        public Builder setExperience(int experience) {
            this.experience = experience;

            return this;
        }

        public Builder setNumberOfLoads(int numberOfLoads) {
            this.numberOfLoads = numberOfLoads;

            return this;
        }

        public Teacher build() {

            Teacher teacher = new Teacher();
            teacher.code = new SimpleIntegerProperty(this.code);
            teacher.lastName = new SimpleStringProperty(this.lastName);
            teacher.firstName = new SimpleStringProperty(this.firstName);
            teacher.patronymic = new SimpleStringProperty(this.patronymic);
            teacher.phone = new SimpleStringProperty(this.phone);
            teacher.experience = new SimpleIntegerProperty(this.experience);
            teacher.numberOfLoads = new SimpleIntegerProperty(this.numberOfLoads);

            return teacher;
        }
    }

}
