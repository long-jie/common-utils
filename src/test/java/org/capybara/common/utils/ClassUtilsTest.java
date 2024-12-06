package org.capybara.common.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilsTest {

    private ClassUtils classUtils;

    @BeforeEach
    void setUp() {
        classUtils = new ClassUtils();
    }

    @Test
    void scanPackage() {
        List<String> classNames = classUtils.scanPackage("org.capybara.common.utils.test");
        assertNotNull(classNames);
        assertEquals(2, classNames.size());
    }

    @Test
    void testScanPackage() {
        List<String> classNames = classUtils.scanPackage("org.capybara.common.utils.test", false);
        assertEquals(1, classNames.size());
        assertNotNull(classNames);
    }

}