package com.pholser.types.supply;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.junit.experimental.theories.ParametersSuppliedBy;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

public class PrimitiveArrayTypeSupplier extends TypeSupplier {
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @ParametersSuppliedBy(PrimitiveArrayTypeSupplier.class)
    public @interface PrimitiveArrayType {
    }

    @Override
    protected Predicate<? super Type> predicate() {
        return new Predicate<Type>() {
            @Override
            public boolean apply(Type type) {
                if (!(type instanceof Class<?>))
                    return false;
                Class<?> asClass = (Class<?>) type;
                return asClass.isArray() && asClass.getComponentType().isPrimitive();
            }
        };
    }
}
