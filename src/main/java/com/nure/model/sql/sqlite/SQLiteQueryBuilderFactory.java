package com.nure.model.sql.sqlite;

import com.nure.model.sql.*;

/**
 * Created by Vadim_ on 17.02.2018.
 */
class SQLiteQueryBuilderFactory implements QueryBuilderFactory {
    @Override
    public SchemaBuilder getSchemeBuilder() {
        return new SQLiteSchemaBuilder();
    }

    @Override
    public TableBuilder getTableBuilder() {
        return new SQLiteTableBuilder();
    }

    @Override
    public ColumnBuilder getColumnBuilder() {
        return new SQLiteColumnBuilder();
    }
}
