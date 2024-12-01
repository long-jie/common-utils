package org.capybara.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IPUtilsTest {

    @Test
    void atoi() {
        int number = new IPUtils().atoi("192.168.1.1");
        assertEquals(-1062731519, number);
    }

    @Test
    void itoa() {
        String address = new IPUtils().itoa(-1062731519);
        assertEquals("192.168.1.1", address);
    }

}