package com.pholser.types;

import java.lang.reflect.Type;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PrimitiveConvertibilityTest {
    private final Type to;
    private final Type from;
    private final boolean expectedOutcome;

    public PrimitiveConvertibilityTest(Type to, Type from, boolean expectedOutcome) {
        this.to = to;
        this.from = from;
        this.expectedOutcome = expectedOutcome;
    }

    @Test
    public void assignability() {
        assertEquals(to + " vs. " + from, expectedOutcome, Types.areConvertible(to, from));
    }

    @Parameters
    public static Collection<?> data() {
        return asList(new Object[][] {
            { byte.class, byte.class, true },
            { byte.class, short.class, false },
            { byte.class, char.class, false },
            { byte.class, int.class, false },
            { byte.class, float.class, false },
            { byte.class, long.class, false },
            { byte.class, double.class, false },
            { byte.class, Byte.class, true },
            { byte.class, Short.class, false },
            { byte.class, Character.class, false },
            { byte.class, Integer.class, false },
            { byte.class, Float.class, false },
            { byte.class, Long.class, false },
            { byte.class, Double.class, false },

            { char.class, byte.class, false },
            { char.class, short.class, false },
            { char.class, char.class, true },
            { char.class, int.class, false },
            { char.class, float.class, false },
            { char.class, long.class, false },
            { char.class, double.class, false },
            { char.class, Byte.class, false },
            { char.class, Short.class, false },
            { char.class, Character.class, true },
            { char.class, Integer.class, false },
            { char.class, Float.class, false },
            { char.class, Long.class, false },
            { char.class, Double.class, false },

            { short.class, byte.class, true },
            { short.class, short.class, true },
            { short.class, char.class, false },
            { short.class, int.class, false },
            { short.class, float.class, false },
            { short.class, long.class, false },
            { short.class, double.class, false },
            { short.class, Byte.class, true },
            { short.class, Short.class, true },
            { short.class, Character.class, false },
            { short.class, Integer.class, false },
            { short.class, Float.class, false },
            { short.class, Long.class, false },
            { short.class, Double.class, false },

            { int.class, byte.class, true },
            { int.class, short.class, true },
            { int.class, char.class, true },
            { int.class, int.class, true },
            { int.class, float.class, false },
            { int.class, long.class, false },
            { int.class, double.class, false },
            { int.class, Byte.class, true },
            { int.class, Short.class, true },
            { int.class, Character.class, true },
            { int.class, Integer.class, true },
            { int.class, Float.class, false },
            { int.class, Long.class, false },
            { int.class, Double.class, false },

            { long.class, byte.class, true },
            { long.class, short.class, true },
            { long.class, char.class, true },
            { long.class, int.class, true },
            { long.class, float.class, false },
            { long.class, long.class, true },
            { long.class, double.class, false },
            { long.class, Byte.class, true },
            { long.class, Short.class, true },
            { long.class, Character.class, true },
            { long.class, Integer.class, true },
            { long.class, Float.class, false },
            { long.class, Long.class, true },
            { long.class, Double.class, false },

            { float.class, byte.class, true },
            { float.class, short.class, true },
            { float.class, char.class, true },
            { float.class, int.class, true },
            { float.class, float.class, true },
            { float.class, long.class, true },
            { float.class, double.class, false },
            { float.class, Byte.class, true },
            { float.class, Short.class, true },
            { float.class, Character.class, true },
            { float.class, Integer.class, true },
            { float.class, Float.class, true },
            { float.class, Long.class, true },
            { float.class, Double.class, false },

            { double.class, byte.class, true },
            { double.class, short.class, true },
            { double.class, char.class, true },
            { double.class, int.class, true },
            { double.class, float.class, true },
            { double.class, long.class, true },
            { double.class, double.class, true },
            { double.class, Byte.class, true },
            { double.class, Short.class, true },
            { double.class, Character.class, true },
            { double.class, Integer.class, true },
            { double.class, Float.class, true },
            { double.class, Long.class, true },
            { double.class, Double.class, true },

            { boolean.class, boolean.class, true },
            { boolean.class, byte.class, false },
            { boolean.class, short.class, false },
            { boolean.class, char.class, false },
            { boolean.class, int.class, false },
            { boolean.class, float.class, false },
            { boolean.class, long.class, false },
            { boolean.class, double.class, false },
            { boolean.class, Boolean.class, true },
            { boolean.class, Byte.class, false },
            { boolean.class, Short.class, false },
            { boolean.class, Character.class, false },
            { boolean.class, Integer.class, false },
            { boolean.class, Float.class, false },
            { boolean.class, Long.class, false },
            { boolean.class, Double.class, false },
            { Boolean.class, boolean.class, true },
            { Boolean.class, byte.class, false },
            { Boolean.class, short.class, false },
            { Boolean.class, char.class, false },
            { Boolean.class, int.class, false },
            { Boolean.class, float.class, false },
            { Boolean.class, long.class, false },
            { Boolean.class, double.class, false },
            { Boolean.class, Boolean.class, true },
            { Boolean.class, Byte.class, false },
            { Boolean.class, Short.class, false },
            { Boolean.class, Character.class, false },
            { Boolean.class, Integer.class, false },
            { Boolean.class, Float.class, false },
            { Boolean.class, Long.class, false },
            { Boolean.class, Double.class, false },

            { Byte.class, byte.class, true },
            { Byte.class, short.class, false },
            { Byte.class, char.class, false },
            { Byte.class, int.class, false },
            { Byte.class, float.class, false },
            { Byte.class, long.class, false },
            { Byte.class, double.class, false },
            { Byte.class, Byte.class, true },
            { Byte.class, Short.class, false },
            { Byte.class, Character.class, false },
            { Byte.class, Integer.class, false },
            { Byte.class, Float.class, false },
            { Byte.class, Long.class, false },
            { Byte.class, Double.class, false },

            { Character.class, byte.class, false },
            { Character.class, short.class, false },
            { Character.class, char.class, true },
            { Character.class, int.class, false },
            { Character.class, float.class, false },
            { Character.class, long.class, false },
            { Character.class, double.class, false },
            { Character.class, Byte.class, false },
            { Character.class, Short.class, false },
            { Character.class, Character.class, true },
            { Character.class, Integer.class, false },
            { Character.class, Float.class, false },
            { Character.class, Long.class, false },
            { Character.class, Double.class, false },

            { Short.class, byte.class, true },
            { Short.class, short.class, true },
            { Short.class, char.class, false },
            { Short.class, int.class, false },
            { Short.class, float.class, false },
            { Short.class, long.class, false },
            { Short.class, double.class, false },
            { Short.class, Byte.class, true },
            { Short.class, Short.class, true },
            { Short.class, Character.class, false },
            { Short.class, Integer.class, false },
            { Short.class, Float.class, false },
            { Short.class, Long.class, false },
            { Short.class, Double.class, false },

            { Integer.class, byte.class, true },
            { Integer.class, short.class, true },
            { Integer.class, char.class, true },
            { Integer.class, int.class, true },
            { Integer.class, float.class, false },
            { Integer.class, long.class, false },
            { Integer.class, double.class, false },
            { Integer.class, Byte.class, true },
            { Integer.class, Short.class, true },
            { Integer.class, Character.class, true },
            { Integer.class, Integer.class, true },
            { Integer.class, Float.class, false },
            { Integer.class, Long.class, false },
            { Integer.class, Double.class, false },

            { Long.class, byte.class, true },
            { Long.class, short.class, true },
            { Long.class, char.class, true },
            { Long.class, int.class, true },
            { Long.class, float.class, false },
            { Long.class, long.class, true },
            { Long.class, double.class, false },
            { Long.class, Byte.class, true },
            { Long.class, Short.class, true },
            { Long.class, Character.class, true },
            { Long.class, Integer.class, true },
            { Long.class, Float.class, false },
            { Long.class, Long.class, true },
            { Long.class, Double.class, false },

            { Float.class, byte.class, true },
            { Float.class, short.class, true },
            { Float.class, char.class, true },
            { Float.class, int.class, true },
            { Float.class, float.class, true },
            { Float.class, long.class, true },
            { Float.class, double.class, false },
            { Float.class, Byte.class, true },
            { Float.class, Short.class, true },
            { Float.class, Character.class, true },
            { Float.class, Integer.class, true },
            { Float.class, Float.class, true },
            { Float.class, Long.class, true },
            { Float.class, Double.class, false },

            { Double.class, byte.class, true },
            { Double.class, short.class, true },
            { Double.class, char.class, true },
            { Double.class, int.class, true },
            { Double.class, float.class, true },
            { Double.class, long.class, true },
            { Double.class, double.class, true },
            { Double.class, Byte.class, true },
            { Double.class, Short.class, true },
            { Double.class, Character.class, true },
            { Double.class, Integer.class, true },
            { Double.class, Float.class, true },
            { Double.class, Long.class, true },
            { Double.class, Double.class, true },

            { void.class, void.class, true },
            { void.class, Void.class, false },
            { Void.class, void.class, false },
            { Void.class, Void.class, true },
        });
    }
}
