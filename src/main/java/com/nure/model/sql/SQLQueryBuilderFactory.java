package com.nure.model.sql;

import com.nure.model.sql.exceptions.NoSuchFactoryException;
import com.nure.model.sql.sqlite.SQLiteQueryBuilderFactory;

/**
 * Created by Vadim_ on 17.02.2018.
 */
public class SQLQueryBuilderFactory {
    public static QueryBuilderFactory newInstance(Dialect dialect) throws NoSuchFactoryException {
        if (dialect == Dialect.SQLITE) {
            return new SQLiteQueryBuilderFactory();
        }
        throw new NoSuchFactoryException();
    }
}
