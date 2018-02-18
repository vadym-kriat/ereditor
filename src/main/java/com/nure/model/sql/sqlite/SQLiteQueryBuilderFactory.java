package com.nure.model.sql.sqlite;

import com.nure.model.sql.*;

/**
 * Created by Vadim_ on 17.02.2018.
 */
public class SQLiteQueryBuilderFactory implements QueryBuilderFactory {
    @Override
    public SchemaBuilder getSchemeBuilder() {
        return null;
    }

    @Override
    public TableBuilder getTableBuilder() {
        return null;
    }

    @Override
    public ColumnBuilder getColumnBuilder() {
        return new SQLiteColumnBuilder();
    }

    @Override
    public ForeignKeyBuilder getForeignKeyBuilder() {
        return null;
    }
}
