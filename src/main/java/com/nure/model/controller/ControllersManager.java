package com.nure.model.controller;

public class ControllersManager {
    private static StartWindowController startWindowController;

    public static void setController(Object controller) {
        if (controller instanceof StartWindowController)
            startWindowController = (StartWindowController) controller;
    }
}
