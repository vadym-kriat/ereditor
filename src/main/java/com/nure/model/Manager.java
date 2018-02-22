package com.nure.model;

import com.nure.model.sql.Dialect;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public interface Manager {
    void exportSchemeToFile(String fileName) throws Exception;

    void loadSchemeFromFile(String fileName) throws Exception;

    String generateDDLSQLQuery(Dialect dialect) throws Exception;
}
