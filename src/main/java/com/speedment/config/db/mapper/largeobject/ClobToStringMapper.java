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
package com.speedment.config.db.mapper.largeobject;

import com.speedment.config.db.mapper.TypeMapper;
import com.speedment.exception.SpeedmentException;
import java.sql.Clob;
import java.sql.SQLException;

/**
 *
 * @author pemi
 */
public class ClobToStringMapper implements TypeMapper<Clob, String> {

    @Override
    public Class<String> getJavaType() {
        return String.class;
    }

    @Override
    public Class<Clob> getDatabaseType() {
        return Clob.class;
    }

    @Override
    public String toJavaType(Clob value) {
        if (value == null) {
            return null;
        }
        try {
            if (value.length() < Integer.MAX_VALUE) {
                return value.getSubString(1, (int) value.length());
            }
            throw new SpeedmentException("The provided Clob contains too many characters >" + Integer.MAX_VALUE);
//            final StringBuilder sb = new StringBuilder();
//            try (BufferedReader br = new BufferedReader(value.getCharacterStream())) {
//            
//            }
//            return sb.toString();
        } catch (SQLException sqle) {
            throw new SpeedmentException("Unable to convert Clob to String.", sqle);
        }
    }

    @Override
    public Clob toDatabaseType(String value) {
        if (value == null) {
            return null;
        }
        return new StringClob(value);
    }

    @Override
    public boolean isIdentityMapper() {
        return false;
    }
}
