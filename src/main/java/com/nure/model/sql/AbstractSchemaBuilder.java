package com.nure.model.sql;

import com.nure.model.schema.Schema;
import com.nure.model.schema.table.Table;

import java.util.List;

/**
 * Created by Vadim_ on 18.02.2018.
 */
public abstract class AbstractSchemaBuilder implements SchemaBuilder {
    private String name;
    private List<Table> tables;

    @Override
    public void forScheme(Schema schema) {

    }

    @Override
    public SchemaBuilder setName(String name) {
        return null;
    }

    @Override
    public SchemaBuilder addTable(Table table) {
        return null;
    }
}
