package com.pholser.types;

import java.lang.reflect.Type;

import com.pholser.types.supply.ReferenceTypeSupplier.ReferenceType;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static com.pholser.types.Primitives.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

@RunWith(Theories.class)
public class ClassConvertibilityTest {
    @Theory
    public void assignable(@ReferenceType Type to, @ReferenceType Type from) {
        assumeTrue(assignableClasses(to, from));

        assertTrue(Types.areConvertible(to, from));
    }

    @Theory
    public void notAssignable(@ReferenceType Type to, @ReferenceType Type from) {
        assumeTrue(!assignableClasses(to, from));
        assumeTrue(!isPrimitiveWrapper(to));
        assumeTrue(!isPrimitiveWrapper(from));

        assertFalse(Types.areConvertible(to, from));
    }

    private boolean assignableClasses(Type to, Type from) {
        return ((Class<?>) to).isAssignableFrom((Class<?>) from);
    }
}
