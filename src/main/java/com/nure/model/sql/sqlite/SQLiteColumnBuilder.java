package com.nure.model.sql.sqlite;

import com.nure.model.sql.AbstractColumnBuilder;
import com.nure.model.sql.ColumnConstraint;
import com.nure.model.sql.Dialect;
import com.nure.model.util.DatatypeMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vadim_ on 17.02.2018.
 */
class SQLiteColumnBuilder extends AbstractColumnBuilder {

    @Override
    public String buildQuery() {
        StringBuilder sql = new StringBuilder();
        //add name of column
        sql.append(name).append(SPACE);
        //add sqlite data type
        sql.append(DatatypeMapper.getSpecificDatatypeFor(Dialect.SQLITE, datatype)).append(SPACE);
        //add constraint
        for (ColumnConstraint constraint : constraints) {
            sql.append(constraint).append(SPACE);
        }
        //add default data type
        if (defaultValue != null) {
            sql.append(ColumnConstraint.DEFAULT).append(SPACE);
            if (defaultValue instanceof String) {
                sql.append("\"").append(defaultValue).append("\"");
            } else if (defaultValue instanceof Date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss.SSS");
                sql.append("\"").append(format.format(defaultValue)).append("\"");
            } else {
                sql.append(defaultValue);
            }
            sql.append(SPACE);
        }
        return sql.toString();
    }
}
