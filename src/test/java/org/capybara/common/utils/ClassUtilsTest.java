package org.capybara.common.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    void testScanPackageRecursive() throws Exception {
        List<String> classNames = scanPackages("org.capybara.common.utils.test");
        assertIterableEquals(classUtils.scanPackage("org.capybara.common.utils.test"), classNames);
    }

    private List<String> scanPackages(String packageName) throws Exception {
        List<String> classNames = new ArrayList<>();
        URL url = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
        URI uri = url.toURI();
        Path path = Paths.get(uri);
        List<String> collect = Files.walk(path).filter(Files::isRegularFile).map(e -> {
            Path relativized = path.relativize(e);
            return packageName + "." + relativized.toString().replace(".class", "").replace(File.separator, ".");
        }).collect(Collectors.toList());
        return collect;
    }

}