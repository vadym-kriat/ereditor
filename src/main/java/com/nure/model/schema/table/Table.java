package com.nure.model.schema.table;

import com.nure.model.schema.table.exceptions.ColumnIsExistException;
import com.nure.model.schema.table.exceptions.TablePropertyException;
import com.nure.model.schema.util.NameValidator;

import javax.xml.bind.ValidationException;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public void addColumn(Column column) throws TablePropertyException {
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

}
