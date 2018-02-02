package com.nure.model.schema;

import com.nure.model.schema.exceptions.TableIsExistException;
import com.nure.model.schema.table.Table;
import com.nure.model.schema.util.NameValidator;
import com.nure.model.schema.util.Sets;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class Schema {
    private static AtomicInteger id;
    private String name;
    private Set<Table> tables;
    private static Properties properties;

    static {
        id = new AtomicInteger(0);
        properties = new Properties();
        try {
            properties.load(Schema.class.
                    getClassLoader().getResourceAsStream("conf/text.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Schema() {
        this.tables = new LinkedHashSet<>();
    }

    public Schema(String name) throws ValidationException {
        this();
        setName(name);
    }

    public Table newTable() {
        Table table = new Table();
        try {
            table.setName(String.format("%s%d", properties.getProperty("names.default.table.name"),
                    id.incrementAndGet()));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        tables.add(table);
        return table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (NameValidator.schemeNameIsValid(name)) {
            this.name = name;
        } else {
            throw new ValidationException("The scheme name incorrectly.");
        }
    }

    public void addTable(Table table) throws TableIsExistException {
        if (!tables.add(table)) {
            throw new TableIsExistException("Name of columns are duplicated.");
        }
    }

    public void removeTable(Table table) {
        tables.forEach(tb -> tb.resetForeignKeyForTable(table.getName()));
        tables.remove(table);
    }

    public Table getTable(int index) {
        return Sets.getElementByIndex(tables, index);
    }
}
