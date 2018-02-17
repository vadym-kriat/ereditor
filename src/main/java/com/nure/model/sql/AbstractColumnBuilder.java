package com.nure.model.sql;

import com.nure.model.schema.table.Column;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vadim_ on 17.02.2018.
 */
public abstract class AbstractColumnBuilder implements ColumnBuilder {

    protected String name;
    protected Set<ColumnConstraint> constraints;
    protected Set<String> customConstraint;
    protected String datatype;
    protected String defaultValue;

    protected AbstractColumnBuilder() {
        constraints = new HashSet<>();
    }

    @Override
    public void forColumn(Column column) {
        this.name = column.getName();

    }

    @Override
    public ColumnBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ColumnBuilder addConstraint(ColumnConstraint constraint) {
        constraints.add(constraint);
        return this;
    }

    @Override
    public ColumnBuilder addDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public ColumnBuilder setCustomDatatype(String datatype) {
        this.datatype = datatype;
        return this;
    }

    @Override
    public ColumnBuilder addCustomConstraint(String constraint) {
        customConstraint.add(constraint);
        return this;
    }
}
