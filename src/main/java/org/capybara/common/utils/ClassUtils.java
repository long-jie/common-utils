package org.capybara.common.utils;

import org.capybara.common.utils.exception.ExceptionUtils;
import org.capybara.common.utils.exception.UtilsException;

import java.io.File;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

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
            Path path = Paths.get(packageUrl.toURI());
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (recursive) {
                        return FileVisitResult.CONTINUE;
                    }
                    if (!dir.toString().equals(path.toString())) { // 不是当前目录直接跳过
                        return FileVisitResult.SKIP_SUBTREE;
                    } else {
                        return FileVisitResult.CONTINUE;
                    }
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    String relative = path.relativize(file).toString();
                    String fileName = relative.replace(CommonConstants.CLASS_FILE_EXTENSION, CommonConstants.EMPTY_STRING).replace(File.separator, CommonConstants.DOT);
                    classNames.add(packageName + CommonConstants.DOT + fileName);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (Exception e) {
            ExceptionUtils.throwRuntimeException(String.format("scan package error: %s", e.getMessage()), e);
        }
        return classNames;
    }

}
