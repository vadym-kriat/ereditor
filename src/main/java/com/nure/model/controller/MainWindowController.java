package com.nure.model.controller;

import com.nure.model.ProjectManager;
import com.nure.model.schema.Schema;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;
import com.nure.model.view.VisualEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

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

        test();

    }

    private void test() {
        try {
            ProjectManager.getInstance().createNewSchema("Schema");

            Schema schema = ProjectManager.getInstance().getSchema();

            drawSchema(schema);
        } catch (SchemeException e) {
            e.printStackTrace();
        }
    }

    public void drawSchema(Schema schema) {
        visualEntityList.clear();
        workSpace.getChildren().clear();
        Set<Table> tables = schema.getTables();
        for (Table table : tables) {
            visualEntityList.add(new VisualEntity(table));

        }
        workSpace.getChildren().addAll(visualEntityList);
        for (VisualEntity visualEntity : visualEntityList) {
            visualEntity.updateRelationships(visualEntityList);
        }
    }

    public void updateRelationships(VisualEntity visualEntity) {
        visualEntity.updateRelationships(visualEntityList);
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
        VisualEntity newEntity = newVisualEntity();
        visualEntityList.add(newEntity);
        workSpace.getChildren().add(newEntity);
    }

    private VisualEntity newVisualEntity() {
        Table newTable = ProjectManager.getInstance().getSchema().newTable();
        return new VisualEntity(newTable);
    }

    public void createSQL(ActionEvent actionEvent) {

    }
}
