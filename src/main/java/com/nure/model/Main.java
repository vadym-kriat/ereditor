package com.nure.model;

import com.nure.model.controller.ControllersManager;
import com.nure.model.controller.EReditorStageHolder;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ControllersManager.getInstance().startApp(primaryStage);
    }
}
