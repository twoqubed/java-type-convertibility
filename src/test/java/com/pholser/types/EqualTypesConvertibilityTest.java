package com.pholser.types;

import java.lang.reflect.Type;

import com.pholser.types.supply.TypeSupplier.AnyType;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

@RunWith(Theories.class)
public class EqualTypesConvertibilityTest {
    @Theory
    public void areConvertible(@AnyType Type to, @AnyType Type from) throws Exception {
        assumeThat(to, equalTo(from));

        assertTrue(Types.areConvertible(to, from));
    }
}
