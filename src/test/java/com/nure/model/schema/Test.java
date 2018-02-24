package com.nure.model.schema;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.Table;

/**
 * Created by Vadim_ on 18.02.2018.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema("new_schema");

        Table table1 = new Table("temp");
        Column column = new Column("temp1");
        column.setDatatype("INT").setPK(true);
        table1.addColumn(column);

        Table table = new Table(table1);
    }
}
