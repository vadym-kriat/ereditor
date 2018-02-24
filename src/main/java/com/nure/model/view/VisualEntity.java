package com.nure.model.view;

import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;
import com.nure.model.util.Util;
import javafx.collections.FXCollections;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualEntity extends TitledPane {
    public static final double ROW_HEIGHT = 30;

    private Table entity;
    private ListView<Column> attributesListView;
    private Map<VisualEntity, Relationship> relationshipMap;
    private Map<VisualEntity, Relationship> externalRelationshipMap;

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
        relationshipMap = new HashMap<>();
        externalRelationshipMap = new HashMap<>();

        attributesListView = new ListView<>();
        attributesListView.setItems(FXCollections.observableArrayList(entity.getAllColumns()));
        attributesListView.setCellFactory(param -> new AttributeListCell());
        attributesListView.setMouseTransparent(true);
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
            for (Relationship relationship : relationshipMap.values()) {
                relationship.setSide();
            }
            for (Relationship relationship : externalRelationshipMap.values()) {
                relationship.setSide();
            }
        });
        setOnMouseReleased(event -> setCursor(Cursor.HAND));
    }

    private void updateSize() {
        attributesListView.setPrefWidth(Util.getMaxLenghtAttrNameSize(entity) * 10 + 40);
        attributesListView.setPrefHeight(attributesListView.getItems().size() * 30);

    }

    private void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    public Table getEntity() {
        return entity;
    }

    public void updateRelationships(List<VisualEntity> visualEntityList) {
        for (Relationship relationship : relationshipMap.values()) {
            relationship.delete();
        }
        relationshipMap.clear();

        List<ForeignKey> foreignKeys = entity.listOfForeignKeys();
        for (ForeignKey foreignKey : foreignKeys) {
            for (VisualEntity visualEntity : visualEntityList) {
                if (foreignKey.getReferencedTableName().equals(visualEntity.getEntity().getName())) {
                    Relationship relationship = new Relationship(this.getParent(), this, visualEntity);
                    relationshipMap.put(visualEntity, relationship);
                    visualEntity.addExternalRelationship(this, relationship);
                }
            }
        }

        for (Relationship relationship : relationshipMap.values()) {
            relationship.draw();
        }
    }

    public void addExternalRelationship(VisualEntity visualEntity, Relationship relationship) {
        externalRelationshipMap.put(visualEntity, relationship);
    }

    public void deleteRelationship(VisualEntity visualEntity) {
        relationshipMap.get(visualEntity).delete();
        relationshipMap.remove(visualEntity);
    }

    static class AttributeListCell extends ListCell<Column> {
        private HBox root;
        private Label icon;
        private Label attrName;

        AttributeListCell() {
            super();
            root = new HBox();
            root.setMaxHeight(ROW_HEIGHT);
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
