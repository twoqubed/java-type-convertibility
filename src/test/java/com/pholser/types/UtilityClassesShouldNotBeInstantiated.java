package com.pholser.types;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.rules.ExpectedException.*;

public abstract class UtilityClassesShouldNotBeInstantiated {
    @Rule public final ExpectedException thrown = none();

    private final Class<?> utility;

    protected UtilityClassesShouldNotBeInstantiated(Class<?> utility) {
        this.utility = utility;
    }

    @Test
    public void attemptInstantiation() throws Exception {
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
