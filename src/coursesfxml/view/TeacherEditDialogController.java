/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.view;

import coursesfxml.Data;
import coursesfxml.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Окно для изменения информации о преподавателях.
 *
 * @author Marco Jakob
 */
public class TeacherEditDialogController {

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField patronymicField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField experienceField;
    @FXML
    private Label numberOfLoadsLabel;

    private Stage dialogStage;
    private Teacher teacher;
    private boolean acceptClicked = false;

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

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;

        lastNameField.setText(teacher.getLastName());
        firstNameField.setText(teacher.getFirstName());
        patronymicField.setText(teacher.getPatronymic());
        phoneField.setText(teacher.getPhone());
        experienceField.setText(Integer.toString(teacher.getExperience()));
        numberOfLoadsLabel.setText(Integer.toString(teacher.getNumberOfLoads()));
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
            teacher.setCode(Data.getTeacherCode());
            teacher.setLastName(lastNameField.getText());
            teacher.setFirstName(firstNameField.getText());
            teacher.setPatronymic(patronymicField.getText());
            teacher.setPhone(phoneField.getText());
            teacher.setExperience(Integer.parseInt(experienceField.getText()));

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

        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Некорректная фамилия!\n";
        }

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Некорректное имя!\n";
        }

        if (patronymicField.getText() == null || patronymicField.getText().length() == 0) {
            errorMessage += "Некорректное отчество!\n";
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "Некорректный номер телефона!\n";
        } else {
            // пытаемся преобразовать номер телефона в int.
            try {
                //  Integer.parseInt(phoneField.getText());
                Long.parseLong(phoneField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Некоррректный номер телефона!\n";
            }
        }

        if (experienceField.getText() == null || experienceField.getText().length() == 0 || Integer.parseInt(experienceField.getText()) < 0) {
            errorMessage += "Некорректный ввод опыта!\n";
        } else {
            // пытаемся преобразовать опыт в int.
            try {
                Integer.parseInt(experienceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Некоррректный ввод опыта!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Недействительные поля ввода");
            alert.setHeaderText("Пожалуйста исправьте недействительные поля ввода");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
