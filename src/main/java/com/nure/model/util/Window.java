package com.nure.model.util;

public enum Window {
    START("EReditor",800, 500, false),
    MAIN("EReditor",900, 500, true),
    EDIT_ENTITY("Edit enity",520, 600, false),
    CREATE_MODEL("New model",400, 300, false);

    private final String name;
    private final double width;
    private final double height;
    private final boolean isResizable;

    Window(String name, int width, int height, boolean isResizable) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.isResizable = isResizable;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isResizable() {
        return isResizable;
    }

    public String getName() {
        return name;
    }
}
