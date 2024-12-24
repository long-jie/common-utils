package org.capybara.common.utils;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SystemUtilsTest {

    @Test
    void getProcessId() {
        Optional<String> processIdOptional = SystemUtils.getProcessId();
        assertTrue(processIdOptional.isPresent());
    }

}