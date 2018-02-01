package com.nure.model.schema.table;

import com.nure.model.schema.util.DatatypeLoader;
import com.nure.model.schema.util.NameValidator;

import javax.xml.bind.ValidationException;

/**
 * Created by Vadim_ on 31.01.2018.
 */
//todo make type of data
public class Column {
    private String name;
    private String datatype;
    private Object defValue;
    private boolean isPK;
    private boolean isNotNull;
    private boolean isUnique;
    private boolean isUnsigned;
    private boolean isZeroFill;

    public Column() {
    }

    public String getName() {
        return name;
    }

    public Column setName(String name) throws ValidationException {
        if (NameValidator.columnNameIsValid(name)) {
            this.name = name;
            return this;
        } else {
            throw new ValidationException("Enter the name incorrectly.");
        }
    }

    public String getDatatype() {
        return datatype;
    }

    public Column setDatatype(String datatype) throws ValidationException {
        if (DatatypeLoader.datatypeIsExist(datatype)) {
            this.datatype = datatype;
            return this;
        } else {
            throw new ValidationException("This type of data does not exist.");
        }
    }

    public Object getDefValue() {
        return defValue;
    }

    public Column setDefValue(Object defValue) {
        this.defValue = defValue;
        return this;
    }

    public boolean isPK() {
        return isPK;
    }

    public Column setPK(boolean PK) {
        isPK = PK;
        return this;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public Column setNotNull(boolean notNull) {
        isNotNull = notNull;
        return this;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public Column setUnique(boolean unique) {
        isUnique = unique;
        return this;
    }

    public boolean isUnsigned() {
        return isUnsigned;
    }

    public Column setUnsigned(boolean unsigned) {
        isUnsigned = unsigned;
        return this;
    }

    public boolean isZeroFill() {
        return isZeroFill;
    }

    public Column setZeroFill(boolean zeroFill) {
        isZeroFill = zeroFill;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column column = (Column) o;

        return name.equals(column.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
