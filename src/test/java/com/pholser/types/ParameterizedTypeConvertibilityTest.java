package com.pholser.types;

import com.pholser.types.supply.ParameterizedCollectionTypeSupplier.ParameterizedCollectionType;
import com.pholser.types.supply.RawCollectionTypeSupplier.RawCollectionType;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

@RunWith(Theories.class)
public class ParameterizedTypeConvertibilityTest {
    
    /*
     * Collection -> Collection<?>
     * ArrayList -> Collection<?>
     */
    @Theory
    public void toWildcardTypeFromRawType(@ParameterizedCollectionType Type to, @RawCollectionType Type from) {
        assumeTrue(isSingleWildcardWithNoUpperOrLowerBound((ParameterizedType) to));
        assumeTrue(wildcardTypeAssignableFromRawType((ParameterizedType) to, (Class) from));

//        System.out.println(String.format("%s -> %s", from, to));
        assertTrue(Types.areConvertible(to, from));
    }

    /*
     * Collection<?> -> Collection
     * ArrayList<?> -> Collection
     */
    @Theory
    public void toRawTypeFromWildcardType(@RawCollectionType Type to, @ParameterizedCollectionType Type from) {
        assumeTrue(isSingleWildcardWithNoUpperOrLowerBound((ParameterizedType) from));
        assumeTrue(rawTypeAssignableFromWildcardType((Class) to, (ParameterizedType) from));

//        System.out.println(String.format("%s -> %s", from, to));
        assertTrue(Types.areConvertible(to, from));
    }

    private boolean wildcardTypeAssignableFromRawType(ParameterizedType parameterizedType, Class rawType) {
        return parameterizedType.getRawType() instanceof Class &&
                ((Class) parameterizedType.getRawType()).isAssignableFrom(rawType);
    }

    private boolean rawTypeAssignableFromWildcardType(Class rawType, ParameterizedType parameterizedType) {
        return parameterizedType.getRawType() instanceof Class &&
                rawType.isAssignableFrom(((Class) parameterizedType.getRawType()));
    }

    private boolean isSingleWildcardWithNoUpperOrLowerBound(ParameterizedType parameterizedType) {
        if (parameterizedType.getActualTypeArguments().length != 1) {
            return false;
        }
        if (!(parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcard = (WildcardType) parameterizedType.getActualTypeArguments()[0];
        return wildcard.getUpperBounds()[0].equals(Object.class)
                && wildcard.getLowerBounds().length == 0;
    }

}
