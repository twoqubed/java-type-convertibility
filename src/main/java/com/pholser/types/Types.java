package com.pholser.types;

import java.lang.reflect.Type;

import static com.pholser.types.Primitives.*;

public class Types {
    private Types() {
        throw new UnsupportedOperationException();
    }

    public static boolean areConvertible(Type to, Type from) {
        if (to.equals(from))
            return true;

        Type toEquivalent = Primitives.equivalentOf(to);
        Type fromEquivalent = Primitives.equivalentOf(from);
        if (isPrimitive(toEquivalent))
            return canBeWidened(toEquivalent, fromEquivalent);
        return false;
    }
}
