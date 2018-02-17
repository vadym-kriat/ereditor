package com.nure.model.sql;

/**
 * Created by Vadim_ on 17.02.2018.
 */
public interface QueryBuilderFactory {

    SchemeBuilder getSchemeBuilder();

    TableBuilder getTableBuilder();

    ColumnBuilder getColumnBuilder();

    ForeignKeyBuilder getForeignKeyBuilder();
}
