package com.nure.model.schema.table;

import com.nure.model.schema.exceptions.ColumnIsExistException;
import com.nure.model.schema.exceptions.IncorrectNameException;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.util.NameValidator;
import com.nure.model.util.Sets;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vadim_ on 31.01.2018.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
    @XmlElement(name = "name")
    private String name;
    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    private Set<Column> columns;
    @XmlElementWrapper(name = "keys")
    @XmlElement(name = "foreignKeys")
    private Set<ForeignKey> foreignKeys;

    public Table() {
        name = "";
        columns = new LinkedHashSet<>();
        foreignKeys = new LinkedHashSet<>();
    }

    public Table(String name) throws SchemeException {
        this();
        setName(name);
    }

    public Table(Table table) {
        super();
        this.name = table.getName();
        columns.addAll(table.getColumns().stream().map(
                column -> column = new Column(column)).collect(Collectors.toList()));
        foreignKeys.addAll(table.getForeignKeys().stream().map(
                key -> key = new ForeignKey(key)).collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SchemeException {
        if (NameValidator.tableNameIsValid(name)) {
            this.name = name;
        } else {
            throw new IncorrectNameException("The table name is incorrect.");
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

    public Set<Column> getColumns() {
        return columns;
    }

    public Set<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public List<Column> listOfColumns() {
        return new ArrayList<>(columns);
    }

    public List<ForeignKey> listOfForeignKeys() {
        return new ArrayList<>(foreignKeys);
    }

    public Column getColumn(int index) {
        return Sets.getElementByIndex(columns, index);
    }

    public ForeignKey getForeignKey(int index) {
        return Sets.getElementByIndex(foreignKeys, index);
    }

    public int sizeColumns() {
        return columns.size();
    }

    public int sizeForeignKeys() {
        return foreignKeys.size();
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
        if (!foreignKeys.isEmpty()) {
            List<ForeignKey> removedForeignKey = foreignKeys.stream().filter(
                    key -> key.getReferencedTableName().equals(tableName))
                    .collect(Collectors.toList());
            foreignKeys.removeAll(removedForeignKey);
            columns.addAll(removedForeignKey.stream().map(Column::new).collect(Collectors.toList()));
        }
    }

    public List<Column> getAllColumns() {
        List<Column> columns = new ArrayList<>(listOfColumns());
        columns.addAll(listOfForeignKeys());
        return columns;
    }
}
