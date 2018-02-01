package com.hainet.spring.test.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JdbcTestUtilsTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void countRowsInTableTest() {
        final int countRowsInTable = JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");

        assertThat(countRowsInTable, is(1));
    }

    @Test
    public void countRowsInTableWhereTest() {
        final int nobody = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "person", "name = 'nobody'");
        final int hainet = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "person", "name = 'hainet'");

        assertThat(nobody, is(0));
        assertThat(hainet, is(1));
    }

    @Test
    public void deleteFromTablesTest() {
        final int deleteFromTables = JdbcTestUtils.deleteFromTables(jdbcTemplate, "person");

        assertThat(deleteFromTables, is(1));
    }

    @Test
    public void deleteFromTablesWhereTest() {
        final int nobody = JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "person", "name = 'nobody'");
        final int hainet = JdbcTestUtils.deleteFromTableWhere(jdbcTemplate, "person", "name = 'hainet'");

        assertThat(nobody, is(0));
        assertThat(hainet, is(1));
    }

    @Test(expected = BadSqlGrammarException.class)
    public void dropTablesTest() {
        JdbcTestUtils.dropTables(jdbcTemplate, "person");

        JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");
    }
}
