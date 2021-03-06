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
package com.speedment.db.trait;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;
import java.sql.Struct;

/**
 *
 * @author Per Minborg
 */
public interface HasCreateFromConnectionMethods {

    /**
     * Constructs an object that implements the <code>Clob</code> interface. The
     * object returned initially contains no data. The
     * <code>setAsciiStream</code>, <code>setCharacterStream</code> and
     * <code>setString</code> methods of the <code>Clob</code> interface may be
     * used to add data to the <code>Clob</code>.
     *
     * @return An object that implements the <code>Clob</code> interface
     * @throws SQLException if an object that implements the <code>Clob</code>
     * interface can not be constructed, this method is called on a closed
     * connection or a database access error occurs.
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not
     * support this data type
     *
     * @since 2.3.2
     */
    public Clob createClob() throws SQLException;

    /**
     * Constructs an object that implements the <code>Blob</code> interface. The
     * object returned initially contains no data. The
     * <code>setBinaryStream</code> and <code>setBytes</code> methods of the
     * <code>Blob</code> interface may be used to add data to the
     * <code>Blob</code>.
     *
     * @return An object that implements the <code>Blob</code> interface
     * @throws SQLException if an object that implements the <code>Blob</code>
     * interface can not be constructed, this method is called on a closed
     * connection or a database access error occurs.
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not
     * support this data type
     *
     * @since 2.3.2
     */
    public Blob createBlob() throws SQLException;

    /**
     * Constructs an object that implements the <code>NClob</code> interface.
     * The object returned initially contains no data. The
     * <code>setAsciiStream</code>, <code>setCharacterStream</code> and
     * <code>setString</code> methods of the <code>NClob</code> interface may be
     * used to add data to the <code>NClob</code>.
     *
     * @return An object that implements the <code>NClob</code> interface
     * @throws SQLException if an object that implements the <code>NClob</code>
     * interface can not be constructed, this method is called on a closed
     * connection or a database access error occurs.
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not
     * support this data type
     *
     * @since 2.3.2
     */
    public NClob createNClob() throws SQLException;

    /**
     * Constructs an object that implements the <code>SQLXML</code> interface.
     * The object returned initially contains no data. The
     * <code>createXmlStreamWriter</code> object and <code>setString</code>
     * method of the <code>SQLXML</code> interface may be used to add data to
     * the <code>SQLXML</code> object.
     *
     * @return An object that implements the <code>SQLXML</code> interface
     * @throws SQLException if an object that implements the <code>SQLXML</code>
     * interface can not be constructed, this method is called on a closed
     * connection or a database access error occurs.
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not
     * support this data type
     *
     * @since 2.3.2
     */
    public SQLXML createSQLXML() throws SQLException;

    /**
     * Factory method for creating Array objects.
     * <p>
     * <b>Note: </b>When <code>createArrayOf</code> is used to create an array
     * object that maps to a primitive data type, then it is
     * implementation-defined whether the <code>Array</code> object is an array
     * of that primitive data type or an array of <code>Object</code>.
     * <p>
     * <b>Note: </b>The JDBC driver is responsible for mapping the elements
     * <code>Object</code> array to the default JDBC SQL type defined in
     * java.sql.Types for the given class of <code>Object</code>. The default
     * mapping is specified in Appendix B of the JDBC specification. If the
     * resulting JDBC type is not the appropriate type for the given typeName
     * then it is implementation defined whether an <code>SQLException</code> is
     * thrown or the driver supports the resulting conversion.
     *
     * @param typeName the SQL name of the type the elements of the array map
     * to. The typeName is a database-specific name which may be the name of a
     * built-in type, a user-defined type or a standard SQL type supported by
     * this database. This is the value returned by
     * <code>Array.getBaseTypeName</code>
     * @param elements the elements that populate the returned object
     * @return an Array object whose elements map to the specified SQL type
     * @throws SQLException if a database error occurs, the JDBC type is not
     * appropriate for the typeName and the conversion is not supported, the
     * typeName is null or this method is called on a closed connection
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not
     * support this data type
     *
     * @since 2.3.2
     */
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException;

    /**
     * Factory method for creating Struct objects.
     *
     * @param typeName the SQL type name of the SQL structured type that this
     * <code>Struct</code> object maps to. The typeName is the name of a
     * user-defined type that has been defined for this database. It is the
     * value returned by <code>Struct.getSQLTypeName</code>.
     *
     * @param attributes the attributes that populate the returned object
     * @return a Struct object that maps to the given SQL type and is populated
     * with the given attributes
     * @throws SQLException if a database error occurs, the typeName is null or
     * this method is called on a closed connection
     * @throws SQLFeatureNotSupportedException if the JDBC driver does not
     * support this data type
     *
     * @since 2.3.2
     */
    Struct createStruct(String typeName, Object[] attributes) throws SQLException;

}
