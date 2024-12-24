package org.capybara.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.util.Optional;

/**
 * @author Long Jie
 * @since 2024/12/24 12:13
 */
@Slf4j
public class SystemUtils {

    public static Optional<String> getProcessId() {
        String processId = null;
        if (isJava9OrHigher()) {
            try {
                processId = String.valueOf(ProcessHandle.current().pid()); // 使用 Java 9 中的 API
            } catch (Exception e) {
                // ignore
            }
        }
        // 对于 Java 8 及以下版本，使用 ManagementFactory
        if (processId == null) {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            processId = name.split("@")[0];
        }
        return Optional.ofNullable(processId);
    }

    private static boolean isJava9OrHigher() {
        String version = System.getProperty("java.version");
        return version.startsWith("9") || Integer.parseInt(version.split("\\.")[0]) > 8;
    }

}
