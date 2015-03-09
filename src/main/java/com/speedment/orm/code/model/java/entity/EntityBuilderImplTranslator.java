/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
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
package com.speedment.orm.code.model.java.entity;

import com.speedment.codegen.Formatting;
import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.lang.models.Method;
import com.speedment.codegen.lang.models.Type;
import com.speedment.codegen.lang.models.Class;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Import;
import static com.speedment.codegen.lang.models.constants.DefaultAnnotationUsage.OVERRIDE;
import com.speedment.orm.config.model.Table;

/**
 *
 * @author pemi
 */
public class EntityBuilderImplTranslator extends BaseEntityTranslator<Class> {

    public EntityBuilderImplTranslator(CodeGenerator cg, Table configEntity) {
        super(cg, configEntity);
    }

    @Override
    protected Class make(File file) {

        return new ClassBuilder(BUILDER.getImplType().getName())
            .addColumnConsumer((cl, c) -> {
                cl
                .add(fieldFor(c).private_())
                .add(Method.of(GETTER_METHOD_PREFIX + typeName(c), Type.of(c.getMapping()))
                    .public_()
                    .add(OVERRIDE)
                    .add("return " + variableName(c) + ";"))
                .add(Method.of(BUILDER_METHOD_PREFIX + typeName(c), BUILDER.getType())
                    .public_()
                    .add(OVERRIDE)
                    .add(fieldFor(c))
                    .add("this." + variableName(c) + " = " + variableName(c) + ";")
                    .add("return this;"));
            })
            .build()
            .public_()
            .add(BUILDER.getType())
            .add(emptyConstructor())
            .add(copyConstructor(INTERFACE.getType(), CopyConstructorMode.BUILDER))
            .add(Method.of("build", INTERFACE.getType())
                .add(OVERRIDE)
                .public_()
                .call(m -> file.add(Import.of(INTERFACE.getImplType())))
                .add("return new " + Formatting.shortName(INTERFACE.getImplType().getName()) + "(this);"));
    }

    @Override
    protected String getJavadocRepresentText() {
        return "A builder implementation ";
    }

    @Override
    protected String getFileName() {
        return Formatting.shortName(BUILDER.getImplType().getName());
    }

    @Override
    protected boolean isInImplPackage() {
        return true;
    }

}