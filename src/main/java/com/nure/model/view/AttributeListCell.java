package com.nure.model.view;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class AttributeListCell extends ListCell<Column> {
    private HBox root;
    private Label icon;
    private Label attrName;

    public AttributeListCell(double height) {
        super();
        root = new HBox();
        root.setMaxHeight(height);
        root.setSpacing(10);

        icon = new Label();
        icon.setPrefSize(height/1.5, height/1.5);
        root.getChildren().add(icon);

        attrName = new Label();
        root.getChildren().add(attrName);
    }

    @Override
    protected void updateItem(Column item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(null);
            attrName.setText(item.getName());

            icon.getStyleClass().clear();
            if (item.isPK()) {
                icon.getStyleClass().add("pk");
            } else if (item instanceof ForeignKey) {
                icon.getStyleClass().add("fk");
            } else {
                icon.getStyleClass().add(item.isNotNull() ? "not-null" : "null");
            }

            setGraphic(root);
        }
    }
}
