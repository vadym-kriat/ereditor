package com.nure.model.schema.table.exceptions;

/**
 * Created by Vadim_ on 01.02.2018.
 */
public class ColumnIsExistException extends SchemePropertyException {
    public ColumnIsExistException(String message) {
        super(message);
    }
}