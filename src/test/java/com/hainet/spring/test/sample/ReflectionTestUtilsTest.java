package com.hainet.spring.test.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReflectionTestUtilsTest {

    @Test
    public void setFieldTest() {
        // Setup
        final ImmutableObject object = new ImmutableObject("Value");

        // Exercise
        ReflectionTestUtils.setField(object, "value", "Updated");

        // Verify
        assertThat(ReflectionTestUtils.getField(object, "value"), is("Updated"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFieldCaseSensitiveTest() {
        // Setup
        final ImmutableObject object = new ImmutableObject("hainet");

        // Exercise
        ReflectionTestUtils.getField(object, "Value");
    }

    @Test
    public void setStaticFieldTest() {
        // Exercise
        ReflectionTestUtils.setField(ImmutableObject.class, "STATIC_VALUE", "Updated");

        // Verify
        assertThat(ReflectionTestUtils.getField(ImmutableObject.class, "STATIC_VALUE"), is("Updated"));
    }

    @Test
    public void invokeMethodTest() {
        // Setup
        final ImmutableObject object = new ImmutableObject("hainet");

        // Exercise
        final int result = ReflectionTestUtils.invokeMethod(object, "power", 3);

        // Verify
        assertThat(result, is(9));
    }

    public static class ImmutableObject {

        public ImmutableObject(final String value) {
            this.value = value;
        }

        private String value;

        private static String STATIC_VALUE = "Static value";

        private int power(final int x) {
            return x * x;
        }
    }
}
