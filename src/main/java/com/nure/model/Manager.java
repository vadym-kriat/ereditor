package com.nure.model;

import com.nure.model.sql.Dialect;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public interface Manager {
    void exportSchemeToXMLFile(String fileName);

    void loadSchemeFromXMLFile(String fileName);

    String generateDDLSQLQuery(Dialect dialect) throws Exception;
}
