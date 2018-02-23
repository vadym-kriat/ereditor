package com.nure.model.view;

import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;
import com.nure.model.util.Util;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

import javax.swing.text.Position;

public class VisualEntity extends TitledPane {
    private Table entity;
    private ListView<Column> attributesListView;

    public VisualEntity() {
        super();
        try {
            initialize(new Table("entity"));

            setPosition(50, 50);
            updateSize();
        } catch (SchemeException e) {
            e.printStackTrace();
        }
    }


    public VisualEntity(Table entity) {
        super();
        initialize(entity);

        setPosition(50, 50);
        updateSize();

    }

    private void initialize(Table entity) {
        this.entity = entity;

        attributesListView = new ListView<>();
        attributesListView.setItems(FXCollections.observableArrayList(entity.getAllColumns()));
        attributesListView.setCellFactory(param -> new AttributeListCell());
        //todo set context menu

        setText(entity.getName());
        setContent(attributesListView);
        setCollapsible(false);
        setExpanded(true);

        initListeners();
    }

    private void initListeners() {
        final Delta delta = new Delta();

        setOnMouseEntered(event -> setCursor(Cursor.HAND));
        setOnMousePressed(event -> {
            delta.x = getLayoutX() - event.getSceneX();
            delta.y = getLayoutY() - event.getSceneY();
            setCursor(Cursor.MOVE);
        });
        setOnMouseDragged(event -> {
            setLayoutX(event.getSceneX() + delta.x);
            setLayoutY(event.getSceneY() + delta.y);
        });
        setOnMouseReleased(event -> setCursor(Cursor.HAND));
    }

    private void updateSize() {
        //todo redo
        attributesListView.setPrefWidth(Util.getMaxLenghtAttrNameSize(entity) * 10 + 40);
        attributesListView.setPrefHeight(attributesListView.getItems().size() * 30);

    }

    private void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    static class AttributeListCell extends ListCell<Column> {
        private HBox root;
        private Label icon;
        private Label attrName;

        AttributeListCell() {
            super();
            root = new HBox();
            root.setMaxHeight(30);
            root.setSpacing(10);

            icon = new Label();
            icon.setPrefSize(20, 20);
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
                //todo set icon style class
                attrName.setText(item.getName());

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

    class Delta {double x, y;}
}
