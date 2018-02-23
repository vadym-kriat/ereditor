package com.nure.model.util;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.Table;

public class Util {
    public static int getMaxLenghtAttrNameSize(Table entity) {
        String name = "";
        for (Column column : entity.getAllColumns()) {
            name = column.getName().length() > name.length() ? column.getName() : name;
        }
        return name.length();
    }
}
