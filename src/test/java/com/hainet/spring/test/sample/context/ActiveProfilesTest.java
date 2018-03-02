package com.hainet.spring.test.sample.context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class ActiveProfilesTest {

    @Value("${env}")
    private String env;

    @Test
    public void activeProfilesTest() {
        assertThat(env, is("prod"));
    }
}
