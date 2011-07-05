package com.pholser.types.supply;

import com.google.common.base.Predicate;
import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.Collection;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

public class RawCollectionTypeSupplier extends TypeSupplier {
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @ParametersSuppliedBy(RawCollectionTypeSupplier.class)
    public @interface RawCollectionType {
    }

    @Override
    protected Predicate<? super Type> predicate() {
        return new Predicate<Type>() {
            @Override
            public boolean apply(Type type) {
                if (!(type instanceof Class<?>))
                    return false;
                Class<?> asClass = (Class<?>) type;
                return Collection.class.isAssignableFrom(asClass);
            }
        };
    }
}
