package com.nure.model.sql;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim_ on 18.02.2018.
 */
public abstract class AbstractTableBuilder implements TableBuilder {

    protected String name;
    protected List<Column> columns;
    protected List<ColumnBuilder> columnBuilders;
    protected List<ForeignKey> foreignKeys;

    protected static final String CREATE_TABLE = "CREATE TABLE";
    protected static final String IF_NOT_EXISTS = "IF NOT EXISTS";
    protected static final String LINE_SEP = System.lineSeparator();
    protected static final String SPACE = " ";
    protected static final String SEMICOLON = ";";
    protected static final String COMMA = ",";

    protected AbstractTableBuilder() {
        columns = new ArrayList<>();
        columnBuilders = new ArrayList<>();
        foreignKeys = new ArrayList<>();
    }

    @Override
    public void forTable(Table table) {
        this.name = table.getName();
        this.columns.addAll(table.listOfColumns());
        this.foreignKeys.addAll(table.listOfForeignKeys());
    }

    @Override
    public TableBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TableBuilder addColumn(Column column) {
        this.columns.add(column);
        return this;
    }

    @Override
    public TableBuilder addColumn(ColumnBuilder column) {
        this.columnBuilders.add(column);
        return this;
    }

    @Override
    public TableBuilder addForeignKey(ForeignKey foreignKey) {
        this.foreignKeys.add(foreignKey);
        return this;
    }
}
