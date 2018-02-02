package com.nure.model.schema.table;

import com.nure.model.schema.exceptions.IncorrectNameException;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.schema.util.NameValidator;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class ForeignKey extends Column {
    private String fkName;
    private String referencedTable;
    private String referencedColumnName;
    private ForeignKeyOption onUpdate;
    private ForeignKeyOption onDelete;

    public ForeignKey(String name, String fkName) throws SchemeException {
        super(name);
        setFkName(fkName);
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) throws SchemeException {
        if (NameValidator.columnNameIsValid(fkName)) {
            this.fkName = fkName;
        } else {
            throw new IncorrectNameException("Enter the name incorrectly.");
        }
    }

    public String getReferencedTableName() {
        return referencedTable;
    }

    public ForeignKey setReferencedTable(String referencedTable) {
        this.referencedTable = referencedTable;
        return this;
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public ForeignKey setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
        return this;
    }

    public ForeignKeyOption getOnUpdate() {
        return onUpdate;
    }

    public ForeignKey setOnUpdate(ForeignKeyOption onUpdate) {
        this.onUpdate = onUpdate;
        return this;
    }

    public ForeignKeyOption getOnDelete() {
        return onDelete;
    }

    public ForeignKey setOnDelete(ForeignKeyOption onDelete) {
        this.onDelete = onDelete;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ForeignKey that = (ForeignKey) o;

        return fkName.equals(that.fkName) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + fkName.hashCode();
        result += super.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ForeignKey{" +
                "fkName='" + fkName + '\'' +
                ", referencedTable='" + referencedTable + '\'' +
                ", referencedColumnName='" + referencedColumnName + '\'' +
                ", onUpdate=" + onUpdate +
                ", onDelete=" + onDelete +
                '}';
    }
}
