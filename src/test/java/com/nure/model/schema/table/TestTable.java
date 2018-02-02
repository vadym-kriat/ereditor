package com.nure.model.schema.table;

import com.nure.model.schema.exceptions.SchemeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Vadim_ on 02.02.2018.
 */
public class TestTable {

    private Table table;

    @Before
    public void setUp() throws SchemeException {
        table = new Table("table");
        table.addColumn(new Column("column1"));
        table.addColumn(new Column("column2"));
    }

    @After
    public void tearDown() {
        table = null;
    }

    @Test
    public void testCreateTable() throws SchemeException {
        Table table = new Table("table");
        assertNotNull(table);
    }

    @Test(expected = SchemeException.class)
    public void testCreateTableWithWrongName() throws SchemeException {
        Table table = new Table("111table");
    }

    @Test
    public void testGetColumnByIndex() throws SchemeException {
        assertEquals(2, table.sizeColumns());
        Column column = new Column("column3");
        table.addColumn(column);
        assertEquals(3, table.sizeColumns());
        assertEquals(column, table.getColumn(2));
    }

    @Test
    public void testGetForeignKeyByIndex() throws SchemeException {
        assertEquals(0, table.sizeForeignKeys());
        ForeignKey foreignKey = new ForeignKey("foreignKey", "table");
        table.addForeignKey(foreignKey);
        assertEquals(1, table.sizeForeignKeys());
        assertEquals(foreignKey, table.getForeignKey(0));
    }
}
