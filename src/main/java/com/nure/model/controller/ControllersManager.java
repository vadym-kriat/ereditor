package com.nure.model.controller;

import com.nure.model.ProjectManager;
import com.nure.model.view.VisualEntity;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;

import static com.nure.model.util.Window.*;

public class ControllersManager {
    private static ControllersManager instance;

    private StartWindowController startWindowController;
    private MainWindowController mainWindowController;
    private EditEntityWindowController editEntityWindowController;
    private CreateNewModelWindowController createNewModelWindowController;

    public static ControllersManager getInstance() {
        if (instance == null)
            instance = new ControllersManager();
        return instance;
    }

    public void setController(Initializable controller) {
        if (controller instanceof StartWindowController)
            startWindowController = (StartWindowController) controller;
        if (controller instanceof MainWindowController)
            mainWindowController = (MainWindowController) controller;
        if (controller instanceof EditEntityWindowController)
            editEntityWindowController = (EditEntityWindowController) controller;
        if (controller instanceof CreateNewModelWindowController)
            createNewModelWindowController = (CreateNewModelWindowController) controller;
    }

    public void startApp(Stage primaryStage) throws IOException {
        EReditorStageHolder.getInstance().setPrimaryStage(primaryStage);
        EReditorStageHolder.getInstance().openWindow(START);
    }

    public void startLMeditor() {
        EReditorStageHolder.getInstance().openWindow(MAIN);
    }

    public void editEntity(VisualEntity visualEntity) {
        editEntityWindowController.setEntity(visualEntity.getEntity());
        BooleanProperty isChanged = new SimpleBooleanProperty(false);
        editEntityWindowController.setIsChanged(isChanged);
        EReditorStageHolder.getInstance().openWindow(EDIT_ENTITY);
        if (isChanged.get()) {
            ProjectManager.getInstance().getSchema().updateTable(visualEntity.getEntity(), editEntityWindowController.getEntity());
            visualEntity.setEntity(editEntityWindowController.getEntity());
            mainWindowController.updateRelationships(visualEntity);
        }

    }
}
