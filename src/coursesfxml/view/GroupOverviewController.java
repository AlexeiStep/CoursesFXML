/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.view;

import coursesfxml.Data;
import coursesfxml.DataBase;
import coursesfxml.MainApp;
import coursesfxml.model.Group;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author MrHuySGory
 */
public class GroupOverviewController {

    @FXML
    private TableView<Group> groupsTable;
    @FXML
    private TableColumn<Group, Integer> codeColumn;
    @FXML
    private TableColumn<Group, Integer> numberColumn;
    @FXML
    private TableColumn<Group, String> specialityColumn;
    @FXML
    private TableColumn<Group, String> officeColumn;
    @FXML
    private TableColumn<Group, Integer> numberOfStudentsColumn;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор. Конструктор вызывается раньше метода initialize().
     */
    public GroupOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty().asObject());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        specialityColumn.setCellValueFactory(cellData -> cellData.getValue().specialityProperty());
        officeColumn.setCellValueFactory(cellData -> cellData.getValue().officeProperty());
        numberOfStudentsColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfStudentsProperty().asObject());
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        groupsTable.setItems(Data.getGroupsData());
    }

    @FXML
    private void handleExit() {
        try {
            DataBase.WriteDB();
            DataBase.CloseDB();
        } catch (SQLException ex) {
            Logger.getLogger(LoadOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    
    /**
     * Вызывается, когда пользователь кликает по кнопке New... Открывает
     * диалоговое окно с дополнительной информацией новой группы.
     */
    @FXML
    private void handleNewGroup() {
        Group tempGroup = Group.newBuilder()
                .setCode(Data.getGroupCode())
                .setNumber(0)
                .setSpeciality("")
                .setOffice("")
                .setNumberOfStudents(0)
                .build();
        boolean acceptClicked = mainApp.showGroupEditDialog(tempGroup);
        if (acceptClicked) {
            Data.getGroupsData().add(tempGroup);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit... Открывает
     * диалоговое окно для изменения выбранной группы.
     */
    @FXML
    private void handleEditGroup() {
        Group selectedGroup = groupsTable.getSelectionModel().getSelectedItem();
        if (selectedGroup != null) {
            boolean acceptClicked = mainApp.showGroupEditDialog(selectedGroup);
            /*
             if (acceptClicked) {
             showTeacherDetails(selectedGroup);
             }
             */

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка выбора");
            alert.setHeaderText("Группа не выбрана");
            alert.setContentText("Пожалуйста выберите группу в таблице");

            alert.showAndWait();
        }
    }
    
    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeleteGroup() {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Предупреждение");
        alert.setHeaderText("При удалении группы произойдёт удаление её нагрузки");
        alert.setContentText("Удалить группу?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        } else {
            return;
        }

        int selectedIndex = groupsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            
            //Removal of the load after removal of the group
            for(int i=0; i<Data.getLoadsData().size(); i++){
                if(Data.getLoadsData().get(i).getTeacherCode() == groupsTable.getSelectionModel().getSelectedItem().getCode()){
                    Data.removeLoad(groupsTable.getSelectionModel().getSelectedItem().getCode());
                }
            }
            
            groupsTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка выбора");
            alert.setHeaderText("Не выбрана группа");
            alert.setContentText("Пожалуйста выберите группу в таблице");

            alert.showAndWait();

        }
    }

}
