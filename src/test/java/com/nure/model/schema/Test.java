package com.nure.model.schema;

import com.nure.model.schema.table.Column;
import com.nure.model.sql.ColumnBuilder;
import com.nure.model.sql.Dialect;
import com.nure.model.sql.SQLQueryBuilderFactory;
import com.nure.model.sql.exceptions.NoSuchFactoryException;

import java.sql.Date;

/**
 * Created by Vadim_ on 18.02.2018.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ColumnBuilder columnBuilder = SQLQueryBuilderFactory.newInstance(Dialect.SQLITE).getColumnBuilder();
        Column column = new Column("date");
        column.setDatatype("DATE");
        column.setDefValue(new Date(System.currentTimeMillis()));
        column.setNotNull(true);
        columnBuilder.forColumn(column);
        System.out.println(columnBuilder.buildQuery());
    }
}
