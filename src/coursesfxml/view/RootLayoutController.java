/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursesfxml.view;

import coursesfxml.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author MrHuySGory
 */
public class RootLayoutController {
    
    @FXML
    private Button teachersButton;
    
    @FXML
    private Button groupsButton;
    
    @FXML
    private Button loadsButton;
    
    
    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор.
     */
    public RootLayoutController() {
    }
    
    @FXML
    private void showTeachersOverview()
    {
        mainApp.hideOverview();
        mainApp.showTeacherOverview();
    }
    
    @FXML
    private void showGroupsOverview()
    {
        mainApp.hideOverview();
        mainApp.showGroupOverview();
    }
    
    @FXML
    private void showLoadsOverview()
    {
        mainApp.hideOverview();
        mainApp.showLoadOverview();
    }
    
    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
