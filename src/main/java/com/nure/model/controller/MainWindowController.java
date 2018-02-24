package com.nure.model.controller;

import com.nure.model.schema.Schema;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;
import com.nure.model.view.VisualEntity;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class MainWindowController implements Initializable {
    @FXML
    public AnchorPane workSpace;

    private List<VisualEntity> visualEntityList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllersManager.getInstance().setController(this);
        visualEntityList = new ArrayList<>();

        //test
        /*try {
            Table table = new Table("Table");
            Column column = new Column();
            column.setName("primary_key");
            column.setPK(true);
            table.addColumn(column);
            Column col = new Column();
            col.setName("not_null");
            col.setNotNull(true);
            table.addColumn(col);

            workSpace.getChildren().add(new VisualEntity(table));
        } catch (SchemeException e) {
            e.printStackTrace();
        }*/

        test();

    }

    private void test() {
        try {
            Schema schema = new Schema("Schema");
            Table table = schema.newTable();
            table.setName("hutor");
            Column column = new Column();
            column.setName("primary_key");
            column.setPK(true);
            table.addColumn(column);
            Column col = new Column();
            col.setName("not_null");
            col.setNotNull(true);
            table.addColumn(col);
            ForeignKey foreignKey = new ForeignKey();
            foreignKey.setName("kura_id");
            foreignKey.setFkName("kura_id");
            foreignKey.setReferencedTable("kura");
            foreignKey.setReferencedColumnName("primary_key");
            foreignKey.setNotNull(true);
            table.addForeignKey(foreignKey);

            table = schema.newTable();
            table.setName("kura");
            column = new Column();
            column.setName("primary_key");
            column.setPK(true);
            table.addColumn(column);

            drawSchema(schema);
        } catch (SchemeException e) {
            e.printStackTrace();
        }
    }

    public void drawSchema(Schema schema) {
        Set<Table> tables = schema.getTables();
        for (Table table : tables) {
            visualEntityList.add(new VisualEntity(table));

        }
        workSpace.getChildren().addAll(visualEntityList);
        for (VisualEntity visualEntity : visualEntityList) {
            visualEntity.updateRelationships(visualEntityList);
        }
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
