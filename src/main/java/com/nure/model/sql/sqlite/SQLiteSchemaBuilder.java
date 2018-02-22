package com.nure.model.sql.sqlite;

import com.nure.model.sql.AbstractSchemaBuilder;
import com.nure.model.sql.TableBuilder;

import java.util.stream.Collectors;

/**
 * Created by Vadim_ on 19.02.2018.
 */
class SQLiteSchemaBuilder extends AbstractSchemaBuilder {

    @Override
    public String buildQuery() {
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            separator.append(LINE_SEP);
        }
        return String.join(separator, tables.stream().map(table -> {
            TableBuilder tableBuilder = new SQLiteTableBuilder();
            tableBuilder.forTable(table);
            return tableBuilder.buildQuery();
        }).collect(Collectors.toList()));
    }
}
