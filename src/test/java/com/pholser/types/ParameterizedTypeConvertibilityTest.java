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
    @Theory
    public void rawTypeToWildcardType(@ParameterizedCollectionType Type to, @RawCollectionType Type from) {
        assumeTrue(isSingleWildcardWithNoUpperOrLowerBound(to, from));
        assumeTrue(wildcardTypeSameAsRawType(to, from));

        assertTrue(Types.areConvertible(to, from));
    }

    private boolean wildcardTypeSameAsRawType(Type to, Type from) {
        ParameterizedType parameterizedType = (ParameterizedType) to;
        Type rawType = parameterizedType.getRawType();
        return rawType.equals(from);
    }

    private boolean isSingleWildcardWithNoUpperOrLowerBound(Type to, Type from) {
        ParameterizedType parameterizedType = (ParameterizedType) to;
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
