package org.capybara.common.utils;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MySQLUtilsTest {

    @Test
    void split() {
        InputStream is = MySQLUtilsTest.class.getResourceAsStream("/test.sql");
        MySQLUtils utils = new MySQLUtils();
        List<String> sqlStatements = utils.split(is, StandardCharsets.UTF_8);
        assertEquals(3, sqlStatements.size());
    }

}