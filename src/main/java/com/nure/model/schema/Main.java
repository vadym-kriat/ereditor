package com.nure.model.schema;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;

import javax.xml.bind.ValidationException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Vadim_ on 01.02.2018.
 */
public class Main {
    public static void main(String[] args) throws ValidationException {
        Set<Column> columns = new LinkedHashSet<>();
        columns.add(new Column("column"));

        Set<ForeignKey> keys = new LinkedHashSet<>();
        keys.add(new ForeignKey("foreign_key", "foreign_key1"));

        List<Column> ss = keys.stream().map(Column::new).collect(Collectors.toList());

        System.out.println(ss);
    }
}
