/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.view;

import coursesfxml.Data;
import coursesfxml.MainApp;
import coursesfxml.model.Group;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author MrHuySGory
 */
public class GroupEditDialogController {
    
    @FXML
    private TextField numberField;
    @FXML
    private TextField specialityField;
    @FXML
    private TextField officeField;
    @FXML
    private TextField numberOfStudentsField;

    private Stage dialogStage;
    private Group group;
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
     * @param group
     */
    public void setGroup(Group group) {
        this.group = group;

        numberField.setText(Integer.toString(group.getNumber()));
        specialityField.setText(group.getSpeciality());
        officeField.setText(group.getOffice());
        numberOfStudentsField.setText(Integer.toString(group.getNumberOfStudents()));
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
            
            group.setCode(Data.getGroupCode());
            group.setNumber(Integer.parseInt(numberField.getText()));
            group.setSpeciality(specialityField.getText());
            group.setOffice(officeField.getText());
            group.setNumberOfStudents(Integer.parseInt(numberOfStudentsField.getText()));

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

        if (numberField.getText() == null 
                || numberField.getText().length() == 0 
                || Integer.parseInt(numberField.getText()) < 111 
              /*  || (Integer.parseInt(numberField.getText()) != group.getCode() && ifRepeated(Integer.parseInt(numberField.getText()))) */) {
            errorMessage += "Некорректный номер группы!\n";
        }

        if (specialityField.getText() == null || specialityField.getText().length() == 0) {
            errorMessage += "Некорректно введена специальность!\n";
        }

        if (officeField.getText() == null || officeField.getText().length() == 0) {
            errorMessage += "Некорректно введён факультет!\n";
        }

        if (numberOfStudentsField.getText() == null 
                || numberOfStudentsField.getText().length() == 0 
                || Integer.parseInt(numberOfStudentsField.getText()) < 1) {
            errorMessage += "Некорректное количество студентов!\n";
        } else {
            // пытаемся преобразовать количество студентов в int.
            try {
                //  Integer.parseInt(phoneField.getText());
                Integer.parseInt(numberOfStudentsField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Некоррректно введено количество студентов!\n";
            }
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
    
    /*
    private boolean ifRepeated(Integer num){
    for(Group g: mainApp.data.getGroupsData()){
            if(num.equals(g.getNumber()) == true){
                return true;
            }
        }
    return false;
    }

    */
}
