package com.nure.model.io;

import com.nure.model.schema.Schema;

/**
 * Created by Vadim_ on 22.02.2018.
 */
public class SchemaFileManager {
    private String pathname;

    public SchemaFileManager(String pathname) {
        this.pathname = pathname;
    }

    public void save(Schema schema) throws Exception {

    }

    public Schema load() throws Exception {
        return null;
    }
}
