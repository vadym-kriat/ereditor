package com.nure.model.schema;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.ForeignKeyOption;
import com.nure.model.schema.table.Table;
import com.nure.model.sql.Dialect;
import com.nure.model.sql.SQLQueryBuilderFactory;
import com.nure.model.sql.TableBuilder;

/**
 * Created by Vadim_ on 18.02.2018.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        TableBuilder tableBuilder = SQLQueryBuilderFactory.newInstance(Dialect.SQLITE).getTableBuilder();
        Table table = new Table("suppliers");
        Column column1 = new Column("supplier_id");
        column1.setDatatype("INT").setPK(true);
        Column column2 = new Column("supplier_name");
        column2.setDatatype("VARCHAR").setNotNull(true);
        Column column3 = new Column("group_id");

        ForeignKey key1 = new ForeignKey("ref", "ref_id");
        key1.setDatatype("INT");
        key1.setReferencedTable("user").setReferencedColumnName("user_id")
                .setOnUpdate(ForeignKeyOption.CASCADE)
                .setOnDelete(ForeignKeyOption.RESTRICT);

        ForeignKey key2 = new ForeignKey("ref1", "ref_id1");
        key2.setDatatype("INT");
        key2.setReferencedTable("user").setReferencedColumnName("user_id");
        table.addForeignKey(key1);
        table.addForeignKey(key2);

        column3.setDatatype("INT").setNotNull(true);
        table.addColumn(column1);
        table.addColumn(column2);
        table.addColumn(column3);
        tableBuilder.forTable(table);
        System.out.println(tableBuilder.buildQuery());
    }
}
