package com.nure.model.sql;

import com.nure.model.schema.Schema;
import com.nure.model.schema.table.Table;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public interface SchemeBuilder extends Builder {
    void forScheme(Schema schema);

    SchemeBuilder setName(String name);

    SchemeBuilder addTable(Table table);

    SchemeBuilder addTable(TableBuilder table);
}
