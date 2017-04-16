/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.view;

import coursesfxml.Data;
import coursesfxml.MainApp;
import coursesfxml.model.Group;
import coursesfxml.model.Load;
import coursesfxml.model.Teacher;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author MrHuySGory
 */
public class LoadCreateDialogController {
    
    @FXML
    private ChoiceBox teacherNameBox;
    @FXML
    private ChoiceBox<Integer> groupNumberBox;
    @FXML
    private TextField numberOfHoursesField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField typeOfEmploymentField;
    @FXML
    private Label paymentLabel;

    private Stage dialogStage;
    private Load load;
    private boolean acceptClicked = false;

    private MainApp mainApp;
    
    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param load
     */
    public void setLoad(Load load) {
        this.load = load;
        
        ObservableList<String> teachersList = FXCollections.observableArrayList(Data.getTeachersData().stream().map(Teacher::getFullName).collect(Collectors.toList()));
        ObservableList<Integer> groupsList = FXCollections.observableArrayList(Data.getGroupsData().stream().map(Group::getNumber).collect(Collectors.toList()));
        
        teacherNameBox.setItems(teachersList);
        groupNumberBox.setItems(groupsList);
        numberOfHoursesField.setText(null);
        subjectField.setText(null);
        typeOfEmploymentField.setText(null);
        paymentLabel.setText(Integer.toString(0));
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isAcceptClicked() {
        return acceptClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleAccept() throws InstantiationException, IllegalAccessException {
        if (isInputValid()) {
            
            load.setCode(Data.getLoadCode());
            load.setTeacher(teacherNameBox.getSelectionModel().getSelectedItem().toString());
            load.setGroup(groupNumberBox.getSelectionModel().getSelectedItem());
            load.setNumberOfHourses(Integer.parseInt(numberOfHoursesField.getText()));
            load.setSubject(subjectField.getText());
            load.setTypeOfEmployment(typeOfEmploymentField.getText());
            load.setPayment(payment(subjectField.getText(), typeOfEmploymentField.getText()));
            
            for(Teacher t: Data.getTeachersData()){
                if(t.getFullName().equalsIgnoreCase(teacherNameBox.getSelectionModel().getSelectedItem().toString()) == true){
                    t.increaseNumberOfLoads();
                }
            }
            
            acceptClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (numberOfHoursesField.getText() == null || numberOfHoursesField.getText().length() == 0 || Integer.parseInt(numberOfHoursesField.getText()) < 1) {
            errorMessage += "Некорректное количество часов!\n";
        }

        if (subjectField.getText() == null || subjectField.getText().length() == 0 || isNotEmployment(subjectField)) {
            errorMessage += "Некорректно введён предмет!\n";
        }

        if (typeOfEmploymentField.getText() == null
                || typeOfEmploymentField.getText().length() == 0
                || isNotTypeOfEmployment(typeOfEmploymentField)) {
            errorMessage += "Некорректно введён тип занятия!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Недействительные поля ввода");
            alert.setHeaderText("Пожалуйста исправьте недействительные поля ввода");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private boolean isNotEmployment(TextField field) {

        if (field.getText().equalsIgnoreCase("English") != true
                & field.getText().equalsIgnoreCase("BigData") != true
                & field.getText().equalsIgnoreCase("OS") != true
                & field.getText().equalsIgnoreCase("OOP") != true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNotTypeOfEmployment(TextField field) {

        if (field.getText().equalsIgnoreCase("Lecture") != true
                & field.getText().equalsIgnoreCase("Practice") != true) {
            return true;
        } else {
            return false;
        }
    }

    private <String> int payment(String subject, String typeOfEmployment) {

        int subj = 0;
        int tOE = 0;

        if ((subject.equals("English")) == true) {
            subj = 2;
        } else if ((subject.equals("BigData")) == true) {
            subj = 3;
        } else if ((subject.equals("OS")) == true) {
            subj = 2;
        } else if ((subject.equals("OOP")) == true) {
            subj = 2;
        }

        if ((typeOfEmployment.equals("Lecture")) == true) {
            tOE = 2;
        } else if ((typeOfEmployment.equals("Practice")) == true) {
            tOE = 3;
        }

        return (2 * (subj + tOE));
    }
    
}
