package coursesfxml;

import coursesfxml.model.Group;
import coursesfxml.model.Load;
import coursesfxml.model.Teacher;
import coursesfxml.view.GroupEditDialogController;
import coursesfxml.view.GroupOverviewController;
import coursesfxml.view.LoadCreateDialogController;
import coursesfxml.view.LoadEditDialogController;
import coursesfxml.view.LoadOverviewController;
import coursesfxml.view.RootLayoutController;
import coursesfxml.view.TeacherEditDialogController;
import coursesfxml.view.TeacherOverviewController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author MrHuySGory
 */
public class MainApp extends Application {

//    public static Teachers teachers = new Teachers();
//    public static Groups groups = new Groups();
    //   public static Loads loads = new Loads();
    public Data data = new Data();

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Курсы повышения квалификации");

        initRootLayout();

        showLoadOverview();
        showGroupOverview();
        showTeacherOverview();

    }

    
    // Инициализирует корневой макет.
     
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IllegalStateException | IOException e) {
        }
    }

    /**
     * Показывает в корневом макете сведения о преподавателях.
     */
    public void showTeacherOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TeacherOverview.fxml"));
            AnchorPane teacherOverview = (AnchorPane) loader.load();

            // Помещаем сведения о преподавателях в центр корневого макета.
            rootLayout.setCenter(teacherOverview);

            // Даём контроллеру доступ к главному приложению.
            TeacherOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
        }
    }

    /**
     * Показывает в корневом макете сведения о группах.
     */
    public void showGroupOverview() {
        try {
            // Загружаем сведения о группах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/GroupOverview.fxml"));
            AnchorPane groupOverview = (AnchorPane) loader.load();

            // Помещаем сведения о группах в центр корневого макета.
            rootLayout.setCenter(groupOverview);

            // Даём контроллеру доступ к главному приложению.
            GroupOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
        }
    }

    /**
     * Показывает в корневом макете сведения о нагрузке.
     */
    public void showLoadOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoadOverview.fxml"));
            AnchorPane loadOverview = (AnchorPane) loader.load();
            
            // Помещаем сведения о преподавателях в центр корневого макета.
            rootLayout.setCenter(loadOverview);

            // Даём контроллеру доступ к главному приложению.
            LoadOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
        }
    }

    public void hideOverview() {
        Node root = primaryStage.getScene().getWindow().getScene().getRoot();
        if (root instanceof BorderPane && ((BorderPane) root).getCenter() != null) {
            ((BorderPane) root).getCenter().setVisible(false);
        }
    }

    /**
     * Открывает диалоговое окно для изменения деталей указанного преподавателя.
     * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте преподавателя и возвращается значение true.
     *
     * @param teacher - объект преподавателя, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showTeacherEditDialog(Teacher teacher) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TeacherEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование данных преподавателя");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём преподавателя в контроллер.
            TeacherEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTeacher(teacher);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isAcceptClicked();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Открывает диалоговое окно для изменения деталей указанной группы. Если
     * пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте группы и возвращается значение true.
     *
     * @param group - объект группы, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showGroupEditDialog(Group group) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GroupEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование данных группы");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём преподавателя в контроллер.
            GroupEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGroup(group);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isAcceptClicked();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Открывает диалоговое окно для изменения деталей указанной группы. Если
     * пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте группы и возвращается значение true.
     *
     * @param load - объект группы, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showLoadEditDialog(Load load) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoadEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование данных нагрузки");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём преподавателя в контроллер.
            LoadEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLoad(load);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isAcceptClicked();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Открывает диалоговое окно для создания нагрузки. Если пользователь
     * кликнул OK, то изменения сохраняются в предоставленном объекте нагрузки и
     * возвращается значение true.
     *
     * @param load - объект нагрузки, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showLoadCreateDialog(Load load) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoadCreateDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Создание нагрузки");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём преподавателя в контроллер.
            LoadCreateDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLoad(load);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isAcceptClicked();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Возвращает главную сцену.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
