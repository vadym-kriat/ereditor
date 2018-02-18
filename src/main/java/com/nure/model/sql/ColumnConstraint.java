package com.nure.model.sql;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public enum ColumnConstraint {
    PRIMARY_KEY("PRIMARY KEY"), AUTO_INCREMENT("AUTO_INCREMENT"), NOT_NULL("NOT NULL"), UNIQUE("UNIQUE"),
    BINARY("BINARY"), UNSIGNED("UNSIGNED"), ZERO_FILL("ZERO FILL"), GENERATED("GENERATED"),
    FOREIGN_KEY("FOREIGN KEY"), REFERENCES("REFERENCES"),
    ON_DELETE("ON DELETE"), ON_UPDATE("ON UPDATE"), DEFAULT("DEFAULT");

    private String sql;

    ColumnConstraint(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return sql;
    }
}
