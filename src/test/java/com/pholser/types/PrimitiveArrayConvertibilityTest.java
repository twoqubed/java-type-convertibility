package com.pholser.types;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.pholser.types.supply.PrimitiveArrayTypeSupplier.PrimitiveArrayType;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

@RunWith(Theories.class)
public class PrimitiveArrayConvertibilityTest {
    private static Map<Type, Type> PRIMITIVE_WRAPPER_ARRAY_TYPES = new HashMap<Type, Type>(13) {
        private static final long serialVersionUID = 1L;
        {
            put(boolean[].class, Boolean[].class);
            put(byte[].class, Byte[].class);
            put(char[].class, Character[].class);
            put(double[].class, Double[].class);
            put(float[].class, Float[].class);
            put(int[].class, Integer[].class);
            put(long[].class, Long[].class);
            put(short[].class, Short[].class);
        }
    };

    private static Type correspondingPrimitiveWrapperArrayType(Type primitiveArrayType) {
        return PRIMITIVE_WRAPPER_ARRAY_TYPES.get(primitiveArrayType);
    }

    @Theory
    public void versusObject(@PrimitiveArrayType Type type) {
        assertTrue(Types.areConvertible(Object.class, type));
    }

    @Theory
    public void versusCloneable(@PrimitiveArrayType Type type) {
        assertTrue(Types.areConvertible(Cloneable.class, type));
    }

    @Theory
    public void versusSerializable(@PrimitiveArrayType Type type) {
        assertTrue(Types.areConvertible(Serializable.class, type));
    }

    @Theory
    public void versusOtherPrimitiveArrayTypes(@PrimitiveArrayType Type first, @PrimitiveArrayType Type second) {
        assumeThat(first, not(is(second)));

        assertFalse(Types.areConvertible(first, second));
    }

    @Theory
    public void versusCorrespondingPrimitiveWrapperArrayType(@PrimitiveArrayType Type type) {
        Type primitiveWrapperArrayType = correspondingPrimitiveWrapperArrayType(type);

        assertFalse(Types.areConvertible(type, primitiveWrapperArrayType));
        assertFalse(Types.areConvertible(primitiveWrapperArrayType, type));
    }
}
