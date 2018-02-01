package com.nure.model.schema.table;

/**
 * Created by Vadim_ on 01.02.2018.
 */
public enum ForeignKeyOption {
    RESTRICT("RESTRICT"), NO_ACTION("NO ACTION"), CASCADE("CASCADE"), SET_NULL("SET NULL");

    private String sqlTitle;

    ForeignKeyOption(String sqlTitle) {
        this.sqlTitle = sqlTitle;
    }

    @Override
    public String toString() {
        return sqlTitle;
    }
}
