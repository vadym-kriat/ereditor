package com.nure.model.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EditEntityWindowController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllersManager.getInstance().setController(this);
    }

    public void cancel(ActionEvent actionEvent) {

    }

    public void saveEntity(ActionEvent actionEvent) {

    }

    public void newAttribute(ActionEvent actionEvent) {

    }

    public void deleteAttribute(ActionEvent actionEvent) {

    }
}
