package com.nure.model.controller;

import com.nure.model.util.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.nure.model.util.Window.*;

import java.io.IOException;
import java.util.HashMap;

public class EReditorStageHolder {
    private Stage primaryStage;
    private static EReditorStageHolder instance;
    private static HashMap<Window, Parent> roots;

    public static EReditorStageHolder getInstance() {
        if (instance == null) {
            instance = new EReditorStageHolder();
        }
        return instance;
    }

    private EReditorStageHolder() {
        FXMLLoader loader = new FXMLLoader();
        roots = new HashMap<>();
        try {
            roots.put(START, loader.load(getClass().getResource("/fxml/start_window.fxml")));
            roots.put(MAIN, loader.load(getClass().getResource("/fxml/main_window.fxml")));
            roots.put(EDIT_ENTITY, loader.load(getClass().getResource("/fxml/edit_entity_window.)fxml"));
            roots.put(CREATE_MODEL, loader.load(getClass().getResource("/fxml/create_model_window).fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openWindow(Window windowName) {
        switch (windowName) {
            case MAIN:
            case START:
                primaryStage.setScene(new Scene(roots.get(windowName), windowName.getWidth(), windowName.getHeight()));
                primaryStage.setResizable(windowName.isResizable());
                primaryStage.setTitle(windowName.getName());
                primaryStage.show();
                break;
            case EDIT_ENTITY:
            case CREATE_MODEL:
                Stage stage = new Stage();
                stage.setScene(new Scene(roots.get(windowName), windowName.getWidth(), windowName.getHeight()));
                stage.setResizable(windowName.isResizable());
                stage.setTitle(windowName.getName());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(primaryStage);
                stage.show();
                break;
        }
    }

    public void setPrimaryStage(Stage primaryStage) throws IOException {
        if (this.primaryStage != null) {
            throw new IOException("Stage already set!");
        }
        this.primaryStage = primaryStage;
    }
}
