package com.nure.model.sql;

import com.nure.model.schema.Schema;
import com.nure.model.schema.table.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim_ on 18.02.2018.
 */
public abstract class AbstractSchemaBuilder implements SchemaBuilder {
    protected String name;
    protected List<Table> tables;

    protected static final String SPACE = " ";
    protected static final String LINE_SEP = System.lineSeparator();

    protected AbstractSchemaBuilder() {
        this.tables = new ArrayList<>();
    }

    @Override
    public void forScheme(Schema schema) {
        this.name = schema.getName();
        this.tables = schema.listOfTables();
    }

    @Override
    public SchemaBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public SchemaBuilder addTable(Table table) {
        this.tables.add(table);
        return this;
    }
}
