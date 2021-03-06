package com.hainet.spring.test.sample.context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "test-property-source.properties")
//@TestPropertySource(properties = { "key1 = value1", "key2 = value2"})
public class TestPropertySourceTest {

    @Autowired
    private Environment environment;

    @Test
    public void testPropertySourceTest() {
        final String value = environment.getProperty("key");

        assertThat(value, is("value"));
    }
}
