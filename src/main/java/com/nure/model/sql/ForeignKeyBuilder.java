package com.nure.model.sql;

import com.nure.model.schema.table.ForeignKey;
import com.nure.model.schema.table.ForeignKeyOption;

/**
 * Created by Vadim_ on 16.02.2018.
 */
public interface ForeignKeyBuilder extends Builder {
    void forForeignKey(ForeignKey foreignKey);

    ForeignKeyBuilder setForeignKeyName(String foreignKeyName);

    ForeignKeyBuilder setRefTableName(String tableName);

    ForeignKeyBuilder setRefColumnName(String columnName);

    ForeignKeyBuilder addOnUpdateOption(ForeignKeyOption option);

    ForeignKeyBuilder addOnDeleteOption(ForeignKeyOption option);
}
