package com.nure.model.sql;

/**
 * Created by Vadim_ on 17.02.2018.
 */
public interface QueryBuilderFactory {

    SchemaBuilder getSchemeBuilder();

    TableBuilder getTableBuilder();

    ColumnBuilder getColumnBuilder();
}
