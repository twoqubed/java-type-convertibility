package com.pholser.types;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.pholser.types.supply.ReferenceArrayTypeSupplier.ReferenceArrayType;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

@RunWith(Theories.class)
public class ReferenceArrayConvertibilityTest {
    @Theory
    public void versusObject(@ReferenceArrayType Type type) {
        assertTrue(Types.areConvertible(Object.class, type));
    }

    @Theory
    public void versusCloneable(@ReferenceArrayType Type type) {
        assertTrue(Types.areConvertible(Cloneable.class, type));
    }

    @Theory
    public void versusSerializable(@ReferenceArrayType Type type) {
        assertTrue(Types.areConvertible(Serializable.class, type));
    }

    @Theory
    public void componentTypesAssignable(@ReferenceArrayType Type to, @ReferenceArrayType Type from) {
        assumeTrue(((Class<?>) to).getComponentType().isAssignableFrom(((Class<?>) from).getComponentType()));
        assertTrue(Types.areConvertible(to, from));
    }

    @Theory
    public void componentTypesNotAssignable(@ReferenceArrayType Type to, @ReferenceArrayType Type from) {
        assumeTrue(!((Class<?>) to).getComponentType().isAssignableFrom(((Class<?>) from).getComponentType()));
        assertFalse(Types.areConvertible(to, from));
    }
}
