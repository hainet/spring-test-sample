package com.hainet.spring.test.sample.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeforeAfterTransactionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeTransaction
    public void beforeTransaction() {
        System.out.println("This is before transaction.");
    }

    @AfterTransaction
    public void afterTransaction() {
        System.out.println("This is after transaction.");
    }

    @Test
    @Transactional
    public void beforeTransactionTest() {
        jdbcTemplate.queryForList("SELECT id, name FROM person");
    }
}
