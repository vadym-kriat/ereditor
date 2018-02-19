package com.nure.model.sql;

import com.nure.model.schema.Schema;
import com.nure.model.schema.table.Table;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public interface SchemaBuilder extends Builder {
    void forScheme(Schema schema);

    SchemaBuilder setName(String name);

    SchemaBuilder addTable(Table table);
}
