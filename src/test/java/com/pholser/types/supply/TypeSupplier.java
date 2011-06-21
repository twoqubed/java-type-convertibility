package com.pholser.types.supply;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.reflect.Modifier.*;
import static java.util.Collections.*;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.experimental.theories.PotentialAssignment;

public class TypeSupplier extends ParameterSupplier {
    @Target(PARAMETER)
    @Retention(RUNTIME)
    @ParametersSuppliedBy(TypeSupplier.class)
    public @interface AnyType {
    }

    private final List<Type> allTypes = new ArrayList<Type>();

    public TypeSupplier() {
        Collections.addAll(allTypes, new Type[] {
            boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class,
            void.class, Boolean.class, Byte.class, Character.class, Double.class, Float.class, Integer.class,
            Long.class, Short.class, Void.class,

            boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class,
            long[].class, short[].class, Boolean[].class, Byte[].class, Character[].class, Double[].class,
            Float[].class, Integer[].class, Long[].class, Short[].class,

            Collection.class, List.class, AbstractList.class, ArrayList.class, LinkedList.class,
            Set.class, AbstractSet.class, HashSet.class, TreeSet.class,
            Map.class, AbstractMap.class, HashMap.class, TreeMap.class,
            Cloneable.class, Serializable.class,
        });

        for (Field each : TypeSupplier.class.getDeclaredFields()) {
            int modifiers = each.getModifiers();
            if (isPrivate(modifiers) && !isStatic(modifiers))
                allTypes.add(each.getGenericType());
        }
    }

    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature sig) {
        if (!Type.class.equals(sig.getType()))
            return emptyList();

        List<PotentialAssignment> potentials = new ArrayList<PotentialAssignment>();
        for (Type each : filteredTypes(allTypes))
            potentials.add(PotentialAssignment.forValue(each.toString(), each));
        return potentials;
    }

    private Iterable<Type> filteredTypes(Iterable<Type> unfiltered) {
        return Iterables.filter(unfiltered, predicate());
    }

    protected Predicate<? super Type> predicate() {
        return Predicates.alwaysTrue();
    }

    private Collection<?> collectionOfHuh;
    private Collection<Object> collectionOfObject;
    private Collection<? super Object> collectionOfAtMostObject;
    private Collection<StringBuilder> collectionOfStringBuilder;
    private Collection<String> collectionOfString;
    private Collection<? super Comparable<Object>> collectionOfAtMostComparableOfObject;
    private Collection<? extends Number> collectionOfAtLeastNumber;
    private Collection<? super Number> collectionOfAtMostNumber;
    private Collection<Number> collectionOfNumber;
    private Collection<Serializable> collectionOfSerializable;
    private Collection<? extends Serializable> collectionOfAtLeastSerializable;
    private Collection<? super Serializable> collectionOfAtMostSerializable;
    private Collection<Integer> collectionOfInteger;
    private Collection<? extends Long> collectionOfAtLeastLong;
    private Collection<? super Long> collectionOfAtMostLong;
    private Collection<Long> collectionOfLong;
    private Collection<? extends Short> collectionOfAtLeastShort;
    private Collection<Short> collectionOfShort;
    private Collection<? extends List<?>> collectionOfAtLeastListOfHuh;
    private Collection<? extends List<? extends Serializable>> collectionOfAtLeastListOfAtLeastSerializable;
    private Collection<? extends List<? extends Number>> collectionOfAtLeastListOfAtLeastNumber;
    private Collection<? extends List<Long>> collectionOfAtLeastListOfLong;
    private Collection<ArrayList<Long>> collectionOfArrayListOfLong;
    private Collection<? extends List<? super Long>> collectionOfAtLeastListOfAtMostLong;
    private Collection<? extends List<? super Number>> collectionOfAtLeastListOfAtMostNumber;
    private Collection<? extends List<Serializable>> collectionOfAtLeastListOfSerializable;
    private Collection<LinkedList<Serializable>> collectionOfLinkedListOfSerializable;
    private Collection<? super List<Long>> collectionOfAtMostListOfLong;
    private Collection<? super List<? extends Number>> collectionOfAtMostListOfAtLeastNumber;
    private Collection<? super List<? extends Serializable>> collectionOfAtMostListOfAtLeastSerializable;
    private Collection<? super List<Object>> collectionOfAtMostListOfObject;
    private Collection<? super List<? super Serializable>> collectionOfAtMostListOfAtMostSerializable;
    private Collection<? super List<? super Number>> collectionOfAtMostListOfAtMostNumber;
    private Collection<? super List<?>> collectionOfAtMostListOfHuh;
    private Collection<Collection<?>> collectionOfCollectionOfHuh;

    private List<?> listOfHuh;
    private List<? extends Number> listOfAtLeastNumber;
    private List<? super Number> listOfAtMostNumber;
    private List<Number> listOfNumber;
    private List<? super Long> listOfAtMostLong;
    private List<Long> listOfLong;
    private List<? extends Serializable> listOfAtLeastSerializable;

    private AbstractList<?> abstractListOfHuh;

    private ArrayList<?> arrayListOfHuh;
    private ArrayList<? extends Number> arrayListOfAtLeastNumber;
    private ArrayList<? super Number> arrayListOfAtMostNumber;
    private ArrayList<Number> arrayListOfNumber;
    private ArrayList<? super Long> arrayListOfAtMostLong;
    private ArrayList<Long> arrayListOfLong;
    private ArrayList<? extends Serializable> arrayListOfAtLeastSerializable;

    private LinkedList<?> linkedListOfHuh;
    private LinkedList<? extends Number> linkedListOfAtLeastNumber;
    private LinkedList<? super Number> linkedListOfAtMostNumber;
    private LinkedList<Number> linkedListOfNumber;

    private Set<?> setOfHuh;
    private Set<Object> setOfObject;
    private Set<Number> setOfNumber;
    private Set<? extends Number> setOfAtLeastNumber;
    private Set<? super Number> setOfAtMostNumber;
    private Set<? extends Long> setOfAtLeastLong;

    private AbstractSet<?> abstractSetOfHuh;

    private HashSet<?> hashSetOfHuh;
    private HashSet<Object> hashSetOfObject;
    private HashSet<? extends Number> hashSetOfAtLeastNumber;
    private HashSet<? super Number> hashSetOfAtMostNumber;
    private HashSet<Number> hashSetOfNumber;

    private TreeSet<?> treeSetOfHuh;
    private TreeSet<Object> treeSetOfObject;
    private TreeSet<? extends Number> treeSetOfAtLeastNumber;
    private TreeSet<? super Number> treeSetOfAtMostNumber;
    private TreeSet<Number> treeSetOfNumber;
    private TreeSet<Long> treeSetOfLong;

    private Map<?, ?> mapOfHuhToHuh;
    private Map<? extends Serializable, ?> mapOfAtLeastSerializableToHuh;
    private Map<? extends Number, ?> mapOfAtLeastNumberToHuh;
    private Map<Long, ?> mapOfLongToHuh;
    private Map<?, ? super Long> mapOfHuhToAtMostLong;
    private Map<? extends Serializable, ? super Long> mapOfAtLeastSerializableToAtMostLong;
    private Map<? extends Number, ? super Long> mapOfAtLeastNumberToAtMostLong;
    private Map<Long, ? super Long> mapOfLongToAtMostLong;
    private Map<?, ? super Number> mapOfHuhToAtMostNumber;
    private Map<? extends Serializable, ? super Number> mapOfAtLeastSerializableToAtMostNumber;
    private Map<? extends Number, ? super Number> mapOfAtLeastNumberToAtMostNumber;
    private Map<Long, ? super Number> mapOfLongToAtMostNumber;
    private Map<?, Object> mapOfHuhToObject;
    private Map<? extends Serializable, Object> mapOfAtLeastSerializableToObject;
    private Map<? extends Number, Object> mapOfAtLeastNumberToObject;
    private Map<Long, Object> mapOfLongToObject;

    private AbstractMap<?, ?> abstractMapOfHuhToHuh;
    private HashMap<?, ?> hashMapOfHuhToHuh;
    private TreeMap<?, ?> treeMapOfHuhToHuh;
}
