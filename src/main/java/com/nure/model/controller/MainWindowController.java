package com.nure.model.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllersManager.getInstance().setController(this);
    }

    //Menu-bar methods
    public void newModel(ActionEvent actionEvent) {

    }

    public void openModel(ActionEvent actionEvent) {

    }

    public void saveModel(ActionEvent actionEvent) {

    }

    public void saveModelAs(ActionEvent actionEvent) {

    }

    public void newEntity(ActionEvent actionEvent) {

    }

    public void createSQL(ActionEvent actionEvent) {

    }
}
