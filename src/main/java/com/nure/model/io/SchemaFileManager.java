package com.nure.model.io;

import com.nure.model.schema.Schema;
import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.Table;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Vadim_ on 22.02.2018.
 */
public class SchemaFileManager {
    private String pathname;

    public SchemaFileManager(String pathname) {
        this.pathname = pathname;
    }

    public void save(Schema schema) throws Exception {
        File file = new File(pathname);
        JAXBContext context = JAXBContext.newInstance(
                Schema.class, Table.class, Column.class, ForeignKey.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(schema, file);
    }

    public Schema load() throws Exception {
        File file = new File(pathname);
        JAXBContext context = JAXBContext.newInstance(
                Schema.class, Table.class, Column.class, ForeignKey.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Schema schema = (Schema) unmarshaller.unmarshal(file);
        return schema;
    }
}
