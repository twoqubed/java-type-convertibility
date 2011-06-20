java-type-convertibility
========================

Setting out to answer the question: Given two instances of 
java.lang.reflect.Type, is one *convertible* to the other?  

...subject to these constraints:
--------------------------------

* Must respect boxing/unboxing: 

        Types.areConvertible(int.class, Integer.class)  // true
        Types.areConvertible(Integer.class, int.class)  // true

* Must respect primitive widening conversions:

        Types.areConvertible(int.class, short.class)  // true
        Types.areConvertible(int.class, Short.class)  // true

* No external dependencies

* Supports ParameterizedTypes, WildcardTypes, GenericArrayTypes, etc.

* No published API other than Types.areConvertible(to, from)

