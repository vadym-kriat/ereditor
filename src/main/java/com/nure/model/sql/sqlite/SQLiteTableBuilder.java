package com.nure.model.sql.sqlite;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.sql.AbstractTableBuilder;
import com.nure.model.sql.ColumnBuilder;
import com.nure.model.sql.ColumnConstraint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vadim_ on 18.02.2018.
 */
class SQLiteTableBuilder extends AbstractTableBuilder {

    private String[] buildColumnsSQL(List<Column> columns) {
        String[] declareColumns = new String[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            ColumnBuilder builder = new SQLiteColumnBuilder();
            builder.forColumn(columns.get(i));
            declareColumns[i] = builder.buildQuery();
        }
        return declareColumns;
    }

    private String[] buildForeignKeyConstraints(List<ForeignKey> keys) {
        String[] declareConstraint = new String[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            ForeignKey key = keys.get(i);
            StringBuilder sql = new StringBuilder();
            sql.append(String.format("%s (%s) %s %s (%s)",
                    ColumnConstraint.FOREIGN_KEY, key.getName(),
                    ColumnConstraint.REFERENCES, key.getReferencedTableName(),
                    key.getReferencedColumnName())).append(LINE_SEP);
            sql.append(String.format("%s %s %n%s %s",
                    ColumnConstraint.ON_UPDATE, key.getOnUpdate(),
                    ColumnConstraint.ON_DELETE, key.getOnDelete()));
            declareConstraint[i] = sql.toString();
        }
        return declareConstraint;
    }

    @Override
    public String buildQuery() {
        StringBuilder sql = new StringBuilder();
//        add name of table
        sql.append(CREATE_TABLE).append(SPACE).append(IF_NOT_EXISTS).append(SPACE)
                .append(name).append(SPACE);
//        open table body
        sql.append("(").append(LINE_SEP);
//        join all columns
        List<Column> allColumns = new ArrayList<>();
        allColumns.addAll(columns);
        allColumns.addAll(foreignKeys);
        String[] declareColumns = buildColumnsSQL(allColumns);
        String[] declareFKColumns = buildForeignKeyConstraints(foreignKeys);
//        collect declare columns and constraints
        String separatedDeclaration = String.join(COMMA + LINE_SEP,
                Stream.of(declareColumns, declareFKColumns)
                        .flatMap(Stream::of).collect(Collectors.toList()));
        sql.append(separatedDeclaration).append(LINE_SEP);
//        close table body
        sql.append(")").append(SEMICOLON).append(LINE_SEP);
        return sql.toString();
    }
}
