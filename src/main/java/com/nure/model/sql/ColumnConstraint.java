package com.nure.model.sql;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public enum ColumnConstraint {
    PRIMARY_KEY, NOT_NULL, UNIQUE, BINARY,
    UNSIGNED, ZERO_FILL, AUTO_INCREMENT, GENERATED,
    FOREIGN_KEY, REFERENCES, ON_DELETE, ON_UPDATE
}
