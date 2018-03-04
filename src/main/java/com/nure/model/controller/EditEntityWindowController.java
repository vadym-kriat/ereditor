package com.nure.model.controller;

import com.nure.model.ProjectManager;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.ForeignKeyOption;
import com.nure.model.schema.table.Table;
import com.nure.model.util.DatatypeMapper;
import com.nure.model.view.AttributeListCell;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.bind.ValidationException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class EditEntityWindowController implements Initializable {
    public TextField tfName;
    public ListView<Column> lvAttributes;
    public TabPane settingsPane;
    public CheckBox isPrimaryKey;
    public CheckBox isForeignKey;
    public CheckBox isNotNull;
    public CheckBox isAutoIncrement;
    public CheckBox isUnique;
    public ComboBox<String> cmbDatatype;
    public TextField txtDataSize;
    public TextField txtDefault;
    public TextField txtDataMin;
    public TextField txtDataMax;
    public ComboBox<String> cmbRefTable;
    public ComboBox<ForeignKeyOption> cmbOnUpdate;
    public ComboBox<ForeignKeyOption> cmbOnDelete;
    public AnchorPane paneForeignKey;
    public Label lblNameValidator;
    public Button btnDelete;

    private Table entity;
    private BooleanProperty isChanged;

    private static final double ROW_HEIGHT = 40;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllersManager.getInstance().setController(this);
        lvAttributes.setCellFactory(param -> new AttributeListCell(ROW_HEIGHT));
        lvAttributes.setItems(FXCollections.observableArrayList());
        initListViewListeners();
        initCmbBoxItems();
        initSettingsListeners();
    }

    private void initSettingsListeners() {
        isPrimaryKey.selectedProperty().addListener((observable, oldValue, newValue) -> {
            boolean isSelected = newValue;
            isNotNull.setDisable(isSelected);
            isUnique.setDisable(isSelected);
            isForeignKey.setDisable(isSelected);
            cmbDatatype.setDisable(isSelected);
            if (isSelected) {
                isNotNull.setSelected(false);
                isUnique.setSelected(false);
                isForeignKey.setSelected(false);
                cmbDatatype.getSelectionModel().select("INT");
            }
        });

        isForeignKey.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            boolean isSelected = newValue;
            isPrimaryKey.setDisable(isSelected);
            paneForeignKey.setDisable(!isSelected);
            cmbDatatype.setDisable(isSelected);
            if (isSelected) {
                isPrimaryKey.setSelected(false);
                cmbDatatype.getSelectionModel().select("INT");
            }
        }));

        cmbRefTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            boolean isEmpty = newValue == null;
            cmbOnDelete.setDisable(isEmpty);
            cmbOnUpdate.setDisable(isEmpty);
        }));


        tfName.textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                entity.setName(newValue);
                lblNameValidator.setVisible(false);
            } catch (SchemeException e) {
                lblNameValidator.setVisible(true);
            }
        }));
    }

    private void initCmbBoxItems() {
        //init options
        ObservableList<ForeignKeyOption> options = FXCollections.observableArrayList(ForeignKeyOption.values());
        cmbOnDelete.setItems(options);
        cmbOnUpdate.setItems(options);
        cmbOnDelete.getSelectionModel().select(ForeignKeyOption.NO_ACTION);
        cmbOnUpdate.getSelectionModel().select(ForeignKeyOption.NO_ACTION);

        cmbDatatype.setItems(FXCollections.observableArrayList(DatatypeMapper.listOfDatatypes()));
    }

    private void initRefTables() {
        Set<Table> tables = ProjectManager.getInstance().getSchema().getTables();
        ObservableList<String> tableNameList = FXCollections.observableArrayList(
                tables.stream().map(Table::getName).collect(Collectors.toList())
        );
        cmbRefTable.setItems(tableNameList);
    }

    private void initListViewListeners() {
        lvAttributes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && entity.getAllColumns().contains(oldValue)) {
                saveColumn(oldValue);
            }
            if (newValue != null && entity.getAllColumns().contains(newValue))  {
                btnDelete.setDisable(false);
                settingsPane.setDisable(false);
                clearSettings();
                loadColumn(newValue);
            }
        });
        //todo rename text field on double click
    }

    private void loadColumn(Column column) {
        paneForeignKey.setDisable(true);
        cmbOnDelete.setDisable(true);
        cmbOnUpdate.setDisable(true);

        isPrimaryKey.setSelected(column.isPK());
        isForeignKey.setSelected(column instanceof ForeignKey);
        isAutoIncrement.setSelected(column.isAutoIncrement());
        isNotNull.setSelected(column.isPK() || column.isNotNull());
        isUnique.setSelected(column.isPK() || column.isUnique());

        cmbDatatype.getSelectionModel().select(column.getDatatype() == null &&
                cmbDatatype.getSelectionModel().isEmpty() ? "VARCHAR" : column.getDatatype());
        txtDefault.setText(String.valueOf(column.getDefValue()).equals("null") ? "" : String.valueOf(column.getDefValue()));
        //todo check size min max

        if (column instanceof ForeignKey) {
            ForeignKey foreignKey = (ForeignKey) column;
            cmbRefTable.getSelectionModel().select(foreignKey.getReferencedTableName());
            cmbOnDelete.getSelectionModel().select(foreignKey.getOnDelete());
            cmbOnUpdate.getSelectionModel().select(foreignKey.getOnUpdate());
        }

    }

    private void saveColumn(Column column) {
        column.setPK(isPrimaryKey.isSelected());
        column.setNotNull(isNotNull.isSelected());
        column.setAutoIncrement(isAutoIncrement.isSelected());
        column.setUnique(isUnique.isSelected());

        try {
            //todo datatype not selected popup
            column.setDatatype(cmbDatatype.getValue());
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        column.setDefValue(txtDefault.getText().equals("") ? null : txtDefault.getText());

        if (isForeignKey.isSelected()) {
            ForeignKey foreignKey;
            if (column instanceof ForeignKey) {
                foreignKey = (ForeignKey) column;
            } else {
                foreignKey = new ForeignKey(column);
                entity.getColumns().remove(column);
                entity.getForeignKeys().add(foreignKey);
            }

            foreignKey.setReferencedTable(cmbRefTable.getValue());
            foreignKey.setOnUpdate(cmbOnUpdate.getValue());
            foreignKey.setOnDelete(cmbOnDelete.getValue());
        }
    }

    public void cancel(ActionEvent actionEvent) {
        isChanged.setValue(false);
        ((Stage)((Button)actionEvent.getTarget()).getScene().getWindow()).close();
    }

    public void saveEntity(ActionEvent actionEvent) {
        isChanged.setValue(true);
        lvAttributes.getSelectionModel().clearSelection();
        try {
            entity.setName(tfName.getText());
            lblNameValidator.setVisible(false);
            ((Stage)((Button)actionEvent.getTarget()).getScene().getWindow()).close();
        } catch (SchemeException e) {
            lblNameValidator.setVisible(true);
        }
    }

    public void newAttribute(ActionEvent actionEvent) {
        Column column = entity.newColumn();
        lvAttributes.getItems().add(column);
        lvAttributes.getSelectionModel().select(column);
    }

    public void deleteAttribute(ActionEvent actionEvent) {
        //todo popup "Are u sure"
        Column column = lvAttributes.getSelectionModel().getSelectedItem();
        entity.removeColumn(column);
        lvAttributes.getItems().remove(column);
    }

    public void setEntity(Table entity) {
        settingsPane.setDisable(true);
        btnDelete.setDisable(true);
        clearSettings();
        this.entity = new Table(entity);
        tfName.setText(entity.getName());
        lvAttributes.getSelectionModel().clearSelection();
        lvAttributes.getItems().clear();
        lvAttributes.getItems().addAll(entity.getAllColumns());
        initRefTables();
    }

    private void clearSettings() {
        isPrimaryKey.setSelected(false);
        isForeignKey.setSelected(false);
        isNotNull.setSelected(false);
        isAutoIncrement.setSelected(false);
        isUnique.setSelected(false);
        cmbRefTable.getSelectionModel().clearSelection();
        cmbOnUpdate.getSelectionModel().clearSelection();
        cmbOnDelete.getSelectionModel().clearSelection();
        cmbDatatype.getSelectionModel().clearSelection();
        txtDataSize.clear();
        txtDataMin.clear();
        txtDataMax.clear();
        txtDefault.clear();
    }

    public void setIsChanged(BooleanProperty isChanged) {
        this.isChanged = isChanged;
    }

    public Table getEntity() {
        return entity;
    }
}
