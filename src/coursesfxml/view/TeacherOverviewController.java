/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.view;

import coursesfxml.Data;
import coursesfxml.MainApp;
import coursesfxml.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author MrHuySGory
 */
public class TeacherOverviewController {

    @FXML
    private TableView<Teacher> teachersTable;
    @FXML
    private TableColumn<Teacher, Integer> codeColumn;
    @FXML
    private TableColumn<Teacher, String> lastNameColumn;
    @FXML
    private TableColumn<Teacher, String> firstNameColumn;
    @FXML
    private TableColumn<Teacher, String> patronymicColumn;
    @FXML
    private TableColumn<Teacher, String> phoneColumn;
    @FXML
    private TableColumn<Teacher, Integer> experienceColumn;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор. Конструктор вызывается раньше метода initialize().
     */
    public TeacherOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty().asObject());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        patronymicColumn.setCellValueFactory(cellData -> cellData.getValue().patronymicProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        experienceColumn.setCellValueFactory(cellData -> cellData.getValue().experienceProperty().asObject());

        
         // Слушаем изменения выбора, и при изменении отображаем
         // дополнительную информацию об адресате.
         //teachersTable.getSelectionModel().selectedItemProperty().addListener(
         //(observable, oldValue, newValue) -> System.out.print(newValue.getFullName()));
         
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        teachersTable.setItems(Data.getTeachersData());
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке New... Открывает
     * диалоговое окно с дополнительной информацией нового преподавателя.
     */
    @FXML
    private void handleNewTeacher() {
        Teacher tempTeacher = Teacher.newBuilder()
                .setCode(Data.getTeacherCode())
                .setLastName("")
                .setFirstName("")
                .setPatronymic("")
                .setPhone("")
                .setExperience(0)
                .setNumberOfLoads(0)
                .build();
        boolean acceptClicked = mainApp.showTeacherEditDialog(tempTeacher);
        if (acceptClicked) {
            Data.getTeachersData().add(tempTeacher);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit... Открывает
     * диалоговое окно для изменения выбранного преподавателя.
     */
    @FXML
    private void handleEditTeacher() {
        Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            boolean acceptClicked = mainApp.showTeacherEditDialog(selectedTeacher);
            /*
             if (acceptClicked) {
             showTeacherDetails(selectedTeacher);
             }
             */

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка выбора");
            alert.setHeaderText("Преподаватель не выбран");
            alert.setContentText("Пожалуйста выберите преподавателя в таблице");

            alert.showAndWait();
        }
    }
    
    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeleteTeacher() {

        int selectedIndex = teachersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            teachersTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка выбора");
            alert.setHeaderText("Не выбран преподаватель");
            alert.setContentText("Пожалуйста выберите преподавателя в таблице");

            alert.showAndWait();

        }
    }

}
