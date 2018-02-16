package com.nure.model.schema.table;

/**
 * Created by Vadim_ on 01.02.2018.
 */
public enum ForeignKeyOption {
    RESTRICT("RESTRICT"), NO_ACTION("NO ACTION"), CASCADE("CASCADE"), SET_NULL("SET NULL");

    private String sql;

    ForeignKeyOption(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return sql;
    }
}
