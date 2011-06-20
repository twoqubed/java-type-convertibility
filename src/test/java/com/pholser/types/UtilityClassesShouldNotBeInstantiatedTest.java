package com.pholser.types;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.rules.ExpectedException.*;

@RunWith(Theories.class)
public class UtilityClassesShouldNotBeInstantiatedTest {
    @Rule public final ExpectedException thrown = none();

    @DataPoints
    public static Class<?>[] utilityClasses() {
        return new Class<?>[] { Types.class, Primitives.class };
    }

    @Theory
    public void attemptInstantiation(Class<?> utility) throws Exception {
        thrown.expect(InvocationTargetException.class);
        thrown.expect(causeOfType(UnsupportedOperationException.class));

        Constructor<?> constructor = utility.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    private static Matcher<InvocationTargetException> causeOfType(final Class<? extends Throwable> type) {
        return new TypeSafeMatcher<InvocationTargetException>() {
            @Override
            public boolean matchesSafely(InvocationTargetException item) {
                return type.isInstance(item.getTargetException());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("an InvocationTargetException with target exception of ");
                description.appendValue(type);
            }
        };
    }
}
