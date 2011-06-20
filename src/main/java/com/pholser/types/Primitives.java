package com.pholser.types;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Primitives {
    private static final Map<Type, Type> PRIMITIVE_EQUIVALENTS = new HashMap<Type, Type>(13);
    private static final Map<Type, Type> PRIMITIVE_WRAPPERS = new HashMap<Type, Type>(13);

    private static final Map<Type, Set<Type>> PRIMITIVE_WIDENINGS = new HashMap<Type, Set<Type>>(13) {
        private static final long serialVersionUID = 1L;
        {
            put(short.class, Collections.<Type> singleton(byte.class));
            put(int.class, new HashSet<Type>(Arrays.<Type> asList(byte.class, char.class, short.class)));
            put(long.class, new HashSet<Type>(
                Arrays.<Type> asList(byte.class, char.class, short.class, int.class)));
            put(float.class, new HashSet<Type>(
                Arrays.<Type> asList(byte.class, char.class, short.class, int.class, long.class)));
            put(double.class, new HashSet<Type>(
                Arrays.<Type> asList(
                    byte.class, char.class, short.class, int.class, long.class, float.class)));
        }
    };

    static {
        PRIMITIVE_EQUIVALENTS.put(Boolean.class, boolean.class);
        PRIMITIVE_EQUIVALENTS.put(Byte.class, byte.class);
        PRIMITIVE_EQUIVALENTS.put(Character.class, char.class);
        PRIMITIVE_EQUIVALENTS.put(Short.class, short.class);
        PRIMITIVE_EQUIVALENTS.put(Integer.class, int.class);
        PRIMITIVE_EQUIVALENTS.put(Long.class, long.class);
        PRIMITIVE_EQUIVALENTS.put(Float.class, float.class);
        PRIMITIVE_EQUIVALENTS.put(Double.class, double.class);

        PRIMITIVE_WRAPPERS.put(boolean.class, Boolean.class);
        PRIMITIVE_WRAPPERS.put(byte.class, Byte.class);
        PRIMITIVE_WRAPPERS.put(char.class, Character.class);
        PRIMITIVE_WRAPPERS.put(short.class, Short.class);
        PRIMITIVE_WRAPPERS.put(int.class, Integer.class);
        PRIMITIVE_WRAPPERS.put(long.class, Long.class);
        PRIMITIVE_WRAPPERS.put(float.class, Float.class);
        PRIMITIVE_WRAPPERS.put(double.class, Double.class);
    }

    private Primitives() {
        throw new UnsupportedOperationException();
    }

    static boolean canBeWidened(Type to, Type from) {
        return to.equals(from) ||
            (PRIMITIVE_WIDENINGS.containsKey(to) && PRIMITIVE_WIDENINGS.get(to).contains(from));
    }

    static Type equivalentOf(Type type) {
        return isPrimitive(type) ? type : PRIMITIVE_EQUIVALENTS.get(type);
    }

    static boolean isPrimitive(Type type) {
        return type instanceof Class<?> && ((Class<?>) type).isPrimitive();
    }
}
