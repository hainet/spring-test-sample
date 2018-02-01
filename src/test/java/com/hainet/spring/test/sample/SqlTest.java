package com.hainet.spring.test.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SqlTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql("/sql.sql")
    public void sqlTest() {
        final int rows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");

        assertThat(rows, is(2));
    }

    /**
     * #で始まる行は実行されず、>>>で区切られたSQLは正常に実行される。
     */
    @Test
    @Sql(scripts = "/sql-config.sql", config = @SqlConfig(commentPrefix = "#", separator = ">>>"))
    public void sqlConfigTest() {
        final int rows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");

        assertThat(rows, is(3));
    }

    /**
     * IntelliJ IDEA上のJavadocビューアからは@Sqlアノテーションが一つだけ付与されているように見える。
     */
    @Test
    @Sql("/data.sql")
    @Sql("/sql.sql")
    public void sqlRepeatTest() {
        final int rows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");

        assertThat(rows, is(3));
    }

    @Test
    @SqlGroup({
            @Sql("/data.sql"),
            @Sql("/sql.sql")
    })
    public void sqlGroupTest() {
        final int rows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");

        assertThat(rows, is(3));
    }

    /**
     * 同じSQLファイルを複数記述しても一度しか実行されない。
     */
    @Test
    @Sql("/sql.sql")
    @Sql("/sql.sql")
    @Sql("/sql.sql")
    public void sameSqlTest() {
        final int rows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");

        assertThat(rows, is(2));
    }
}
