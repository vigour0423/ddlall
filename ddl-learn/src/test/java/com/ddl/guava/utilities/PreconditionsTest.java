package com.ddl.guava.utilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Predicates.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;


public class PreconditionsTest {


    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        try {
            checkNotNullWithMessage(null);
        } catch (Exception e) {
            Assert.assertThat(instanceOf(e.getClass()), is(instanceOf(NullPointerException.class)));
            Assert.assertThat(e.getMessage(), equalTo("The list should not be null"));
        }
    }

    @Test
    public void testCheckNotNullWithFormatMessage() {
        try {
            checkNotNullWithFormatMessage(null);
        } catch (Exception e) {
            //Assert.assertThat(e, is(NullPointerException.class));
            Assert.assertThat(e.getMessage(), equalTo("The list should not be null and the size must be 2"));
        }
    }

    @Test
    public void testCheckArguments() {
        final String type = "A";
        try {
            Preconditions.checkArgument(type.equals("B"));
        } catch (Exception e) {
            Assert.assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    @Test
    public void testCheckState() {
        try {
            final String state = "A";
            Preconditions.checkState(state.equals("B"), "The state is illegal.");
            fail("should not process to here.");
        } catch (Exception e) {
            Assert.assertThat(instanceOf(e.getClass()), is(instanceOf(IllegalStateException.class)));
        }
    }

    @Test
    public void testCheckIndex() {
        try {
            List<String> list = ImmutableList.of();
            Preconditions.checkElementIndex(10, list.size());
        } catch (Exception e) {
            Assert.assertThat(instanceOf(e.getClass()), is(instanceOf(IndexOutOfBoundsException.class)));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testByObjects() {
        Objects.requireNonNull(null);
    }

    @Test(expected = AssertionError.class)
    public void testAssert() {
        List<String> list = null;
        assert list != null;
    }

    @Test
    public void testAssertWithMessage() {
        try {
            List<String> list = null;
            assert list != null : "The list should not be null.";
        } catch (Error e) {
            Assert.assertThat(e, is(instanceOf(AssertionError.class)));
            Assert.assertThat(e.getMessage(), equalTo("The list should not be null."));
        }
    }

    private void checkNotNull(final List<String> list) {
        Preconditions.checkNotNull(list);
    }

    private void checkNotNullWithMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null");
    }

    private void checkNotNullWithFormatMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null and the size must be %s", 2);
    }
}
