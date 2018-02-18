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
    protected Object defaultValue;

    protected static final String SPACE = " ";
    protected static final String LINE_SEP = System.lineSeparator();
    protected static final String COMMA = ",";

    protected AbstractColumnBuilder() {
        constraints = new HashSet<>();
        customConstraint = new HashSet<>();
    }

    @Override
    public void forColumn(Column column) {
        this.name = column.getName();
        if (column.isPK()) {
            constraints.add(ColumnConstraint.PRIMARY_KEY);
        }
        if (column.isAutoIncrement()) {
            constraints.add(ColumnConstraint.AUTO_INCREMENT);
        }
        if (column.isNotNull()) {
            constraints.add(ColumnConstraint.NOT_NULL);
        }
        if (column.isUnique()) {
            constraints.add(ColumnConstraint.UNIQUE);
        }
        if (column.isUnsigned()) {
            constraints.add(ColumnConstraint.UNSIGNED);
        }
        if (column.isZeroFill()) {
            constraints.add(ColumnConstraint.ZERO_FILL);
        }
        this.datatype = column.getDatatype();
        this.defaultValue = column.getDefValue();
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
    public ColumnBuilder addDefaultValue(Object defaultValue) {
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
