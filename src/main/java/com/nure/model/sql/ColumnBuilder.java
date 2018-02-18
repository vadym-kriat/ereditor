package com.nure.model.sql;

import com.nure.model.schema.table.Column;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public interface ColumnBuilder extends Builder {
    void forColumn(Column column);

    ColumnBuilder setName(String name);

    ColumnBuilder addConstraint(ColumnConstraint constraint);

    ColumnBuilder addDefaultValue(Object defaultValue);

    ColumnBuilder setCustomDatatype(String datatype);

    ColumnBuilder addCustomConstraint(String constraint);
}
