/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.db;

import com.speedment.annotation.Api;
import com.speedment.config.db.Column;
import com.speedment.config.db.PrimaryKeyColumn;
import com.speedment.config.db.Schema;
import com.speedment.config.db.Table;
import com.speedment.exception.SpeedmentException;
import com.speedment.field.FieldIdentifier;
import java.util.Set;

/**
 * Regulates how the full name of a database entity is constructed.
 *
 * @author Emil Forslund
 * @since 2.3
 */
@Api(version = "2.3")
public interface DatabaseNamingConvention {

    /**
     * Returns the full name used in the database for the specified parameters.
     * This is typically constructed by combining the schema, table and the
     * column name with a separator, but that might be different in different
     * implementations.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @param columnName column name
     * @return the full name
     */
    String fullNameOf(String schemaName, String tableName, String columnName);

    /**
     * Returns the full name used in the database for the specified parameters.
     * This is typically constructed by combining the schema and the table name
     * with a separator, but that might be different in different
     * implementations.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @return the full name
     */
    String fullNameOf(String schemaName, String tableName);

    /**
     * Returns the full name used in the database for the specified
     * FieldIdentifier. This is typically constructed by combining the schema,
     * table and column name with a separator, but that might be different in
     * different implementations.
     *
     * @param fieldIdentifier to use
     * @return the full name
     */
    default String fullNameOf(FieldIdentifier<?> fieldIdentifier) {
        return fullNameOf(fieldIdentifier.schemaName(), fieldIdentifier.tableName(), fieldIdentifier.columnName());
    }

    /**
     * Returns the full name used in the database for the specified
     * {@link PrimaryKeyColumn}. This is typically constructed by combining the
     * table and the column name with a separator, but that might be different
     * in different implementations.
     *
     * @param pkc the primary key column to retrieve the name of
     * @return the full name
     */
    default String fullNameOf(PrimaryKeyColumn pkc) {
        return fullNameOf(pkc.findColumn().orElseThrow(() -> new SpeedmentException("Cannot find column for " + pkc)));
    }

    /**
     * Returns the full name used in the database for the specified
     * {@link Column}. This is typically constructed by combining the table and
     * the column name with a separator, but that might be different in
     * different implementations.
     *
     * @param column the column to retrieve the name of
     * @return the full name
     */
    default String fullNameOf(Column column) {
        final Table table = column.getParentOrThrow();
        final Schema schema = table.getParentOrThrow();
        return fullNameOf(schema.getName(), table.getName(), column.getName());
    }

    /**
     * Returns the full name used in the database for the specified
     * {@link Table}. This is typically constructed by combining the schema and
     * the table name with a separator, but that might be different in different
     * implementations.
     *
     * @param table the table to retrieve the name of
     * @return the full name
     */
    default String fullNameOf(Table table) {
        final Schema schema = table.getParentOrThrow();
        return fullNameOf(schema.getName(), table.getName());
    }

    /**
     * Quotes the specified value field in accordance to this database naming
     * convention. This is typically used around values.
     *
     * @param field the content to quote
     * @return the quoted content
     */
    String quoteField(String field);

    /**
     * Encloses the specified database name field in accordance to this database
     * naming convention. This is typically used around column or table names.
     *
     * @param field the content to quote
     * @return the quoted content
     */
    String encloseField(String field);

    /**
     * Returns a non-null Set of Strings that represents schema names that are
     * to be excluded when examining a Dbms for schemas. The set typically
     * contains names for system tables and similar things. For example for
     * MySQL, the schemas "MySQL" and "information_schema" are typically
     * excluded.
     *
     * @return a non-null Set of Strings that represents schema names that are
     * to be excluded when examining a Dbms for schemas
     */
    Set<String> getSchemaExcludeSet();
}
