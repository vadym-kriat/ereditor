package com.nure.model.sql;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public interface TableBuilder extends Builder {
    void forTable(Table table);

    TableBuilder setName(String name);

    TableBuilder addColumn(Column column);

    TableBuilder addColumn(ColumnBuilder column);

    TableBuilder addForeignKey(ForeignKey foreignKey);

    TableBuilder addForeignKey(ForeignKeyBuilder foreignKey);
}
