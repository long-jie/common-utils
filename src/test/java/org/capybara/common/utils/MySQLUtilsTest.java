package org.capybara.common.utils;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySQLUtilsTest {

    @Test
    void split1() {
        InputStream is = MySQLUtilsTest.class.getResourceAsStream("/test.sql");
        MySQLUtils utils = new MySQLUtils();
        List<String> sqlStatements = utils.split(is, StandardCharsets.UTF_8);
        assertEquals(6, sqlStatements.size());
    }

    @Test
    void split2() {
        InputStream is = MySQLUtilsTest.class.getResourceAsStream("/test.sql");
        MySQLUtils utils = new MySQLUtils();
        List<String> sqlStatements = utils.split(is);
        assertEquals(6, sqlStatements.size());
    }

}