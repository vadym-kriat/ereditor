package com.nure.model.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StartWindowController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllersManager.getInstance().setController(this);
    }

    public void openLMEditor(ActionEvent actionEvent) {
        ControllersManager.getInstance().startLMeditor();
    }

    public void openDBEditor(ActionEvent actionEvent) {
        //todo ControllersManager.getInstance().startDBeditor();
    }
}
