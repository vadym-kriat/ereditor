package com.nure.model.schema.table;

import com.nure.model.schema.util.NameValidator;

import javax.xml.bind.ValidationException;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class ForeignKey extends Column {
    private String fkName;
    private String referencedTable;
    private String referencedColumnName;
    private ForeignKeyOption onUpdate;
    private ForeignKeyOption onDelete;

    public ForeignKey() {
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) throws ValidationException {
        if (NameValidator.columnNameIsValid(fkName)) {
            this.fkName = fkName;
        } else {
            throw new ValidationException("Enter the name incorrectly.");
        }
    }

    public String getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(String referencedTable) {
        this.referencedTable = referencedTable;
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
    }

    public ForeignKeyOption getOnUpdate() {
        return onUpdate;
    }

    public void setOnUpdate(ForeignKeyOption onUpdate) {
        this.onUpdate = onUpdate;
    }

    public ForeignKeyOption getOnDelete() {
        return onDelete;
    }

    public void setOnDelete(ForeignKeyOption onDelete) {
        this.onDelete = onDelete;
    }
}
