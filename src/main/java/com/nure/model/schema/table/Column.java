package com.nure.model.schema.table;

import com.nure.model.schema.exceptions.IncorrectNameException;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.util.DatatypeMapper;
import com.nure.model.util.NameValidator;

import javax.xml.bind.ValidationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Vadim_ on 31.01.2018.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "datatype")
    private String datatype;
    @XmlElement(name = "defaultValue")
    private Object defValue;
    @XmlAttribute(name = "isPrimaryKey")
    private boolean isPK;
    @XmlAttribute(name = "isAutoIncrement")
    private boolean isAutoIncrement;
    @XmlAttribute(name = "isNotNull")
    private boolean isNotNull;
    @XmlAttribute(name = "isUnique")
    private boolean isUnique;
    @XmlAttribute(name = "isUnsigned")
    private boolean isUnsigned;
    @XmlAttribute(name = "isZeroFill")
    private boolean isZeroFill;

    public Column() {
    }

    public Column(String name) throws SchemeException {
        setName(name);
    }

    public Column(ForeignKey key) {
        this.name = key.getName();
        this.datatype = key.getDatatype();
        this.defValue = key.getDefValue();
        this.isPK = key.isPK();
        this.isAutoIncrement = key.isAutoIncrement();
        this.isNotNull = key.isNotNull();
        this.isUnique = key.isUnique();
        this.isUnsigned = key.isUnsigned();
        this.isZeroFill = key.isZeroFill();
    }

    public String getName() {
        return name;
    }

    public Column setName(String name) throws SchemeException {
        if (NameValidator.columnNameIsValid(name)) {
            this.name = name;
            return this;
        } else {
            throw new IncorrectNameException("Enter the name incorrectly.");
        }
    }

    public String getDatatype() {
        return datatype;
    }

    public Column setDatatype(String datatype) throws ValidationException {
        if (DatatypeMapper.datatypeIsExist(datatype)) {
            this.datatype = datatype;
            return this;
        } else {
            throw new ValidationException("This type of data does not exist: " + datatype);
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

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
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

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", datatype='" + datatype + '\'' +
                ", defValue=" + defValue +
                ", isPK=" + isPK +
                ", isNotNull=" + isNotNull +
                ", isUnique=" + isUnique +
                ", isUnsigned=" + isUnsigned +
                ", isZeroFill=" + isZeroFill +
                '}';
    }
}
