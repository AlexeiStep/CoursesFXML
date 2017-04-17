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
import coursesfxml.model.Load;
import coursesfxml.model.Teacher;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author MrHuySGory
 */
public class LoadOverviewController {

    @FXML
    private TableView<Load> loadsTable;
    @FXML
    private TableColumn<Load, Integer> codeColumn;
    @FXML
    private TableColumn<Load, Integer> teacherCodeColumn;
    @FXML
    private TableColumn<Load, Integer> groupNumberrColumn;
    @FXML
    private TableColumn<Load, Integer> numberOfHoursesColumn;
    @FXML
    private TableColumn<Load, String> subjectColumn;
    @FXML
    private TableColumn<Load, String> typeOfEmploymentColumn;
    @FXML
    private TableColumn<Load, Integer> paymentColumn;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор. Конструктор вызывается раньше метода initialize().
     */
    public LoadOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty().asObject());
        teacherCodeColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty().getValue().codeProperty().asObject());
        groupNumberrColumn.setCellValueFactory(cellData -> cellData.getValue().groupProperty().getValue().numberProperty().asObject());
        numberOfHoursesColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfHoursesProperty().asObject());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        typeOfEmploymentColumn.setCellValueFactory(cellData -> cellData.getValue().typeOfEmploymentProperty());
        paymentColumn.setCellValueFactory(cellData -> cellData.getValue().paymentProperty().asObject());

    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        loadsTable.setItems(Data.getLoadsData());
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
    private void handleNewLoad() {
        Load tempLoad = Load.newBuilder()
                .setCode(0)
                .setTeacher(new Teacher()) //Вместо null создать препода
                .setGroup(new Group())
                .setNumberOfHourses(0)
                .setSubject("")
                .setTypeOfEmployment("")
                .setPayment(0)
                .build();
        boolean acceptClicked = mainApp.showLoadCreateDialog(tempLoad);
        if (acceptClicked) {
            Data.getLoadsData().add(tempLoad);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit... Открывает
     * диалоговое окно для изменения выбранной группы.
     */
    @FXML
    private void handleEditLoad() {
        Load selectedLoad = loadsTable.getSelectionModel().getSelectedItem();
        if (selectedLoad != null) {
            boolean acceptClicked = mainApp.showLoadEditDialog(selectedLoad);
            /*
             if (acceptClicked) {
             showTeacherDetails(selectedLoad);
             }
             */

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка выбора");
            alert.setHeaderText("Нагрузка не выбрана");
            alert.setContentText("Пожалуйста выберите нагрузку в таблице");

            alert.showAndWait();
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeleteLoad() {

        int selectedIndex = loadsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            for (Teacher t : Data.getTeachersData()) {
                if (t.getFullName().equalsIgnoreCase(loadsTable.getSelectionModel().getSelectedItems().get(selectedIndex).getTeacher().get().getFullName()) == true) {
                    t.decreaseNumberOfLoads();
                }
            }

            loadsTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка выбора");
            alert.setHeaderText("Не выбрана нагрузка");
            alert.setContentText("Пожалуйста выберите нагрузку в таблице");

            alert.showAndWait();

        }

    }

}
