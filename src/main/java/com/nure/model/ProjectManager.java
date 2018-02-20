package com.nure.model;

import com.nure.model.schema.Schema;
import com.nure.model.schema.exceptions.CreateSchemaException;
import com.nure.model.schema.exceptions.SchemeException;
import com.nure.model.sql.Dialect;
import com.nure.model.sql.SQLQueryBuilderFactory;
import com.nure.model.sql.SchemaBuilder;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class ProjectManager implements Manager {
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

    public Schema getSchema() {
        return schema;
    }

    @Override
    public void exportSchemeToXMLFile(String fileName) {

    }

    @Override
    public void loadSchemeFromXMLFile(String fileName) {

    }

    @Override
    public String generateDDLSQLQuery(Dialect dialect) throws Exception {
        SchemaBuilder schemaBuilder = SQLQueryBuilderFactory.newInstance(dialect).getSchemeBuilder();
        schemaBuilder.forScheme(schema);
        return schemaBuilder.buildQuery();
    }
}
