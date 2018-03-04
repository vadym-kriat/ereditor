package com.nure.model.schema;

import com.nure.model.schema.exceptions.IncorrectNameException;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.exceptions.TableIsExistException;
import com.nure.model.schema.table.Table;
import com.nure.model.util.NameValidator;
import com.nure.model.util.Sets;

import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by Vadim_ on 31.01.2018.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "schema")
public class Schema {
    private static AtomicInteger id;
    @XmlElement(name = "name")
    private String name;
    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
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

    public Schema(String name) throws SchemeException {
        this();
        setName(name);
    }

    public Table newTable() {
        Table table = new Table();
        try {
            table.setName(String.format("%s%d", properties.getProperty("names.default.table.name"),
                    id.incrementAndGet()));
        } catch (SchemeException e) {
            throw new RuntimeException(e);
        }
        tables.add(table);
        return table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SchemeException {
        if (NameValidator.schemeNameIsValid(name)) {
            this.name = name;
        } else {
            throw new IncorrectNameException("The scheme name incorrectly.");
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

    public Set<Table> getTables() {
        return tables;
    }

    public List<Table> listOfTables() {
        return new ArrayList<>(tables);
    }

    public int sizeTables() {
        return tables.size();
    }

    public void updateTable(Table oldTable, Table newTable) {
        //todo if args null
        if (oldTable == null || newTable == null)
            throw new NullPointerException("Can't update table.");
        if (tables.contains(oldTable)) {
            try {
                oldTable.setName(newTable.getName());
            } catch (SchemeException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }
}
