package com.nure.model.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewModelWindowController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllersManager.getInstance().setController(this);
    }

    public void setModel(ActionEvent actionEvent) {

    }

    public void cancel(ActionEvent actionEvent) {

    }
}
