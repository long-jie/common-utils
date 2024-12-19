package org.capybara.common.utils;

import org.capybara.common.utils.exception.ExceptionUtils;
import org.capybara.common.utils.exception.UtilsException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Class工具类
 *
 * @author Long Jie
 * @since 2024/12/6 12:20
 */
public class ClassUtils {

    public List<String> scanPackage(String packageName) {
        return scanPackage(packageName, true);
    }

    /**
     * 扫描指定包下所有的类，并返回类的全限定名
     *
     * @param packageName 指定包
     * @param recursive 是否递归子包
     * @return 包下所有类的全限定名
     */
    public List<String> scanPackage(String packageName, boolean recursive) {
        List<String> classNames = new ArrayList<>();
        String packageDir = packageName.replace(CommonConstants.DOT, CommonConstants.SLASH);
        try {
            URL packageUrl = this.getClass().getClassLoader().getResource(packageDir);
            if (packageUrl == null) {
                throw new UtilsException(String.format("package: [%s] does not exist", packageName));
            }
            Path parentPath = Paths.get(packageUrl.toURI());
            Stream<Path> pathStream = getSubPathStream(parentPath, recursive);
            pathStream.map(convertToClassName(parentPath, packageName)).forEach(classNames::add);
        } catch (Exception e) {
            ExceptionUtils.throwRuntimeException(String.format("scan package error: %s", e.getMessage()), e);
        }
        return classNames;
    }

    private static Function<Path, String> convertToClassName(Path parentPath, String packageName) {
        return filePath -> {
            Path relativized = parentPath.relativize(filePath);
            String fieldName = relativized.toString().replace(CommonConstants.CLASS_FILE_EXTENSION, CommonConstants.EMPTY_STRING)
                    .replace(File.separator, CommonConstants.DOT);
            return packageName + CommonConstants.DOT + fieldName;
        };
    }

    private static Stream<Path> getSubPathStream(Path path, boolean recursive) throws IOException {
        Stream<Path> pathStream;
        if (recursive) {
            pathStream = Files.walk(path).filter(Files::isRegularFile);
        } else {
            pathStream = Files.list(path).filter(Files::isRegularFile);
        }
        return pathStream;
    }

}
