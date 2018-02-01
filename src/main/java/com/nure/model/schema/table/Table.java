package com.nure.model.schema.table;

import com.nure.model.schema.exceptions.ColumnIsExistException;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.util.NameValidator;

import javax.xml.bind.ValidationException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class Table {
    private String name;
    private Set<Column> columns;
    private Set<ForeignKey> foreignKeys;

    public Table() {
        columns = new LinkedHashSet<>();
        foreignKeys = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (NameValidator.tableNameIsValid(name)) {
            this.name = name;
        } else {
            throw new ValidationException("The table name incorrectly.");
        }
    }

    public void addColumn(Column column) throws SchemeException {
        if (!columns.add(column)) {
            throw new ColumnIsExistException("Name of columns are duplicated.");
        }
    }

    public void removeColumn(Column column) {
        columns.remove(column);
    }

    public void addForeignKey(ForeignKey key) throws ColumnIsExistException {
        if (!foreignKeys.add(key)) {
            throw new ColumnIsExistException("Name of columns are duplicated.");
        }
    }

    public void removeForeignKey(ForeignKey key) {
        foreignKeys.remove(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return name.equals(table.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Removes invalid foreign keys from set and
     * wrap them in simple columns adding them to set of columns.
     *
     * @param tableName the name of the table for which the keys are reset.
     */
    public void resetForeignKeyForTable(String tableName) {
        List<ForeignKey> removedForeignKey = foreignKeys.stream().filter(
                key -> key.getReferencedTableName().equals(tableName))
                .collect(Collectors.toList());
        foreignKeys.removeAll(removedForeignKey);
        columns.addAll(removedForeignKey.stream().map(Column::new).collect(Collectors.toList()));
    }
}
