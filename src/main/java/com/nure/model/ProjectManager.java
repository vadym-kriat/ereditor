package com.nure.model;

import com.nure.model.schema.Schema;
import com.nure.model.schema.exceptions.CreateSchemaException;
import com.nure.model.schema.exceptions.SchemeException;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class ProjectManager {
    private static volatile ProjectManager instance;

    public static ProjectManager getInstance() {
        if (instance == null) {
            synchronized (ProjectManager.class) {
                if (instance == null) {
                    instance = new ProjectManager();
                }
            }
        }
        return instance;
    }

    private Schema schema;

    private ProjectManager() {

    }

    public void createNewSchema(String name) throws SchemeException {
        if (schema != null) {
            throw new CreateSchemaException("Schema already created.");
        }
        this.schema = new Schema(name);
    }

    public void exportSchemaToXMLFile(String fileName) {
        //todo create method
    }

    public void loadSchemeFromXMLFile(String fileName) {
        //todo create method
    }

    public Schema getSchema() {
        return schema;
    }
}
