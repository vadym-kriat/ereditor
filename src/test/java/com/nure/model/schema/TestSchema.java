package com.nure.model.schema;

import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vadim_ on 02.02.2018.
 */
public class TestSchema {
    private Schema schema;
    private Table table;
    private Table refTable;

    @Before
    public void setUp() throws SchemeException {
        schema = new Schema("schema");

        table = new Table("table");
        Column column = new Column("id").setPK(true);
        table.addColumn(column);

        refTable = new Table("ref_table");
        ForeignKey fk = new ForeignKey("sss", "fx");
        fk.setReferencedTable("table");
        fk.setReferencedColumnName("id");
        refTable.addForeignKey(fk);

        schema.addTable(table);
        schema.addTable(refTable);

    }

    @After
    public void tearDown() {
        schema = null;
        table = null;
        refTable = null;
    }

    @Test
    public void testCreateSchema() throws SchemeException {
        Schema schema = new Schema("new_schema");
        assertNotNull(schema);
    }

    @Test(expected = SchemeException.class)
    public void testCreateSchemaWithWrongName() throws SchemeException {
        Schema schema = new Schema("123@#$schema");
        assertNotNull(schema);
    }

    @Test
    public void testGetTableByIndex() throws SchemeException {
        Table table = new Table("table1");
        assertEquals(2, schema.sizeTables());
        schema.addTable(table);
        assertEquals(3, schema.sizeTables());
        assertEquals(table, schema.getTable(2));
    }

    @Test
    public void testRemoveReferencedTable() {
        assertEquals(2, schema.sizeTables());
        assertEquals(1, refTable.sizeForeignKeys());
        assertEquals(0, refTable.sizeColumns());
        schema.removeTable(table);
        assertEquals(1, schema.sizeTables());
        assertEquals(0, refTable.sizeForeignKeys());
        assertEquals(1, refTable.sizeColumns());
    }

    @Test
    public void testRemoveTable() {
        assertEquals(2, schema.sizeTables());
        schema.removeTable(refTable);
        assertEquals(1, schema.sizeTables());
        assertNull(schema.getTable(1));
    }
}
