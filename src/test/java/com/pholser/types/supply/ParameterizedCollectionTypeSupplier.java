package com.pholser.types.supply;

import com.google.common.base.Predicate;
import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

public class ParameterizedCollectionTypeSupplier extends TypeSupplier {
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @ParametersSuppliedBy(ParameterizedCollectionTypeSupplier.class)
    public @interface ParameterizedCollectionType {
    }

    @Override
    protected Predicate<? super Type> predicate() {
        return new Predicate<Type>() {
            @Override
            public boolean apply(Type type) {
                return type instanceof ParameterizedType;
            }
        };
    }
}
